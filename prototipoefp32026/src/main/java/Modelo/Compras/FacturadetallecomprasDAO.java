/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Compras;
import Controlador.Compras.clsFacturadetallecompras;
import Modelo.Conexion;
import Modelo.BitacoraDAO;
import Controlador.clsUsuarioConectado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Isaias Cedillo
 */
public class FacturadetallecomprasDAO {

    private static final int APL_CODIGO = 30000;

    // LISTAR
    public List<clsFacturadetallecompras> listar() {

        List<clsFacturadetallecompras> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM Facturadetallecompras";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                clsFacturadetallecompras detalle =
                        new clsFacturadetallecompras();

                detalle.setFaccomdetid(
                        rs.getInt("Faccomdetid"));

                detalle.setFaccomid(
                        rs.getInt("Faccomid"));

                detalle.setProid(
                        rs.getInt("Proid"));
                
                detalle.setPronombre(rs.getString("Pronombre"));
                
                detalle.setFaccomcantidad(
                        rs.getDouble("Faccomcantidad"));

                detalle.setFaccomprecio(
                        rs.getDouble("Faccomprecio"));

                detalle.setFaccomsubtotal(
                        rs.getDouble("Faccomsubtotal"));

                lista.add(detalle);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }

    // INSERTAR
    public void insert(
            clsFacturadetallecompras detalle) {

        String sql = "INSERT INTO Facturadetallecompras "
           + "(Faccomid, Proid, Pronombre, " // nuevo campo
           + "Faccomcantidad, Faccomprecio, Faccomsubtotal) "
           + "VALUES (?,?,?,?,?,?)"; 

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setInt(1, detalle.getFaccomid());
            ps.setInt(2, detalle.getProid());
            ps.setString(3, detalle.getPronombre()); // nuevo - posición 3
            ps.setDouble(4, detalle.getFaccomcantidad()); // se corrió a 4
            ps.setDouble(5, detalle.getFaccomprecio());   // se corrió a 5
            ps.setDouble(6, detalle.getFaccomsubtotal()); // se corrió a 6

            ps.executeUpdate();

            new BitacoraDAO().insert(
                    clsUsuarioConectado.getUsuId(),
                    APL_CODIGO,
                    "INSERT");

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Error al insertar detalle",
                    e);
        }
    }

    // ACTUALIZAR
    public void update(
            clsFacturadetallecompras detalle) {

        String sql = "UPDATE Facturadetallecompras SET "
           + "Faccomid=?, Proid=?, Pronombre=?, " 
           + "Faccomcantidad=?, Faccomprecio=?, Faccomsubtotal=? "
           + "WHERE Faccomdetid=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setInt(1, detalle.getFaccomid());
            ps.setInt(2, detalle.getProid());
            ps.setString(3, detalle.getPronombre()); // nuevo
            ps.setDouble(4, detalle.getFaccomcantidad());
            ps.setDouble(5, detalle.getFaccomprecio());
            ps.setDouble(6, detalle.getFaccomsubtotal());
            ps.setInt(7, detalle.getFaccomdetid()); // WHERE se corrió a 7

            int rows =
                    ps.executeUpdate();

            if (rows == 0) {

                throw new RuntimeException(
                        "No se encontró el detalle");
            }

            new BitacoraDAO().insert(
                    clsUsuarioConectado.getUsuId(),
                    APL_CODIGO,
                    "UPDATE");

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Error al actualizar detalle",
                    e);
        }
    }

    // ELIMINAR
    public void delete(int idDetalle) {

        String sql =
                "DELETE FROM Facturadetallecompras "
              + "WHERE Faccomdetid=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setInt(1, idDetalle);

            int rows =
                    ps.executeUpdate();

            if (rows == 0) {

                throw new RuntimeException(
                        "No se encontró el detalle");
            }

            new BitacoraDAO().insert(
                    clsUsuarioConectado.getUsuId(),
                    APL_CODIGO,
                    "DELETE");

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Error al eliminar detalle",
                    e);
        }
    }

    // CONSULTAR
    public clsFacturadetallecompras query(
            int idDetalle) {

        clsFacturadetallecompras detalle =
                null;

        String sql =
                "SELECT * FROM Facturadetallecompras "
              + "WHERE Faccomdetid=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setInt(1, idDetalle);

            try (ResultSet rs =
                         ps.executeQuery()) {

                if (rs.next()) {

                    detalle =
                            new clsFacturadetallecompras();

                    detalle.setFaccomdetid(
                            rs.getInt("Faccomdetid"));

                    detalle.setFaccomid(
                            rs.getInt("Faccomid"));

                    detalle.setProid(
                            rs.getInt("Proid"));
                    
                    detalle.setPronombre(rs.getString("Pronombre"));

                    detalle.setFaccomcantidad(
                            rs.getDouble("Faccomcantidad"));

                    detalle.setFaccomprecio(
                            rs.getDouble("Faccomprecio"));

                    detalle.setFaccomsubtotal(
                            rs.getDouble("Faccomsubtotal"));
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Error al consultar detalle",
                    e);
        }

        return detalle;
    }
}
