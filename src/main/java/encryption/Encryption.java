package encryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Encryption {
	
	/**
	 * Will take a selected file and encrypt it using GCM method
	 * @param algorithm is the encryption algorithm being implemented
	 * @param key is the SecretKey that was generated
	 * @param iv is the Initialization Vector, a randomly generated value
	 * @param inputFile is the file being encrypted
	 * @param outputFile is the result of encryption
	 * @throws IOException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */

	public static void encryptFile(String algorithm, SecretKey key, IvParameterSpec iv,
		    File inputFile, File outputFile) throws IOException, NoSuchPaddingException,
		    NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
		    BadPaddingException, IllegalBlockSizeException {
		    
		    Cipher cipher = Cipher.getInstance(algorithm);
		    cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		    FileInputStream inputStream = new FileInputStream(inputFile);
		    FileOutputStream outputStream = new FileOutputStream(outputFile);
		    byte[] buffer = new byte[64];
		    int bytesRead;
		    while ((bytesRead = inputStream.read(buffer)) != -1) {
		        byte[] output = cipher.update(buffer, 0, bytesRead);
		        if (output != null) {
		            outputStream.write(output);
		        }
		    }
		    byte[] outputBytes = cipher.doFinal();
		    if (outputBytes != null) {
		        outputStream.write(outputBytes);
		    }
		    inputStream.close();
		    outputStream.close();
		}
	/**
	 * Will take a selected encrypted file and decrypt it using GCM method
	 * @param algorithm is the encryption algorithm being implemented
	 * @param key is the SecretKey that was generated
	 * @param iv is the Initialization Vector, a randomly generated value
	 * @param inputFile is the file being decrypted
	 * @param outputFile is the result of decryption
	 * @throws IOException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static void decryptFile(String algorithm, SecretKey key, IvParameterSpec iv,
		    File inputFile, File outputFile) throws IOException, NoSuchPaddingException,
		    NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
		    BadPaddingException, IllegalBlockSizeException {
		    
		    Cipher cipher = Cipher.getInstance(algorithm);
		    cipher.init(Cipher.DECRYPT_MODE, key, iv);
		    FileInputStream inputStream = new FileInputStream(inputFile);
		    FileOutputStream outputStream = new FileOutputStream(outputFile);
		    byte[] buffer = new byte[64];
		    int bytesRead;
		    while ((bytesRead = inputStream.read(buffer)) != -1) {
		        byte[] output = cipher.update(buffer, 0, bytesRead);
		        if (output != null) {
		            outputStream.write(output);
		        }
		    }
		    byte[] outputBytes = cipher.doFinal();
		    if (outputBytes != null) {
		        outputStream.write(outputBytes);
		    }
		    inputStream.close();
		    outputStream.close();
		}
}
