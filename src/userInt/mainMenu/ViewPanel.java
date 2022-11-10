//Panel to add buttons and list for view window
package userInt.mainMenu;

import java.awt.Label;

import javax.swing.*;

public class ViewPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	JButton cancel;
	Label viewLabel;
	DefaultListModel listModel;
	JList files;
	JScrollPane fileScroll;
	
	public ViewPanel() {
		cancel = new JButton("Cancel");
		viewLabel = new Label("View and edit files");
		listModel = new DefaultListModel<String>();

		files = new JList<String>(listModel);
		fileScroll = new JScrollPane();
		fileScroll.getViewport().setView(files);
		
		setLayout(null);

		fileScroll.setBounds(25, 50, 440,100);
		add(fileScroll);

		viewLabel.setBounds(25, 0, 160, 50);
		add(viewLabel);

		cancel.setBounds(25, 200, 100, 25);
		add(cancel);
	}
	
}
