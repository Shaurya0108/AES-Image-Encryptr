package userInt;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.filechooser.*;

public class DecryptFile extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	DecryptPanel decryptPanel;
	
	//JFileChooser selFileDialog;
	
	public DecryptFile() {
		setTitle("decrypt File");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
	}
	
	public DecryptFile(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500,300);
		setLocationRelativeTo(null);
		setVisible(false);
		
		setLayout(new FlowLayout());
		decryptPanel = new DecryptPanel();
		
		add(decryptPanel);
		
		decryptPanel.cancel.addActionListener(this);
		decryptPanel.selectFile.addActionListener(this);
		decryptPanel.decrypt.addActionListener(this);
		//pack();
	}
	
	public static void main(String[] args) {
		new DecryptFile("Decrypt File");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == decryptPanel.cancel) {
			dispose();
		}
		if(e.getSource() == decryptPanel.selectFile) {
			JFileChooser selFileDialog = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG, JPG", "png, jpg");
			selFileDialog.setFileFilter(filter);
			selFileDialog.setBounds(0, 7, 500, 300);
			selFileDialog.showOpenDialog(this);
			/*int returnVal = selFileDialog.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = selFileDialog.getSelectedFile();
			}*/
		}
		if(e.getSource() == decryptPanel.decrypt) {
			
		}
		
	}
}
