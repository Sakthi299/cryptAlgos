package informsecurity;

import java.util.Scanner;

public class diffiehellman 
{  
    private static long power(long a, long b, long p)
    {
        if (b == 1)
            return a;
        else
            return (((long)Math.pow(a, b)) % p);
    }
    
    public static void main(String[] args)
    {
        long p, g, x, a, y, b, sa, sb;

        Scanner sc=new Scanner(System.in);
        System.out.println("Enter modulo(p):");
        p=sc.nextLong();
        System.out.println("Enter primitive root of "+p+" :");
        g=sc.nextLong();
        System.out.println("Choose 1st secret no(Alice) :");
        a =sc.nextLong();
        System.out.println("Choose 2nd secret no(BOB) :");
        b =sc.nextLong();

        x = power(g, a, p);
        y = power(g, b, p);
        sa = power(y, a, p); // Secret key for Alice
        sb = power(x, b, p); // Secret key for Bob
        System.out.println("Secret key of a (Alice): \n" + sa);
        System.out.println("Secret key of b (Bob): \n" + sb);
    }
}