package modelo;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

public final class EstiloVisual {
    public static final Color FONDO = new Color(248, 247, 251);
    public static final Color TEXTO = new Color(50, 54, 72);
    public static final Color SUAVE = new Color(111, 115, 133);
    public static final Color LILA = new Color(232, 225, 249);
    public static final Color MENTA = new Color(222, 241, 232);
    public static final Color DURAZNO = new Color(252, 232, 218);
    public static final Color AZUL = new Color(225, 237, 250);
    public static final Color ROSA = new Color(248, 227, 237);
    public static final Font FUENTE = new Font(Font.SANS_SERIF, Font.PLAIN, 14);

    private EstiloVisual() { throw new AssertionError(); }

    public static void configurar() {
        for (UIManager.LookAndFeelInfo apariencia : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(apariencia.getName())) {
                try { UIManager.setLookAndFeel(apariencia.getClassName()); }
                catch (Exception excepcion) { UIManager.put("control", FONDO); }
                break;
            }
        }
        UIManager.put("defaultFont", FUENTE);
        UIManager.put("control", FONDO);
        UIManager.put("text", TEXTO);
        UIManager.put("nimbusBase", new Color(175, 162, 206));
        UIManager.put("nimbusSelectionBackground", LILA);
        UIManager.put("nimbusSelectedText", TEXTO);
        UIManager.put("OptionPane.messageFont", FUENTE);
        UIManager.put("Button.font", FUENTE);
        UIManager.put("Label.font", FUENTE);
        UIManager.put("TextArea.font", FUENTE);
        UIManager.put("TextField.font", FUENTE);
        UIManager.put("ComboBox.font", FUENTE);
    }

    public static JLabel etiqueta(String texto, int tamano, Color color) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(FUENTE.deriveFont((float) tamano));
        etiqueta.setForeground(color);
        return etiqueta;
    }

    public static JPanel tarjeta(Color fondo, int radio) {
        JPanel panel = new JPanel(new BorderLayout(12, 16)) {
            @Override
            protected void paintComponent(Graphics graficos) {
                super.paintComponent(graficos);
                Graphics2D dibujo = (Graphics2D) graficos.create();
                dibujo.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                dibujo.setColor(new Color(46, 41, 67, 10));
                dibujo.fillRoundRect(0, 3, getWidth(), getHeight() - 3, radio, radio);
                dibujo.setColor(fondo);
                dibujo.fillRoundRect(0, 0, getWidth(), getHeight() - 3, radio, radio);
                dibujo.dispose();
            }
        };
        panel.setOpaque(false);
        panel.setBackground(fondo);
        panel.setBorder(new EmptyBorder(22, 22, 22, 22));
        return panel;
    }

    public static JButton boton(String texto, Color color) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics graficos) {
                Graphics2D dibujo = (Graphics2D) graficos.create();
                dibujo.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                Color fondo = !isEnabled() ? new Color(236, 237, 240)
                        : getModel().isPressed() ? color.darker()
                        : getModel().isRollover() ? color.brighter() : color;
                dibujo.setColor(fondo);
                dibujo.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
                if (isFocusOwner()) {
                    dibujo.setColor(new Color(136, 115, 175));
                    dibujo.setStroke(new BasicStroke(2f));
                    dibujo.drawRoundRect(2, 2, getWidth() - 5, getHeight() - 5, 16, 16);
                }
                dibujo.dispose();
                super.paintComponent(graficos);
            }
        };
        boton.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        boton.setFont(FUENTE);
        boton.setForeground(TEXTO);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setOpaque(false);
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setBorder(new EmptyBorder(12, 18, 12, 18));
        return boton;
    }

    public static JScrollPane desplazar(JComponent contenido) {
        JScrollPane panel = new JScrollPane(contenido);
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setOpaque(false);
        panel.getViewport().setOpaque(false);
        panel.getVerticalScrollBar().setUnitIncrement(22);
        panel.getVerticalScrollBar().setPreferredSize(new Dimension(7, 0));
        panel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                thumbColor = new Color(210, 204, 222);
                trackColor = FONDO;
            }
            @Override
            protected JButton createDecreaseButton(int orientacion) { return botonVacio(); }
            @Override
            protected JButton createIncreaseButton(int orientacion) { return botonVacio(); }
            private JButton botonVacio() {
                JButton boton = new JButton();
                boton.setPreferredSize(new Dimension(0, 0));
                return boton;
            }
        });
        return panel;
    }

    public static void prepararTexto(JTextArea area, Color fondo) {
        area.setFont(FUENTE.deriveFont(15f));
        area.setForeground(TEXTO);
        area.setBackground(fondo);
        area.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 0));
        area.setSelectionColor(LILA);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
    }
}
