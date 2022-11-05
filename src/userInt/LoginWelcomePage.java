package userInt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginWelcomePage extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	JFrame frm = new JFrame();
	JButton loginBtn = new JButton("Log In");
	JButton clearBtn = new JButton("Clear");
	JTextField uName = new JTextField();
	JPasswordField pWord = new JPasswordField();
	JLabel mLabel = new JLabel("Welcome to Securecrypt!");
	JLabel uNameLabel = new JLabel("Username: ");
	JLabel pWordLabel = new JLabel("Password: ");
	
	/* constructor for LoginWelcomePage, creates and sizes text fields and 
	 * labels for the user to enter their username and password, and two 
	 * buttons for logging in and clearing input
	 */
	public LoginWelcomePage() {
		
		uNameLabel.setBounds(50, 100, 75, 25);
		pWordLabel.setBounds(50, 150, 75, 25);
		
		mLabel.setBounds(120, 250, 250, 35);
		mLabel.setFont(new Font(null, Font.ROMAN_BASELINE, 20));
		
		uName.setBounds(130,100,200,25);
		pWord.setBounds(130,150,200,25);
		
		loginBtn.setBounds(130,200,100,25);
		loginBtn.addActionListener(this);
		loginBtn.setFocusable(false);
		
		clearBtn.setBounds(230,200,100,25);
		clearBtn.addActionListener(this);
		clearBtn.setFocusable(false);
		
		frm.add(mLabel);
		frm.add(uNameLabel);
		frm.add(pWordLabel);
		frm.add(uName);
		frm.add(pWord);
		frm.add(loginBtn);
		frm.add(clearBtn);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setSize(415,415);
		frm.setLayout(null);
		frm.setVisible(true);	
		
	}
	
	@Override  //overrides ActionListener's default actionPerformed() method
	/* Takes an ActionEvent object that holds the user's input/action for "login" 
	 * or "clear" and continues based on that action. "Clear" clears the username
	 * and password text fields whereas "login" opens up the main menu and closes 
	 * the login/welcome page
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == clearBtn) {
			uName.setText("");
			pWord.setText("");
		}
		
		if(e.getSource() == loginBtn) {
			MainUI mUI = new MainUI("SecureCrypt File Encryptor 0.1.2");
			frm.dispose();
		}
	}
}