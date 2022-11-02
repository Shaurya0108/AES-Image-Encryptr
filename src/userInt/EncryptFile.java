package userInt;

import javax.swing.*;
import javax.swing.filechooser.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class EncryptFile extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	EncryptPanel encryptPanel;
	DataPanel dataPanel;
	
	//JFileChooser selFileDialog;
	
	public EncryptFile() {
		setTitle("Encrypt File");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
	}
	
	public EncryptFile(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500,300);
		setLocationRelativeTo(null);
		setVisible(false);
		
		setLayout(new FlowLayout());
		encryptPanel = new EncryptPanel();
		dataPanel = new DataPanel();
		
		
		add(encryptPanel);
		
		encryptPanel.cancel.addActionListener(this);
		encryptPanel.selectFile.addActionListener(this);
		encryptPanel.encrypt.addActionListener(this);
		//pack();
	}
	
	public static void main(String[] args) {
		new EncryptFile("Encrypt File");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == encryptPanel.cancel) {
			dispose();
		}
		if(e.getSource() == encryptPanel.selectFile) {
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
		if(e.getSource() == encryptPanel.encrypt) {
			
		}
		
	}
}
