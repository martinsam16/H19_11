package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Persona;
import modelo.Sucursal;
import modelo.Trabajador;
import org.primefaces.model.StreamedContent;

public class TrabajadorImpl extends Conexion implements ICrud<Trabajador> {

    @Override
    public void registrar(Trabajador modelo) throws Exception {
        try {
            String sql = "INSERT INTO TRABAJADOR (IDPER, FECINITRAB, TIPTRAB, IDSUC) VALUES (?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getPersona().getIDPER());
            ps.setDate(2, new Date(modelo.getFECINITRAB().getTime()));
            ps.setString(3, modelo.getTIPTRAB());
            ps.setInt(4, modelo.getSucursal().getIDSUC());
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
    public void editar(Trabajador modelo) throws Exception {
        try {
            String sql = "UPDATE TRABAJADOR SET IDPER=?, FECINITRAB=?, FECFINTRAB=?, TIPTRAB=?, IDSUC=? WHERE IDTRAB=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getPersona().getIDPER());
            ps.setDate(2, new Date(modelo.getFECINITRAB().getTime()));
            if (modelo.getFECFINTRAB() == null) {
                ps.setNull(3, java.sql.Types.DATE);
            } else {
                ps.setDate(3, new Date(modelo.getFECFINTRAB().getTime()));
            }
            ps.setString(4, modelo.getTIPTRAB());
            ps.setInt(5, modelo.getSucursal().getIDSUC());
            ps.setInt(6, modelo.getIDTRAB());
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
    public void eliminar(Trabajador modelo) throws Exception {
        try {
            String sql = "UPDATE TRABAJADOR SET ESTTRAB='I' WHERE IDTRAB=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getIDTRAB());
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
    public Trabajador obtenerModelo(Trabajador modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Trabajador> listar() throws Exception {
        List<Trabajador> listaTrabajadores = new ArrayList<>();
        try {
            String sql = "SELECT trabajador.IDTRAB, trabajador.IDPER, trabajador.FECINITRAB, trabajador.FECFINTRAB, trabajador.TIPTRAB, trabajador.IDSUC, trabajador.ESTTRAB ,\n"
                    + "persona.NOMPER, persona.APEPER, persona.DNIPER,\n"
                    + "sucursal.NOMSUC\n"
                    + "FROM TRABAJADOR trabajador\n"
                    + "INNER JOIN dbo.PERSONA persona\n"
                    + "	ON trabajador.IDPER = persona.IDPER\n"
                    + "INNER JOIN dbo.SUCURSAL sucursal\n"
                    + "	ON trabajador.IDSUC = sucursal.IDSUC\n"
                    + "WHERE ESTTRAB='A'";
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            while (rs.next()) {
                Trabajador trabajador = new Trabajador();
                Persona persona = new Persona();
                Sucursal sucursal = new Sucursal();
                trabajador.setIDTRAB(rs.getInt(1));
                persona.setIDPER(rs.getInt(2));
                trabajador.setFECINITRAB(rs.getDate(3));
                trabajador.setFECFINTRAB(rs.getDate(4));
                trabajador.setTIPTRAB(rs.getString(5));
                sucursal.setIDSUC(rs.getInt(6));
                trabajador.setESTTRAB(rs.getString(7));
                persona.setNOMPER(rs.getString(8));
                persona.setAPEPER(rs.getString(9));
                persona.setDNIPER(rs.getString(10));
                sucursal.setNOMSUC(rs.getString(11));
                trabajador.setSucursal(sucursal);
                trabajador.setPersona(persona);
                listaTrabajadores.add(trabajador);
            }
            rs.clearWarnings();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return listaTrabajadores;
    }

    @Override
    public StreamedContent generarReporte(Trabajador modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existe(Trabajador modelo, List<Trabajador> listaModelo) {
        for (Trabajador trabajador : listaModelo) {
            if (modelo.equals(trabajador)) {
                return true;
            }
        }
        return false;
    }

}
