/**
 * Inicjalizuje kontroller z podaną przez użytkownika pulą wątków (plik app.config) oraz widok (okno aplikacji)
 */
package pl.wit.projekt.imagesorter;


import java.io.FileInputStream;
import java.util.Properties;

import pl.wit.projekt.imagesorter.controller.ImageSorterController;
import pl.wit.projekt.imagesorter.ui.MainWindow;

/**
 * @author 
 *
 */
public class App {

	  public static void main(String[] args) {
		  Properties prop = new Properties();
		  String fileName = "app.config";
		  int poolsize;
		  try (FileInputStream fis = new FileInputStream(fileName)) {
		      prop.load(fis);
		      poolsize = Integer.parseInt(prop.getProperty("ThreadPool.size"));
		  } catch (Exception ex) {
			  poolsize = 4;
		  }
        ImageSorterController controller = new ImageSorterController(poolsize);
        MainWindow mainWindow = new MainWindow(controller);
        mainWindow.setVisible(true);
	    }

}
