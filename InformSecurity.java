/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informsecurity;

import java.io.*;
import java.lang.*;
import java.util.*;

public class InformSecurity {

        public static String encrypt(String plaintext, int shift)
        {
        String ciphertext = "";

        for (int i = 0; i < plaintext.length(); i++)
        {
        char letter = plaintext.charAt(i);
        if(letter >= 'a' && letter <= 'z')
        {
         ciphertext += (char)((letter - 'a' + shift)%26 + 'a');
        }
        else if(letter >= 'A' && letter <= 'Z')
        {
         ciphertext += (char)((letter - 'A' + shift)%26 + 'A');
        }
        else
        {
            ciphertext += letter;
        }
        }
        return ciphertext;
        }
        
        public static String decrypt(String ciphertext, int shift)
        {
        String message = "";

        for (int i = 0; i < ciphertext.length(); i++)
        {
        char letter = ciphertext.charAt(i);
        if(letter >= 'a' && letter <= 'z')
        {
         message += (char)(((26 + (letter - 'a') - shift)%26) + 'a');
        }
        else if(letter >= 'A' && letter <= 'Z')
        {
         message += (char)(((26 + (letter - 'A') - shift)%26) + 'A');
        }
        else
        {
            message += letter;
        }
        }
        return message;
        //return encrypt(ciphertext, 26-shift);
        }
        
    public static void main(String[] args) {
       
        Scanner myInp = new Scanner(System.in);  
        System.out.println("Enter String ");

        String plaintext = myInp.nextLine();  
        System.out.println("Enter ShiftKey ");
        int shift = myInp.nextInt();  
        System.out.println("PlainText is: " + plaintext);
        System.out.println("Shiftkey is: " + shift);
        
        System.out.println("Encrypted Text is "+encrypt(plaintext, shift));
        System.out.println("Decrypted Text is "+decrypt(encrypt(plaintext, shift),shift));
     } 
}