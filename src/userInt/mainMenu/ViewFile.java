/*Module which allows for encrypted files to be viewed from a specified location on the user's hard drive
 * 
 * */

package userInt.mainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;

public class ViewFile extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	ViewPanel viewPanel;
	File file;
	String name;
	long size;
	
	public ViewFile() {
		setTitle("View Encrypted Files");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
	}
	
	public ViewFile(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500,300);
		setLocationRelativeTo(null);
		setVisible(false);
		
		viewPanel = new ViewPanel();
		
		add(viewPanel);
		
		viewPanel.cancel.addActionListener(this);
		
	}

	public static void main(String[] args) {
		new ViewFile("View Encrypted Files");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == viewPanel.cancel) {
			dispose();
		}
		
	}
}
