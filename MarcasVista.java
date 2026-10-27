package Polizas.Marcas;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MarcasVista extends JPanel {

    public JButton btn1, btn_a, btn_u, btn_d;
    public JTextField txt1;
    public JLabel lbl1, lbl2;
    public JTable tabla;
    public MarcasTM marcasModelo;
    public String[] columnas = {"Id", "Nombre"};
    public JComboBox<Marca> cmbMarcas;

    public MarcasVista() {
        setLayout(null);

        this.lbl1 = new JLabel("Welcome to Marcas Admin.");
        lbl1.setBounds(350, 5, 200, 30);

        this.lbl2 = new JLabel("Ingresa tu nombre: ");
        lbl2.setBounds(50, 40, 150, 30);

        this.txt1 = new JTextField("Marca");
        this.txt1.setBounds(170, 40, 250, 30);

        cmbMarcas = new JComboBox<>();
        cmbMarcas.setBounds(550, 40, 250, 30); // Movido más a la derecha para no chocar
        add(cmbMarcas);

        // Botones reacomodados
        this.btn1 = new JButton("Consultar");
        this.btn1.setBounds(50, 90, 100, 30);

        this.btn_a = new JButton("Agregar");
        this.btn_a.setBounds(160, 90, 100, 30);

        this.btn_u = new JButton("Actualizar");
        this.btn_u.setBounds(270, 90, 100, 30);

        this.btn_d = new JButton("Eliminar");
        this.btn_d.setBounds(380, 90, 100, 30);

        this.add(btn1);
        this.add(btn_a);
        this.add(btn_u);
        this.add(btn_d);

        marcasModelo = new MarcasTM();
        tabla = new JTable(marcasModelo);
        marcasModelo.fireTableDataChanged();

        JScrollPane sc = new JScrollPane(tabla);
        sc.setBounds(50, 150, 800, 450);

        this.add(sc);
        this.add(lbl1);
        this.add(lbl2);
        this.add(txt1);
    }

    public void actualizarVista(List<Marca> lista) {
        marcasModelo.vaciarlista();
        marcasModelo.fireTableDataChanged();
        if (lista != null) {
            marcasModelo.llenar(lista);
            marcasModelo.fireTableDataChanged();

            cmbMarcas.removeAllItems();
            for (Marca m : lista) {
                cmbMarcas.addItem(m);
            }
        }
    }
}