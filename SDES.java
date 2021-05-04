package informsecurity;

import java.io.*;
import java.lang.*;
import java.util.Scanner;

class priorsetup
       {
        public static final int S0[][] = { { 1, 0, 3, 2},
                                           { 3, 2, 1, 0},
                                           { 0, 2, 1, 3},
                                           { 3, 1, 3, 2} };
        public static final int S1[][] = { { 0, 1, 2, 3},
                                           { 2, 0, 1, 3},
                                           { 3, 0, 1, 2},
                                           { 2, 1, 0, 3} };
        public int first_key, second_key;
        public static final int P10[] = { 3, 5, 2, 7, 4, 10, 1, 9, 8, 6};
        public static final int max_value_P10 = 10;
        public static final int P8[] = { 6, 3, 7, 4, 8, 5, 10, 9};
        public static final int max_value_P8 = 10;
        public static final int P4[] = { 2, 4, 3, 1};
        public static final int max_value_P4 = 4;
        public static final int IP[] = { 2, 6, 3, 1, 4, 8, 5, 7};
        public static final int max_value_IP = 8;
        public static final int IPI[] = { 4, 1, 3, 5, 7, 2, 8, 6};
        public static final int max_value_IPI = 8;
        public static final int EP[] = { 4, 1, 2, 3, 2, 3, 4, 1};
        public static final int max_value_EP = 4;
       
         public byte finding_encryption( int m)
        {
          m = finding_permutation( m, IP, max_value_IP);
          m = finding_nextkey( m, first_key);
          m = finding_switch( m);
          m = finding_nextkey( m, second_key);  
          m = finding_permutation( m, IPI, max_value_IPI);
          return (byte) m;
        }
      
        public byte finding_decryption( int m)
        {
          m = finding_permutation( m, IP, max_value_IP); 
          m = finding_nextkey( m, second_key);
          m = finding_switch( m);
          m = finding_nextkey( m, first_key);
          m = finding_permutation( m, IPI, max_value_IPI);
          return (byte) m;
        }
       
       public static int finding_permutation( int x, int p[], int pmax)
       {
         int y = 0;
         for( int i = 0; i < p.length; ++i)
          {
            y <<= 1;
            y |= (x >> (pmax - p[i])) & 1;
        }
         return y;
       }
       
       public static int finding_sbox( int R, int K)
       {
          int c = finding_permutation( R, EP, max_value_EP) ^ K;
          int c0 = (c >> 4) & 0xF;
          int c1 = c & 0xF;
          c0 = S0[ ((c0 & 0x8) >> 2) | (c0 & 1) ][ (c0 >> 1) & 0x3 ];
          c1 = S1[ ((c1 & 0x8) >> 2) | (c1 & 1) ][ (c1 >> 1) & 0x3 ];
          c = finding_permutation( (c0 << 2) | c1, P4, max_value_P4);
         return c;
       
        }

        public static int finding_nextkey( int m, int K)
        {
            int Left = (m >> 4) & 0xF;
            int Right = m & 0xF;
            return ((Left ^ finding_sbox(Right,K)) << 4) | Right;
        }
       
        public static int finding_switch( int x)
        {
           return ((x & 0xF) << 4) | ((x >> 4) & 0xF);
        }
        
          public priorsetup( int K)
        {
          K = finding_permutation( K, P10, max_value_P10);
          int c1 = (K >> 5) & 0x1F;
          int t2 = K & 0x1F;
          c1 = ((c1 & 0xF) << 1) | ((c1 & 0x10) >> 4);
          t2 = ((t2 & 0xF) << 1) | ((t2 & 0x10) >> 4);
          first_key = finding_permutation( (c1 << 5)| t2, P8, max_value_P8);
          c1 = ((c1 & 0x7) << 2) | ((c1 & 0x18) >> 3);
          t2 = ((t2 & 0x7) << 2) | ((t2 & 0x18) >> 3);
          second_key = finding_permutation( (c1 << 5)| t2, P8, max_value_P8);
        }

        public static void display_value( int x, int n)
         {
           int flag = 1 << (n-1);
           while( flag > 0)
           {
           System.out.print( ((x & flag) == 0) ? '0' : '1');
           flag >>= 1;
           }
        }
      }

      public class SDES
      {
        public static void main( String args[]) throws Exception
        {
            Scanner s = new Scanner(System.in);
            System.out.println("Enter the key {10 Bit} :");
            int key = Integer.parseInt(s.nextLine(),2);
            priorsetup obj = new priorsetup(key);
            System.out.println("Enter the Plain Text {8 Bit} : ");
            int m = Integer.parseInt(s.nextLine(),2);
            System.out.print("\n"+"------------------------------------------------------"+"\n");
            System.out.print("SIMPLIFIED DATA ENCRYPTION STANDARD"+"\n");
            System.out.print("------------------------------------------------------"+"\n");
            System.out.print("\nFirst SubKey {K1} : ");
            priorsetup.display_value( obj.first_key, 8);
            System.out.print("\nSecond SubKey {K2}: ");
            priorsetup.display_value( obj.second_key, 8);
            m = obj.finding_encryption( m);
            System.out.print("\nAfter Encryption : ");
            priorsetup.display_value( m, 8);
            m = obj.finding_decryption( m);
            System.out.print("\nAfter Decryption : ");
            priorsetup.display_value( m, 8);
            System.out.print("\n"+"------------------------------------------------------"+"\n");
        }
    }
