package informsecurity;

import java.util.Scanner;

public class basicgcd {
    static int gcd(int a, int b)
    {
        if (a == 0)
            return b;
         
        return gcd(b%a, a);
    }
    public static void main(String[] args)
    {
        Scanner in= new Scanner(System.in);
        System.out.println("------Enter two numbers to compute GCD------");
        System.out.println("Number 1: ");
        int num1= in.nextInt();
        System.out.println("Number 2: ");
        int num2= in.nextInt();
        int result = gcd(num1, num2);
        System.out.println("GCD(" + num1 +  ", " + num2+ ") = " + result);
    }
}
