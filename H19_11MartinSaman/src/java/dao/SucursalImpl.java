package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Sucursal;
import org.primefaces.model.StreamedContent;

public class SucursalImpl extends Conexion implements ICrud<Sucursal> {

    @Override
    public void registrar(Sucursal modelo) throws Exception {
        try {
            String sql = "INSERT INTO SUCURSAL (NOMSUC, DIRSUC) VALUES (?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOMSUC().trim());
            ps.setString(2, modelo.getDIRSUC().trim());
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
    public void editar(Sucursal modelo) throws Exception {
        try {
            String sql = "UPDATE SUCURSAL SET NOMSUC=?, DIRSUC=? WHERE IDSUC=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOMSUC());
            ps.setString(2, modelo.getDIRSUC());
            ps.setInt(3, modelo.getIDSUC());
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
    public void eliminar(Sucursal modelo) throws Exception {
        try {
            String sql = "UPDATE SUCURSAL SET ESTSUC='I' WHERE IDSUC=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getIDSUC());
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
    public Sucursal obtenerModelo(Sucursal modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sucursal> listar() throws Exception {
        List<Sucursal> listaSucursal = new ArrayList<>();
        try {
            String sql = "SELECT IDSUC, NOMSUC, DIRSUC, ESTSUC FROM SUCURSAL WHERE ESTSUC='A'";
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            while (rs.next()) {
                Sucursal sucursal = new Sucursal();
                sucursal.setIDSUC(rs.getInt(1));
                sucursal.setNOMSUC(rs.getString(2));
                sucursal.setDIRSUC(rs.getString(3));
                sucursal.setESTSUC(rs.getString(4));
                listaSucursal.add(sucursal);
            }
            rs.clearWarnings();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return listaSucursal;
    }

    @Override
    public StreamedContent generarReporte(Sucursal modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existe(Sucursal modelo, List<Sucursal> listaModelo) {
        for (Sucursal sucursal : listaModelo) {
            if (modelo.equals(sucursal)) {
                return true;
            }
        }
        return false;
    }

}
