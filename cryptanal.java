package informsecurity;


import java.util.Scanner;
public class cryptanal {
 char [] input;
 int key;

    void bruteforce()
 {
     Scanner sc = new Scanner(System.in);
     System.out.print("Enter Cipher String : ");
     String ip = sc.nextLine();
     input = ip.toCharArray();
     for(key=1;key<27;key++)
     {
         for(int i=0;i<input.length;i++)
         {
             if(input[i] == ' ')
                 continue;
             else
             {
                 if(input[i] >='A' && input[i] <='Z')
                 {
                     input[i] = (char) (input[i] - key);
                     if(input[i] < 'A')
                     {
                         input[i] = (char) (input[i] + 26);
                     }
                 }
                 else
                 {
                     input[i] = (char) (input[i] - key);
                     if(input[i] < 'a')
                     {
                         input[i] = (char) (input[i] + 26);
                     }
                 }
             }
         }
         System.out.println("Key = " + key + " Decrypted String : " + String.valueOf(input));
         input = ip.toCharArray();
     }
 }
    
 public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);
     int c;
     do
     {
         System.out.println("1:Bruteforce\n2:Exit\n");
         c = sc.nextInt();
         switch(c)
         {
             case 1 : new cryptanal().bruteforce();  break;
             case 2 : break;
         }
     }while(c!=2);
 }
}
