package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Venta;

public class VentaImpl extends Conexion implements ICrud<Venta> {

    @Override
    public void registrar(Venta modelo) throws Exception {
        try {
            String sql = "INSERT INTO VENTA (IDLOG, IDPER, TOTVEN) VALUES (?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getVendedor().getIDLOG());
            ps.setInt(2, modelo.getComprador().getIDPER());
            ps.setFloat(3, modelo.getTOTVEN());
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
    public void editar(Venta modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Venta modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Venta obtenerModelo(Venta modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Venta> listar() throws Exception {
        List<Venta> lista = new ArrayList<>();
        try {
            String sql = "SELECT TOP(1) IDVEN FROM VENTA ORDER BY IDVEN DESC";
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setIDVEN(rs.getInt(1));
                lista.add(venta);
            }
            rs.clearWarnings();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            this.desconectar();
        }
        return lista;
    }

}
