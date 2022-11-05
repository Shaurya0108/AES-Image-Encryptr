package userInt;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class EncryptFile extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	EncryptPanel encryptPanel;
	File file;
	String name;
	long size;
	//DataPanel dataPanel;
	
	public EncryptFile() {
		setTitle("Encrypt File");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
	}
	
	public EncryptFile(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500,300);
		setLocationRelativeTo(null);
		setVisible(false);							//Will open when selected from main menu
		
		encryptPanel = new EncryptPanel();
		//dataPanel = new DataPanel();
		
		
		add(encryptPanel);
		
		encryptPanel.cancel.addActionListener(this);
		encryptPanel.selectFile.addActionListener(this);		//Opens select file dialog using JFileChooser
		encryptPanel.encrypt.addActionListener(this);
	}
	
	public static void main(String[] args) {
		new EncryptFile("Encrypt File");
	}
	
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
		if(e.getSource() == encryptPanel.encrypt) {
			//AES Encryption and Save Dialog should go here, or function to call these 
		}
		
	}
}
