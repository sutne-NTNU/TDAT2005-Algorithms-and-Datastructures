package src;

import static src.Printer.black;
import static src.Printer.gray;

class StateMachine
{
    final char[] alphabet;
    final int[][] nextStateArray;
    final int[] acceptingStates;
    int currentState;

    StateMachine(String alphabet, int numStates, int acceptingState)
    {
        this.alphabet = alphabet.toCharArray();
        this.nextStateArray = new int[numStates][this.alphabet.length];
        this.acceptingStates = new int[] { acceptingState };
    }

    StateMachine(String alphabet, int numStates, int[] acceptingStates)
    {
        this.alphabet = alphabet.toCharArray();
        this.nextStateArray = new int[numStates][this.alphabet.length];
        this.acceptingStates = acceptingStates;
    }

    /**
     * Adds a function to the nextStateArray
     */
    void addFunction(int fromState, char value, int toState)
    {
        this.nextStateArray[fromState][this.indexOf(value)] = toState;
    }

    /**
     * @return index of the value in the nextStateArray
     */
    int indexOf(char value)
    {
        int index = -1;
        for (int i = 0; i < this.alphabet.length; i++)
        {
            if (this.alphabet[i] == value)
            {
                index = i;
            }
        }
        return index;
    }

    /**
     * @return The new state of the machine after chaning from current state based on value
     */
    void performStateChange(char value)
    {
        int valueIndex = this.indexOf(value);
        int newState = this.nextStateArray[this.currentState][valueIndex];
        this.currentState = newState;
    }

    /**
     * @return true if state is in accepting states after going through the input string
     */
    boolean validate(String inputStr)
    {
        this.currentState = 0;
        char[] input = inputStr.toCharArray();
        for (char value : input)
        {
            if (this.indexOf(value) == -1)
            {
                return false; // value is not in accepted alphabet
            }
            this.performStateChange(value);
        }
        for (int acceptingState : this.acceptingStates)
        {
            if (this.currentState == acceptingState) return true;
        }
        return false;
    }

    @Override
    public String toString()
    {
        String str = "Next State Array:\n  ";
        for (char value : this.alphabet)
        {
            str += String.format(" %s", gray(value));
        }
        str += "\n";
        for (int i = 0; i < this.nextStateArray.length; i++)
        {
            str += String.format("%s: ", gray(i));
            for (int j = 0; j < this.nextStateArray[i].length; j++)
            {
                str += String.format("%s ", black(this.nextStateArray[i][j]));
            }
            str += "\n";
        }
        return str;
    }
}
