package modelo;

import java.util.Objects;

public class Persona {

    private int IDPER;
    private String NOMPER, APEPER, DNIPER, DIRPER, ESTPER;

    @Override
    public int hashCode() {
        int hash = 7;
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
        // Si ambos tienen dni comparo por eso, sino por nombres y apellidos
        final Persona other = (Persona) obj;
        if (this.DNIPER.isEmpty()) {
            if (!Objects.equals(this.NOMPER, other.NOMPER)) {
                return false;
            }
            if (!Objects.equals(this.APEPER, other.APEPER)) {
                return false;
            }
        } else {
            if (!Objects.equals(this.DNIPER, other.DNIPER)) {
                return false;
            }
        }

        return true;
    }

    public void clear() {
        this.IDPER = 0;
        this.NOMPER = null;
        this.APEPER = null;
        this.DNIPER = null;
        this.DIRPER = null;
        this.ESTPER = null;
    }

    public int getIDPER() {
        return IDPER;
    }

    public void setIDPER(int IDPER) {
        this.IDPER = IDPER;
    }

    public String getNOMPER() {
        return NOMPER;
    }

    public void setNOMPER(String NOMPER) {
        this.NOMPER = NOMPER;
    }

    public String getAPEPER() {
        return APEPER;
    }

    public void setAPEPER(String APEPER) {
        this.APEPER = APEPER;
    }

    public String getDNIPER() {
        return DNIPER;
    }

    public void setDNIPER(String DNIPER) {
        this.DNIPER = DNIPER;
    }

    public String getDIRPER() {
        return DIRPER;
    }

    public void setDIRPER(String DIRPER) {
        this.DIRPER = DIRPER;
    }

    public String getESTPER() {
        return ESTPER;
    }

    public void setESTPER(String ESTPER) {
        this.ESTPER = ESTPER;
    }

}
