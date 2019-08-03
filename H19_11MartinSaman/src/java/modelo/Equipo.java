package modelo;

import java.util.Objects;

public class Equipo {

    private int IDEQ;
    private String NOMEQ, MAREQ, MODEQ, ESTEQ;
    private float PREEQ = (float) 0.1;
    private int cantidadInventario, cantidadVender = 1;

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Equipo other = (Equipo) obj;
        if (!Objects.equals(this.NOMEQ, other.NOMEQ.trim())) {
            return false;
        }
        if (!Objects.equals(this.MAREQ, other.MAREQ.trim())) {
            return false;
        }
        if (!Objects.equals(this.MODEQ, other.MODEQ.trim())) {
            return false;
        }
        return true;
    }

    public void clear() {
        this.IDEQ = 0;
        this.PREEQ = (float) 0.1;
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
