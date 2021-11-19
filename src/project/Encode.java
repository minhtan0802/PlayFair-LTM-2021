/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;

/**
 *
 * @author minht
 */
public class Encode {
    //length of digraph array  

    //length of digraph array  
    private String keyTable;
    private int length = 0;
//creates a matrix for Playfair cipher   
    private String[][] table;
//main() method to test Playfair method  

    private static String resultEncode = "";

//main run of the program, Playfair method  
//constructor of the class  
    public static String getResultEncode() {
        return resultEncode;

    }

    public String getKeyTable() {
        return keyTable;
    }

    public void setKeyTable(String keyTable) {
        this.keyTable = keyTable;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String[][] getTable() {
        return table;
    }

    public void setTable(String[][] table) {
        this.table = table;
    }

    public Encode(String keyInput, String input) {
        //prompts user for the keyword to use for encoding & creates tables  
        System.out.print("Enter the key for playfair cipher: ");
        Scanner sc = new Scanner(System.in);
        String key = parseString(keyInput);
        table = this.cipherTable(key);
//prompts user for message to be encoded
        System.out.print("Enter the plaintext to be encipher: ");
//System.out.println("using the previously given keyword");
        String message = parseString(input);
        while (input.equals("")) {
            input = parseString(input);
        }
//encodes and then decodes the encoded message

        resultEncode = cipher(message);

//output the results to user
        this.keyTable(table);
//     this.printResults(output, decodedOutput);
    }
//parses an input string to remove numbers, punctuation,
//replaces any J's with I's and makes string all caps  

    private String parseString(String text) {

//converts all the letters in upper case  
        text = text.toUpperCase();
//the string to be substituted by space for each match (A to Z)  
        text = text.replaceAll("[^A-Z]", "");
//replace the letter J by I  
        text = text.replace("J", "I");
        return text;
    }
//creates the cipher table based on some input string (already parsed)  

    private String[][] cipherTable(String key) {
//creates a matrix of 5*5     
        String[][] playfairTable = new String[5][5];
        String keyString = key + "ABCDEFGHIKLMNOPQRSTUVWXYZ";
//fill string array with empty string  
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                playfairTable[i][j] = "";
            }
        }
        for (int k = 0; k < keyString.length(); k++) {
            boolean repeat = false;
            boolean used = false;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (playfairTable[i][j].equals("" + keyString.charAt(k))) {
                        repeat = true;
                    } else if (playfairTable[i][j].equals("") && !repeat && !used) {
                        playfairTable[i][j] = "" + keyString.charAt(k);
                        used = true;
                    }
                }
            }
        }
        return playfairTable;
    }
//cipher: takes input (all upper-case), encodes it, and returns the output  

    private String cipher(String in) {
        length = (int) in.length() / 2 + in.length() % 2;
//insert x between double-letter digraphs & redefines "length"  

        for (int i = 0; i < (length - 1); i++) {
            if (in.charAt(2 * i) == in.charAt(2 * i + 1)) {
                in = new StringBuffer(in).insert(2 * i + 1, 'X').toString();
                length = (int) in.length() / 2 + in.length() % 2;
            }
        }
//------------makes plaintext of even length--------------  
//creates an array of digraphs  
        String[] digraph = new String[length];
//loop iterates over the plaintext  
        for (int j = 0; j < length; j++) {
//checks the plaintext is of even length or not   
            if (j == (length - 1) && in.length() / 2 == (length - 1)) //if not addends X at the end of the plaintext    
            {
                in = in + "X";
            }
            digraph[j] = in.charAt(2 * j) + "" + in.charAt(2 * j + 1);
        }
//encodes the digraphs and returns the output  
        String out = "";
        String[] encDigraphs = new String[length];
        encDigraphs = encodeDigraph(digraph);
        for (int k = 0; k < length; k++) {
            out = out + encDigraphs[k];
        }
        return out;
    }
//---------------encryption logic-----------------  
//encodes the digraph input with the cipher's specifications  

    private String[] encodeDigraph(String di[]) {
        String[] encipher = new String[length];
        for (int i = 0; i < length; i++) {
            char a = di[i].charAt(0);
            char b = di[i].charAt(1);
            int r1 = (int) getPoint(a).getX();
            int r2 = (int) getPoint(b).getX();
            int c1 = (int) getPoint(a).getY();
            int c2 = (int) getPoint(b).getY();
//executes if the letters of digraph appear in the same row  
//in such case shift columns to right  
            if (r1 == r2) {
                c1 = (c1 + 1) % 5;
                c2 = (c2 + 1) % 5;
            } //executes if the letters of digraph appear in the same column  
            //in such case shift rows down  
            else if (c1 == c2) {
                r1 = (r1 + 1) % 5;
                r2 = (r2 + 1) % 5;
            } //executes if the letters of digraph appear in the different row and different column  
            //in such case swap the first column with the second column  
            else {
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }
//performs the table look-up and puts those values into the encoded array  
            encipher[i] = table[r1][c1] + "" + table[r2][c2];
        }
        return encipher;
    }
//-----------------------decryption logic---------------------  
// decodes the output given from the cipher and decode methods (opp. of encoding process)  
// returns a point containing the row and column of the letter  

    private Point getPoint(char c) {
        Point pt = new Point(0, 0);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (c == table[i][j].charAt(0)) {
                    pt = new Point(i, j);
                }
            }
        }
        return pt;
    }
//function prints the key-table in matrix form for playfair cipher  

    public void keyTable(String[][] printTable) {
        keyTable="";
        System.out.println("Playfair Cipher Key Matrix: ");
        System.out.println();
//loop iterates for rows  
        for (int i = 0; i < 5; i++) {
//loop iterates for column    
            for (int j = 0; j < 5; j++) {
//prints the key-table in matrix form     
                keyTable=keyTable+printTable[i][j] + " ";
            }
            keyTable=keyTable+"\n";
        }
         keyTable=keyTable+"\n";
        
    }
}
