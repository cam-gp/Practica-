package Polizas.Marcas;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import Polizas.General.Conexion;
import Polizas.General.DAO;

public class MarcasModelo extends Conexion implements DAO {

    public MarcasModelo() {
        super();
    }

    @Override
    public List<Marca> read() {
        List<Marca> lista = new ArrayList<>();
        try {
            this.conectar();
            if (cnx != null) {
                ps = cnx.prepareStatement("SELECT * FROM marcas");
                rs = ps.executeQuery();
                lista.clear();
                while (rs.next()) {
                    lista.add(new Marca(rs.getInt("id"), rs.getString("nombre")));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al leer: " + ex.getMessage());
        } finally {
            this.close();
        }
        return lista;
    }

    @Override
    public int create(Object obj) {
        try {
            this.conectar();
            if (cnx != null) {
                Marca marca = (Marca) obj;
                ps = cnx.prepareStatement("INSERT INTO marcas (nombre, velocidad) VALUES (?, ?)");
                ps.setString(1, marca.getNombre());
                ps.setInt(2, 0); 
                return ps.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Error al crear: " + ex.getMessage());
        } finally {
            this.close();
        }
        return 0;
    }

    @Override
    public int update(Object obj) {
        try {
            this.conectar();
            if (cnx != null) {
                Marca marca = (Marca) obj;
                ps = cnx.prepareStatement("UPDATE marcas SET nombre = ? WHERE id = ?");
                ps.setString(1, marca.getNombre());
                ps.setInt(2, marca.getId());
                return ps.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Error al actualizar: " + ex.getMessage());
        } finally {
            this.close();
        }
        return 0;
    }

    @Override
    public int delete(Object obj) {
        try {
            this.conectar();
            if (cnx != null) {
                int id = (int) obj;
                ps = cnx.prepareStatement("DELETE FROM marcas WHERE id = ?");
                ps.setInt(1, id);
                return ps.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Error al eliminar: " + ex.getMessage());
        } finally {
            this.close();
        }
        return 0;
    }
}