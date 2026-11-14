package com.mucraft.view.panels;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import com.mucraft.config.AppTheme;

/** Vista de inicio temporal hasta disponer de métricas y permisos del esquema. */
public final class DashboardPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public DashboardPanel() {
        super(new BorderLayout());
        setBackground(AppTheme.BACKGROUND);
        JLabel label = new JLabel("Panel de inicio de Mucraft", SwingConstants.CENTER);
        label.setFont(AppTheme.TITLE);
        add(label, BorderLayout.CENTER);
    }
}
