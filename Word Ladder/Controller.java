import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class Controller {

	public static void main(String[] args) {
		SymbolDigraph sg = new SymbolDigraph(StdIn.readString()," ");

		try {
			String testFileName = StdIn.readString();
			FileInputStream fstream = new FileInputStream(testFileName);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			
			FileWriter fout = new FileWriter(testFileName.substring(0, testFileName.length()-3) + ".out");
			BufferedWriter bout = new BufferedWriter(fout);
			
			while ((strLine = br.readLine()) != null) {
				String[] s = strLine.split(" ");
				bout.write("" + sg.getPath(s[0], s[1]) + "\n");
			}
			br.close();
			bout.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
