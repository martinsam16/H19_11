package modelo;

public class Equipo {

    private int IDEQ;
    private String NOMEQ, MAREQ, MODEQ, ESTEQ;
    private float PREEQ;
    private int cantidadInventario, cantidadVender = 1;

    public void clear() {
        this.IDEQ = 0;
        this.PREEQ = 0;
        this.cantidadVender = 1;
        this.cantidadInventario = 0;
        this.NOMEQ = null;
        this.MAREQ = null;
        this.MODEQ = null;
        this.ESTEQ = null;
    }

    public int getCantidadInventario() {
        return cantidadInventario;
    }

    public void setCantidadInventario(int cantidadInventario) {
        this.cantidadInventario = cantidadInventario;
    }

    public int getCantidadVender() {
        return cantidadVender;
    }

    public void setCantidadVender(int cantidadVender) {
        this.cantidadVender = cantidadVender;
    }

    public int getIDEQ() {
        return IDEQ;
    }

    public void setIDEQ(int IDEQ) {
        this.IDEQ = IDEQ;
    }

    public String getNOMEQ() {
        return NOMEQ;
    }

    public void setNOMEQ(String NOMEQ) {
        this.NOMEQ = NOMEQ;
    }

    public String getMAREQ() {
        return MAREQ;
    }

    public void setMAREQ(String MAREQ) {
        this.MAREQ = MAREQ;
    }

    public String getMODEQ() {
        return MODEQ;
    }

    public void setMODEQ(String MODEQ) {
        this.MODEQ = MODEQ;
    }

    public String getESTEQ() {
        return ESTEQ;
    }

    public void setESTEQ(String ESTEQ) {
        this.ESTEQ = ESTEQ;
    }

    public float getPREEQ() {
        return PREEQ;
    }

    public void setPREEQ(float PREEQ) {
        this.PREEQ = PREEQ;
    }
}
