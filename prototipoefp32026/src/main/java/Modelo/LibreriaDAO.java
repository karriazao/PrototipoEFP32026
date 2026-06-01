//Karina Alejandra Arriaza Ortiz 9959-24-14190
package Modelo;

/**
 * Data Access Object para la gestión de Libros en la Librería.
 *
 * Proporciona métodos para realizar operaciones CRUD sobre la tabla libreria,
 * incluyendo registro automático de operaciones en la bitácora del sistema.
 *
 * Funcionalidades principales:
 * - Insertar nuevos libros
 * - Actualizar datos de libros existentes
 * - Eliminar libros de la base de datos
 * - Recuperar lista completa de libros
 * - Recuperar libros por categoría
 * - Obtener un libro específico por su código
 * - Registro automático de operaciones en bitácora
 *
 * Nota: Los campos BIT(1) (LibIns, LibSel, LibUpd, LibDel, LibRep) se manejan
 * con getBoolean() en lectura y setBoolean() en escritura para compatibilidad
 * con MySQL.
 */

import Controlador.clsLibreria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibreriaDAO {

    // ── INSERTAR libro ────────────────────────────────────────────────────────

    /**
     * Inserta un nuevo libro en la base de datos.
     *
     * @param libro Objeto con los datos del libro a registrar.
     * @return Número de filas afectadas (1 si fue exitoso, 0 si falló).
     */
    public int ingresaLibro(clsLibreria libro) {

        int resultado = 0;

        String sql = "INSERT INTO libreria "
                + "(Libtitulo, Libautor, Libcategoria, Libeditorial, "
                + "Libanio, Libexistencias, LibIns, LibSel, LibUpd, LibDel, LibRep) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1,  libro.getLibTitulo());
            stmt.setString(2,  libro.getLibAutor());
            stmt.setString(3,  libro.getLibCategoria());
            stmt.setString(4,  libro.getLibEditorial());
            stmt.setInt(5,     libro.getLibAnio());
            stmt.setInt(6,     libro.getLibExistencias());
            stmt.setBoolean(7,  "1".equals(libro.getLibIns()));
            stmt.setBoolean(8,  "1".equals(libro.getLibSel()));
            stmt.setBoolean(9,  "1".equals(libro.getLibUpd()));
            stmt.setBoolean(10, "1".equals(libro.getLibDel()));
            stmt.setBoolean(11, "1".equals(libro.getLibRep()));

            resultado = stmt.executeUpdate();

            // REGISTRAR EN BITÁCORA
            if (resultado > 0) {
                String accion = "Insertar Libro";
                new BitacoraDAO().insert(0, libro.getLibCodigo(), accion);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return resultado;
    }

    // ── ACTUALIZAR libro ──────────────────────────────────────────────────────

    /**
     * Actualiza los datos de un libro existente en la base de datos.
     *
     * @param libro Objeto con los datos actualizados del libro.
     * @return Número de filas afectadas (1 si fue exitoso, 0 si falló).
     */
    public int actualizaLibro(clsLibreria libro) {

        int resultado = 0;

        String sql = "UPDATE libreria "
                + "SET Libtitulo=?, Libautor=?, Libcategoria=?, Libeditorial=?, "
                + "Libanio=?, Libexistencias=?, LibIns=?, LibSel=?, LibUpd=?, LibDel=?, LibRep=? "
                + "WHERE Libcodigo=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1,  libro.getLibTitulo());
            ps.setString(2,  libro.getLibAutor());
            ps.setString(3,  libro.getLibCategoria());
            ps.setString(4,  libro.getLibEditorial());
            ps.setInt(5,     libro.getLibAnio());
            ps.setInt(6,     libro.getLibExistencias());
            ps.setBoolean(7,  "1".equals(libro.getLibIns()));
            ps.setBoolean(8,  "1".equals(libro.getLibSel()));
            ps.setBoolean(9,  "1".equals(libro.getLibUpd()));
            ps.setBoolean(10, "1".equals(libro.getLibDel()));
            ps.setBoolean(11, "1".equals(libro.getLibRep()));
            ps.setInt(12,    libro.getLibCodigo());

            resultado = ps.executeUpdate();

            // REGISTRAR EN BITÁCORA
            if (resultado > 0) {
                String accion = "Mod Libro: " + libro.getLibTitulo();
                new BitacoraDAO().insert(0, libro.getLibCodigo(), accion);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }

    // ── ELIMINAR libro ────────────────────────────────────────────────────────

    /**
     * Elimina un libro de la base de datos por su código.
     *
     * @param libro Objeto con el código del libro a eliminar.
     * @return Número de filas afectadas (1 si fue exitoso, 0 si falló).
     */
    public int borraLibro(clsLibreria libro) {

        int resultado = 0;

        String sql = "DELETE FROM libreria WHERE Libcodigo=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, libro.getLibCodigo());

            resultado = stmt.executeUpdate();

            // REGISTRAR EN BITÁCORA
            if (resultado > 0) {
                String accion = "Eliminar Libro";
                new BitacoraDAO().insert(0, libro.getLibCodigo(), accion);
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
    public List<clsLibreria> getListaLibros() {

        List<clsLibreria> lista = new ArrayList<>();

        String sql = "SELECT Libcodigo, Libtitulo, Libautor, Libcategoria, "
                + "Libeditorial, Libanio, Libexistencias, "
                + "LibIns, LibSel, LibUpd, LibDel, LibRep "
                + "FROM libreria";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(mapearLibro(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return lista;
    }

    // ── LIBROS POR CATEGORÍA ──────────────────────────────────────────────────

    /**
     * Obtiene la lista de libros que pertenecen a una categoría específica.
     *
     * @param categoria Nombre de la categoría a filtrar.
     * @return Lista de objetos {@code clsLibreria} filtrados por categoría.
     */
    public List<clsLibreria> getLibrosPorCategoria(String categoria) {

        List<clsLibreria> lista = new ArrayList<>();

        String sql = "SELECT Libcodigo, Libtitulo, Libautor, Libcategoria, "
                + "Libeditorial, Libanio, Libexistencias, "
                + "LibIns, LibSel, LibUpd, LibDel, LibRep "
                + "FROM libreria "
                + "WHERE Libcategoria = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(mapearLibro(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return lista;
    }

    // ── OBTENER libro por código ───────────────────────────────────────────────

    /**
     * Obtiene un libro específico buscándolo por su código.
     *
     * @param libCodigo Código del libro a buscar.
     * @return Objeto {@code clsLibreria} con los datos del libro,
     *         o {@code null} si no se encontró.
     */
    public clsLibreria getLibro(int libCodigo) {

        clsLibreria lib = null;

        String sql = "SELECT * FROM libreria WHERE Libcodigo=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, libCodigo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                lib = mapearLibro(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lib;
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
    private clsLibreria mapearLibro(ResultSet rs) throws SQLException {

        clsLibreria lib = new clsLibreria();

        lib.setLibCodigo(rs.getInt("Libcodigo"));
        lib.setLibTitulo(rs.getString("Libtitulo"));
        lib.setLibAutor(rs.getString("Libautor"));
        lib.setLibCategoria(rs.getString("Libcategoria"));
        lib.setLibEditorial(rs.getString("Libeditorial"));
        lib.setLibAnio(rs.getInt("Libanio"));
        lib.setLibExistencias(rs.getInt("Libexistencias"));
        lib.setLibIns(rs.getBoolean("LibIns") ? "1" : "0");
        lib.setLibSel(rs.getBoolean("LibSel") ? "1" : "0");
        lib.setLibUpd(rs.getBoolean("LibUpd") ? "1" : "0");
        lib.setLibDel(rs.getBoolean("LibDel") ? "1" : "0");
        lib.setLibRep(rs.getBoolean("LibRep") ? "1" : "0");

        return lib;
    }

}