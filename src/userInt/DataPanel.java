package userInt;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DataPanel extends JPanel implements Scrollable{
	private static final long serialVersionUID = 1L;
	
	String filename;
	double size;
	Label file, filesize;
	JLabel displayPic;
	JScrollBar scrollBar;
	
	public DataPanel() {
		setLayout(new FlowLayout());
		
	}
	
	public DataPanel(String fn, double s) {
		filename = fn;
		size = s;
		
		file = new Label(filename);
		//filesize = new Label(size);

	}

	@Override
	public Dimension getPreferredScrollableViewportSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		// TODO Auto-generated method stub
		return false;
	}
}
