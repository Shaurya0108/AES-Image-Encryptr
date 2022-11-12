/*Module which selects a file and runs the encryption algorithm.
 * Can allow multiple files to be selected, and each file will be encrypted in succession.
 * */

package userInt.mainMenu;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
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

public class EncryptFile extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final String CIPHER = "AES";
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
						if (readImage != null) {
							name = file.getName();
							size = file.length() / (1024 * 1024);
							encryptPanel.listModel.insertElementAt(name, 0);
							System.out.println("Name: " + name + "\nSize: " + size + " MB\n");				//For debugging getting file info
						}
					} catch (IOException e1) {
						System.out.println("File could not be read.");
						e1.printStackTrace();
					}
					
			}
						
		}
		if(e.getSource() == encryptPanel.encrypt) {
			//AES Encryption should go here, or function to call it
			ConvertBytes imgConvert, revertToFile;
			Key encryptKey;
			File encryptedFile;
			
			if(file == null) {
				System.out.println("No files found");
			}
			
			byte[] bytes = null;
			
			imgConvert = new ConvertBytes();
			try {
				bytes = imgConvert.getBytes(file);
			} catch (FileNotFoundException e1) {
				System.out.println("Could not locate file");
				e1.printStackTrace();
			} catch (IOException e1) {
				System.out.println("An error occured, could not convert file");
				e1.printStackTrace();
			}
			
			encryptKey = getRandomKey(CIPHER, 128);
			
			AES encryptAES = new AES();
			byte[] encryptedFileBytes = encryptAES.encrypt(bytes, encryptKey.getEncoded());
			
			revertToFile = new ConvertBytes();
			encryptedFile = file;
			try {
				revertToFile.revertToFile(encryptedFileBytes, encryptedFile);	//Using the bytes of the encrypted file, it will convert back to a file
			} catch (IOException e2) {
				System.out.println("Unable to convert back to file");
				e2.printStackTrace();
			}				
			
			FileDialog saveFile = new FileDialog(this, "Save", FileDialog.SAVE);
			saveFile.setVisible(true);
			String path = saveFile.getDirectory() + saveFile.getFile();
			File f = new File(path);
			try {
				f.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			/*JFileChooser saveFileDialog = new JFileChooser();
			FileNameExtensionFilter saveFil = new FileNameExtensionFilter("PNG, JPG", "png", "jpg"); //Can be replaced with other file types
			saveFileDialog.setFileFilter(saveFil);
			saveFileDialog.setBounds(0, 7, 500, 300);
			switch(saveFileDialog.showSaveDialog(this)) {
			case JFileChooser.APPROVE_OPTION:
				try {
					FileWriter fw = new FileWriter(encryptedFile+".png");
					fw.write(encryptedFile.toString());
					fw.close();
					
				} catch (IOException e1) {
					System.out.println("Unable to save file");
					e1.printStackTrace();
				}
				
			}*/
			dispose();
		}
		
	}
	
	private static Key getRandomKey(String cipher, int keySize) {
		byte[] randomKeyBytes = new byte[keySize / 8];
		Random random = new Random();
		random.nextBytes(randomKeyBytes);
		return new SecretKeySpec(randomKeyBytes, cipher);
	}
}
