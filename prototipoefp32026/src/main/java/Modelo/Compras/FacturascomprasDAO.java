/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Compras;
//Librerias
import Controlador.Compras.clsFacturascompras;
import Modelo.Conexion;
import Modelo.BitacoraDAO;
import Controlador.clsUsuarioConectado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class FacturascomprasDAO {

    private static final int APL_CODIGO = 30001;

    // LISTAR
    public List<clsFacturascompras> listar() {

        List<clsFacturascompras> lista = new ArrayList<>();

        String sql = "SELECT * FROM Facturascompras";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                clsFacturascompras factura =
                        new clsFacturascompras();

                factura.setFaccomid(
                        rs.getInt("Faccomid"));

                factura.setFaccomnumero(
                        rs.getString("Faccomnumero"));

                factura.setProvcodigo(
                        rs.getInt("Provcodigo"));

                factura.setProvnombre(
                        rs.getString("Provnombre"));

                factura.setImpid(
                        rs.getInt("Impid"));

                factura.setFaccomsubtotal(
                        rs.getDouble("Faccomsubtotal"));

                // IVA eliminado

                factura.setFaccomtotal(
                        rs.getDouble("Faccomtotal"));

                lista.add(factura);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // INSERTAR
    public int insert(clsFacturascompras factura) {

        String sql =
                "INSERT INTO Facturascompras "
              + "(Faccomnumero, Provcodigo, "
              + "Provnombre, Impid, "
              + "Faccomsubtotal, Faccomtotal) "
              + "VALUES (?,?,?,?,?,?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(
                             sql,
                             Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1,
                    factura.getFaccomnumero());

            ps.setInt(2,
                    factura.getProvcodigo());

            ps.setString(3,
                    factura.getProvnombre());

            ps.setInt(4,
                    factura.getImpid());

            ps.setDouble(5,
                    factura.getFaccomsubtotal());

            // IVA eliminado

            ps.setDouble(6,
                    factura.getFaccomtotal());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {

                new BitacoraDAO().insert(
                        clsUsuarioConectado.getUsuId(),
                        APL_CODIGO,
                        "INSERT"
                );

                return rs.getInt(1);
            }

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Error al insertar factura", e);
        }

        return -1;
    }

    // ACTUALIZAR
    public void update(clsFacturascompras factura) {

        String sql =
                "UPDATE Facturascompras SET "
              + "Faccomnumero=?, "
              + "Provcodigo=?, "
              + "Provnombre=?, "
              + "Impid=?, "
              + "Faccomsubtotal=?, "
              + "Faccomtotal=? "
              + "WHERE Faccomid=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setString(1,
                    factura.getFaccomnumero());

            ps.setInt(2,
                    factura.getProvcodigo());

            ps.setString(3,
                    factura.getProvnombre());

            ps.setInt(4,
                    factura.getImpid());

            ps.setDouble(5,
                    factura.getFaccomsubtotal());

            // IVA eliminado

            ps.setDouble(6,
                    factura.getFaccomtotal());

            ps.setInt(7,
                    factura.getFaccomid());

            int rows = ps.executeUpdate();

            if (rows == 0) {

                throw new RuntimeException(
                        "No se encontró la factura");
            }

            new BitacoraDAO().insert(
                    clsUsuarioConectado.getUsuId(),
                    APL_CODIGO,
                    "UPDATE"
            );

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Error al actualizar factura", e);
        }
    }

    // ELIMINAR
    public void delete(int idFactura) {

        String sql =
                "DELETE FROM Facturascompras "
              + "WHERE Faccomid=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setInt(1, idFactura);

            int rows = ps.executeUpdate();

            if (rows == 0) {

                throw new RuntimeException(
                        "No se encontró la factura");
            }

            new BitacoraDAO().insert(
                    clsUsuarioConectado.getUsuId(),
                    APL_CODIGO,
                    "DELETE"
            );

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Error al eliminar factura", e);
        }
    }

    // CONSULTAR
    public clsFacturascompras query(int idFactura) {

        clsFacturascompras factura = null;

        String sql =
                "SELECT * FROM Facturascompras "
              + "WHERE Faccomid=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setInt(1, idFactura);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    factura =
                            new clsFacturascompras();

                    factura.setFaccomid(
                            rs.getInt("Faccomid"));

                    factura.setFaccomnumero(
                            rs.getString("Faccomnumero"));

                    factura.setProvcodigo(
                            rs.getInt("Provcodigo"));

                    factura.setProvnombre(
                            rs.getString("Provnombre"));

                    factura.setImpid(
                            rs.getInt("Impid"));

                    factura.setFaccomsubtotal(
                            rs.getDouble("Faccomsubtotal"));

                    // IVA eliminado

                    factura.setFaccomtotal(
                            rs.getDouble("Faccomtotal"));
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Error al consultar factura", e);
        }

        return factura;
    }
}
