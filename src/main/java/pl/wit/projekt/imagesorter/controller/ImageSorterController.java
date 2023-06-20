package pl.wit.projekt.imagesorter.controller;

import pl.wit.projekt.imagesorter.model.ImageFile;
import pl.wit.projekt.imagesorter.thread.ImageCopyTask;
import pl.wit.projekt.imagesorter.util.FileUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

/**
 * Klasa kontrolera aplikacji, odpowiada za utworzenie obiektów typu ImageFile z folderu podanego przez użytkonika jako źródło
 * oraz skopiowanie do podfolderów utworzonych na podstawie daty utworzenia zdjęcia. 
 * @author Monika Wolska
 */
public class ImageSorterController {
    private ExecutorService executorService;

    /**
     * Konstruktor klasy ImageSorterController.
     *
     * @param threadPoolSize liczba wątków w puli
     */
    public ImageSorterController(int threadPoolSize) {
        executorService = Executors.newFixedThreadPool(threadPoolSize);
    }

    /**
     * Metoda kopiujca zdjęcia do folderu docelowego, odpowiada za utworzenie podfolderów, sortowanie plików i wywołąnie zadania kopiowania.
     *
     * @param sourceDirectory      katalog źródłowy
     * @param destinationDirectory katalog docelowy
     */
    public void startSorting(String sourceDirectory, String destinationDirectory) {
    	//Sprawdzamy czy użytkownik podał scieżkę do folderu
        File sourceDir = new File(sourceDirectory);
        if (!sourceDir.isDirectory()) {
            throw new IllegalArgumentException("Invalid source directory, not type of directory");
        }
        File destDir = new File(destinationDirectory);
        if (!destDir.isDirectory()) {
        	throw new IllegalArgumentException("Invalid destination directory, not type of directory");
        }
        //Tworzymy listę plików z wspieranym formatem
        List<File> files = FileUtils.findImageFiles(sourceDir);
        int totalFiles = files.size();
        if (totalFiles == 0) {
        	throw new IllegalArgumentException("No supported image files found in the source directory");
        }
        //Konwertujemy obiekty typu File na ImageFile
        List<ImageFile> imageFiles = FileUtils.convertToImageFiles(files);
        //Sortujemy obiekty typu ImageFile wg daty utworzenia
        List<ImageFile> sortedImageFiles = FileUtils.sortImageFiles(imageFiles);
        //Używamy słownika aby przechowywać nazwę podfolderu (data) i liczność
        HashMap<String,Integer> subdirectoryMap = new HashMap<String,Integer>();

        for (ImageFile current : sortedImageFiles) {
            //Sprawdzamy czy istnieje już w folderze docelowym folder z datą (YYYY-MM-DD)
            String subdirectoryName = ConvertDateTimeStringToDateString(current.getCreationDate());
            File subdirectory = new File(destDir, subdirectoryName);
            
            if (!subdirectory.exists()) {
                //Tworzymy folder i inkrementujemy słownik
            	subdirectory.mkdir();
                subdirectoryMap.put(subdirectoryName, 1);  // Dodaj nowy wpis do mapy
            } else {
            	//Uwzgledniamy, że folder o tej nazwie już może istnieć stąd metoda getOrDefault
                int count = subdirectoryMap.getOrDefault(subdirectoryName, 0);
                count++;
                subdirectoryMap.put(subdirectoryName, count);  // Inkrementuj wartość w mapie
            }
            //Tworzymy zadania kopiowania w oddzielnych wątkach
            String newFileName = subdirectoryMap.get(subdirectoryName) + current.getFileExtension();
            ImageCopyTask copyTask = new ImageCopyTask(current, subdirectory, newFileName);
            executorService.execute(copyTask);
        }

        //Wyswietl komunikat zakonczenia pracy
        String message = "Sorting completed. Total files copied: " + totalFiles;
        JOptionPane.showMessageDialog(null, message, "Sorting Completed", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /***
     * Klasa która odpowiada za zamknięcie egzekutora, przed zamknięciem aplikacji
     */
    public void stopExecutorService() {
    	//Zaniechaj przyjmowanie nowych zadań, ale zaczekaj na już działające
        executorService.shutdown();
        try {
        	//Wymuś zatrzymanie zadań po przekroczeniu limitu
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            } 
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
    
    /***
     * Metoda pomocnicza, pobiera string z datą i czasem i usuwa z niego czas. 
     * @param dateTimeString  (w formacie 2023-06-02T14:30:00)
     * @return string z samą datą (w formacie 2023-06-02)
     */
    private String ConvertDateTimeStringToDateString(String dateTimeString)
    {
         int lastTCharIndex = dateTimeString.lastIndexOf('T');
         if (lastTCharIndex != -1 && lastTCharIndex < dateTimeString.length() - 1) {
             return dateTimeString.substring(0,lastTCharIndex);
         }
         return null;
    }
}