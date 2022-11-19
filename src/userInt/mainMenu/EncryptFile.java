/*Module which selects a file and runs the encryption algorithm.
 * Can allow multiple files to be selected, and each file will be encrypted in succession.
 * */

package userInt.mainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import encryption.Encryption;

public class EncryptFile extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final String CIPHER = "AES/CBC/PKCS5Padding";
	EncryptPanel encryptPanel;
	File file;
	String name;
	long size;
	
	public EncryptFile() {
		setTitle("Encrypt File");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
	}
	
	public EncryptFile(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500,300);
		setLocationRelativeTo(null);				//Opens in the center of screen
		setVisible(false);							//Will open when selected from main menu
		
		encryptPanel = new EncryptPanel();
		add(encryptPanel);
		
		encryptPanel.cancel.addActionListener(this);
		encryptPanel.selectFile.addActionListener(this);		//Opens select file dialog using JFileChooser
		encryptPanel.encrypt.addActionListener(this);


	}
	
	public static void main(String[] args) {
		new EncryptFile("Encrypt File");
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		//Close the window without exiting program
		if(e.getSource() == encryptPanel.cancel) {
			dispose();
		}
		//Select a file
		if(e.getSource() == encryptPanel.selectFile) {
			JFileChooser selFileDialog = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG, JPG", "png", "jpg"); //File must be a PNG or JPG file (can add more types later)
			selFileDialog.setFileFilter(filter);
			selFileDialog.setBounds(0, 7, 500, 300);
			switch (selFileDialog.showOpenDialog(this)) {
				case JFileChooser.APPROVE_OPTION:
					file = selFileDialog.getSelectedFile();
					BufferedImage readImage;					//BufferedImage reader for reading image files
					try {
						readImage = ImageIO.read(file);
						//encryptPanel.listModel.insertElementAt(file.getName(), 0);
						if (readImage != null) {
							name = file.getName();
							size = file.length() / (1024 * 1024);
							encryptPanel.listModel.insertElementAt(name, 0);
							System.out.println("Name: " + name + "\nSize: " + size + " MB\n");				//For debugging getting file info
						}
						else
							System.out.println("File is null");
					} catch (IOException e1) {
						System.out.println("File could not be read.");
						e1.printStackTrace();
					}
					
			}
						
		}
		if(e.getSource() == encryptPanel.encrypt) {
			Encryption encryptFile = null;
			
			String abosPath = file.getAbsolutePath();

			File tempDir = new File(abosPath);
			File encryptedFile = new File(tempDir.toPath().toString() + ".aes");
			
			
			SecretKey key = null;
			try {
				key = getKey(128);
				Test testKey = new Test();
				testKey.testKey = key;
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			IvParameterSpec iv = generateIv();
			Test testIv = new Test();
			testIv.testIv = iv;
				
			if(file == null) {
				System.out.println("No files found");
			}
			
			try {
				encryptFile.encryptFile(CIPHER, key, iv, file, encryptedFile);
			} catch (InvalidKeyException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchPaddingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvalidAlgorithmParameterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (BadPaddingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalBlockSizeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			/*FileDialog saveFile = new FileDialog(this, "Save", FileDialog.SAVE);
			saveFile.setVisible(true);
			String path = saveFile.getDirectory() + saveFile.getFile();
			File f = new File(path);
			try {
				f.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			
			
			file.delete();
			
			dispose();
		}
		
	}
	
	/**Generates a psuedo-value to be used to initialize an encryption method
	 * 
	 * @return IvParameterSpec(iv)
	 */
	public static IvParameterSpec generateIv() {
		byte [] iv  = new byte[16];
		new SecureRandom().nextBytes(iv);
		return new IvParameterSpec(iv);
	}
	
	/**
	 * Generates a SecretKey to be used for GCM encryption method
	 * @param n is the number of bits to generate the key (default is 128)
	 * @return originalKey
	 * @throws NoSuchAlgorithmException
	 */
	private static SecretKey getKey(int n) throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(n);
		SecretKey originalKey = keyGenerator.generateKey();
		return originalKey;
	}
}
