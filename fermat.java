package informsecurity;

import java.util.Scanner;
public class fermat 
{ 
static int find_gcd(int y, int z) 
{
if(z == 0) 
{ 
return y; 
} 
else
{ 
return find_gcd(z, y % z); 
} 
} 
static int higher_value(int x,int y,int m) 
{ 
if (y == 0) 
return 1; 
int p = higher_value(x, y / 2, m) % m; 
p = (p * p) % m; 
return (y % 2 == 0) ? p : (x * p) % m; 
} 
static void finding_inverse(int a, int m) 
{ 
if (find_gcd(a, m) != 1) 
System.out.print("Inverse not possible"); 
else { 
System.out.print("------------------------------------------------------"+"\n");
System.out.print("The modular multiplicative inverse : "+higher_value(a, m - 2, m)); 
System.out.print("\n"+"------------------------------------------------------"+"\n");
} 
} 
public static void main (String[] args) 
{ 
 int f_number , s_number ;
 Scanner s = new Scanner(System.in);
 System.out.println("Enter the First Number (a): ");
 f_number = s.nextInt();
 System.out.println("Enter the Second Number (m): ");
s_number = s.nextInt();
finding_inverse(f_number, s_number); 
 
}
}
