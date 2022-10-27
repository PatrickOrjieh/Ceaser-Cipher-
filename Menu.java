package Ply_Gursimar;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Menu {

    public void displayMenu() {

        System.out.println("");
        System.out.println("** Ceaser Cipher **");
        System.out.println("\n** 1. Decrypt the message with known key. **");
        System.out.println("** 2. Decrypt the message with using exhaustive key search method.**");
        System.out.println("** 3. Quit the program **");
    }

    public int getMenuInput() {

        

        try {
            int inputValue;
           

                Scanner keyboard = new Scanner(System.in);
                System.out.print("Input Value: ");
                inputValue = keyboard.nextInt();
               
                return inputValue;
            

        } catch (InputMismatchException e) {
            System.out.println("Pleaser enter a number..");
        }

        return 0;

    }

    public void mainMenu() {

        displayMenu();
        

       int  switcher = getMenuInput();

        switch (switcher) {
            case 1:

                String ecryptedMsg = readFile(1);

                Decyptor firstDecyptor = new Decyptor(ecryptedMsg, 3);

                String decryptedMsg = firstDecyptor.decryptWithKnowKey();

                System.out.println("Encrypted Message : " + ecryptedMsg);
                System.out.println("Decrypted Message : " + decryptedMsg);


                mainMenu();
                break;

            case 2:

                ecryptedMsg = readFile(2);

                Decyptor secoundDecyptor = new Decyptor(ecryptedMsg);

                decryptedMsg = secoundDecyptor.decryptionWithExhaustiveKey();

                System.out.println("Encrypted Message : " + ecryptedMsg);
                System.out.println("Decrypted Message : " + decryptedMsg);
                System.out.print("Found Key :"+ secoundDecyptor.getKey());
                mainMenu();

            case 3:

                System.exit(0);
                break;

            default:
                System.out.println("Wrong input");
                System.out.println();
                mainMenu();
                break;
        }

    }

    public String readFile(int switchLine) {

        try {

            File myObj = new File("classCAs/Ply_Gursimar/cipertext.txt");
            Scanner myReader = new Scanner(myObj);

            String lineOne = " ";
            String lineTwo = " ";
            lineOne = myReader.nextLine();
            lineTwo = myReader.nextLine();

            myReader.close();

            if (switchLine == 1) {
                return lineOne;
            } else {
                return lineTwo;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            mainMenu();
        }

        return null;

    }

    public String readFile(int switchLine,String filePath) {

        try {

            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);

            String lineOne = " ";
            String lineTwo = " ";
            lineOne = myReader.nextLine();
            lineTwo = myReader.nextLine();

            myReader.close();

            if (switchLine == 1) {
                return lineOne;
            } else {
                return lineTwo;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            mainMenu();
        }

        return null;

    }

}
