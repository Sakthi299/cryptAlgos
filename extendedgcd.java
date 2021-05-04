package informsecurity;

import java.util.Scanner;

public class extendedgcd {
    static int extendedgcd(int a, int b, int x, int y)
    {
        if (a == 0)
        {
            x = 0;
            y = 1;
            return b;
        }
        int x1=1, y1=1; 
        int gcd = extendedgcd(b%a, a, x1, y1);
        x = y1 - (b/a) * x1;
        y = x1;
 
        return gcd;
    }
    public static void main(String[] args)
    {
        Scanner in= new Scanner(System.in);
        int x=1,y=1;
        System.out.println("------Enter two numbers to compute GCD with Extended Euclid's Algorithm------");
        System.out.println("Number 1: ");
        int num1= in.nextInt();
        System.out.println("Number 2: ");
        int num2= in.nextInt();
        int result = extendedgcd(num1, num2, x, y);
        System.out.println("GCD(" + num1 +  ", " + num2+ ") = " + result);
    }
}
