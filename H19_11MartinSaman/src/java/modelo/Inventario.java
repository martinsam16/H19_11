package modelo;

import java.util.Date;

public class Inventario {

    private Equipo equipo = new Equipo();
    private Date FECINV;
    private int IDINV, CNTINV;
    private String ESTINV, TIPINV;

    public void clear() {
        this.equipo = new Equipo();
        this.FECINV = null;
        this.ESTINV = null;
        this.TIPINV = null;
        this.IDINV = 0;
        this.CNTINV = 0;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Date getFECINV() {
        return FECINV;
    }

    public void setFECINV(Date FECINV) {
        this.FECINV = FECINV;
    }

    public int getIDINV() {
        return IDINV;
    }

    public void setIDINV(int IDINV) {
        this.IDINV = IDINV;
    }

    public int getCNTINV() {
        return CNTINV;
    }

    public void setCNTINV(int CNTINV) {
        this.CNTINV = CNTINV;
    }

    public String getESTINV() {
        return ESTINV;
    }

    public void setESTINV(String ESTINV) {
        this.ESTINV = ESTINV;
    }

    public String getTIPINV() {
        return TIPINV;
    }

    public void setTIPINV(String TIPINV) {
        this.TIPINV = TIPINV;
    }
}
