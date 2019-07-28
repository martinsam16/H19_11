package modelo;

import java.util.Date;

public class Trabajador {

    private Sucursal sucursal = new Sucursal();
    private Persona persona = new Persona();
    private int IDTRAB;
    private Date FECINITRAB, FECFINTRAB;
    private String ESTTRAB, TIPTRAB="V";

    @Override
    public String toString() {
        return "Trabajador{" + "sucursal=" + sucursal + ", persona=" + persona + ", IDTRAB=" + IDTRAB + ", FECINITRAB=" + FECINITRAB + ", FECFINTRAB=" + FECFINTRAB + ", ESTTRAB=" + ESTTRAB + ", TIPTRAB=" + TIPTRAB + '}';
    }
    

    public void clear() {
//        this.sucursal.clear();
//        this.persona.clear();
        this.IDTRAB = 0;
        this.FECINITRAB = null;
        this.FECFINTRAB = null;
        this.ESTTRAB = null;
        this.TIPTRAB = "V";
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public int getIDTRAB() {
        return IDTRAB;
    }

    public void setIDTRAB(int IDTRAB) {
        this.IDTRAB = IDTRAB;
    }

    public Date getFECINITRAB() {
        return FECINITRAB;
    }

    public void setFECINITRAB(Date FECINITRAB) {
        this.FECINITRAB = FECINITRAB;
    }

    public Date getFECFINTRAB() {
        return FECFINTRAB;
    }

    public void setFECFINTRAB(Date FECFINTRAB) {
        this.FECFINTRAB = FECFINTRAB;
    }

    public String getESTTRAB() {
        return ESTTRAB;
    }

    public void setESTTRAB(String ESTTRAB) {
        this.ESTTRAB = ESTTRAB;
    }

    public String getTIPTRAB() {
        return TIPTRAB;
    }

    public void setTIPTRAB(String TIPTRAB) {
        this.TIPTRAB = TIPTRAB;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
