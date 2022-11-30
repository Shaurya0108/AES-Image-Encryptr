package userInt.mainMenu;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.AbstractMap;

public class KeyPanel extends JPanel {

    public JButton cancel;
    public DefaultListModel listModel;
    JList files;
    public JScrollPane fileScroll;
    JTextField keyField;
    MainUI mainUI;
    ListSelectionModel selectionModel;

    KeyPanel(MainUI mainUI) {
        this.mainUI = mainUI;
        cancel = new JButton("Cancel");
        listModel = new DefaultListModel();
        files = new JList(listModel);
        fileScroll = new JScrollPane();
        fileScroll.getViewport().setView(files);
        keyField = new JTextField("Enter new key");
        selectionModel = files.getSelectionModel();

        keyField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MessageDigest digest = MessageDigest.getInstance("MD5");
                    byte[] bytes = digest.digest(keyField.getText().getBytes(StandardCharsets.UTF_8));
                    SecretKey key = new SecretKeySpec(bytes, 0, 16, "AES");
                    AbstractMap.SimpleEntry<String, SecretKey> keyPair = new AbstractMap.SimpleEntry(keyField.getText(), key);
                    listModel.addElement(keyPair);
                } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                    noSuchAlgorithmException.printStackTrace();
                }
            }
        });

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    if (files.getSelectedIndex() == -1) {
                        mainUI.updateKeyPair(new AbstractMap.SimpleEntry<>("", null));
                    } else {
                        mainUI.updateKeyPair((AbstractMap.SimpleEntry<String, SecretKey>) files.getSelectedValue());
                    }
                    System.out.println(mainUI.getSecretKey());
                }
            }
        });

        setLayout(null);

        keyField.setBounds(25,25,440,25);
        add(keyField);

        fileScroll.setBounds(25, 50, 440,100);
        add(fileScroll);

        cancel.setBounds(25, 200, 100, 25);
        add(cancel);
    }

}

