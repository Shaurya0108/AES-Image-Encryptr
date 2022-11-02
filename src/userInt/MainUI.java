package userInt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	MainMenu mainMenu;
	private EncryptFile encryptFile = new EncryptFile("Encrypt File");
	private DecryptFile decryptFile = new DecryptFile("Decrypt File");
	
	public MainUI () {
		setTitle("SecureCrypt File Encryptor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		pack();
	}

	public MainUI(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,400);
		
		mainMenu = new MainMenu();
		add(mainMenu);
		setLocationRelativeTo(null);
		setVisible(true);
		
		mainMenu.encOpt.addActionListener(this);
		mainMenu.decOpt.addActionListener(this);
		mainMenu.viewFile.addActionListener(this);
	}

	public static void main (String [] args) {
		 new MainUI("SecureCrypt File Encryptor 0.1"); //Change version number for now, about window will be available soon.
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mainMenu.encOpt) {
			encryptFile.setVisible(true);
		}
		if(e.getSource() == mainMenu.decOpt) {
			decryptFile.setVisible(true);
		}
		if(e.getSource() == mainMenu.viewFile) {
			
		}
	}

}
