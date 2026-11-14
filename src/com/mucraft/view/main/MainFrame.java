package com.mucraft.view.main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.mucraft.config.AppTheme;
import com.mucraft.config.ViewNames;
import com.mucraft.controller.NavigationController;
import com.mucraft.view.panels.DashboardPanel;
import com.mucraft.view.panels.LoginPanel;

/** Ventana principal única de la aplicación. */
public final class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cards = new JPanel(cardLayout);
    private final NavigationController navigation;

    public MainFrame() {
        super("Mucraft");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 600));
        setSize(1100, 700);
        setLocationRelativeTo(null);
        getContentPane().setBackground(AppTheme.BACKGROUND);
        getContentPane().setLayout(new BorderLayout());

        Map<String, Container> views = new LinkedHashMap<>();
        LoginPanel login = new LoginPanel();
        DashboardPanel dashboard = new DashboardPanel();
        views.put(ViewNames.LOGIN, login);
        views.put(ViewNames.DASHBOARD, dashboard);
        cards.add(login, ViewNames.LOGIN);
        cards.add(dashboard, ViewNames.DASHBOARD);
        getContentPane().add(cards, BorderLayout.CENTER);

        navigation = new NavigationController(cardLayout, cards, views);
        login.setOnContinue(() -> navigation.show(ViewNames.DASHBOARD));
        navigation.show(ViewNames.LOGIN);
    }
}
