/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informsecurity;

import java.io.*; 
import java.util.*;

public class monoalpha {
     public static char plain[] 
        = {'a','b','c','d','e','f','g','h','i','j','k','l','m',
           'n','o','p','q','r','s','t','u','v','w','x','y','z'}; 
     public static char cipher[] 
        = {'z','y','x','w','v','u','t','s','r','q','p','o','n',
           'm','l','k','j','i','h','g','f','e','d','c','b','a'}; 
    
     public static String stringencrypt(String s) 
     { 
        String encrypted = ""; 
        for (int i = 0; i < s.length(); i++) { 
            for (int j = 0; j < 26; j++) { 
                if (s.charAt(i) == plain[j])  
                { 
                    encrypted += cipher[j]; 
                    break; 
                } 
                if (s.charAt(i) < 'a' || s.charAt(i) > 'z')  
                { 
                    encrypted += s.charAt(i); 
                    break; 
                } 
            } 
        } 
             return encrypted; 
    }
     public static String stringdecrypt(String s) 
    { 
        String decrypted = ""; 
        for (int i = 0; i < s.length(); i++) 
        { 
            for (int j = 0; j < 26; j++) { 
                if (s.charAt(i) == cipher[j]) 
                { 
                    decrypted += plain[j]; 
                    break; 
                } 
                if (s.charAt(i) < 'a' || s.charAt(i) > 'z')  
                { 
                    decrypted += s.charAt(i); 
                    break; 
                } 
            } 
        } 
       return decrypted; 
    } 
     
     public static void main(String args[]) 
    { 
        Scanner myInp = new Scanner(System.in);
        
        System.out.println("Enter message :");
        String plaintext = myInp.nextLine(); 
        System.out.println("Plain text: " + plaintext); 
        
        String encryptedString = stringencrypt(plaintext.toLowerCase()); 
        System.out.println("Encrypted message: " + encryptedString); 
        System.out.println("Decrypted message: " + stringdecrypt(encryptedString)); 
        
    } 
}
