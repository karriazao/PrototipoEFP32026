//Karina Alejandra Arriaza Ortiz 9959-24-14190
package Modelo;

/**
 * Data Access Object para la gestión de as Carreras en la universidad.
 *
 * Proporciona métodos para realizar operaciones CRUD sobre la tabla carreras,
 * incluyendo registro automático de operaciones en la bitácora del sistema.
 *
 * Funcionalidades principales:
 * - Insertar
 * - Actualizar datos existentes
 * - Eliminar de la base de datos
 * - Obtener una carrera específico por su código
 * - Registro automático de operaciones en bitácora
 */

import Controlador.clsCarreras;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarrerasDAO {

    // ── INSERTAR ────────────────────────────────────────────────────────

    public int ingresaCarrera(clsCarreras carrera) {

        int resultado = 0;

        String sql = "INSERT INTO carreras "
                + "(Carnombre, Faccodigo, Carestatus, "
                + "VALUES (?, ?, ?,)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1,  carrera.getCarnombre());
            stmt.setString(2,  carrera.getFaccodigo());
            stmt.setString(3,  carrera.getCarestatus());

            resultado = stmt.executeUpdate();

            // REGISTRAR EN BITÁCORA
            if (resultado > 0) {
                String accion = "Insertar Carrera";
                new BitacoraDAO().insert(0, carrera.getCarcodigo(), accion);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return resultado;
    }

    // ── ACTUALIZAR ──────────────────────────────────────────────────────

    public int actualizaCarrera(clsCarreras carrera) {

        int resultado = 0;

        String sql = "UPDATE carreras "
                + "SET Carnombre=?, Faccodigo=?, Carestatus=?, "
                + "WHERE Libcodigo=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1,  carrera.getCarnombre());
            ps.setString(2,  carrera.getFaccodigo());
            ps.setString(3,  carrera.getCarestatus());
            ps.setInt(4,    carrera.getCarcodigo());

            resultado = ps.executeUpdate();

            // REGISTRAR EN BITÁCORA
            if (resultado > 0) {
                String accion = "Mod Carrera: " + carrera.getCarnombre();
                new BitacoraDAO().insert(0, carrera.getCarcodigo(), accion);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }

    // ── ELIMINAR ────────────────────────────────────────────────────────

    public int borraCarrera(clsCarreras carrera) {

        int resultado = 0;

        String sql = "DELETE FROM carreras WHERE Carcodigo=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, carrera.getCarcodigo());

            resultado = stmt.executeUpdate();

            // REGISTRAR EN BITÁCORA
            if (resultado > 0) {
                String accion = "Eliminar Carrera";
                new BitacoraDAO().insert(0, carrera.getCarcodigo(), accion);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return resultado;
    }

    // ── LISTA COMPLETA de libros ───────────────────────────────────────────────

    /**
     * Obtiene la lista completa de libros registrados en la base de datos.
     *
     * @return Lista de objetos {@code clsLibreria} con todos los libros.
     */
    public List<clsCarreras> getListaCarrera() {

        List<clsCarreras> lista = new ArrayList<>();

        String sql = "SELECT Carcodigo, Carnombre, Faccodigo, Carestatus, "
                + "FROM carreras";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(mapearCarreras(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return lista;
    }


    // ── OBTENER carrera por código ───────────────────────────────────────────────

    public clsCarreras getCarrera(int Carcodigo) {

        clsCarreras car = null;

        String sql = "SELECT * FROM carreras WHERE Carcodigo=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Carcodigo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                car = mapearCarrera(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return car;
    }

    // ── MAPEO PRIVADO ─────────────────────────────────────────────────────────

    /**
     * Mapea una fila del ResultSet a un objeto clsLibreria.
     * Centraliza la lectura de columnas para evitar repetición de código.
     * Los campos BIT(1) se leen con getBoolean() y se convierten a "1"/"0".
     *
     * @param rs ResultSet posicionado en la fila a mapear.
     * @return Objeto {@code clsLibreria} con los datos de la fila.
     * @throws SQLException si ocurre un error al leer el ResultSet.
     */
    private clsCarreras mapearCarrera(ResultSet rs) throws SQLException {

        clsCarreras car = new clsCarreras();

        car.setCarcodigo(rs.getInt("Carcodigo"));
        car.setCarnombre(rs.getString("Carnombre"));
        car.setFaccodigo(rs.getString("Faccodigo"));
        car.setCarestatus(rs.getString("Carestatus"));
        return car;
    }

    private clsCarreras mapearCarreras(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}