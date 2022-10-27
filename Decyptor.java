package Ply_Gursimar;

import java.util.ArrayList;

public class Decyptor {

    //Creating decyptor attributes
    private String encryptedMessage;
    private String decryptedMessage;
    private int key;

    //Creating constructor for unknow key
    public Decyptor(String encryptedMessage) {
        this.encryptedMessage = encryptedMessage;
        this.decryptedMessage = "";
        this.key = 0;
    }

    //Creating constructor for given key
    public Decyptor(String encryptedMessage, int key) {

        if (key != 0) {
            if (key < 0 || key > 25) {
                throw new IllegalArgumentException("Key must be between 0 and 25");
            } else {
                this.encryptedMessage = encryptedMessage;
                this.decryptedMessage = "";
                this.key = key;
            }
        } else {
            this.encryptedMessage = encryptedMessage;
            this.decryptedMessage = encryptedMessage;
        }
    }


    //getter for encrypted message
    public String getEncryptedMessage() {
        return encryptedMessage;
    }

    //setter for getting encrypted message
    public void setEncryptedMessage(String encryptedMessage) {
        this.encryptedMessage = encryptedMessage;
    }

    //getter for decrupted message
    public String getDecryptedMessage() {
        return decryptedMessage;
    }

    //getter for key
    public int getKey() {
        return key;
    }

    //Decryptor with a known key
    public String decryptWithKnowKey() {

        String decryptedMessage = "";

        encryptedMessage = encryptedMessage.toLowerCase(); //Converts the message to lowercase to avoid missmatch

        for (int i = 0; i < encryptedMessage.length(); i++) {   //for loop to loop throug the message

            if (encryptedMessage.charAt(i) != ' ') {  //condition to check if the charAt is a space as we need to skip it

                int asciiValue = encryptedMessage.charAt(i) - 3; //shifts characters by key i.e 3

                if (asciiValue < 'a') {      // if asciiValue is less than the value of 'a' we need to 

                    asciiValue = 26 + asciiValue;

                    decryptedMessage += (char) asciiValue;
                } else {
                    decryptedMessage += (char) asciiValue;
                }
            } else {
                decryptedMessage += " ";
            }

        }

       return decryptedMessage;
    }


    
    public String decryptionWithExhaustiveKey() {

        this.encryptedMessage = encryptedMessage.toLowerCase(); 

        // get words from string
        String[] words = encryptedMessage.split(" ");       //Splits the words in the encrypted message

        // make arraylist
        ArrayList<String> wordList = new ArrayList<String>();  //ArrayList to store the words of length four


        /*
         * We know that the hint is the message has the word "done" 
         * so now we can use exhaustive search method to decryte the words until word = "done"
         * and then we can get the key through
         */

        // find the word with length of 4
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() == 4) {
                wordList.add(words[i]);
            }
        }

        // space in output
        System.out.println(" ");

        // decrypt with key 1 to 25
        for (int i = 0; i < wordList.size(); i++) {

            for (int j = 0; j < 25; j++) {

                String decryptedWord = "";

                for (int k = 0; k < wordList.get(i).length(); k++) {

                    int asciiValue = wordList.get(i).charAt(k) - j;
                    
                    if (asciiValue < 'a') {
                        
                        asciiValue = 26 + asciiValue;
                        decryptedWord += (char) asciiValue;
                   
                    } else {
                    
                        decryptedWord += (char) asciiValue;
                    
                    }
                }


                if (decryptedWord.equalsIgnoreCase("done")) {
                    
                    this.key = j;
                    // decrypt with key data
                    String decryptedMessage = "";

                    for (int k = 0; k < encryptedMessage.length(); k++) {

                        if (encryptedMessage.charAt(k) != ' ') {
                            
                            int c = encryptedMessage.charAt(k) - key;
                            
                            if (c < 'a') {

                                c = 26 + c;
                                decryptedMessage += (char) c;
                            
                            } else {
                            
                                decryptedMessage += (char) c;
                            
                            }
                        } else {
                            
                            decryptedMessage += " ";
                        
                        }

                    }
                    return decryptedMessage;
                }
            }
        }
        return decryptedMessage;
    }

}
