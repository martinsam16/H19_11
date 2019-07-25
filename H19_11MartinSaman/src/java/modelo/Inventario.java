package modelo;

import java.time.Instant;
import java.util.Date;

public class Inventario {

    private Equipo equipo = new Equipo();
    private Date FECINV = Date.from(Instant.now());
    private int IDINV, CNTINV=1;
    private String ESTINV, TIPINV = "E";

    public void clear() {
        this.equipo = new Equipo();
        this.FECINV = Date.from(Instant.now());
        this.ESTINV = null;
        this.TIPINV = "E";
        this.IDINV = 0;
        this.CNTINV = 1;
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
