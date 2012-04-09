import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class Controller {

	public static void main(String[] args) {
		SymbolDigraph sg = new SymbolDigraph(StdIn.readString()," ");

		try {
			FileInputStream fstream = new FileInputStream(StdIn.readString());
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			
			while ((strLine = br.readLine()) != null) {
				String[] s = strLine.split(" ");
				System.out.println(sg.getPath(s[0], s[1]));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
