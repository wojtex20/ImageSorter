/**
 * 
 */
package pl.wit.projekt.imagesorter.ui;

import javax.swing.SwingUtilities;

/**
 * @author 
 *
 */
public class ImageSorterUI {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainWindow main = new MainWindow();
				main.show();
			}
		});
	}

}
