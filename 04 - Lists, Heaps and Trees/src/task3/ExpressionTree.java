package src.task3;

/*
6.2-5:

a) same as example in assignment
b) in-order-traversal
c) post-order-traversal
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
        this.root = new Node("/", true);

        this.root.left = new Node("*");
        this.root.left.left = new Node(3);
        this.root.left.right = new Node("+");
        this.root.left.right.left = new Node(2);
        this.root.left.right.right = new Node(4);

        this.root.right = new Node("-");
        this.root.right.left = new Node(7);
        this.root.right.right = new Node("*");
        this.root.right.right.left = new Node(2);
        this.root.right.right.right = new Node(2);
    }
}
