
package informsecurity;

import java.util.*;

public class playfairone 
{
    public String formattext(String str)
    {
        String restr=str.replaceAll("\\s", ""); 
        restr= restr.toLowerCase();
        return restr;
    }
    public String uniquekey       = new String();
    public String keystream       = new String();
    public char   keymatrix[][]  = new char[5][5]; 
    public void setkey(String key)
    {
        /*for(int i=0;i<uniquekey.length();i++)
        {
            if(uniquekey.charAt(i) == "j")
            {
                
            }
        }*/
        String key_temp = new String();
        //boolean flag = true;
        //key_temp = key_temp + key.charAt(0);
        key_temp = "";
        for (int i =0; i < key.length(); i++)
        {
            int j;
            for (j=0; j<i; j++)
            {
                if (key.charAt(i) == key.charAt(j))
                {
                    break;
                }
            }
            if (j == i)
            {
                key_temp = key_temp + key.charAt(i);
            }
            //flag = false;
        }
        uniquekey = key_temp;
    }
    
    public void generatekey()
    {
        int index = 0;
        boolean flag = true;
        char current;
        keystream = uniquekey;
        for(int i=0;i<26;i++)
        {
            current =(char)(i + 97);
            if(current == 'j')
                continue;
            for (int j=0;j<uniquekey.length();j++)
            {
                if(current==uniquekey.charAt(j))
                {
                    flag = false;
                    break;
                }
            }
            if (flag)
                keystream = keystream + current;
            flag = true;
        }
       // System.out.println(keystream);
        System.out.println("----------");
        for (int i=0; i<5; i++)
        {
            for (int j=0; j<5; j++)
            {
                keymatrix[i][j] = keystream.charAt(index);
                System.out.print(keymatrix[i][j] + " ");
                index++;
            }
            System.out.println();
        }
        System.out.println("----------");
    }
    
    public String[] digram(String str)
    {
        String new_str=str;
        int size = new_str.length();
        if (size % 2 != 0)
        {
            size++;
            new_str = new_str+'x';
        }
        new_str = insertfiller(new_str);
        String pairs[] = new String[size / 2];
        int counter = 0;
        for (int i=0; i<size/2;i++)
        {
            pairs[i]=new_str.substring(counter, counter + 2);
            counter = counter + 2;
        }
        return pairs;
    }
    
    public String insertfiller(String old_str)
    {
        int i = 0;
        int len = 0;
        String new_str = new String();
        len = old_str.length();
        for (int tmp = 0; tmp < len; tmp++)
        {
            if (old_str.charAt(tmp) == 'j')
            {
                new_str = new_str+'i';
            }
            else
                new_str = new_str + old_str.charAt(tmp);
        }
        len = new_str.length();
        for (i = 0; i < len; i = i + 2)
        {
            if (new_str.charAt(i + 1) == new_str.charAt(i))
            {
                new_str = new_str.substring(0, i + 1) + 'x' + new_str.substring(i + 1);
            }
        }
        return new_str;
    }
    
    public String deletefiller(String old_str)
    {
        int i = 0;
        int len = 0;
        String new_str = new String();
        len = old_str.length();
        for (int tmp = 0; tmp < len; tmp++)
        {
            if (old_str.charAt(tmp) == 'j')
            {
                new_str = new_str+'i';
            }
            else
                new_str = new_str+old_str.charAt(tmp);
        }
        new_str=new_str.replaceAll("x", "");
         
       /* for ( i=0;i < new_str.length(); i=i+2 )
        {
            if (new_str.charAt(i + 1) == new_str.charAt(i))
            {
                new_str = new_str.substring(0, i + 1) + 'x' + new_str.substring(i + 1);
            } 
            if(new_str.charAt(i+1)== 'x')
            {
                new_str.charAt(i+1)= "" ;
            }
        }*/
    
        
        return new_str;
    }
    
    public int[] GetDiminsions(char letter)
    {
        int[] key = new int[2];
        if (letter == 'j')
            letter = 'i';
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (keymatrix[i][j] == letter)
                {
                    key[0] = i;
                    key[1] = j;
                    break;
                }
            }
        }
        return key;
    }
    
    // Optional Function
   /* public String preparetext(String str)
    {
        String restr;
        restr = str;
        if(str.length() % 2 != 0 )
        {
            restr = str+"z";
        }
        return restr;
    }*/
    

    public String encryptplayfair(String str, String key)
    {
        setkey(key);
        generatekey();
        String di_str[] = digram(str);
        //System.out.println(Arrays.toString(di_str));
        String Code = new String();
        char one;
        char two;
        int part1[] = new int[2];
        int part2[] = new int[2];
        for (int i = 0; i < di_str.length; i++)
        {
            one = di_str[i].charAt(0); // 1st element in digram
            two = di_str[i].charAt(1); // 2nd element in digram
            part1 = GetDiminsions(one);
            part2 = GetDiminsions(two);
            
            // element in same row
            if (part1[0] == part2[0])
            {
                if (part1[1] < 4)
                    part1[1]++;
                else
                    part1[1] = 0;
                if (part2[1] < 4)
                    part2[1]++;
                else
                    part2[1] = 0;
            }
            // element in same column
            else if (part1[1] == part2[1])
            {
                if (part1[0] < 4)
                    part1[0]++;
                else
                    part1[0] = 0;
                if (part2[0] < 4)
                    part2[0]++;
                else
                    part2[0] = 0;
            }
            // element in rectangle space
            else
            {
                int temp = part1[1];
                part1[1] = part2[1];
                part2[1] = temp;
            }
            Code = Code + keymatrix[part1[0]][part1[1]]
                    + keymatrix[part2[0]][part2[1]];
        }
        return Code;
    }
    
    public String[] str_to_pairs(String new_str)
    {
        int size=new_str.length();
        String pairs[] = new String[size / 2];
        int counter = 0;
        for (int i=0; i<size/2;i++)
        {
            pairs[i]=new_str.substring(counter, counter + 2);
            counter = counter + 2;
        }
        return pairs;
    }
    
    public void decryptplayfair(String str)
    {
        String di_str[] = str_to_pairs(str);
        //System.out.println(Arrays.toString(di_str));
        
        String Code = new String();
        char one;
        char two;
        int part1[] = new int[2];
        int part2[] = new int[2];
        for (int i=0;i<di_str.length;i++)
        {
            one = di_str[i].charAt(0); // 1st element in digram
            two = di_str[i].charAt(1); // 2nd element in digram
            part1 = GetDiminsions(one);
            part2 = GetDiminsions(two);
            
            // element in same row
            if (part1[0] == part2[0])
            {
                if (part1[1] > 0)
                    part1[1]--;
                else
                    part1[1] = 4;
                if (part2[1] > 0)
                    part2[1]--;
                else
                    part2[1] = 4;
            }
            // element in same column
            else if (part1[1] == part2[1])
            {
                if (part1[0] > 0)
                    part1[0]--;
                else
                    part1[0] = 4;
                if (part2[0] > 0)
                    part2[0]--;
                else
                    part2[0] = 4;
            }
            // element in rectangle space
            else
            {
                int temp = part1[1];
                part1[1] = part2[1];
                part2[1] = temp;
            }
            Code = Code + keymatrix[part1[0]][part1[1]]
                    + keymatrix[part2[0]][part2[1]];
        }
      Code = deletefiller(Code);
      System.out.println("Decrypted Message: "+Code);
    }
    
    public static void main(String[] args)
    {
        playfairone  pf= new playfairone();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a keyword: ");
        String key=in.nextLine();
        System.out.println("Enter a Plaintext: ");
        String plaintext = in.nextLine();
        plaintext=pf.formattext(plaintext);
        key=pf.formattext(key);
        String message = pf.encryptplayfair(plaintext,key);
        System.out.println("Encrypted Message: "+message);
        pf.decryptplayfair(message);
        System.out.println("---------------------");
    }
}
