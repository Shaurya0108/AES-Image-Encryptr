package userInt;

import javax.swing.*;
import java.awt.*;

public class EncryptPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	JButton cancel, selectFile, encrypt;
	Label selectlbl;
	DefaultListModel listModel;
	JList files;
	
	public EncryptPanel() {
		cancel = new JButton("Cancel");
		selectFile = new JButton("Select File(s)");
		encrypt = new JButton("Begin Encryption");
		selectlbl = new Label("Select file(s) to be encrypted");
		listModel = new DefaultListModel<String>();
		listModel.addElement("Test");
		files = new JList<String>(listModel);
		
		setLayout(null);

		files.setBounds(25, 100, 50,50);
		add(files);

		selectlbl.setBounds(25, 0, 160, 50);
		add(selectlbl);

		cancel.setBounds(25, 200, 100, 25);
		selectFile.setBounds(160, 200, 125, 25);
		encrypt.setBounds(325, 200, 140, 25);
		
		add(cancel);
		add(selectFile);
		add(encrypt);
	}
	
	
}
