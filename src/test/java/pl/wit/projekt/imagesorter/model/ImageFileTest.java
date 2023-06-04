/**
 * 
 */
package pl.wit.projekt.imagesorter.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.File;

public class ImageFileTest {

    private ImageFile imageFile;

    @Before
    public void setUp() {
        File file = new File("test.jpg");
        String creationDate = "2021-05-20";
        imageFile = new ImageFile(file);
    }

    @Test
    public void testGetFile() {
        File expectedFile = new File("test.jpg");
        File actualFile = imageFile.getFile();
        Assert.assertEquals(expectedFile, actualFile);
    }

    @Test
    public void testSetFile() {
        File newFile = new File("new_test.jpg");
        imageFile.setFile(newFile);
        Assert.assertEquals(newFile, imageFile.getFile());
    }

    @Test
    public void testGetCreationDate() {
        String expectedDate = "2021-05-20";
        String actualDate = imageFile.getCreationDate();
        Assert.assertEquals(expectedDate, actualDate);
    }

    @Test
    public void testSetCreationDate() {
        String newDate = "2022-01-01";
        imageFile.setCreationDate(newDate);
        Assert.assertEquals(newDate, imageFile.getCreationDate());
    }
}
