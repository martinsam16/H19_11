package modelo;

import java.util.Date;

public class Venta {

    private Login vendedor = new Login();
    private Persona comprador = new Persona();
    private int IDVEN;
    private Date FECVEN;
    private String ESTVEN, TIPVEN;
    private float TOTVEN;
    
    public void clear(){
        this.comprador.clear();
        this.TOTVEN = 0;
    }

    public Login getVendedor() {
        return vendedor;
    }

    public void setVendedor(Login vendedor) {
        this.vendedor = vendedor;
    }

    public Persona getComprador() {
        return comprador;
    }

    public void setComprador(Persona comprador) {
        this.comprador = comprador;
    }

    public int getIDVEN() {
        return IDVEN;
    }

    public void setIDVEN(int IDVEN) {
        this.IDVEN = IDVEN;
    }

    public Date getFECVEN() {
        return FECVEN;
    }

    public void setFECVEN(Date FECVEN) {
        this.FECVEN = FECVEN;
    }

    public String getESTVEN() {
        return ESTVEN;
    }

    public void setESTVEN(String ESTVEN) {
        this.ESTVEN = ESTVEN;
    }

    public String getTIPVEN() {
        return TIPVEN;
    }

    public void setTIPVEN(String TIPVEN) {
        this.TIPVEN = TIPVEN;
    }

    public float getTOTVEN() {
        return TOTVEN;
    }

    public void setTOTVEN(float TOTVEN) {
        this.TOTVEN = TOTVEN;
    }
}
