package com.mucraft.view.panels;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.SwingConstants;
import com.mucraft.config.AppTheme;

/** Pantalla inicial provisional; la autenticación se conecta al implementar el
 * contrato real de Empleados y sus estados en el esquema. */
public final class LoginPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private Runnable onContinue = () -> { };
    private final JLabel connectionStatus = new JLabel("Base de datos: sin comprobar", SwingConstants.CENTER);

    public LoginPanel() {
        super(new BorderLayout(12, 12));
        setBackground(AppTheme.BACKGROUND);
        JLabel title = new JLabel("Mucraft", SwingConstants.CENTER);
        title.setFont(AppTheme.TITLE);
        add(title, BorderLayout.NORTH);
        JLabel description = new JLabel("Aplicación de gestión de fincas y animales", SwingConstants.CENTER);
        add(description, BorderLayout.CENTER);
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.CENTER));
        actions.setOpaque(false);
        JButton testConnection = new JButton("Probar conexión MySQL");
        testConnection.addActionListener(event -> checkConnection(testConnection));
        actions.add(testConnection);
        JButton continueButton = new JButton("Abrir panel de inicio");
        continueButton.addActionListener(event -> onContinue.run());
        actions.add(continueButton);
        JPanel footer = new JPanel(new BorderLayout());
        footer.setOpaque(false);
        footer.add(connectionStatus, BorderLayout.NORTH);
        footer.add(actions, BorderLayout.SOUTH);
        add(footer, BorderLayout.SOUTH);
    }

    public void setOnContinue(Runnable action) {
        onContinue = action == null ? () -> { } : action;
    }

    private void checkConnection(JButton button) {
        button.setEnabled(false);
        connectionStatus.setText("Conectando con MySQL...");
        new SwingWorker<String, Void>() {
            @Override protected String doInBackground() throws Exception {
                return com.mucraft.config.DatabaseConnection.testConnection();
            }
            @Override protected void done() {
                try {
                    connectionStatus.setText(get());
                } catch (Exception ex) {
                    Throwable cause = ex.getCause() == null ? ex : ex.getCause();
                    connectionStatus.setText("Conexión fallida: " + cause.getMessage());
                } finally {
                    button.setEnabled(true);
                }
            }
        }.execute();
    }
}
