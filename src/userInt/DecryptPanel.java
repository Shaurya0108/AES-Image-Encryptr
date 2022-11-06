package userInt;

import java.awt.Label;

import javax.swing.*;

public class DecryptPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	JButton cancel, selectFile, decrypt;
	Label selectlbl;
	
	public DecryptPanel() {
		cancel = new JButton("Cancel");
		selectFile = new JButton("Select File(s)");
		decrypt = new JButton("Begin decryption");
		selectlbl = new Label("Select file(s) to be decrypted");

		setLayout(null);
		selectlbl.setBounds(25, 0, 160, 50);
		add(selectlbl);

		cancel.setBounds(25, 200, 100, 25);
		selectFile.setBounds(160, 200, 125, 25);
		decrypt.setBounds(325, 200, 140, 25);
		
		add(cancel);
		add(selectFile);
		add(decrypt);
	}
	
}

