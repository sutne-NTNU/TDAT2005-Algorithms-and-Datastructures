package src.task3;

/*
6.2-5:

a) likt eksempelet i øvingen
b) inordentraversering
c) postordentraversering
 */

public class ExpressionTree
{
    public Node root;

    public static void main(String[] args)
    {
        ExpressionTree example = new ExpressionTree();
        System.out.println(example.root + " = " + example.root.value());
    }

    public ExpressionTree()
    {
        root = new Node("/", true);

        root.left = new Node("*");
        root.left.left = new Node(3);
        root.left.right = new Node("+");
        root.left.right.left = new Node(2);
        root.left.right.right = new Node(4);

        root.right = new Node("-");
        root.right.left = new Node(7);
        root.right.right = new Node("*");
        root.right.right.left = new Node(2);
        root.right.right.right = new Node(2);
    }
}
