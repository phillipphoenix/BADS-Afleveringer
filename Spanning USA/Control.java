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
		
		// While there are still lines left in the document
		while(scanner.hasNextLine()) {
			String s = scanner.nextLine();
			if ( !s.equals("")) {
				if ( !s.contains("--") ) {
					if ( s.contains( new String(new byte[] {'"'} ) ) )
						s = s.substring( 1 , s.length() - 2 ).trim();
					System.out.println(s);
					strings.put(s, strings.size());
				} else {
					
					//TODO Remove!
					for ( String str : strings.keySet() )
						System.out.println("  " + str + "  " + strings.get(str));
					
					if ( g == null )
						g = new EdgeWeightedGraph(strings.size());
					String[] edge = new String[3];

					String[] tmp = s.split("--");

					// Get the first city
					if ( tmp[0].contains( new String(new byte[] {'"'} ) ) )
						tmp[0] = tmp[0].substring( 1 , tmp[0].length() - 1 ).trim();
					edge[0] = tmp[0];

					//TODO Remove!
//					System.out.println("tmp[1]: " + tmp[1]);
					
					tmp = tmp[1].split("\\[");
					
					//TODO Remove!
//					System.out.println("tmp[0]: " + tmp[0] + " tmp[1]: " + tmp[1]);

					// Get the second city
					if ( tmp[0].contains( new String(new byte[] {'"'} ) ) )
						tmp[0] = tmp[0].trim().substring( 1 , tmp[0].length() - 2 ).trim();
					edge[1] = tmp[0];

					// Get the distance
					edge[2] = tmp[1].trim().substring( 0 , tmp[1].length() - 2 ).trim();

					//TODO Remove!
					if ( g == null ) System.err.println("No Graph created!");
					System.out.println("Print edge - 1: " + edge[0] + " 2: " + edge[1] + " dist: " +edge[2]);
					System.out.println("Print edge int - 1: " + strings.get(edge[0]) + " 2: " + strings.get(edge[1]) + " dist: " + Integer.parseInt(edge[2]));
					
					g.addEdge(new Edge(strings.get(edge[0]), strings.get(edge[1]), Integer.parseInt(edge[2])));
				}
			}
		}

		// We make create the minimum spanning tree
		PrimMST prim = new PrimMST(g);

		// We print out the total weight
		System.out.println(prim.weight());

	}

}
