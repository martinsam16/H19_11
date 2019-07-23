package modelo;

public class Sucursal {

    private int IDSUC;
    private String NOMSUC, DIRSUC, ESTSUC;
    
    public void clear(){
        this.IDSUC = 0;
        this.NOMSUC = null;
        this.DIRSUC = null;
        this.ESTSUC = null;
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
