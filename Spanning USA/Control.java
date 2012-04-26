import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Control {

	private static EdgeWeightedGraph g;
	
	
	public static void main(String[] args) throws FileNotFoundException {

		Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream("USA-highway-miles.in")), "UTF-8");
		
		HashMap<String,Integer> strings = new HashMap<String,Integer>();
		
		String s = "";
		while(scanner.hasNextLine()) {
			s = scanner.nextLine();
			s = s.replaceAll("\"", "");
			if ( !s.contains("--") ) {
				strings.put(s.trim(), strings.size());
			} else {
				if ( g == null )
					g = new EdgeWeightedGraph(strings.size());
				
				s = s.replaceAll("\\[", "").replaceAll("\\]", "");
				String[] tmp 	= s.split("--");
				String city1	= tmp[0].trim();
				String city2 	= tmp[1].replaceAll("\\d", "").trim();
				int dist		= Integer.parseInt(tmp[1].replaceAll("\\D", "").trim());
				
				g.addEdge(new Edge(strings.get(city1), strings.get(city2), dist));
			}
		}
		
		// We make create the minimum spanning tree
		PrimMST prim = new PrimMST(g);
		
		// We print out the total weight
		System.out.println("Total weight of MST:   " + (int) prim.weight());
		
	}

}
