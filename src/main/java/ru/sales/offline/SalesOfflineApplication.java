package ru.sales.offline;

import ru.sales.offline.gui.AuthDialog;
import ru.sales.offline.gui.main.MainApplicationForm;

public class SalesOfflineApplication {

    public static void main(String[] args) {
        AuthDialog authDialog = new AuthDialog();

        new MainApplicationForm();
    }

}
