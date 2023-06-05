package pl.wit.projekt.imagesorter.thread;

import pl.wit.projekt.imagesorter.model.ImageFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Klasa reprezentująca zadanie kopiowania pliku obrazu.
 */
public class ImageCopyTask implements Runnable {
    private ImageFile imageFile;
    private File destinationDirectory;
    private String destinationFileName;

    /**
     * Konstruktor klasy ImageCopyTask.
     *
     * @param imageFile            plik obrazu do skopiowania
     * @param destinationDirectory katalog docelowy
     * @param destinationFileName  nazwa pliku docelowego
     */
    public ImageCopyTask(ImageFile imageFile, File destinationDirectory, String destinationFileName) {
        this.imageFile = imageFile;
        this.destinationDirectory = destinationDirectory;
        this.destinationFileName = destinationFileName;
    }

    /**
     * Metoda wykonująca zadanie kopiowania pliku obrazu.
     */
    @Override
    public void run(){
        try {
            File sourceFile = imageFile.getFile();
            File destinationFile = new File(destinationDirectory, destinationFileName);
            Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred during file copy", e);
        }
    }
}
