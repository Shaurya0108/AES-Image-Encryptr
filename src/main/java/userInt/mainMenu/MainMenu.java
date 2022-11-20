/*This module sets up the menu objects including buttons and menu bars.
 * Adds functionality to the program including accessing encryption and decryption modules.
 * 
 * */
package userInt.mainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

public class MainMenu extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public JButton encOpt, decOpt, viewFile; //Three main options: encrypt, decrypt and view
	public JMenuBar optionsBar;
	JMenu accountOptions, accountSub, encryptOptions, encryptSub;
	public JMenuItem createAcc, manAcc, changeAcc, saveOption;
	public JRadioButtonMenuItem save, replace;
	
	
	public MainMenu() {
		encOpt = new JButton("Encrypt File");
		decOpt = new JButton("Decrypt File");
		viewFile = new JButton("View Encrypted Files");
		
		optionsBar = new JMenuBar();						//Main menu bar
		
		//Account options
		accountOptions = new JMenu("Account");
		accountOptions.setMnemonic(KeyEvent.VK_A);
		accountOptions.getAccessibleContext().setAccessibleDescription("Create and manage accounts to view encrypted files");
		optionsBar.add(accountOptions);
		//Account sub options
		createAcc = new JMenuItem("Create Account", KeyEvent.VK_C);									   //Key press
		createAcc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK)); //Alt command to access option
		createAcc.getAccessibleContext().setAccessibleDescription("Create an account");
		createAcc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		accountOptions.add(createAcc);
		manAcc = new JMenuItem("Manage Account", KeyEvent.VK_E);									   //Key press
		manAcc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK)); //Alt command to access option
		manAcc.getAccessibleContext().setAccessibleDescription("Edit an account");
		manAcc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		accountOptions.add(manAcc);
		changeAcc = new JMenuItem("Switch Account");
		changeAcc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
		changeAcc.getAccessibleContext().setAccessibleDescription("Login to another account");
		changeAcc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		accountOptions.add(changeAcc);

		//Settings option
		encryptOptions = new JMenu("Config");
		encryptOptions.setMnemonic(KeyEvent.VK_C);
		encryptOptions.getAccessibleContext().setAccessibleDescription("Change settings for encryption/decryption");
		optionsBar.add(encryptOptions);
		//saving sub option
		saveOption = new JMenu("Save/Replace");
		saveOption.getAccessibleContext().setAccessibleDescription("Choose whether the software saves or replaces the original file after encryption");
		ButtonGroup saveGroup = new ButtonGroup();					//Button group allows for only one option to be selected.
		save = new JRadioButtonMenuItem("Save file");
		save.setSelected(true);
		replace = new JRadioButtonMenuItem("Replace file");
		saveGroup.add(save);
		saveGroup.add(replace);
		saveOption.add(save);
		saveOption.add(replace);
		encryptOptions.add(saveOption);
		
		setLayout(null);
		//Custom placement of buttons
		encOpt.setBounds(75, 60, 220, 50);
		decOpt.setBounds(75, 158, 220, 50);
		viewFile.setBounds(75, 255, 220, 50);
		
		add(encOpt);
		add(decOpt);
		add(viewFile);
	}


}
