
public class Controller {

	public static void main(String[] args) {
		SymbolDigraph sg = new SymbolDigraph("10data.txt"," ");
		System.out.println(sg.G().toString());
	}
	
}
