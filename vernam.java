//https://isaaccomputerscience.org/concepts/data_encrypt_vernam
package informsecurity;

import java.lang.Math;
import java.util.Scanner;
public class vernam {

    static String expandstring(String str, int length) 
   {
  if (length <= str.length()) 
      return str.substring(0, length);
  while (str.length() * 2 <= length) {
    str += str;
  }
  if (str.length() < length) {
    str += str.substring(0, length - str.length());
  }
  return str;
  }
    
public static void main(String args[]) 
{
Scanner in= new Scanner(System.in);
String text="";
String cipher="";
System.out.println("Enter text to encrypt: ");
text = in.nextLine();
text = text.toLowerCase(); 
System.out.println("Enter Key: ");
cipher = in.nextLine();
cipher = cipher.toLowerCase();

char[] arText = text.toCharArray();

if(cipher.length() < text.length())
{
    cipher = expandstring(cipher, text.length());
}
System.out.println(cipher);

char[] arCipher = cipher.toCharArray();

char[] encoded = new char[text.length()];

System.out.println("Encoded Text is ");
for (int i =0; i<arText.length; i++) 
{
encoded[i] = (char) (arText[i] ^ arCipher[i]);
System.out.print(encoded[i]);
}
System.out.println("Decoded Text is ");

for (int i = 0; i < encoded.length; i++) 
{
char temp = (char) (encoded[i] ^ arCipher[i]);
System.out.print(temp);
}
}
}