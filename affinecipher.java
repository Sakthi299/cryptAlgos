package informsecurity;

import java.util.Scanner;

public class affinecipher {
    
     String textencrypt(String plaintext,int key1,int key2)
    {
        String ciphertext="";
        
        for (int i = 0; i < plaintext.length(); i++)
        {
        char letter = plaintext.charAt(i);
        if(letter >= 'a' && letter <= 'z')
        {
         ciphertext += (char)((((letter - 'a') *key1)+key2)%26 + 'a');
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
    
    String textdecrypt(String ciphertext,int key1,int key2)
    {
        String textdecrypt = "";
 
        int key_inv = 0;
        for (int i = 0; i < 26; i++)  
         { 
            if ((key1 * i) % 26 == 1)  
            { 
                key_inv = i; 
            } 
         }
        //System.out.println(key_inv);
        for (int i = 0; i < ciphertext.length(); i++)
        {
        char letter = ciphertext.charAt(i);
        if(letter >= 'a' && letter <= 'z')
        {
         textdecrypt +=(char) (((key_inv * (26+(letter -'a')-key2))%26)+ 'a');
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
        affinecipher obb=new affinecipher();
        Scanner in= new Scanner(System.in);
        System.out.println("Enter text to encrypt: ");
        String str = in.nextLine();
        str = str.toLowerCase(); 
        int key1,key2;
        do
        {
        System.out.println("Enter Key1(a): ");
        key1 =in.nextInt();
        }while(obb.gcd(key1,26) != 1);
        System.out.println("Enter Key2(b): ");
        key2 =in.nextInt();
        String message = obb.textencrypt(str, key1, key2);
        System.out.println("Encrypted message: "+message);
        System.out.println("Decrypted message: "+obb.textdecrypt(message,key1,key2));   
    } 
}

