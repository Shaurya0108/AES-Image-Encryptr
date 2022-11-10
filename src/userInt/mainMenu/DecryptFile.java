/*Module which selects a file and runs the decryption algorithm.
 * It is similar to the encryption module, except this will decrypt the file.
 * */

package userInt.mainMenu;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DecryptFile extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	DecryptPanel decryptPanel;
	
	File file;
	String name;
	long size;
	
	//JFileChooser selFileDialog;
	
	public DecryptFile() {
		setTitle("decrypt File");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
	}
	
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
						if (readImage != null) {
							name = file.getName();
							size = file.length() / (1024 * 1024);
							System.out.println("Name: " + name + "\nSize: " + size + " MB\n");				//For debugging getting file info
						}
					} catch (IOException e1) {
						System.out.println("File could not be read.");
						e1.printStackTrace();
					}
			}
				
		}
		if(e.getSource() == decryptPanel.decrypt) {
			//AES decryption and save file dialog go here, or functions to call these
		}
		
	}
}
