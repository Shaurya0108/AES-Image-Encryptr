package userInt;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;

public class EncryptPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	JButton cancel, selectFile, encrypt, removeButton;
	Label selectlbl;
	
	JLabel dataLabel;
	
	DefaultListModel<EncData> dataList;
	JList<EncData> toBeEncrypted;
	
	JScrollPane scroll;
	
	public EncryptPanel() {
		cancel = new JButton("Cancel");
		selectFile = new JButton("Select File(s)");
		encrypt = new JButton("Begin Encryption");
		removeButton = new JButton();
		selectlbl = new Label("Select file(s) to be encrypted");
		
		PreviewPanel preview = new PreviewPanel(3);			//Contains name, size, and picture to display in preview
		dataList = new DefaultListModel<>();
		toBeEncrypted = new JList<>();
		
		setLayout(null);
		selectlbl.setBounds(25, 0, 160, 50);
		add(selectlbl);
		
		toBeEncrypted.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		toBeEncrypted.setLayoutOrientation(JList.VERTICAL);
		toBeEncrypted.setVisibleRowCount(3);
		toBeEncrypted.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					removeButton.doClick();
				}
			}
		});
		
		PreviewPanel prevPanel = new PreviewPanel(3);
		scroll = new JScrollPane(prevPanel);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		add(toBeEncrypted);
		add(scroll);
		
		
		cancel.setBounds(25, 200, 100, 25);
		selectFile.setBounds(160, 200, 125, 25);
		encrypt.setBounds(325, 200, 140, 25);
		
		add(cancel);
		add(selectFile);
		add(encrypt);
	}
	

}

class PreviewPanel extends JPanel implements Scrollable {
	private int visibleRowcount = 1;
	
	public PreviewPanel(int visibleRowCount) {
		this.visibleRowcount = visibleRowCount;
		setLayout(new GridLayout(0,1));
	}
	
	public void readInfo(EncData ed) {
		add(new DataPanel(ed));
	}
	@Override
	public Dimension getPreferredScrollableViewportSize() {
		if (getComponentCount() > 0) {
			JComponent comp = (JComponent) getComponents()[0];
			int w = getPreferredSize().width;
			int h = visibleRowcount * getPreferredSize().height;
			Dimension d = new Dimension(w, h);
			return d;
		} 
		else {
			return new Dimension(0,0);
		}
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
		if (getComponentCount() > 0) {
			JComponent comp = (JComponent) getComponents()[0];			//Based on how many options are available on screen
			Dimension d = comp.getPreferredSize();
			if (orientation == SwingConstants.VERTICAL) {
				return d.height;
			}
			else {
				return d.width;
			}
		}
		return 0;
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
		if (getComponentCount() > 0) {
			JComponent comp = (JComponent) getComponents()[0];
			Dimension d = comp.getPreferredSize();
			if (orientation == SwingConstants.VERTICAL) {
				return visibleRowcount * d.height;
			}
			else {
				return d.width;
			}
		}
		return 0;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		// TODO Auto-generated method stub
		return false;
	}
	
}

class DataPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private EncData encData;
	private JButton remove = new JButton();
	private JLabel fnDisplay = new JLabel();
	private JLabel fsDisplay = new JLabel();
	private static BufferedImage imageBuffer;
	private String filepath;
	
	public DataPanel() {
		setLayout(new GridBagLayout());
	}
	public DataPanel(EncData ed) {
		this();
		insertData(ed);
	}
	
	public final void insertData(EncData ed) {
		this.encData = ed;
		
		fnDisplay.setText(encData.getFilename());
		//fsDisplay.setText(encData.getSize());
		
		filepath = encData.getPath();
	}
	
	public static ImageIcon getImage(String filepath) {
		File f = new File(filepath);
		BufferedImage image;
		try {
			image = ImageIO.read(f);
			imageBuffer = image;
			ImageIcon icon = new ImageIcon(image);
			return icon;
		} catch (IOException e) {
			System.out.println("Could not obtain image");
			e.printStackTrace();
			return new ImageIcon();
		}
		
	}
}

class EncData {
	
	private String filename, filepath;
	private double size;
	private Label file, filesize;
	private JLabel displayPic;
		
	public EncData(String fn, double s, String fp) {
		filename = fn;
		size = s;
		filepath = fp;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public double getSize() {
		return size;
	}
	
	//Required to obtain the image contents of the file.
	public String getPath() {
		return filepath;
	}
}