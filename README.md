# CS-3354-Group-9

## Project Name: Securecrypt

## Project for CS3354.005

### Created by Shaurya Dwivedi, Len Bucchino, Marvel Hernandez, Alan Arevalo, and Mason Garcia

#### This project uses an AES-GCM algorithm to encrypt/decrypt files selected by the user. The program is written in Java.

A compiled version of the program is available in the out folder (see AES-Image-Encryptr.jar)
If the program does not launch there is a problem with the MANIFEST.MF in the jar
to fix add "Main-Class: userInt.mainMenu.MainUI" in the META-INF/MANIFEST.MF
You can also import the program into a Java IDE (InteliJ is recommended). Select the desired build version;
main or testing and run.

#### Usage

Open the project using any IDE or code editor that can execute Java

Open MainUI.java

Run MainUI.java

Main is the regular implementation of the program. Enter credentials, login, create and select a key; then you are ready to encrypt / decrypt. The selected key can be changed in the manage key menu. 

#### Testing
Selecting testing will use the JUnit framework to monitor the behavior of the program.
The quality assurance engineer should follow the documentation for performing each test.
All tests should pass
