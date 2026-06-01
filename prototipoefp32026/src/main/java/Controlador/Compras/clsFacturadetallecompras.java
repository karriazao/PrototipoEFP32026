/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Compras;

/**
 *
 * @author Isaias Cedillo 9959-24-1672
 */
public class clsFacturadetallecompras  {
    // Atributos
    private int Faccomdetid;
    private int Faccomid;
    private int Proid;
    private String Pronombre; // nuevo campo
    private double Faccomcantidad;
    private double Faccomprecio;
    private double Faccomsubtotal;

    // GETTERS
    public int getFaccomdetid() { return Faccomdetid; }
    public int getFaccomid() { return Faccomid; }
    public int getProid() { return Proid; }
    public String getPronombre() { return Pronombre; } // nuevo
    public double getFaccomcantidad() { return Faccomcantidad; }
    public double getFaccomprecio() { return Faccomprecio; }
    public double getFaccomsubtotal() { return Faccomsubtotal; }

    // SETTERS
    public void setFaccomdetid(int Faccomdetid) { this.Faccomdetid = Faccomdetid; }
    public void setFaccomid(int Faccomid) { this.Faccomid = Faccomid; }
    public void setProid(int Proid) { this.Proid = Proid; }
    public void setPronombre(String Pronombre) { this.Pronombre = Pronombre; } // nuevo
    public void setFaccomcantidad(double Faccomcantidad) { this.Faccomcantidad = Faccomcantidad; }
    public void setFaccomprecio(double Faccomprecio) { this.Faccomprecio = Faccomprecio; }
    public void setFaccomsubtotal(double Faccomsubtotal) { this.Faccomsubtotal = Faccomsubtotal; }

    // Constructor vacío
    public clsFacturadetallecompras() {}

    // Constructor sin ID - para INSERT
    public clsFacturadetallecompras(
            int Faccomid, int Proid, String Pronombre,
            double Faccomcantidad, double Faccomprecio,
            double Faccomsubtotal) {
        this.Faccomid = Faccomid;
        this.Proid = Proid;
        this.Pronombre = Pronombre; // nuevo
        this.Faccomcantidad = Faccomcantidad;
        this.Faccomprecio = Faccomprecio;
        this.Faccomsubtotal = Faccomsubtotal;
    }

    // Constructor completo - para SELECT
    public clsFacturadetallecompras(
            int Faccomdetid, int Faccomid, int Proid, String Pronombre,
            double Faccomcantidad, double Faccomprecio,
            double Faccomsubtotal) {
        this.Faccomdetid = Faccomdetid;
        this.Faccomid = Faccomid;
        this.Proid = Proid;
        this.Pronombre = Pronombre; // nuevo
        this.Faccomcantidad = Faccomcantidad;
        this.Faccomprecio = Faccomprecio;
        this.Faccomsubtotal = Faccomsubtotal;
    }

    @Override
    public String toString() {
        return "clsFacturadetallecompras{" +
                "Faccomdetid=" + Faccomdetid +
                ", Faccomid=" + Faccomid +
                ", Proid=" + Proid +
                ", Pronombre=" + Pronombre + // nuevo
                ", Faccomcantidad=" + Faccomcantidad +
                ", Faccomprecio=" + Faccomprecio +
                ", Faccomsubtotal=" + Faccomsubtotal + '}';
    }
}
