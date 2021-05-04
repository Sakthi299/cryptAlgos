package informsecurity;

import java.util.*;
public class multiplicativecipher {
   
    String textencrypt(String plaintext,int key)
    {
        String ciphertext="";
        
        for (int i = 0; i < plaintext.length(); i++)
        {
        char letter = plaintext.charAt(i);
        if(letter >= 'a' && letter <= 'z')
        {
         ciphertext += (char)(((letter - 'a') *key)%26 + 'a');
        }
        else
        {
            ciphertext += letter;
        }
        }
        return ciphertext;
    }
    
    int gcd(int a, int b)
    {
    if (a == 0)
        return b;
    return gcd(b % a, a);
    }
    
    String textdecrypt(String ciphertext,int key)
    {
        String textdecrypt = "";
 
        int key_inv = 0;
        for (int i = 0; i < 26; i++)  
         { 
            if ((key * i) % 26 == 1)  
            { 
                key_inv = i; 
            } 
         }
        
        for (int i = 0; i < ciphertext.length(); i++)
        {
        char letter = ciphertext.charAt(i);
        if(letter >= 'a' && letter <= 'z')
        {
         textdecrypt += (char)(((letter - 'a') *key_inv)%26 + 'a');
        }
        else
        {
           textdecrypt += letter;
        }
        }
         return textdecrypt;   
    }
    
    public static void main(String[] args)
    {
        multiplicativecipher obj=new multiplicativecipher();
        Scanner in= new Scanner(System.in);
        System.out.println("Enter text to encrypt: ");
        String str = in.nextLine();
        str = str.toLowerCase(); 
        int key;
        do
        {
        System.out.println("Enter Key: ");
        key =in.nextInt();
        }while(obj.gcd(key,26) != 1);
        String message = obj.textencrypt(str,key);
        System.out.println("Encrypted message: "+message);
        System.out.println("Decrypted message: "+obj.textdecrypt(message,key));   
    } 
}
