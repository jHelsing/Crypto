import java.io.BufferedReader;
import java.io.FileReader;

import javax.xml.bind.DatatypeConverter;

public class CBCXor {

	public static void main(String[] args) {
		String filename = "input.txt";
		byte[] first_block = null;
		byte[] encrypted = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			first_block = br.readLine().getBytes();
			encrypted = DatatypeConverter.parseHexBinary(br.readLine());
			br.close();
		} catch (Exception err) {
			System.err.println("Error handling file.");
			err.printStackTrace();
			System.exit(1);
		}
		String m = recoverMessage(first_block, encrypted);
		System.out.println("Recovered message: " + m);
	}

	/**
	 * Recover the encrypted message (CBC encrypted with XOR, block size = 12).
	 * 
	 * @param first_block
	 *            We know that this is the value of the first block of plain
	 *            text.
	 * @param encrypted
	 *            The encrypted text, of the form IV | C0 | C1 | ... where each
	 *            block is 12 bytes long.
	 */
	private static String recoverMessage(byte[] first_block, byte[] encrypted) {
		byte [] key = first_block;
		byte [] prevC = new byte[12];
		for(int i = 0; i < 12; i++){
			prevC[i] = encrypted[i];
		}
		
		for(int i = 0; i < 12; i++){
			key[i] = (byte) (first_block[i]^encrypted[12+i]^prevC[i]);
		}
		
		for(int i = 12; i < encrypted.length; i +=12){
			byte [] currentC = new byte[12];
			for(int j = 0; j < 12; j++){
				currentC[j] = encrypted[i+j];
				encrypted[i+j] = (byte) ((key[j]^currentC[j])^prevC[j]);
				prevC[j] = currentC[j];
			}
			
		}
		
	
		
		return new String(encrypted);
	}
}
