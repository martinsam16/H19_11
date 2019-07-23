package modelo;

public class Login {

    private Trabajador trabajador;
    private int IDLOG;
    private String USRLOG, PSSWLOG, ESTLOG, TIPLOG;

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public int getIDLOG() {
        return IDLOG;
    }

    public void setIDLOG(int IDLOG) {
        this.IDLOG = IDLOG;
    }

    public String getUSRLOG() {
        return USRLOG;
    }

    public void setUSRLOG(String USRLOG) {
        this.USRLOG = USRLOG;
    }

    public String getPSSWLOG() {
        return PSSWLOG;
    }

    public void setPSSWLOG(String PSSWLOG) {
        this.PSSWLOG = PSSWLOG;
    }

    public String getESTLOG() {
        return ESTLOG;
    }

    public void setESTLOG(String ESTLOG) {
        this.ESTLOG = ESTLOG;
    }

    public String getTIPLOG() {
        return TIPLOG;
    }

    public void setTIPLOG(String TIPLOG) {
        this.TIPLOG = TIPLOG;
    }

}
