//Panel to add buttons and list for view window
package userInt.mainMenu;

import java.awt.Label;

import javax.swing.*;

public class ViewPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public JButton cancel;
	Label viewLabel;
	DefaultListModel listModel;
	JList files;
	JScrollPane fileScroll;
	
	public ViewPanel() {

		setLayout(null);

		listModel = new DefaultListModel<String>();
		files = new JList<String>(listModel);
		fileScroll = new JScrollPane();
		fileScroll.getViewport().setView(files);
		fileScroll.setBounds(25, 50, 440,100);
		add(fileScroll);

		viewLabel = new Label("View and edit files");
		viewLabel.setBounds(25, 0, 160, 50);
		add(viewLabel);

		cancel = new JButton("Cancel");
		cancel.setBounds(25, 200, 100, 25);
		add(cancel);
	}
	
}
