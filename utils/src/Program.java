package src;

public class Program
{
    public static void sleep(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
