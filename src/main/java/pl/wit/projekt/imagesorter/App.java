/**
 * Inicjalizuje kontroller z podaną przez użytkownika pulą wątków (plik app.config) oraz widok (okno aplikacji)
 * @author Wojciech Kwiatkowski
 */
package pl.wit.projekt.imagesorter;


import java.io.FileInputStream;
import java.util.Properties;

import pl.wit.projekt.imagesorter.controller.ImageSorterController;
import pl.wit.projekt.imagesorter.ui.MainWindow;

/**
 * @author Wojciech Kwiatkowski
 *
 */
public class App {

	  public static void main(String[] args) {
		  //Odczytaj pule watkow z pliku app.config
		  //Po napotkaniu bledu ustaw domyslne 4
		  Properties prop = new Properties();
		  String fileName = "./src/main/resources/app.config";
		  int poolsize;
		  try (FileInputStream fis = new FileInputStream(fileName)) {
		      prop.load(fis);
		      poolsize = Integer.parseInt(prop.getProperty("ThreadPool.size"));
		  } catch (Exception ex) {
			  poolsize = 4;
		  }
		//Zainicjalizuj kontroler
        ImageSorterController controller = new ImageSorterController(poolsize);
        //Zainicjalizuj UI
        MainWindow mainWindow = new MainWindow(controller);
        mainWindow.setVisible(true);
	    }

}
