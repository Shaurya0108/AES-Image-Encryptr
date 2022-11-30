package test;

import userInt.mainMenu.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Tests {

    @Test
    public void loginDisplayed() {
        MainUI main = new MainUI();
        Assertions.assertTrue(main.loginExists());
    }

    @Test
    public void menuDisplayed() {
        MainUI main = new MainUI();
        Assertions.assertTrue(main.isVisible());
    }

    @Test
    public void mainMenuHasSaveOption() {
        MainMenu mainMenu = new MainMenu();
        Assertions.assertTrue(mainMenu.save != null);
    }

    @Test
    public void mainMenuHasOverwriteOption() {
        MainMenu mainMenu = new MainMenu();
        Assertions.assertTrue(mainMenu.replace != null);
    }

    @Test
    public void mainMenuHasCreateAccount() {
        MainMenu mainMenu = new MainMenu();
        Assertions.assertTrue(mainMenu.createAcc != null);
        Assertions.assertTrue(mainMenu.createAcc.getActionListeners() != null);
    }

    @Test
    public void mainMenuHasManageAccount() {
        MainMenu mainMenu = new MainMenu();
        Assertions.assertTrue(mainMenu.manAcc != null);
        Assertions.assertTrue(mainMenu.manAcc.getActionListeners() != null);
    }

    @Test
    public void mainMenuHasSwitchAccount() {
        MainMenu mainMenu = new MainMenu();
        Assertions.assertTrue(mainMenu.changeAcc != null);
        Assertions.assertTrue(mainMenu.changeAcc.getActionListeners() != null);
    }

    @Test
    public void mainMenuHasEncryptFile() {
        MainMenu mainMenu = new MainMenu();
        Assertions.assertTrue(mainMenu.encOpt != null);
        Assertions.assertTrue(mainMenu.encOpt.getActionListeners() != null);
    }

    @Test
    public void mainMenuHasDecryptFile() {
        MainMenu mainMenu = new MainMenu();
        Assertions.assertTrue(mainMenu.decOpt != null);
        Assertions.assertTrue(mainMenu.decOpt.getActionListeners() != null);
    }

    @Test
    public void DecryptFileHasCancel() {
        DecryptPanel decryptPanel = new DecryptPanel();
        Assertions.assertTrue(decryptPanel.cancel != null);
        Assertions.assertTrue(decryptPanel.cancel.getActionListeners() != null);
    }

    @Test
    public void DecryptFileHasSelectFile() {
        DecryptPanel decryptPanel = new DecryptPanel();
        Assertions.assertTrue(decryptPanel.selectFile != null);
        Assertions.assertTrue(decryptPanel.selectFile.getActionListeners() != null);
    }

    @Test
    public void DecryptFileHasEncrypt() {
        DecryptPanel decryptPanel = new DecryptPanel();
        Assertions.assertTrue(decryptPanel.decrypt != null);
        Assertions.assertTrue(decryptPanel.decrypt.getActionListeners() != null);
    }

    @Test
    public void ViewPanelHasCancel() {
        ViewPanel viewPanel = new ViewPanel();
        Assertions.assertTrue(viewPanel.cancel != null);
        Assertions.assertTrue(viewPanel.cancel.getActionListeners() != null);
    }

    @Test
    public void programDisposesOnClose() {
        MainUI main = new MainUI();
        Assertions.assertFalse(main.isVisible());
    }

}
