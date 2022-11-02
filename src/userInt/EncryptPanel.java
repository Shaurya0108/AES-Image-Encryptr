package userInt;

import javax.swing.*;
import java.awt.*;

public class EncryptPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	JButton cancel, selectFile, encrypt;
	ButtonGroup ecrGrp;
	Label selectlbl;
	JTextField dataToEncr; //This will display the filename, size, type and a mini display of the contents
	JScrollBar scroll;
	
	public EncryptPanel() {
		cancel = new JButton("Cancel");
		selectFile = new JButton("Select File(s)");
		encrypt = new JButton("Begin Encryption");
		selectlbl = new Label("Select file(s) to be encrypted");
		dataToEncr = new JTextField();
		
		dataToEncr.setEditable(false);
		
		setLayout(new GridLayout(3,1));
		add(selectlbl);
		//add(dataToEncr);
		//add(scroll, BorderLayout.EAST);
		//setLayout(new FlowLayout());
		ecrGrp = new ButtonGroup();
		ecrGrp.add(cancel);
		ecrGrp.add(selectFile);
		ecrGrp.add(encrypt);
		
		add(cancel);
		add(selectFile);
		add(encrypt);
	}
	
	
}
