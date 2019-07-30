package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import modelo.Login;
import modelo.Sucursal;
import modelo.Trabajador;
import org.primefaces.model.StreamedContent;

public class LoginImpl extends Conexion implements ICrud<Login> {
    
    @Override
    public void registrar(Login modelo) throws Exception {
        try {
            String sql = "INSERT INTO LOGIN (IDTRAB, USRLOG, PSSWLOG, TIPLOG) VALUES (?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getTrabajador().getIDTRAB());
            ps.setString(2, modelo.getTrabajador().getPersona().getNOMPER().trim().replace(" ", ".").toLowerCase());
            ps.setString(3, modelo.getTrabajador().getPersona().getAPEPER().trim().replace(" ", ".").toLowerCase());
            ps.setString(4, modelo.getTrabajador().getTIPTRAB());
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
    public void editar(Login modelo) throws Exception {
        try {
            String sql = "UPDATE LOGIN SET TIPLOG=? WHERE IDTRAB=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getTrabajador().getTIPTRAB());
            ps.setInt(2, modelo.getTrabajador().getIDTRAB());
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
    public void eliminar(Login modelo) throws Exception {
        try {
            String sql = "UPDATE LOGIN SET ESTLOG='I' WHERE IDTRAB=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getTrabajador().getIDTRAB());
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
    public Login obtenerModelo(Login modelo) throws Exception {
        Login login = new Login();
        try {
            String sql = "SELECT login.IDLOG, login.TIPLOG, login.ESTLOG, login.IDTRAB, trabajador.IDSUC FROM LOGIN login "
                    + "INNER JOIN TRABAJADOR trabajador "
                    + "ON login.IDTRAB = trabajador.IDTRAB "
                    + "WHERE login.USRLOG=? AND login.PSSWLOG=? AND login.ESTLOG='A'";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getUSRLOG());
            ps.setString(2, modelo.getPSSWLOG());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Trabajador trabajador = new Trabajador();
                Sucursal sucursal = new Sucursal();
                login.setIDLOG(rs.getInt(1));
                login.setTIPLOG(rs.getString(2));
                login.setESTLOG(rs.getString(3));
                trabajador.setIDTRAB(rs.getInt(4));
                sucursal.setIDSUC(rs.getInt(5));
                trabajador.setSucursal(sucursal);
                login.setTrabajador(trabajador);
            }
            rs.clearWarnings();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return login;
    }
    
    @Override
    public List<Login> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamedContent generarReporte(Login modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
