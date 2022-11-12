
package userInt.mainMenu;

import javax.swing.*;
import java.awt.*;

/**Panel to add buttons and list for encryption window
 * 
 * @author Alan
 *
 */
public class EncryptPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	JButton cancel, selectFile, encrypt;
	Label selectlbl;
	DefaultListModel listModel;
	JList files;
	JScrollPane fileScroll;

	public EncryptPanel() {
		cancel = new JButton("Cancel");
		selectFile = new JButton("Select File(s)");
		encrypt = new JButton("Begin Encryption");
		selectlbl = new Label("Select file(s) to be encrypted");
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
		encrypt.setBounds(325, 200, 140, 25);
		
		add(cancel);
		add(selectFile);
		add(encrypt);
	}
	
	
}
