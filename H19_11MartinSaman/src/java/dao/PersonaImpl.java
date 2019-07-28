package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Persona;
import org.primefaces.model.StreamedContent;

public class PersonaImpl extends Conexion implements ICrud<Persona> {

    @Override
    public Persona obtenerModelo(Persona modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Persona> listar() throws Exception {
        List<Persona> listaPersona = new ArrayList<>();
        try {
            String sql = "SELECT IDPER, NOMPER, APEPER, DNIPER, DIRPER, ESTPER FROM PERSONA WHERE ESTPER='A'";
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            while (rs.next()) {
                Persona persona = new Persona();
                persona.setIDPER(rs.getInt(1));
                persona.setNOMPER(rs.getString(2));
                persona.setAPEPER(rs.getString(3));
                persona.setDNIPER(rs.getString(4));
                persona.setDIRPER(rs.getString(5));
                persona.setESTPER(rs.getString(6));
                listaPersona.add(persona);
            }
            rs.clearWarnings();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return listaPersona;
    }

    @Override
    public void registrar(Persona modelo) throws Exception {
        try {
            String sql = "INSERT INTO PERSONA (NOMPER, APEPER, DNIPER, DIRPER) VALUES (?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOMPER());
            ps.setString(2, modelo.getAPEPER());
            ps.setString(3, modelo.getDNIPER());
            ps.setString(4, modelo.getDIRPER());
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void editar(Persona modelo) throws Exception {
        try {
            String sql = "UPDATE PERSONA SET NOMPER=?, APEPER=?, DNIPER=?, DIRPER=? WHERE IDPER=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOMPER());
            ps.setString(2, modelo.getAPEPER());
            ps.setString(3, modelo.getDNIPER());
            ps.setString(4, modelo.getDIRPER());
            ps.setInt(5, modelo.getIDPER());
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void eliminar(Persona modelo) throws Exception {
        try {
            String sql = "UPDATE PERSONA SET ESTPER='I' WHERE IDPER=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getIDPER());
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }

    @Override
    public StreamedContent generarReporte(Persona modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
