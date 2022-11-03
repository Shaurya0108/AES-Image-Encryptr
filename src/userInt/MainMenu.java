package userInt;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel{
	private static final long serialVersionUID = 1L;
	
	JButton encOpt, decOpt, viewFile; //Three main options: encrypt, decrypt and view
	
	public MainMenu() {
		encOpt = new JButton("Encrypt File");
		decOpt = new JButton("Decrypt File");
		viewFile = new JButton("View Encrypted Files");
		
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
