package userInt;

import javax.swing.*;
import java.awt.event.*;

public class MainUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private static MainMenu mainMenu;
	private LoginWelcomePage login;
	private static EncryptFile encryptFile = new EncryptFile("Encrypt File");
	private static DecryptFile decryptFile = new DecryptFile("Decrypt File");
	private static ViewFile viewFile = new ViewFile("View Encrypted Files");
	
	public MainUI () {
		login = new LoginWelcomePage(this, true);
		login.setVisible(true);
	}
	public static void main (String [] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				 JFrame start = new MainUI();
				 start.setTitle("SecureCrypt File Encrypter 0.1.3");
				 start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 start.setSize(400,400);
				 
				 mainMenu = new MainMenu();
				 start.add(mainMenu);
				 start.setLocationRelativeTo(null);
				 start.setVisible(true);
					
				//Action listener for button to go to encrypt menu
				mainMenu.encOpt.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						encryptFile.setVisible(true);
						
					}
				});
				//Action listener for button to go to decrypt menu
				mainMenu.decOpt.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						decryptFile.setVisible(true);
						
					}
				});
				//Action listener for button to go to view file menu
				mainMenu.viewFile.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						viewFile.setVisible(true);
						
					}
				});
			}
		});
	}
}
