package userInt;

import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.*;

public class DecryptPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	JButton cancel, selectFile, decrypt;
	ButtonGroup ecrGrp;
	Label selectlbl;
	
	public DecryptPanel() {
		cancel = new JButton("Cancel");
		selectFile = new JButton("Select File(s)");
		decrypt = new JButton("Begin decryption");
		selectlbl = new Label("Select file(s) to be decrypted");
		
		
		setLayout(new GridLayout(3,1));
		add(selectlbl);
		//add(dataToEncr);
		//add(scroll, BorderLayout.EAST);
		//setLayout(new FlowLayout());
		ecrGrp = new ButtonGroup();
		ecrGrp.add(cancel);
		ecrGrp.add(selectFile);
		ecrGrp.add(decrypt);
		
		add(cancel);
		add(selectFile);
		add(decrypt);
	}
	
}

