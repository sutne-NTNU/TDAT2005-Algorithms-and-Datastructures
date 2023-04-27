package src;

import static src.Printer.blue;
import static src.Printer.green;
import static src.Printer.red;

public class Task8
{
    public static void main(String[] args)
    {
        System.out.println(blue("\nOppgave 8a") + ": bit-string that starts with 0, and contains exactly one 1.");
        StateMachine task8a = new StateMachine("01", 4, 2);

        // Manually add each state change function
        // addFunction(fromState, input, toState)
        task8a.addFunction(0, '0', 1);
        task8a.addFunction(0, '1', 3);
        task8a.addFunction(1, '0', 1);
        task8a.addFunction(1, '1', 2);
        task8a.addFunction(2, '0', 2);
        task8a.addFunction(2, '1', 3);
        task8a.addFunction(3, '0', 3);
        task8a.addFunction(3, '1', 3);
        System.out.println(task8a);

        test(task8a, "100");
        test(task8a, "010");
        test(task8a, "111");
        test(task8a, "");
        test(task8a, "001000");
        test(task8a, "010110");

        System.out.println(blue("\nOppgave 8b") + ": Strings that begin with 'ab'");
        StateMachine task8b = new StateMachine("ab", 5, 3);

        task8b.addFunction(0, 'a', 1);
        task8b.addFunction(0, 'b', 2);
        task8b.addFunction(1, 'a', 4);
        task8b.addFunction(1, 'b', 3);
        task8b.addFunction(2, 'a', 3);
        task8b.addFunction(2, 'b', 4);
        task8b.addFunction(3, 'a', 3);
        task8b.addFunction(3, 'b', 3);
        task8b.addFunction(4, 'a', 4);
        task8b.addFunction(4, 'b', 4);
        System.out.println(task8b);

        test(task8b, "abbb");
        test(task8b, "aaab");
        test(task8b, "");
        test(task8b, "babab");
        test(task8b, "bbbab");
        System.out.println("");
    }

    static void test(StateMachine machine, String input)
    {
        boolean accepted = machine.validate(input);
        String str = "'" + input + "'";
        System.out.println(accepted ? green(str) : red(str));
    }
}
