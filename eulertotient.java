package informsecurity;

import java.io.*; 
import java.util.Scanner;
public class eulertotient { 
static int finding_totient(int number) 
{ 
float result = number; 
for (int p = 2; p * p <= number; ++p) { 
if (number % p == 0) { 
while (number % p == 0) 
number /= p; 
result *= (1.0 - (1.0 / (float)p)); 
} 
} 
if (number > 1) 
result *= (1.0 - (1.0 / (float)number)); 
return (int)result; 
} 
public static void main(String args[]) 
{ 
int number = 0 , result; 
 Scanner s = new Scanner(System.in);
 do
 {
 System.out.println("Enter the number(type -1 to exit)");
 number = s.nextInt();
 if(number == -1)
     break;
result = finding_totient(number);
System.out.print("------------------------------------------------------"+"\n");
System.out.println("Euler's Totient Function Value is : "+result); 
System.out.print("------------------------------------------------------"+"\n");
}while(number!= -1); 
}
} 

