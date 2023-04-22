package oppgave_3;

class Node
{
    Object innhold;
    Node venstre;
    Node høyre;

    Node(Object innhold)
    {
        this.innhold = innhold;
    }

    boolean isLøvNode()
    {
        return this != null && this.venstre == null && this.høyre == null;
    }
}
