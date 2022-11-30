package userInt.mainMenu;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap;

public class KeyManager extends JFrame implements ActionListener {

    public KeyPanel keyPanel;

    KeyManager(MainUI mainUI) {
        setTitle("key manager");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500,300);
        setLocationRelativeTo(null);				//Opens in the center of screen
        setVisible(false);

        keyPanel = new KeyPanel(mainUI);
        add(keyPanel);
        keyPanel.cancel.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == keyPanel.cancel) {
            dispose();
        }
    }
}
