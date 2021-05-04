package informsecurity;

import java.util.*;
public class frequencyanalysis {	
	
	static void findfreq(String str)
        {            
		int n = str.length();
		int[] hash = new int[26];
		for (int i = 0; i < n; i++)
			hash[str.charAt(i) - 'a']++;
		for (int i = 0; i < n; i++) {
			if (hash[str.charAt(i) - 'a'] != 0) {
				System.out.print(str.charAt(i)+"->");
				System.out.print(hash[str.charAt(i) - 'a'] + "\n"); 
				hash[str.charAt(i) - 'a'] = 0;
			}
		}
	}
	public static void main(String args[])
	{
                Scanner in = new Scanner(System.in);
                System.out.println("Enter text to find frequency of letter: ");
		String str = in.nextLine();
                str = str.replaceAll("\\s", ""); 
                System.out.println("-----Frequency Analysis----- ");
		findfreq(str);
	}
}


