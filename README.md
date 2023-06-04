# Opis struktury projektu
```
ImageSorter/
|-- src/
|   |-- main/
|   |   |-- java/
|   |   |   |-- pl/
|   |   |   |   |-- wit/
|   |   |   |   |   |-- projekt/
|   |   |   |   |   |   |-- imagesorter/
|   |   |   |   |   |   |   |-- App.java
|   |   |   |   |   |   |   |-- controller/
|   |   |   |   |   |   |   |   |-- ImageSorterController.java
|   |   |   |   |   |   |   |-- model/
|   |   |   |   |   |   |   |   |-- ImageFile.java
|   |   |   |   |   |   |   |-- thread/
|   |   |   |   |   |   |   |   |-- ImageCopyTask.java
|   |   |   |   |   |   |   |-- ui/
|   |   |   |   |   |   |   |   |-- MainWindow.java
|   |   |   |   |   |   |   |-- util/
|   |   |   |   |   |   |   |   |-- FileUtils.java
|   |-- test/
|   |   |-- java/
|   |   |   |-- pl/
|   |   |   |   |-- wit/
|   |   |   |   |   |-- projekt/
|   |   |   |   |   |   |-- imagesorter/
|   |   |   |   |   |   |   |-- controller/
|   |   |   |   |   |   |   |   |-- ImageSorterControllerTest.java
|   |   |   |   |   |   |   |-- model/
|   |   |   |   |   |   |   |   |-- ImageFileTest.java
|   |   |   |   |   |   |   |-- thread/
|   |   |   |   |   |   |   |   |-- ImageCopyTaskTest.java
|   |   |   |   |   |   |   |-- util/
|   |   |   |   |   |   |   |   |-- FileUtilsTest.java
|-- pom.xml
|-- README.md
```


* ***src/main/java/pl/wit/projekt/imagesorter/*** - folder zawierający pliki źródłowe projektu

* ***src/test/java/pl/wit/projekt/imagesorter/*** - folder zawierający pliki testowe projektu

    * ***App.java*** - klasa zawierająca funkcję main aplikacji 

    * ***controller/*** - pakiet zawierający kontroler

        * ***ImageSorterController.java*** - klasa kontrolera odpowiedzialna za zarządzanie aplikacją

    * ***model/*** - pakiet zawierający modele danych

        * ***ImageFile.java*** - klasa reprezentująca pojedynczy plik zdjęciowy

    * ***thread/*** - pakiet zawierający klasy związane z obsługą wątków

        * ***ImageCopyTask.java*** - klasa odpowiedzialna za kopiowanie plików w oddzielnych wątkach


    * ***ui/*** - pakiet zawierający klasę interfejsu użytkownika

        * ***MainWindow.java*** - klasa z definicją interfejsu okienkowego


    * ***util/*** - pakiet zawierający klasę funkcji pomocniczych

        * ***FileUtils.java*** - klasa z funkcjami pomocniczymi do klasy File 