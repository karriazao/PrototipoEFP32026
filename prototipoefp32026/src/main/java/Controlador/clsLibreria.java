//Karina Alejandra Arriaza Ortiz 9959-24-14190
package Controlador;

import Modelo.LibreriaDAO;
import java.util.List;

/**
 * Controlador de la entidad Libreria.
 * Gestiona la lógica de negocio y delega las operaciones
 * de persistencia al DAO correspondiente.
 *
 * Nota: Los flags BIT(1) (LibIns, LibSel, LibUpd, LibDel, LibRep)
 * se manejan como String "1" (activo) o "0" (inactivo) para
 * mantener consistencia con el DAO y MySQL.
 
 */
public class clsLibreria {

    // ── Atributos ─────────────────────────────────────────────────────────────

    /** Código único del libro (PK, auto incrementable). */
    private int LibCodigo;

    /** Título del libro. */
    private String LibTitulo;

    /** Autor del libro. */
    private String LibAutor;

    /** Categoría a la que pertenece el libro. */
    private String LibCategoria;

    /** Editorial que publicó el libro. */
    private String LibEditorial;

    /** Año de publicación del libro. */
    private int LibAnio;

    /** Cantidad de existencias disponibles en inventario. */
    private int LibExistencias;

    /** Flag de inserción. Valores: "1" activo, "0" inactivo. */
    private String LibIns;

    /** Flag de selección. Valores: "1" activo, "0" inactivo. */
    private String LibSel;

    /** Flag de actualización. Valores: "1" activo, "0" inactivo. */
    private String LibUpd;

    /** Flag de eliminación / baja lógica. Valores: "1" activo, "0" inactivo. */
    private String LibDel;

    /** Flag de reporte. Valores: "1" activo, "0" inactivo. */
    private String LibRep;


    // ── Constructores ─────────────────────────────────────────────────────────

    /**
     * Constructor vacío. Requerido para instancias sin datos iniciales.
     */
    public clsLibreria() {}

    /**
     * Constructor completo con todos los atributos de la tabla libreria.
     *
     * @param LibCodigo      Código único del libro.
     * @param LibTitulo      Título del libro.
     * @param LibAutor       Autor del libro.
     * @param LibCategoria   Categoría del libro.
     * @param LibEditorial   Editorial del libro.
     * @param LibAnio        Año de publicación.
     * @param LibExistencias Cantidad de existencias.
     * @param LibIns         Flag de inserción ("1"/"0").
     * @param LibSel         Flag de selección ("1"/"0").
     * @param LibUpd         Flag de actualización ("1"/"0").
     * @param LibDel         Flag de eliminación ("1"/"0").
     * @param LibRep         Flag de reporte ("1"/"0").
     */
    public clsLibreria(int LibCodigo, String LibTitulo, String LibAutor,
            String LibCategoria, String LibEditorial, int LibAnio,
            int LibExistencias, String LibIns, String LibSel,
            String LibUpd, String LibDel, String LibRep) {
        this.LibCodigo      = LibCodigo;
        this.LibTitulo      = LibTitulo;
        this.LibAutor       = LibAutor;
        this.LibCategoria   = LibCategoria;
        this.LibEditorial   = LibEditorial;
        this.LibAnio        = LibAnio;
        this.LibExistencias = LibExistencias;
        this.LibIns         = LibIns;
        this.LibSel         = LibSel;
        this.LibUpd         = LibUpd;
        this.LibDel         = LibDel;
        this.LibRep         = LibRep;
    }


    // ── Getters y Setters ─────────────────────────────────────────────────────

    /** @return Código del libro. */
    public int getLibCodigo() { return LibCodigo; }
    /** @param LibCodigo Código a asignar. */
    public void setLibCodigo(int LibCodigo) { this.LibCodigo = LibCodigo; }

    /** @return Título del libro. */
    public String getLibTitulo() { return LibTitulo; }
    /** @param LibTitulo Título a asignar. */
    public void setLibTitulo(String LibTitulo) { this.LibTitulo = LibTitulo; }

    /** @return Autor del libro. */
    public String getLibAutor() { return LibAutor; }
    /** @param LibAutor Autor a asignar. */
    public void setLibAutor(String LibAutor) { this.LibAutor = LibAutor; }

    /** @return Categoría del libro. */
    public String getLibCategoria() { return LibCategoria; }
    /** @param LibCategoria Categoría a asignar. */
    public void setLibCategoria(String LibCategoria) { this.LibCategoria = LibCategoria; }

    /** @return Editorial del libro. */
    public String getLibEditorial() { return LibEditorial; }
    /** @param LibEditorial Editorial a asignar. */
    public void setLibEditorial(String LibEditorial) { this.LibEditorial = LibEditorial; }

    /** @return Año de publicación del libro. */
    public int getLibAnio() { return LibAnio; }
    /** @param LibAnio Año a asignar. */
    public void setLibAnio(int LibAnio) { this.LibAnio = LibAnio; }

    /** @return Cantidad de existencias del libro. */
    public int getLibExistencias() { return LibExistencias; }
    /** @param LibExistencias Existencias a asignar. */
    public void setLibExistencias(int LibExistencias) { this.LibExistencias = LibExistencias; }

    /** @return Flag de inserción ("1"/"0"). */
    public String getLibIns() { return LibIns; }
    /** @param LibIns Valor a asignar ("1"/"0"). */
    public void setLibIns(String LibIns) { this.LibIns = LibIns; }

    /** @return Flag de selección ("1"/"0"). */
    public String getLibSel() { return LibSel; }
    /** @param LibSel Valor a asignar ("1"/"0"). */
    public void setLibSel(String LibSel) { this.LibSel = LibSel; }

    /** @return Flag de actualización ("1"/"0"). */
    public String getLibUpd() { return LibUpd; }
    /** @param LibUpd Valor a asignar ("1"/"0"). */
    public void setLibUpd(String LibUpd) { this.LibUpd = LibUpd; }

    /** @return Flag de eliminación / baja lógica ("1"/"0"). */
    public String getLibDel() { return LibDel; }
    /** @param LibDel Valor a asignar ("1"/"0"). */
    public void setLibDel(String LibDel) { this.LibDel = LibDel; }

    /** @return Flag de reporte ("1"/"0"). */
    public String getLibRep() { return LibRep; }
    /** @param LibRep Valor a asignar ("1"/"0"). */
    public void setLibRep(String LibRep) { this.LibRep = LibRep; }


    // ── Métodos de Negocio ────────────────────────────────────────────────────

    /**
     * Registra un nuevo libro en la base de datos.
     *
     * @param libro Objeto con los datos del libro a insertar.
     * @return Número de filas afectadas.
     */
    public int setIngresarLibro(clsLibreria libro) {
        return new LibreriaDAO().ingresaLibro(libro);
    }

    /**
     * Modifica los datos de un libro existente.
     *
     * @param libro Objeto con los datos actualizados del libro.
     * @return Número de filas afectadas.
     */
    public int setModificarLibro(clsLibreria libro) {
        return new LibreriaDAO().actualizaLibro(libro);
    }

    /**
     * Elimina un libro de la base de datos.
     *
     * @param libro Objeto con el código del libro a eliminar.
     * @return Número de filas afectadas.
     */
    public int setEliminarLibro(clsLibreria libro) {
        return new LibreriaDAO().borraLibro(libro);
    }

    /**
     * Obtiene la lista completa de libros registrados.
     *
     * @return Lista de objetos {@code clsLibreria}.
     */
    public List<clsLibreria> getListaLibros() {
        return new LibreriaDAO().getListaLibros();
    }

    /**
     * Busca libros por categoría.
     *
     * @param categoria Categoría a filtrar.
     * @return Lista de libros que pertenecen a la categoría indicada.
     */
    public List<clsLibreria> getLibrosPorCategoria(String categoria) {
        return new LibreriaDAO().getLibrosPorCategoria(categoria);
    }

    /**
     * Obtiene un libro específico por su código.
     *
     * @param libCodigo Código del libro a buscar.
     * @return Objeto {@code clsLibreria} con los datos del libro encontrado,
     *         o {@code null} si no existe.
     */
    public clsLibreria getLibro(int libCodigo) {
        return new LibreriaDAO().getLibro(libCodigo);
    }


    // ── toString ──────────────────────────────────────────────────────────────

    /**
     * Representación en texto del objeto clsLibreria.
     * @return String con el código, título y autor del libro.
     */
    @Override
    public String toString() {
        return "clsLibreria{LibCodigo=" + LibCodigo
                + ", LibTitulo='"  + LibTitulo + "'"
                + ", LibAutor='"   + LibAutor  + "'"
                + '}';
    }
}