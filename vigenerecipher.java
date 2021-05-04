package informsecurity;

import java.util.Scanner;

public class vigenerecipher 
{
   public String key_size_full(String str, String key) 
   { 
    int x = str.length(); 
    for (int i = 0; ; i++) 
    { 
        // wraping end of the text to the start
        if (x == i) 
            i = 0; 
        if (key.length() == str.length()) 
            break; 
        key+=(key.charAt(i)); 
    } 
    return key; 
   }
    
    public String encrypttext(String str, String key) 
    { 
    String ciphertext=""; 
  
    for (int i = 0; i < str.length(); i++) 
    { 
        char letter = str.charAt(i);
        if(letter >= 'a' && letter <= 'z')
        {
        // 0-25 range
        ciphertext += (char)((((str.charAt(i)+key.charAt(i) -2*'a')%26)+'a'));
        }
        else
        {
        ciphertext +=letter;
        }
    }
    return ciphertext; 
    } 
  
    public String decrypttext(String ciphertext, String key) 
    { 
    String plaintext=""; 
  
    for (int i=0;i < ciphertext.length() && i < key.length();i++) 
    { 
        char letter = ciphertext.charAt(i);
        if(letter >= 'a' && letter <= 'z')
        {
        // 0-25 range 
        plaintext += (char)(((26+ciphertext.charAt(i)-key.charAt(i)) %26)+'a'); 
        }
        else
        {
        plaintext +=letter;
        }
    } 
    return plaintext; 
    } 

   public static void main(String[] args)  
   {
        vigenerecipher encryptobj=new vigenerecipher();
        Scanner in= new Scanner(System.in);
        System.out.println("Enter text to encrypt: ");
        String str = in.nextLine();
        str = str.toLowerCase(); 
        System.out.println("Enter Key: ");
        String key =in.nextLine();
        key = key.toLowerCase();
        String fullkey = encryptobj.key_size_full(str,key); 
        System.out.println(fullkey);
        String message = encryptobj.encrypttext(str,fullkey); 
        System.out.println("Encrypted message: "+message);
        System.out.println("Decrypted message: "+encryptobj.decrypttext(message,fullkey));   
   } 
} 
