//Karina Alejandra Arriaza Ortiz 9959-24-14190
package Controlador;

import Modelo.CarrerasDAO;
import java.util.List;

/**
 * Controlador de la entidad Carrera.
 * Gestiona la lógica de negocio y delega las operaciones
 * de persistencia al DAO correspondiente.
 
 */
public class clsCarreras {

    // ── Atributos ─────────────────────────────────────────────────────────────

    /** Código único de la carrera (PK, auto incrementable). */
    private int Carcodigo;

    private String Carnombre;

    private String Faccodigo;

    private String Carestatus;

    // ── Constructores ─────────────────────────────────────────────────────────

    /**
     * Constructor vacío. Requerido para instancias sin datos iniciales.
     */
    public clsCarreras() {}

    /**
     * Constructor completo con todos los atributos de la tabla libreria.
     *
     * @param Carcodigo      Código único de la carrera.
     * @param Carnombre      nombre del estudiante.
     * @param Faccodigo       codigo de la facultad.
     * @param Carestatus   estatus de la carrera.

     */
    public clsCarreras(int Carcodigo, String Carnombre, String Faccodigo,String Carestatus) {
        this.Carcodigo      = Carcodigo;
        this.Carnombre     = Carnombre;
        this.Faccodigo       = Faccodigo;
        this.Carestatus   = Carestatus;
    }


    // ── Getters y Setters ─────────────────────────────────────────────────────

    /** @return Código de la carrera. */
    public int getCarcodigo() { return Carcodigo; }
    public void setCarcodigo(int Carcdigo) { this.Carcodigo = Carcodigo; }

    /** @return Nombre de la carrera. */
    public String getCarnombre() { return Carnombre; }
    public void setCarnombre(String Carnombre) { this.Carnombre = Carnombre; }

    /** @return Codigo de la facultad. */
    public String getFaccodigo() { return Faccodigo; }
    public void setFaccodigo(String Faccodigo) { this.Faccodigo = Faccodigo; }

    /** @return Estatus de la carrera. */
    public String getCarestatus() { return Carestatus; }
    public void setCarestatus(String Carestatus) { this.Carestatus = Carestatus; }


    // ── Métodos de Negocio ────────────────────────────────────────────────────

    /**
     * Registrar en la base de datos.
     *
     * @param carrera Objeto con los datos del libro a insertar.
     * @return Número de filas afectadas.
     */
    public int setIngresarCarreras(clsCarreras carrera) {
        return new CarrerasDAO().ingresaCarrera(carrera);
    }

    /**
     * Modifica los datos de una carrera existente.
     *
     * @param carrera Objeto con los datos actualizados del libro.
     * @return Número de filas afectadas.
     */
    public int setModificarCarreras(clsCarreras carrera) {
        return new CarrerasDAO().actualizaCarrera(carrera);
    }

    /**
     * Elimina una carrera de la base de datos.
     *
     * @param carrera Objeto con el código del libro a eliminar.
     * @return Número de filas afectadas.
     */
    public int setEliminarLibro(clsCarreras carrera) {
        return new CarrerasDAO().borraCarrera(carrera);
    }

    public List<clsCarreras> getListaCarreras() {
        return new CarrerasDAO().getListaCarrera();
    }

    public clsCarreras getCarrera(int Carcodigo) {
        return new CarrerasDAO().getCarrera(Carcodigo);
    }


    // ── toString ──────────────────────────────────────────────────────────────

    /**
     * Representación en texto del objeto clsLibreria.
     * @return String con el código, título y autor del libro.
     */
    @Override
    public String toString() {
        return "clsCarreras{Carcodigo=" + Carcodigo
                + ", Carnombre='"  + Carnombre + "'"
                + ", Facnombre='"   + Faccodigo  + "'"
                + ", Carestatus='"  + Carestatus + "'"
                + '}';
    }
}