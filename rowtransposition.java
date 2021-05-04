package informsecurity;

import java.util.Scanner;
public class rowtransposition
{
    public static String encryptkey;
    public static char   sortedKey[];
    public static int    sortedKeyPos[];
    
    public rowtransposition(String myKey)
    {
        encryptkey = myKey;
        sortedKeyPos = new int[encryptkey.length()];
        sortedKey = encryptkey.toCharArray();
    }
    public static void setkey()
    {
        int min, i, j;
        char orginalKey[] = encryptkey.toCharArray();
        char temp;
        for (i = 0; i < encryptkey.length(); i++)
        {
            min = i;
            for (j = i; j < encryptkey.length(); j++)
            {
                if (sortedKey[min] > sortedKey[j])
                {
                    min = j;
                }
            }
            if (min != i)
            {
                temp = sortedKey[i];
                sortedKey[i] = sortedKey[min];
                sortedKey[min] = temp;
            }
        }
        // Fill the position of array according to alphabetical order
        for (i = 0; i < encryptkey.length(); i++)
        {
            for (j = 0; j < encryptkey.length(); j++)
            {
                if (orginalKey[i] == sortedKey[j])
                    sortedKeyPos[i] = j;
            }
        }
    }
 
    // to encrypt the targeted string
    public static String encrypttext(String plainText)
    {
        int min, i, j;
        char orginalKey[] = encryptkey.toCharArray();
        char temp;
        setkey();
        // Generate encrypted message by doing encryption using Transpotion
        // Cipher
        int row = plainText.length() / encryptkey.length();
        int extrabit = plainText.length() % encryptkey.length();
        int exrow = (extrabit == 0) ? 0 : 1;
        int rowtemp = -1, coltemp = -1;
        int totallength = (row + exrow) * encryptkey.length();
        char matrix[][] = new char[(row + exrow)][(encryptkey.length())];
        char encry[] = new char[totallength];
        int tempcount = -1;
        row = 0;
        for (i = 0; i < totallength; i++)
        {
            coltemp++;
            if (i < plainText.length())
            {
                if (coltemp == (encryptkey.length()))
                {
                    row++;
                    coltemp = 0;
                }
                matrix[row][coltemp] = plainText.charAt(i);
            }
            else
            { 
                matrix[row][coltemp] = '*';
            }
        }
        int len = -1, k;
        for (i = 0; i < encryptkey.length(); i++)
        {
            for (k = 0; k < encryptkey.length(); k++)
            {
                if (i == sortedKeyPos[k])
                {
                    break;
                }
            }
            for (j = 0; j <= row; j++)
            {
                len++;
                encry[len] = matrix[j][k];
            }
        }
        String p1 = new String(encry);
        return (new String(p1));
    }
    public static String decrypttext(String s)
    {
        int min, i, j, k;
        char key[] = encryptkey.toCharArray();
        char encry[] = s.toCharArray();
        char temp;
        setkey();
        // Now generating plain message
        int row = s.length() / encryptkey.length();
        char matrix[][] = new char[row][(encryptkey.length())];
        int tempcount = -1;
        for (i = 0; i < encryptkey.length(); i++)
        {
            for (k = 0; k < encryptkey.length(); k++)
            {
                if (i == sortedKeyPos[k])
                {
                    break;
                }
            }
            for (j = 0; j < row; j++)
            {
                tempcount++;
                matrix[j][k] = encry[tempcount];
            }
        }
        // store matrix character in to a single string
        char p1[] = new char[row * encryptkey.length()];
        k = 0;
        for (i = 0; i < row; i++)
        {
            for (j = 0; j < encryptkey.length(); j++)
            {
                if (matrix[i][j] != '*')
                {
                    p1[k++] = matrix[i][j];
                }
            }
        }
        p1[k++] = '\0';
        return (new String(p1));
    }

    public static void main(String[] args)
    {
        Scanner in= new Scanner(System.in);
        System.out.println("Enter Plaintext: ");
        String str= in.nextLine();
        System.out.println("Enter Key: ");
        String key= in.nextLine();
        rowtransposition tc = new rowtransposition(key);
        System.out.println("Encrypted Message is: " + tc.encrypttext(str)); 
        System.out.println("Decrypted Message is: " + tc.decrypttext(tc.encrypttext(str)));
    }
}