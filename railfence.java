package informsecurity;

import java.util.*;
public class railfence {
    
    static String encryptrail(String plaintext,int key)
    {
        String result = "";
        char rail[][]=new char[key][plaintext.length()]; 
        
        for(int i=0;i<key;i++)
        {
            for(int j=0;j<plaintext.length();j++)
            {
                rail[i][j] = '\n';
            }
        }
        
        boolean down_flag = false;
        int row=0,col=0;
        for(int i=0;i<plaintext.length();i++)
        {
            if(row==0||row==key-1)
            {
                down_flag = !down_flag;
            }
            rail[row][col++] = plaintext.charAt(i);
            if(down_flag)
            {
                row++;
            }
            else
            {
                row--;
            }
        }
        for(int i=0;i<key;i++)
        {
            for(int j=0;j<plaintext.length();j++)
            {
                if(rail[i][j] != '\n')
                    result +=rail[i][j];
            }
        }
        
        return result;
    }
    
    static String decryptrail(String ciphertext,int key)
    {
        String result = "";
        char rail[][]=new char[key][ciphertext.length()]; 
        
        for(int i=0;i<key;i++)
        {
            for(int j=0;j<ciphertext.length();j++)
            {
                rail[i][j] = '\n';
            }
        }
        
        boolean down_flag= false;
        int row=0,col=0;
        for(int i=0;i<ciphertext.length();i++)
        {
            if(row==0||row==key-1)
            {
                down_flag = !down_flag;
            }
            rail[row][col++] = '*';
            
            if(down_flag)
            {
                row++;
            }
            else
            {
                row--;
            }
        }
        
        int index= 0; 
        for(int i=0;i<key;i++)
        {
            for(int j=0;j<ciphertext.length();j++)
            {
                if(rail[i][j] == '*' && index<ciphertext.length())
                    rail[i][j] = ciphertext.charAt(index++);
            }
        }
        
        row=0; col=0;
        for(int i=0;i<ciphertext.length();i++)
        {
            if(row==0||row==key-1)
            {
                down_flag = !down_flag;
            }
            if(rail[row][col] != '*')
            {
                result +=rail[row][col++];
            }
            if(down_flag)
            {
                row++;
            }
            else
            {
                row--;
            }
        }
        
        return result;
    }
 
    public static void main(String[] args)
    {
        Scanner myInp = new Scanner(System.in);
        
        System.out.println("Enter message :");
        String plaintext = myInp.nextLine(); 
        System.out.println("Enter key :");
        int key = myInp.nextInt(); 
        System.out.println("---------------------"); 
        System.out.println("Plain text: " + plaintext); 
        System.out.println("Key: " + key);
        
        String encryptedString = encryptrail(plaintext.toLowerCase(),key); 
        System.out.println("Encrypted message: " + encryptedString); 
        System.out.println("Decrypted message: " + decryptrail(encryptedString,key)); 
    }   
}
