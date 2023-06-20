package pl.wit.projekt.imagesorter.util;

import pl.wit.projekt.imagesorter.model.ImageFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.text.SimpleDateFormat;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;

/**
 * Klasa narzędziowa zawierajaca metody pomocne w pracy z obiektami typu File i ImageFile.
 * @author Łukasz Terlikowski
 */
public class FileUtils {
    /**
     * Lista obsługiwanych rozszerzeń plików obrazów.
     */
    private static final List<String> SUPPORTED_EXTENSIONS = Arrays.asList(".jpg", ".jpeg");

    /**
	 * @return the supportedExtensions
	 */
	public static List<String> getSupportedExtensions() {
		return SUPPORTED_EXTENSIONS;
	}

	/**
     * Metoda znajdująca pliki obrazów w danym katalogu i podkatalogach (rekurencyjnie).
     *
     * @param directory katalog do przeszukania
     * @return lista plików obrazów
     */
    public static List<File> findImageFiles(File directory) {
        List<File> imageFiles = new ArrayList<>();
        findImageFilesRecursive(directory, imageFiles);
        return imageFiles;
    }

    /***
     * Metoda pomocnicza która przeszukuje katalogi w głąb, jeśli plik posiada odpowiednie rozszerzenie dodaje go do listy
     * @param directory katalog do przeszukania
     * @param imageFiles lista plików obrazów do której mają zostać dodane utworzone obiekty
     */
    private static void findImageFilesRecursive(File directory, List<File> imageFiles) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    findImageFilesRecursive(file, imageFiles);
                } else if (isImageFileType(file)) {
                    imageFiles.add(file);
                }
            }
        }
    }

    /***
     * Metoda pomocnicza sprawdzajaca czy plik posiada rozszerzenie dla pliku typu obraz
     * @param file plik do sprawdzenia
     * @return zwraca wartość true jeśli plik jest obrazem
     */
    public static boolean isImageFileType(File file) {
        String extension = getFileExtension(file);
        return extension != null && SUPPORTED_EXTENSIONS.contains(extension.toLowerCase());
    }

    /***
     * Metoda pomocnicza do pobierania rozszerzenia pliku
     * @param file plik do sprawdzenia
     * @return rozszerzenie w formacie ".typ"
     */
    public static String getFileExtension(File file) {
        String name = file.getName();
        int lastDotIndex = name.lastIndexOf('.');
        if (lastDotIndex != -1 && lastDotIndex < name.length() - 1) {
            return name.substring(lastDotIndex);
        }
        return null;
    }
    
    

    /**
     * Metoda konwertująca listę plików na listę obiektów ImageFile.
     *
     * @param files lista plików
     * @return lista obiektów ImageFile
     */
    public static List<ImageFile> convertToImageFiles(List<File> files) {
        List<ImageFile> imageFiles = new ArrayList<>();
        for (File file : files) {
            ImageFile imageFile = new ImageFile(file);
            if (imageFile != null) {
                imageFiles.add(imageFile);
            }
        }
        return imageFiles;
    }
    
    /***
     * Metoda odczytuje datę utworzenia z danych exif, jesli nie zawiera odczytuje date utworzenia pliku w systemie plików
     * @param obiekt typu file
     * @return obiekt typu ImageFile
     */
    public static String readCreationDate(File file) {
        try {
        	String creationDate;
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
            if (directory != null) {
            	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            	creationDate = formatter.format(directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL));
            }else {
	            Path filePath = file.toPath();
	            FileTime creationTime = (FileTime) Files.getAttribute(filePath, "creationTime");
	            creationDate = creationTime.toString();
            }
            return creationDate;
        } catch (IOException | ImageProcessingException e) {
            throw new RuntimeException("Error occurred trying to read creationTime of "+file.getName(), e);
        }
    }
    
    /**
     * Metoda sortująca listę obiektów ImageFile według daty utworzenia.
     *
     * @param imageFiles lista obiektów ImageFile do posortowania
     * @return posortowana lista obiektów ImageFile
     */
    public static List<ImageFile> sortImageFiles(List<ImageFile> imageFiles) {
        Collections.sort(imageFiles, new Comparator<ImageFile>() {
            @Override
            public int compare(ImageFile file1, ImageFile file2) {
                return file1.getCreationDate().compareTo(file2.getCreationDate());
            }
        });
        return imageFiles;
    }
}
