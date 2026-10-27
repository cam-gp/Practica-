package Polizas.Marcas;

import java.awt.event.MouseAdapter;
import java.util.List;
import javax.swing.JOptionPane;

public class MarcasControl {

    private MarcasVista mv;
    private MarcasModelo mm;
    private List<Marca> lista;

    public MarcasControl(MarcasVista marcasVista) {
        this.mv = marcasVista;
        this.mm = new MarcasModelo();
        marcasEventos();
    }

    public void marcasEventos() {
        // Consultar
        mv.btn1.addActionListener(e -> {
            lista = mm.read();
            mv.actualizarVista(lista);
        });

        // Agregar
        mv.btn_a.addActionListener(e -> {
            if (!mv.txt1.getText().trim().isEmpty()) {
                Marca marca = new Marca(0, mv.txt1.getText());
                int res = mm.create(marca);
                JOptionPane.showMessageDialog(null, "Registros creados: " + res);
                mv.actualizarVista(mm.read());
            } else {
                JOptionPane.showMessageDialog(null, "Escribe un nombre de marca.");
            }
        });

        // Actualizar
        mv.btn_u.addActionListener(e -> {
            int fila = mv.tabla.getSelectedRow();
            if (fila != -1) {
                int id = (int) mv.marcasModelo.getValueAt(fila, 0);
                String nuevoNombre = mv.txt1.getText();
                Marca marca = new Marca(id, nuevoNombre);

                int res = mm.update(marca);
                JOptionPane.showMessageDialog(null, "Registros actualizados: " + res);
                mv.actualizarVista(mm.read());
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona una marca de la tabla para actualizar.");
            }
        });

        // Eliminar
        mv.btn_d.addActionListener(e -> {
            int fila = mv.tabla.getSelectedRow();
            if (fila != -1) {
                int id = (int) mv.marcasModelo.getValueAt(fila, 0);
                int confirm = JOptionPane.showConfirmDialog(null, "¿Eliminar marca?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    int res = mm.delete(id);
                    JOptionPane.showMessageDialog(null, "Registros eliminados: " + res);
                    mv.actualizarVista(mm.read());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona una marca de la tabla para eliminar.");
            }
        });

        mv.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int fila = mv.tabla.getSelectedRow();
                if (fila != -1) {
                    mv.txt1.setText((String) mv.marcasModelo.getValueAt(fila, 1));
                }
            }
        });

        mv.tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = mv.tabla.getSelectedRow();
                if (fila != -1) {
                    System.out.println("Fila seleccionada: " + fila);
                }
            }
        });

        mv.cmbMarcas.addActionListener(e -> {
            Marca m = (Marca) mv.cmbMarcas.getSelectedItem();
        });
    }
}