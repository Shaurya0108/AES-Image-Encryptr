package userInt;

import java.awt.Label;

import javax.swing.*;

public class ViewPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	JButton cancel;
	Label viewLabel;
	DefaultListModel listModel;
	JList files;
	
	public ViewPanel() {
		cancel = new JButton("Cancel");
		viewLabel = new Label("View and edit files");
		listModel = new DefaultListModel<String>();
		listModel.addElement("Test");
		files = new JList<String>(listModel);
		
		setLayout(null);
		
		files.setBounds(25, 100, 50,50);
		add(files);

		viewLabel.setBounds(25, 0, 160, 50);
		add(viewLabel);

		cancel.setBounds(25, 200, 100, 25);
		add(cancel);
	}
	
}
