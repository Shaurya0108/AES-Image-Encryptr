package userInt.mainMenu;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Key;
import java.util.Random;

import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import encryption.AES;
import encryption.ConvertBytes;
import userInt.mainMenu.EncryptFile;

/**Module which selects a file and runs the decryption algorithm.
* It is similar to the encryption module, except this will decrypt the file.
* @author Alan
*/
public class DecryptFile extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private static final String CIPHER = "AES";
	DecryptPanel decryptPanel;
	
	File file;
	String name;
	long size;
		
	/**Default constructor for opening the "Decrypt File" menu.
	 * 
	 */
	public DecryptFile() {
		setTitle("Decrypt File");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
	}
	
	/**Constructor for the "Decrypt File" menu.
	 * Uses JFileChooser for opening and reading files
	 * @param title is the name of the window
	 */
	public DecryptFile(String title) {
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
	
	public static void main(String[] args) {
		new DecryptFile("Decrypt File");
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == decryptPanel.cancel) {
			dispose();
		}
		if(e.getSource() == decryptPanel.selectFile) {
			JFileChooser selFileDialog = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG, JPG", "png", "jpg"); //File type for AES files is .aes? Change if necessary.
			selFileDialog.setFileFilter(filter);
			selFileDialog.setBounds(0, 7, 500, 300);
			switch (selFileDialog.showOpenDialog(this)) {
				case JFileChooser.APPROVE_OPTION:
					file = selFileDialog.getSelectedFile();
					BufferedImage readImage;
					try {
						readImage = ImageIO.read(file);
						decryptPanel.listModel.insertElementAt(file.getName(), 0);
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
			Key decryptKey;
			File decryptedFile;
			
			if(file == null) {
				System.out.println("No files found");
			}
			
			byte[] bytes = null;
			
			unencryptImage = new ConvertBytes();
			try {
				bytes = unencryptImage.getBytes(file);
			} catch (FileNotFoundException e1) {
				System.out.println("Could not locate file");
				e1.printStackTrace();
			} catch (IOException e1) {
				System.out.println("An error occured. Could not convert file.");
				e1.printStackTrace();
			}
			
			Test keys = new Test();
			decryptKey = keys.getKey();
			
			AES encryptAES = new AES();
			byte[] decryptedFileBytes = encryptAES.decrypt(bytes, decryptKey.getEncoded());
			
			revertToFile = new ConvertBytes();
			decryptedFile = file;
			try {
				revertToFile.revertToFile(decryptedFileBytes, decryptedFile);			 //Using the bytes of the encrypted file, it will convert back to a file
			} catch (IOException e2) {
				System.out.println("Unable to convert back to file");
				e2.printStackTrace();
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
			}
			
			JFileChooser saveFileDialog = new JFileChooser();
			FileNameExtensionFilter saveFil = new FileNameExtensionFilter("PNG, JPG", "png", "jpg"); //Can be replaced with other file types
			saveFileDialog.setFileFilter(saveFil);
			saveFileDialog.setBounds(0, 7, 500, 300);
			switch(saveFileDialog.showSaveDialog(this)) {
			case JFileChooser.APPROVE_OPTION:
				try {
					FileWriter fw = new FileWriter(decryptedFile+".png");
					fw.write(decryptedFile.toString());
					fw.close();
				} catch (IOException e1) {
					System.out.println("Unable to save file");
					e1.printStackTrace();
				}
				
			}*/
			dispose();
		}
		
		
	}
}
