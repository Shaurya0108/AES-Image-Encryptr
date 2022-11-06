package userInt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//Login Page needs to be a JDialog for it to appear before the main menu
public class LoginWelcomePage extends JDialog{
	private static final long serialVersionUID = 1L;
	JButton loginBtn = new JButton("Log In");
	JButton closeBtn = new JButton("Close");
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
		this (null, true);
	}
	public LoginWelcomePage(final JFrame parent, boolean modal) {
		super(parent, modal); //Parent frame is the main UI, the login window will appear first
		
		uNameLabel.setBounds(50, 100, 75, 25);
		pWordLabel.setBounds(50, 150, 75, 25);
		
		mLabel.setBounds(120, 250, 250, 35);
		mLabel.setFont(new Font(null, Font.ROMAN_BASELINE, 20));
		
		uName.setBounds(130,100,200,25);
		pWord.setBounds(130,150,200,25);
		
		loginBtn.setBounds(130,200,100,25);
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					parent.setVisible(true);
					setVisible(false);
			}
		});
		loginBtn.setFocusable(false);
		
		closeBtn.setBounds(230,200,100,25);
		closeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					setVisible(false);
					parent.dispose();
					System.exit(0);
			}
			
		});
		closeBtn.setFocusable(false);
		
		add(mLabel);
		add(uNameLabel);
		add(pWordLabel);
		add(uName);
		add(pWord);
		add(loginBtn);
		add(closeBtn);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(415,415);
		setLayout(null);
		setLocationRelativeTo(null);
		
	}
}