
public class StringComparator {

	public static boolean compareChars(String v, String w) {
		char[] vChar = v.toCharArray();
		int vTrue = 0;
		char[] wChar = w.toCharArray();
		boolean[] wBool = new boolean[wChar.length];

		for( int cV = vChar.length - 4; cV < vChar.length; cV++ )
			for( int cW = 0; cW < wChar.length; cW++ ) {
				// If the char isn't used in w, and it is existing in w
				if (!wBool[cW] && wChar[cW] == vChar[cV]) {
					// Set used in w to true and increment vTrue so it displays how many occurences there is found
					wBool[cW] = true;
					vTrue += 1;
					break;
				}
			}

		return vTrue == 4;
	}
}
