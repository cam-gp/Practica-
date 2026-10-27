package Polizas.General;

import Polizas.Marcas.MarcasVista;
import Polizas.Marcas.MarcasControl;
import javax.swing.JLabel;

public class Controlador {

    protected Bienvenida vista;
    protected MarcasVista marcasVista;

    public Controlador(Bienvenida vista) {
        this.vista = vista;
        this.eventos();
    }

    void eventos() {
        vista.mensaje.addActionListener(e -> vista.cl.show(vista.pc, "mensaje"));

        vista.home.addActionListener(e -> {
            vista.pc.add(new JLabel("Panel Home"), "home");
            vista.cl.show(vista.pc, "home");
        });

        vista.marcas.addActionListener(e -> {
            marcasVista = new MarcasVista();
            new MarcasControl(marcasVista);
            vista.pc.add(marcasVista, "mk");
            vista.cl.show(vista.pc, "mk");
        });
    }
}