package encryption;

import java.io.*;

/**Class for converting files into a byte array and vice versa
 */
public class ConvertBytes {
		
	/**This method converts a file into readable bytes
	 * @param f is the file that is being converted
	 * @return Converted bytes into a byte array
	 * @throws FileNotFoundException,  IOException
	 * */
	public static byte[] getBytes(File f) throws FileNotFoundException, IOException {
		System.out.println(f.getName());
		byte[] buffer = new byte[1024]; //Set max bytes to be read
		ByteArrayOutputStream imageBytes = new ByteArrayOutputStream(); //Bytes read from files written here
		FileInputStream fis = new FileInputStream(f);	//File is read from here
		int read;
		
		while((read = fis.read(buffer))!= -1) {
			imageBytes.write(buffer, 0, read);
		}
		fis.close();
		imageBytes.close();
		return imageBytes.toByteArray();
	}
	
	/**
	 * Converts bytes of a file back to its original state.
	 * @param data is the bytes of a file stored in a byte array
	 * @param dest is the variable representing a file to be converted back to
	 * @throws IOException
	 */
	public static void revertToFile(byte[] data, File dest) throws IOException {
		try {
			FileOutputStream toFile = new FileOutputStream(dest);
			toFile.write(data);
			toFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
