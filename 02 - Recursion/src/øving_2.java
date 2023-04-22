package src;

import java.util.Date;

public class øving_2
{

    /*
    T(n) =
    {
        1           når n <= 1
        T(n-1) + 1  når n > 1
    }

    T(2) = 2-1+1 = 2
    T(3) = 3-1+1 = 3
    T(n) = n
     */
    public static double oppgave_2_1_1(double X, int n)
    {
        if (n == 0)
        {
            return 1;
        }
        return X * oppgave_2_1_1(X, n - 1);
    }

    /*
    T(n) =
    {
        1               når n <= 1
        T(n/2) + 1      når n > 1 og er et partall
        T(n-1/2) + 1    når n > 1 og er et oddetall
    }

    T(n) = log2(n) + 1
     */

    public static double oppgave_2_2_3(double X, int n)
    {
        if (n == 0)
        {
            return 1;
        }
        if (n % 2 == 0)
        {
            return oppgave_2_2_3(X * X, n / 2);
        }
        return X * oppgave_2_2_3(X * X, (n - 1) / 2);
    }

    public static double tidtaking_211(double x, int n)
    {
        int runder = 0;
        double tid;
        Date slutt;
        double r;

        Date start = new Date();
        do
        {
            r = oppgave_2_1_1(x, n);
            slutt = new Date();
            ++runder;
        } while (slutt.getTime() - start.getTime() < 2000);

        tid = (double)(slutt.getTime() - start.getTime()) / runder;
        System.out.println("\nTid oppgave 2.1-1:  " + tid + " ms");
        System.out.println("res: " + r);
        return tid;
    }

    public static double tidtaking_223(double x, int n)
    {
        int runder = 0;
        double tid;
        Date slutt;
        double r;

        Date start = new Date();
        do
        {
            r = oppgave_2_2_3(x, n);
            slutt = new Date();
            ++runder;
        } while (slutt.getTime() - start.getTime() < 2000);

        tid = (double)(slutt.getTime() - start.getTime()) / runder;
        System.out.println("\nTid oppgave 2.2-3:  " + tid + " ms ");
        System.out.println("res: " + r);
        return tid;
    }

    public static double tidtaking_Math(double x, int n)
    {
        int runder = 0;
        double tid;
        Date slutt;
        double r;

        Date start = new Date();
        do
        {
            r = Math.pow(x, n);
            slutt = new Date();
            ++runder;
        } while (slutt.getTime() - start.getTime() < 2000);

        tid = (double)(slutt.getTime() - start.getTime()) / runder;
        System.out.println("\nTid Math.pow():     " + tid + " ms");
        System.out.println("res: " + r);
        return tid;
    }

    public static void main(String[] args)
    {

        double x = 1.1;
        int n = 7000;

        double tidMath = tidtaking_Math(x, n);
        double tid2_1_1 = tidtaking_211(x, n); // n = 7000 gir T(7000) = 7000
        double tid2_2_3 = tidtaking_223(x, n); // n = 7000 gir T(7000) = log2(7000) + 1 = 14.9

        double dif211_233 = tid2_1_1 / tid2_2_3;
        double dif233_Math = tid2_2_3 / tidMath;

        System.out.println("\n2.1-1 vs 2.2-3:    " + dif211_233); // 7000 / 14.9 = forventet 469 ganger raskere med 2.2-3
        System.out.println("2.2-3 vs Math:    " + dif233_Math);
    }
}
