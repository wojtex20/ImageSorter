package pl.wit.projekt.imagesorter.model;

import java.io.File;

import pl.wit.projekt.imagesorter.util.FileUtils;

/**
 * Klasa reprezentująca pojedyńczy plik obrazu
 */
public class ImageFile {
    private File file;
    private String creationDate;
    private String fileExtension;

    /**
     * Konstruktor klasy ImageFile.
     *
     * @param file         plik obrazu
     * @param creationDate data utworzenia pliku
     */
    public ImageFile(File file) {
        this.file = validateFile(file);
        this.creationDate = FileUtils.readCreationDate(file);
        this.fileExtension = FileUtils.getFileExtension(this.file);
    }

    /***
     * Waliduje, że dostarczony plik posiada opdowiednie rozszerzenie
     * @param file obiekt klasy File
     * @return File obiekt klasy File który napewno posiada dobre rozszerzenie
     * @throws IllegalArgumentException  obiekt klasy File posiada złe rozszerzenie
     */
    private File validateFile(File file) throws IllegalArgumentException{
    	if (FileUtils.isImageFileType(file))
    		return file;
    	else 
    		throw new IllegalArgumentException(file.getName()+" has different extension than supported: " + String.join(", ", FileUtils.getSupportedExtensions()));
	}
	  
	/**
     * Zwraca plik obrazu.
     *
     * @return plik obrazu
     */
    public File getFile() {
        return file;
    }

    /**
     * Ustawia plik obrazu.
     *
     * @param file plik obrazu
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Zwraca datę utworzenia pliku.
     *
     * @return data utworzenia pliku
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * Ustawia datę utworzenia pliku.
     *
     * @param creationDate data utworzenia pliku
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

	/**
	 * @return the fileExtension
	 */
	public String getFileExtension() {
		return fileExtension;
	}
}


