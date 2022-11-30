/*This module runs when the program first starts.
 * The login window is a JDialog that opens first with the main menu running in the background.
 * Once the login window closes the main program will show and the rest of the modules can be accessed.
 * */

package userInt.mainMenu;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.event.*;
import java.util.AbstractMap;

public class MainUI extends JFrame{

	private static final long serialVersionUID = 1L;
	private static MainMenu mainMenu;
	private LoginWelcomePage login;
	public AbstractMap.SimpleEntry<String, SecretKey> keyPair;
	private static EncryptFile encryptFile;
	private static DecryptFile decryptFile;
	private static KeyManager keyManager;
	private static ViewFile viewFile = new ViewFile("View Encrypted Files");

	//This will run first before the main program. 
	public MainUI () {
		login = new LoginWelcomePage(this, true);
		login.setVisible(true);
		encryptFile = new EncryptFile("Encrypt File", this);
		decryptFile = new DecryptFile("Decrypt File", this);
		keyManager = new KeyManager(this);
	}

	public static void main (String [] args) {
		//Main program opens when the user successfully logs in
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JFrame start = new MainUI();
				start.setTitle("SecureCrypt File Encrypter 0.1.3");
				start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				start.setSize(400,400);

				mainMenu = new MainMenu();
				start.add(mainMenu);
				start.setJMenuBar(mainMenu.optionsBar);		//Sets a menu bar for extra options
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
//				mainMenu.viewFile.addActionListener(new ActionListener() {
//
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						viewFile.setVisible(true);
//
//					}
//				});

				mainMenu.keyMan.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						keyManager.setVisible(true);
					}
				});

			}
		});
	}

	public void updateKeyPair(AbstractMap.SimpleEntry<String, SecretKey> keyPair) {
		this.keyPair = keyPair;
	}

	public SecretKey getSecretKey() {
		return keyPair.getValue();
	}

	public boolean loginExists() {
		return login != null;
	}

}
