package modelo;

public class VentaDetalle {

    private Venta venta;
    private Equipo equipo;
    private int CNTVEN, IDVENDET;
    private float TODETVEN;

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public int getCNTVEN() {
        return CNTVEN;
    }

    public void setCNTVEN(int CNTVEN) {
        this.CNTVEN = CNTVEN;
    }

    public int getIDVENDET() {
        return IDVENDET;
    }

    public void setIDVENDET(int IDVENDET) {
        this.IDVENDET = IDVENDET;
    }

    public float getTODETVEN() {
        return TODETVEN;
    }

    public void setTODETVEN(float TODETVEN) {
        this.TODETVEN = TODETVEN;
    }
}
