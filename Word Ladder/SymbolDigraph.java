
public class SymbolDigraph {
	
	private ST<String, Integer> st;  // string -> index
	private String[] keys;           // index  -> string
	private Digraph G;
	private BreadthFirstDirectedPaths bfs;

	public SymbolDigraph(String filename, String delimiter) {
		st = new ST<String, Integer>();

		// First pass builds the index by reading strings to associate
		// distinct strings with an index
		In in = new In(filename);
		while (in.hasNextLine()) {
			String[] a = in.readLine().split(delimiter);
			if (a[0].equals("")) break;
			for (int i = 0; i < a.length; i++) {
				if (!st.contains(a[i]))
					st.put(a[i], st.size());
			}
		}

		// inverted index to get string keys in an array
		keys = new String[st.size()];
		for (String name : st.keys()) {
			keys[st.get(name)] = name;
		}

		// second pass builds the graph by testing if they fit the word ladder description
		// and adding the edge, if fitting
		G = new Digraph(st.size());
		for ( String v : st )
			for ( String w : st )
				if (!v.equals(w) && StringComparator.compareChars(v, w))
					G.addEdge(st.get(v), st.get(w));

	}

	public boolean contains(String s) {
		return st.contains(s);
	}

	public int index(String s) {
		return st.get(s);
	}

	public String name(int v) {
		return keys[v];
	}

	public Digraph G() {
		return G;
	}
	
	// Returns the number of edges from the source s to the destination d
	public int getPath(String s, String d) {
		int sInd = index(s);
		int dInd = index(d);
		bfs = new BreadthFirstDirectedPaths(G, sInd);
		if (bfs.hasPathTo(dInd))
			return bfs.distTo(dInd);
		else
			return -1;
	}
	
	@Override
	public String toString() {
		String s = G.V() + " " + G.E() + "\n";
		for (String string : st) {
			s += string + "\n";
			int index = index(string);
			for (int v : G.adj(index))
				s += "    " + name(v) + "\n";
		}
		return s;
	}


	public static void main(String[] args) {
		String filename  = args[0];
		String delimiter = args[1];
		SymbolDigraph sg = new SymbolDigraph(filename, delimiter);
		Digraph G = sg.G();
		while (StdIn.hasNextLine()) {
			String source = StdIn.readLine();
			int s = sg.index(source);
			for (int v : G.adj(s)) {
				StdOut.println("   " + sg.name(v));
			}
		}
	}
}
