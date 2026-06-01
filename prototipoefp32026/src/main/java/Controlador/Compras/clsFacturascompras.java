/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Compras;

//Librerias
import java.util.Date;
/**
 *
 * @author Isaias Cedillo 9959-24-1672
 */
public class clsFacturascompras  {
   // Atributos
    private int Faccomid;
    private String Faccomnumero;
    private int Provcodigo;
    private String Provnombre;
    private int Impid;

    // Se quitó Faccomiva
    private double Faccomsubtotal;
    private double Faccomtotal;

    // GETTERS
    public int getFaccomid() {
        return Faccomid;
    }

    public String getFaccomnumero() {
        return Faccomnumero;
    }

    public int getProvcodigo() {
        return Provcodigo;
    }

    public String getProvnombre() {
        return Provnombre;
    }

    public int getImpid() {
        return Impid;
    }

    public double getFaccomsubtotal() {
        return Faccomsubtotal;
    }

    public double getFaccomtotal() {
        return Faccomtotal;
    }

    // SETTERS
    public void setFaccomid(int Faccomid) {
        this.Faccomid = Faccomid;
    }

    public void setFaccomnumero(String Faccomnumero) {
        this.Faccomnumero = Faccomnumero;
    }

    public void setProvcodigo(int Provcodigo) {
        this.Provcodigo = Provcodigo;
    }

    public void setProvnombre(String Provnombre) {
        this.Provnombre = Provnombre;
    }

    public void setImpid(int Impid) {
        this.Impid = Impid;
    }

    public void setFaccomsubtotal(double Faccomsubtotal) {
        this.Faccomsubtotal = Faccomsubtotal;
    }

    public void setFaccomtotal(double Faccomtotal) {
        this.Faccomtotal = Faccomtotal;
    }

    // Constructor vacío
    public clsFacturascompras() {
    }

    // Constructor sin ID
    public clsFacturascompras(String Faccomnumero,
                              int Provcodigo,
                              String Provnombre,
                              int Impid,
                              double Faccomsubtotal,
                              double Faccomtotal) {

        this.Faccomnumero = Faccomnumero;
        this.Provcodigo = Provcodigo;
        this.Provnombre = Provnombre;
        this.Impid = Impid;
        this.Faccomsubtotal = Faccomsubtotal;
        this.Faccomtotal = Faccomtotal;
    }

    // Constructor completo
    public clsFacturascompras(int Faccomid,
                              String Faccomnumero,
                              int Provcodigo,
                              String Provnombre,
                              int Impid,
                              double Faccomsubtotal,
                              double Faccomtotal) {

        this.Faccomid = Faccomid;
        this.Faccomnumero = Faccomnumero;
        this.Provcodigo = Provcodigo;
        this.Provnombre = Provnombre;
        this.Impid = Impid;
        this.Faccomsubtotal = Faccomsubtotal;
        this.Faccomtotal = Faccomtotal;
    }

    // toString
    @Override
    public String toString() {

        return "clsFacturascompras{" +
                "Faccomid=" + Faccomid +
                ", Faccomnumero=" + Faccomnumero +
                ", Provcodigo=" + Provcodigo +
                ", Provnombre=" + Provnombre +
                ", Impid=" + Impid +
                ", Faccomsubtotal=" + Faccomsubtotal +
                ", Faccomtotal=" + Faccomtotal +
                '}';
    }
}
