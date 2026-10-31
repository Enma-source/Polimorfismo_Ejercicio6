package aplicacion;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import modelo.EstiloVisual;

public class ContenidoPanel extends JPanel {
    private final JPanel cuadricula = new JPanel(new GridLayout(0, 3, 20, 20));
    private final JPanel soporte = new JPanel(new BorderLayout());
    private final JComboBox<String> tipoBusqueda = new JComboBox<>(new String[]{"ID", "Nombre"});
    private final JTextField campoBusqueda = new JTextField(16);
    private final JButton botonBuscar = EstiloVisual.boton("Buscar", EstiloVisual.LILA);
    private final JButton botonListar = EstiloVisual.boton("Ver todos", Color.WHITE);
    private final JButton botonOrdenar = EstiloVisual.boton("Ordenar por memoria", Color.WHITE);
    private final JLabel estado = EstiloVisual.etiqueta("Tu espacio, a tu ritmo", 13, EstiloVisual.SUAVE);
    private final JLabel cantidad = EstiloVisual.etiqueta("Tus componentes", 18, EstiloVisual.TEXTO);

    public ContenidoPanel() {
        super(new BorderLayout(0, 24));
        setBackground(EstiloVisual.FONDO);
        setBorder(new EmptyBorder(30, 32, 18, 32));
        JPanel cabecera = new JPanel(new BorderLayout(18, 20));
        cabecera.setOpaque(false);
        JPanel titulos = new JPanel(new GridLayout(0, 1, 0, 8));
        titulos.setOpaque(false);
        titulos.add(EstiloVisual.etiqueta("MI ESPACIO", 12, EstiloVisual.SUAVE));
        titulos.add(EstiloVisual.etiqueta("Todo en un solo lugar", 30, EstiloVisual.TEXTO));
        titulos.add(EstiloVisual.etiqueta("Un momento para organizar tu día.", 15, EstiloVisual.SUAVE));
        cabecera.add(titulos, BorderLayout.NORTH);

        JPanel acciones = new JPanel(new BorderLayout(16, 0));
        acciones.setOpaque(false);
        JPanel navegacion = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        navegacion.setOpaque(false);
        navegacion.add(botonListar);
        navegacion.add(botonOrdenar);
        JPanel busqueda = EstiloVisual.tarjeta(Color.WHITE, 20);
        busqueda.setLayout(new BorderLayout(12, 0));
        busqueda.setBorder(new EmptyBorder(4, 12, 4, 4));
        tipoBusqueda.setPreferredSize(new Dimension(100, 34));
        tipoBusqueda.setFont(EstiloVisual.FUENTE);
        campoBusqueda.setFont(EstiloVisual.FUENTE);
        campoBusqueda.setForeground(EstiloVisual.TEXTO);
        campoBusqueda.setBorder(new EmptyBorder(4, 4, 4, 4));
        campoBusqueda.setToolTipText("Escribe el ID o parte del nombre");
        campoBusqueda.getAccessibleContext().setAccessibleName("Buscar componente");
        busqueda.add(tipoBusqueda, BorderLayout.WEST);
        busqueda.add(campoBusqueda, BorderLayout.CENTER);
        busqueda.add(botonBuscar, BorderLayout.EAST);
        acciones.add(navegacion, BorderLayout.WEST);
        acciones.add(busqueda, BorderLayout.CENTER);
        cabecera.add(acciones, BorderLayout.CENTER);
        cabecera.add(cantidad, BorderLayout.SOUTH);
        add(cabecera, BorderLayout.NORTH);

        cuadricula.setOpaque(false);
        cuadricula.setBorder(new EmptyBorder(0, 0, 8, 12));
        soporte.setOpaque(false);
        soporte.add(cuadricula, BorderLayout.NORTH);
        add(EstiloVisual.desplazar(soporte), BorderLayout.CENTER);
        add(estado, BorderLayout.SOUTH);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evento) {
                ajustarColumnas();
            }
        });
    }

    private void ajustarColumnas() {
        int columnas = getWidth() >= 1120 ? 3 : 2;
        GridLayout distribucion = (GridLayout) cuadricula.getLayout();
        if (columnas != distribucion.getColumns()) {
            distribucion.setColumns(columnas);
            cuadricula.revalidate();
        }
    }

    public String obtenerCriterioBusqueda() { return campoBusqueda.getText().trim(); }
    public boolean buscarPorIdSeleccionado() { return tipoBusqueda.getSelectedIndex() == 0; }
    public void alListar(ActionListener accion) { botonListar.addActionListener(accion); }
    public void alOrdenar(ActionListener accion) { botonOrdenar.addActionListener(accion); }
    public void alBuscar(ActionListener accion) {
        botonBuscar.addActionListener(accion);
        campoBusqueda.addActionListener(accion);
    }
    public void mostrarComponentes(List<JComponent> vistas) {
        cuadricula.removeAll();
        for (JComponent vista : vistas) { cuadricula.add(vista); }
        cantidad.setText("Tus componentes  ·  " + vistas.size());
        estado.setText("Todo actualizado en tu espacio  ·  Memoria estimada en KB");
        ajustarColumnas();
        cuadricula.revalidate();
        cuadricula.repaint();
    }
    public void mostrarConfiguracion(String configuracion) {
        JTextArea contenido = new JTextArea(configuracion, 14, 42);
        EstiloVisual.prepararTexto(contenido, EstiloVisual.FONDO);
        contenido.setCaretPosition(0);
        JOptionPane.showMessageDialog(this, EstiloVisual.desplazar(contenido),
                "Detalles del componente", JOptionPane.PLAIN_MESSAGE);
    }
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Mi espacio", JOptionPane.PLAIN_MESSAGE);
    }
}
