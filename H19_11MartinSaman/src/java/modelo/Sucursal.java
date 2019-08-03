package modelo;

import java.util.Objects;

public class Sucursal {

    private int IDSUC;
    private String NOMSUC, DIRSUC, ESTSUC;

    public void clear() {
        this.IDSUC = 0;
        this.NOMSUC = null;
        this.DIRSUC = null;
        this.ESTSUC = null;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Sucursal other = (Sucursal) obj;
        if (!Objects.equals(this.NOMSUC, other.NOMSUC.trim())) {
            return false;
        }
        return true;
    }

    public int getIDSUC() {
        return IDSUC;
    }

    public void setIDSUC(int IDSUC) {
        this.IDSUC = IDSUC;
    }

    public String getNOMSUC() {
        return NOMSUC;
    }

    public void setNOMSUC(String NOMSUC) {
        this.NOMSUC = NOMSUC;
    }

    public String getDIRSUC() {
        return DIRSUC;
    }

    public void setDIRSUC(String DIRSUC) {
        this.DIRSUC = DIRSUC;
    }

    public String getESTSUC() {
        return ESTSUC;
    }

    public void setESTSUC(String ESTSUC) {
        this.ESTSUC = ESTSUC;
    }
}
