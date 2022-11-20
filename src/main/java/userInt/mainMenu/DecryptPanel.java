//Panel to add buttons and list for decryption window
package userInt.mainMenu;

import java.awt.Label;

import javax.swing.*;

public class DecryptPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public JButton cancel, selectFile, decrypt;
	Label selectlbl;
	DefaultListModel listModel;
	JList files;
	public JScrollPane fileScroll;
	
	public DecryptPanel() {
		cancel = new JButton("Cancel");
		selectFile = new JButton("Select File(s)");
		decrypt = new JButton("Begin decryption");
		selectlbl = new Label("Select file(s) to be decrypted");
		
		listModel = new DefaultListModel<String>();

		files = new JList<String>(listModel);
		fileScroll = new JScrollPane();
		fileScroll.getViewport().setView(files);

		setLayout(null);
		
		fileScroll.setBounds(25, 50, 440,100);
		add(fileScroll);
		
		selectlbl.setBounds(25, 0, 160, 50);
		add(selectlbl);

		cancel.setBounds(25, 200, 100, 25);
		selectFile.setBounds(160, 200, 125, 25);
		decrypt.setBounds(325, 200, 140, 25);
		
		add(cancel);
		add(selectFile);
		add(decrypt);
	}

	public void addToList(String name, int index) {
		listModel.insertElementAt(name, index);
	}

	public int filesInList() {
		return listModel.getSize();
	}
	
}

