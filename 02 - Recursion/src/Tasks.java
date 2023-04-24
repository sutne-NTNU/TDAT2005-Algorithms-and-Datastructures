package src;

import java.util.Date;

public class Tasks
{
    public static void main(String[] args)
    {
        double x = 1.1;
        int n = 7000;

        timerMath(x, n);
        timerTask211(x, n); // n = 7000 => T(7000) = 7000
        timerTask223(x, n); // n = 7000 => T(7000) = log2(7000) + 1 = 14.9
    }

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
    public static double task211(double X, int n)
    {
        if (n == 0)
        {
            return 1;
        }
        return X * task211(X, n - 1);
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
    public static double task223(double X, int n)
    {
        if (n == 0)
        {
            return 1;
        }
        if (n % 2 == 0)
        {
            return task223(X * X, n / 2);
        }
        return X * task223(X * X, (n - 1) / 2);
    }

    public static double timerTask211(double x, int n)
    {
        int numRuns = 0;
        Date end;
        double result;

        Date start = new Date();
        do
        {
            result = task211(x, n);
            end = new Date();
            ++numRuns;
        } while (end.getTime() - start.getTime() < 2000);

        double time = (double)(end.getTime() - start.getTime()) / numRuns;
        System.out.println(String.format("Time 2.1-1: %fms\t Result: " + result, time, result));
        return time;
    }

    public static double timerTask223(double x, int n)
    {
        int numRuns = 0;
        Date end;
        double result;

        Date start = new Date();
        do
        {
            result = task223(x, n);
            end = new Date();
            ++numRuns;
        } while (end.getTime() - start.getTime() < 2000);

        double time = (double)(end.getTime() - start.getTime()) / numRuns;
        System.out.println(String.format("Time 2.2-3: %fms\t Result: " + result, time, result));
        return time;
    }

    public static double timerMath(double x, int n)
    {
        int numRuns = 0;
        Date end;
        double result;

        Date start = new Date();
        do
        {
            result = Math.pow(x, n);
            end = new Date();
            ++numRuns;
        } while (end.getTime() - start.getTime() < 2000);

        double time = (double)(end.getTime() - start.getTime()) / numRuns;
        System.out.println(String.format("Time Math:  %fms\t Result: " + result, time, result));
        return time;
    }
}
