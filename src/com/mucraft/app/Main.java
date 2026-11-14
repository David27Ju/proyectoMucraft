package com.mucraft.app;

import java.awt.EventQueue;
import javax.swing.UIManager;
import com.mucraft.view.main.MainFrame;

/** Punto de entrada de Mucraft. Toda creación y actualización inicial de Swing
 * ocurre en el Event Dispatch Thread. */
public final class Main {
    private Main() { }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
                // Swing conserva el look and feel predeterminado.
            }
            new MainFrame().setVisible(true);
        });
    }
}
