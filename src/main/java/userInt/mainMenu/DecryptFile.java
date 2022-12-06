package userInt.mainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import encryption.ConvertBytes;
import encryption.Encryption;

/**Module which selects a file and runs the decryption algorithm.
* It is similar to the encryption module, except this will decrypt the file.
* @author Alan
*/
public class DecryptFile extends JFrame implements ActionListener{

	private static final byte[] IV_DATA = {
			(byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03,
			(byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03,
			(byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03,
			(byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03,
	};

	private static final long serialVersionUID = 1L;
	private static final String CIPHER = "AES/CBC/PKCS5Padding";
	public DecryptPanel decryptPanel;
	MainUI mainUI;
	File file;
	String name;
	long size;
	
	/**Constructor for the "Decrypt File" menu.
	 * Uses JFileChooser for opening and reading files
	 * @param title is the name of the window
	 */
	public DecryptFile(String title, MainUI mainUI) {
		this.mainUI = mainUI;
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500,300);
		setLocationRelativeTo(null);
		setVisible(false);
		
		decryptPanel = new DecryptPanel();
		
		add(decryptPanel);
		
		decryptPanel.cancel.addActionListener(this);
		decryptPanel.selectFile.addActionListener(this);
		decryptPanel.decrypt.addActionListener(this);
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == decryptPanel.cancel) {
			dispose();
		}
		if(e.getSource() == decryptPanel.selectFile) {
			JFileChooser selFileDialog = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("AES", "aes"); //File type for AES files is .aes? Change if necessary.
			selFileDialog.setFileFilter(filter);
			selFileDialog.setBounds(0, 7, 500, 300);
			switch (selFileDialog.showOpenDialog(this)) {
				case JFileChooser.APPROVE_OPTION:
					file = selFileDialog.getSelectedFile();
					BufferedImage readImage;
					try {
						readImage = ImageIO.read(file);
						decryptPanel.addToList(file.getName(), 0);
						if (readImage != null) {
							name = file.getName();
							size = file.length() / (1024 * 1024);
							System.out.println("Name: " + name + "\nSize: " + size + " MB\n");				//For debugging getting file info
							//decryptPanel.listModel.insertElementAt(name, 0);
						}
					} catch (IOException e1) {
						System.out.println("File could not be read.");
						e1.printStackTrace();
					}
			}
				
		}
		if(e.getSource() == decryptPanel.decrypt) {
			//AES decryption and save file dialog go here, or functions to call these
			ConvertBytes unencryptImage, revertToFile;
			Encryption decryptFile = new Encryption();
			Test test;
			
			String abosPath = file.getAbsolutePath();

			File tempDir = new File(abosPath);
			File decryptedFile = new File(tempDir.toPath().toString().replace(".aes", ""));

			SecretKey key = mainUI.getSecretKey();
			IvParameterSpec iv = new IvParameterSpec(IV_DATA);

			if(file == null) {
				System.out.println("No files found");
			}
			
			try {
				decryptFile.decryptFile(CIPHER, key, iv, file, decryptedFile);
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
			
			file.delete();
			dispose();
		}
		
		
	}
}
