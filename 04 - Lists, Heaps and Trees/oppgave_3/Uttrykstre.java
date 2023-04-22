package øving_4.oppgave_3;

/*

6.2-5:

a) likt eksempelet i øvingen
b) inordentraversering
c) postordentraversering

 */

public class Uttrykstre
{
	public Node rot;



	public Uttrykstre( )
	{
		rot = new Node("/");

		rot.venstre = new Node("*");
		rot.venstre.venstre = new Node(3);
		rot.venstre.høyre = new Node("+");
		rot.venstre.høyre.venstre = new Node(2);
		rot.venstre.høyre.høyre = new Node(4);

		rot.høyre = new Node("-");
		rot.høyre.venstre = new Node(7);
		rot.høyre.høyre = new Node("*");
		rot.høyre.høyre.venstre = new Node(2);
		rot.høyre.høyre.høyre = new Node(2);
	}



	public static void main(String[] args)
	{
		Uttrykstre eksempel = new Uttrykstre();
		System.out.println("\n" + eksempel.printNode(eksempel.rot) + " = " + eksempel.finnVerdi(eksempel.rot));
	}



	public String printNode(Node node)
	{
		String print = "";
		if(node != null)
		{
			if(!node.isLøvNode() && node != rot) print += "(";

			print += printNode(node.venstre);
			print += node.innhold;
			print += printNode(node.høyre);

			if(node.venstre != null && node.høyre != null & node != rot) print += ")";
		}
		return print;
	}



	public int finnVerdi(Node node)
	{
		if(!node.isLøvNode())
		{
			int venstre = finnVerdi(node.venstre);
			int høyre = finnVerdi(node.høyre);
			switch ((String)node.innhold)
			{
				case "/":
					return venstre / høyre;
				case "*":
					return venstre * høyre;
				case "+":
					return venstre + høyre;
				case "-":
					return venstre - høyre;
			}
		}
		return (int)node.innhold;
	}
}
