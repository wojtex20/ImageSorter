package pl.wit.projekt.imagesorter.ui;

import pl.wit.projekt.imagesorter.controller.ImageSorterController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

/**
 * Klasa reprezentująca główne okno aplikacji.
 */
public class MainWindow extends JFrame {
    private ImageSorterController controller;
    private JTextField sourceDirectoryField;
    private JTextField destinationDirectoryField;
    private JButton startButton;

    /**
     * Konstruktor klasy MainWindow.
     *
     * @param controller kontroler aplikacji
     */
    public MainWindow(ImageSorterController controller) {
        this.controller = controller;
        setTitle("Image Sorter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 400));

        // Listener do zdarzenia zamykania okna
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	controller.stopExecutorService();
            }
        });
        
        createComponents();

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Metoda tworząca komponenty interfejsu użytkownika.
     */
    private void createComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel sourceLabel = new JLabel("Source Directory:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        mainPanel.add(sourceLabel, constraints);

        sourceDirectoryField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        mainPanel.add(sourceDirectoryField, constraints);

        JButton sourceBrowseButton = new JButton("Browse");
        sourceBrowseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDirectoryChooser(sourceDirectoryField);
            }
        });
        constraints.gridx = 2;
        constraints.gridy = 0;
        mainPanel.add(sourceBrowseButton, constraints);

        JLabel destinationLabel = new JLabel("Destination Directory:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPanel.add(destinationLabel, constraints);

        destinationDirectoryField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        mainPanel.add(destinationDirectoryField, constraints);

        JButton destinationBrowseButton = new JButton("Browse");
        destinationBrowseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDirectoryChooser(destinationDirectoryField);
            }
        });
        constraints.gridx = 2;
        constraints.gridy = 1;
        mainPanel.add(destinationBrowseButton, constraints);

        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startSorting();
            }
        });
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        mainPanel.add(startButton, constraints);

        add(mainPanel);
    }

    /**
     * Metoda wyświetlająca okno wyboru katalogu.
     *
     * @param textField pole tekstowe, w które zostanie wpisana wybrana ścieżka
     */
    private void showDirectoryChooser(JTextField textField) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            textField.setText(selectedDirectory.getAbsolutePath());
        }
    }

    /**
     * Metoda rozpoczynająca sortowanie plików.
     */
    private void startSorting() {
        String sourceDirectory = sourceDirectoryField.getText();
        String destinationDirectory = destinationDirectoryField.getText();
        try {
            controller.startSorting(sourceDirectory, destinationDirectory);
        } catch (Exception e) {
            showErrorDialog("Error occurred: " + e.getMessage());
        }
    }
    
    /**
     * Metoda wyświetlająca okno błędu.
     *
     * @param errorMessage treść błędu
     */
    private void showErrorDialog(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
}

