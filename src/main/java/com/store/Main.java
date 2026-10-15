package com.store;

import com.store.ui.StoreFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
            catch (Exception ignored) { /* Use the cross-platform default look and feel. */ }
            new StoreFrame().setVisible(true);
        });
    }
}