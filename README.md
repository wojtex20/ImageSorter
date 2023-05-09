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
|   |   |   |   |   |   |   |-- controller/
|   |   |   |   |   |   |   |   |-- ImageSorterController.java
|   |   |   |   |   |   |   |-- model/
|   |   |   |   |   |   |   |   |-- ImageFile.java
|   |   |   |   |   |   |   |-- thread/
|   |   |   |   |   |   |   |   |-- ImageCopyTask.java
|   |   |   |   |   |   |   |   |-- ThreadPoolExecutorFactory.java
|   |   |   |   |   |   |   |-- ui/
|   |   |   |   |   |   |   |   |-- ImageSorterUI.java
|   |   |   |   |   |   |   |   |-- MainWindow.java
|   |   |   |   |   |   |   |-- util/
|   |   |   |   |   |   |   |   |-- ImageSorterUtil.java
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
|   |   |   |   |   |   |   |   |-- ImageSorterUtilTest.java
|-- pom.xml
|-- README.md
```


* ***src/main/java/pl/wit/projekt/imagesorter/*** - folder zawierający pliki źródłowe projektu

* ***src/test/java/pl/wit/projekt/imagesorter/*** - folder zawierający pliki testowe projektu

    * ***controller/*** - pakiet zawierający kontroler

        * ***ImageSorterController.java*** - klasa kontrolera odpowiedzialna za zarządzanie aplikacją

    * ***model/*** - pakiet zawierający modele danych

        * ***ImageFile.java*** - klasa reprezentująca pojedynczy plik zdjęciowy

    * ***thread/*** - pakiet zawierający klasy związane z obsługą wątków

        * ***ImageCopyTask.java*** - klasa odpowiedzialna za kopiowanie plików w oddzielnych wątkach

        * ***ThreadPoolExecutorFactory.java*** - klasa odpowiedzialna za tworzenie puli wątków

    * ***ui/*** - pakiet zawierający klasę interfejsu użytkownika

        * ***ImageSorterUI.java*** - klasa interfejsu generująca interfejs okna 
        * ***MainWindow.java*** - klasa z definicją interfejsu okienkowego


    * ***util/*** - pakiet zawierający klasę funkcji pomocniczych

        * ***ImageSorterUtil.java*** - klasa z funkcjami pomocniczymi 