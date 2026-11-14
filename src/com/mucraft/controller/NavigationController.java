package com.mucraft.controller;

import java.awt.CardLayout;
import java.awt.Container;
import java.util.Map;
import javax.swing.JPanel;
import com.mucraft.view.interfaces.RefreshableView;

/** Navegación de ventana única. Instanciar y llamar desde el EDT. */
public final class NavigationController {
    private final CardLayout layout;
    private final JPanel cards;
    private final Map<String, Container> views;

    public NavigationController(CardLayout layout, JPanel cards, Map<String, Container> views) {
        this.layout = layout;
        this.cards = cards;
        this.views = Map.copyOf(views);
    }

    public void show(String name) {
        Container view = views.get(name);
        if (view == null) throw new IllegalArgumentException("Vista no registrada: " + name);
        layout.show(cards, name);
        if (view instanceof RefreshableView refreshable) refreshable.onShow();
    }
}
