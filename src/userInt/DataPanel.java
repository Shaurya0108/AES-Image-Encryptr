package userInt;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DataPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	String filename;
	double size;
	Label file, filesize;
	JLabel displayPic;
	JScrollBar scrollBar;
	
	public DataPanel() {
		
	}
	
	public DataPanel(String fn, double s) {
		filename = fn;
		size = s;
		
		file = new Label(filename);
		//filesize = new Label(size);
		
		
	}
}
