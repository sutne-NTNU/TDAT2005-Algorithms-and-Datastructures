package oppgave_2;

class RandomTabell
{
    private static int[] random_tabell;

    static int[] randomTabell(int antall, int grense)
    {
        random_tabell = new int[antall];
        for (int i = 0; i < random_tabell.length; i++)
        {
            random_tabell[i] = 1 + (int)(Math.random() * grense);
        }
        return random_tabell;
    }
}
