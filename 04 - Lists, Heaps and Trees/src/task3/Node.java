package src.task3;

class Node
{
    boolean isRoot;
    Object content;
    Node left;
    Node right;

    Node(Object innhold)
    {
        this.content = innhold;
        this.isRoot = false;
    }

    Node(Object innhold, boolean isRoot)
    {
        this.content = innhold;
        this.isRoot = isRoot;
    }

    boolean isLeafNode()
    {
        if (this.left != null) return false;
        if (this.right != null) return false;
        return true;
    }

    public int value()
    {
        if (this.isLeafNode()) return (int)this.content;
        int left = this.left != null ? this.left.value() : 0;
        int right = this.right != null ? this.right.value() : 0;
        switch ((String)this.content)
        {
            case "/": return left / right;
            case "*": return left * right;
            case "+": return left + right;
            case "-": return left - right;
            default: return 0;
        }
    }

    @Override
    public String toString()
    {
        String str = "";

        if (!this.isLeafNode() && !this.isRoot) str += "(";
        str += this.left != null ? this.left.toString() : "";
        str += this.content;
        str += this.right != null ? this.right.toString() : "";

        if (this.left != null && this.right != null && !this.isRoot) str += ")";
        return str;
    }
}
