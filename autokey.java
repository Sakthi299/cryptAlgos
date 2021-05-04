import java.lang.*; 
import java.util.*;   
public class autokey { 
  
    private static final String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
  
    public static void main(String[] args) 
    { 
        Scanner in= new Scanner(System.in);
        System.out.println("Enter text to encrypt: ");
        String str = in.nextLine();
        System.out.println("Enter KeyStream: ");
        String autokey =in.nextLine();
        /*if (key.matches("[-+]?\\d*\\.?\\d+")) 
            key = "" + alpha.charAt(Integer.parseInt(key)); */
        String message = autoencrypt(str.toUpperCase(), autokey.toUpperCase()); 
  
        System.out.println("Plaintext : " + str); 
        System.out.println("Encrypted : " + message); 
        System.out.println("Decrypted : " + autodecrypt(message, autokey.toUpperCase())); 
    } 
  
    public static String autoencrypt(String str, String autokey) 
    { 
        int len = str.length(); 
        String newkey = autokey.concat(str); 
        newkey = newkey.substring(0, newkey.length() - autokey.length()); // deletes extra characters after appending
        String encrypttext = ""; 
        for (int i = 0;i<len;i++) { 
            int plainstream = alpha.indexOf(str.charAt(i)); 
            int keystream   = alpha.indexOf(newkey.charAt(i)); 
            int total = (plainstream + keystream) % 26; 
            encrypttext += alpha.charAt(total); 
        } 
        return encrypttext; 
    } 
  
    public static String autodecrypt(String str, String key) 
    { 
        String currentKey = key; 
        String decrypttext = ""; 
        for (int i= 0;i<str.length();i++) { 
            int cipherstream = alpha.indexOf(str.charAt(i)); 
            int keystream = alpha.indexOf(currentKey.charAt(i)); 
            int total = (cipherstream - keystream) % 26; 
            total = (total < 0) ? total + 26 : total; 
            decrypttext += alpha.charAt(total); 
            currentKey += alpha.charAt(total); 
        } 
        return decrypttext; 
    } 
} 


/*

package informsecurity;
// https://www.geeksforgeeks.org/java-program-to-perform-cryptography-using-transposition-technique/
// For transposition cipher 

import java.util.Scanner;

public class rowtransposition { 
  
    // Member variables of this class 
    public static String selectedKey; 
    public static char sortedKey[]; 
    public static int sortedKeyPos[]; 
  
    // Constructor 1 of this class 
    // Default constructor defining the default key 
    public static void keyset(String key) 
    { 
        selectedKey = key;
        sortedKeyPos = new int[selectedKey.length()]; 
        sortedKey = selectedKey.toCharArray(); 
    } 
 
    // Method 1 - doProcessOnKey() 
    // To reorder data do the sorting on selected key 
    public static void doProcessOnKey() 
    { 
        // Find position of each character in selected key 
        // and arranging it in alphabetical order 
        int min, i, j; 
        char orginalKey[] = selectedKey.toCharArray(); 
        char temp; 
  
        // Step 1: Sorting the array of selected key 
        // using nested for loops 
        for (i = 0; i < selectedKey.length(); i++) { 
            min = i; 
            for (j = i; j < selectedKey.length(); j++) { 
                if (sortedKey[min] > sortedKey[j]) { 
                    min = j; 
                } 
            } 
  
            if (min != i) { 
                temp = sortedKey[i]; 
                sortedKey[i] = sortedKey[min]; 
                sortedKey[min] = temp; 
            } 
        } 
  
        // Step 2: Filling the position of array 
        // according to alphabetical order 
        // using nested for loops 
        for (i = 0; i < selectedKey.length(); i++) { 
            for (j = 0; j < selectedKey.length(); j++) { 
                if (orginalKey[i] == sortedKey[j]) 
                    sortedKeyPos[i] = j; 
            } 
        } 
    } 
  
    // Method 2 - doEncryption() 
    // To encrypt the targeted string 
    public static String doencrypt(String plainText) 
    { 
        int min, i, j; 
        char orginalKey[] = selectedKey.toCharArray(); 
        char temp; 
        doProcessOnKey(); 
  
        // Step 3: Generating the encrypted message by 
        // doing encryption using Transpotion Cipher 
        int row = plainText.length() / selectedKey.length(); 
        int extrabit 
            = plainText.length() % selectedKey.length(); 
        int exrow = (extrabit == 0) ? 0 : 1; 
        int rowtemp = -1, coltemp = -1; 
        int totallen = (row + exrow) * selectedKey.length(); 
        char pmat[][] = new char[(row + exrow)] 
                                [(selectedKey.length())]; 
        char encry[] = new char[totallen]; 
  
        int tempcnt = -1; 
        row = 0; 
  
        for (i = 0; i < totallen; i++) { 
            coltemp++; 
            if (i < plainText.length()) { 
                if (coltemp == (selectedKey.length())) { 
                    row++; 
                    coltemp = 0; 
                } 
                pmat[row][coltemp] = plainText.charAt(i); 
            } 
  
            else { 
  
                // Padding can be added between two 
                // consecutive alphabets or a group of 
                // alphabets of the resultant cipher text 
                pmat[row][coltemp] = '-'; 
            } 
        } 
  
        int len = -1, k; 
  
        for (i = 0; i < selectedKey.length(); i++) { 
            for (k = 0; k < selectedKey.length(); k++) { 
                if (i == sortedKeyPos[k]) { 
                    break; 
                } 
            } 
            for (j = 0; j <= row; j++) { 
                len++; 
                encry[len] = pmat[j][k]; 
            } 
        } 
  
        String p1 = new String(encry); 
        return (new String(p1)); 
    } 
  
    // Method 3 - doEncryption() 
    // To decrypt the targeted string 
    public static String dodecrypt(String s) 
    { 
        int min, i, j, k; 
        char key[] = selectedKey.toCharArray(); 
        char encry[] = s.toCharArray(); 
        char temp; 
  
        doProcessOnKey(); 
  
        // Step 4: Generating a plain message 
        int row = s.length(); 
        selectedKey.length(); 
        char pmat[][] = new char[row][(selectedKey.length())]; 
        int tempcnt = 0; 
  
        for (i = 0; i < selectedKey.length(); i++) 
        { 
            for (k = 0; k < selectedKey.length(); k++) { 
                if (i == sortedKeyPos[k]) { 
                    break; 
                } 
            } 
  
            for (j = 0; j < row; j++) 
            { 
               
                pmat[j][k] = encry[tempcnt];  tempcnt++; 
            } 
        } 
  
        // Step 5: Storing matrix character in 
        // to a single string 
        char p1[] = new char[row * selectedKey.length()]; 
  
        k = 0; 
        for (i = 0; i < row; i++) { 
            for (j = 0; j < selectedKey.length(); j++) { 
                if (pmat[i][j] != '*') { 
                    p1[k++] = pmat[i][j]; 
                } 
            } 
        } 
  
        p1[k++] = '\0'; 
        return (new String(p1)); 
    } 
  
    //@SuppressWarnings("static-access") 
    // Method 4 - main() 
    // Main driver method 
    public static void main(String[] args) 
    { 
        rowtransposition obj = new rowtransposition(); 
        Scanner in= new Scanner(System.in);
        System.out.println("Enter text to encrypt: ");
        String str = in.nextLine();
        System.out.println("Enter text to encrypt: ");
        String key = in.nextLine();
        keyset(key);
        System.out.println("Encrypted Text : "+ obj.doencrypt(str));
        System.out.println("Decrypted Text : "+ obj.dodecrypt(obj.doencrypt(str)));  
    } 
}


*/