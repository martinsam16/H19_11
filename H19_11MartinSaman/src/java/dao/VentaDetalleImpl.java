package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Equipo;
import modelo.Login;
import modelo.Persona;
import modelo.Sucursal;
import modelo.Trabajador;
import modelo.Venta;
import modelo.VentaDetalle;

public class VentaDetalleImpl extends Conexion implements ICrud<VentaDetalle> {
    
    @Override
    public void registrar(VentaDetalle modelo) throws Exception {
        try {
            String sql = "INSERT INTO VENTA_DETALLE (IDVEN, IDEQ, CNTVEN, TOTVENDET) VALUES (?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getVenta().getIDVEN());
            ps.setInt(2, modelo.getEquipo().getIDEQ());
            ps.setInt(3, modelo.getEquipo().getCantidadVender());
            ps.setFloat(4, modelo.getTODETVEN());
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
    public void editar(VentaDetalle modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void eliminar(VentaDetalle modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public VentaDetalle obtenerModelo(VentaDetalle modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<VentaDetalle> listar() throws Exception {
        List<VentaDetalle> lista = new ArrayList<>();
        try {
            String sql = "SELECT venta.IDVEN, venta.FECVEN, venta.ETSVEN, venta.TOTVEN, \n"
                    + "	comprador.IDPER, comprador.NOMPER, comprador.APEPER, \n"
                    + "	vendedor.IDPER, vendedor.NOMPER, vendedor.APEPER, \n"
                    + "	sucursal.IDSUC, sucursal.NOMSUC, \n"
                    + "	eq.IDEQ, eq.NOMEQ, eq.MAREQ, eq.MODEQ, \n"
                    + "	detalle.IDVENDET,detalle.CNTVEN, detalle.TOTVENDET \n"
                    + "FROM VENTA venta \n"
                    + "INNER JOIN VENTA_DETALLE detalle \n"
                    + "ON venta.IDVEN = detalle.IDVEN \n"
                    + "INNER JOIN PERSONA comprador \n"
                    + "ON venta.IDPER = comprador.IDPER \n"
                    + "INNER JOIN LOGIN login \n"
                    + "ON venta.IDLOG = login.IDLOG \n"
                    + "INNER JOIN TRABAJADOR empleado \n"
                    + "ON login.IDTRAB = empleado.IDTRAB \n"
                    + "INNER JOIN SUCURSAL sucursal \n"
                    + "ON empleado.IDSUC = sucursal.IDSUC \n"
                    + "INNER JOIN PERSONA vendedor \n"
                    + "ON empleado.IDPER = vendedor.IDPER \n"
                    + "INNER JOIN EQUIPO eq \n"
                    + "ON detalle.IDEQ = eq.IDEQ "
                    + "ORDER BY venta.IDVEN DESC";
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            while (rs.next()) {
                Venta venta = new Venta();
                VentaDetalle detalle = new VentaDetalle();
                Persona vendedor = new Persona();
                Persona comprador = new Persona();
                Sucursal sucursal = new Sucursal();
                Equipo equipo = new Equipo();
                Login login = new Login();
                Trabajador empleado = new Trabajador();
                
                venta.setIDVEN(rs.getInt(1));
                venta.setFECVEN(rs.getDate(2));
                venta.setESTVEN(rs.getString(3));
                venta.setTOTVEN(rs.getFloat(4));
                
                comprador.setIDPER(rs.getInt(5));
                comprador.setNOMPER(rs.getString(6));
                comprador.setAPEPER(rs.getString(7));
                
                vendedor.setIDPER(rs.getInt(8));
                vendedor.setNOMPER(rs.getString(9));
                vendedor.setAPEPER(rs.getString(10));
                
                sucursal.setIDSUC(rs.getInt(11));
                sucursal.setNOMSUC(rs.getString(12));
                
                equipo.setIDEQ(rs.getInt(13));
                equipo.setNOMEQ(rs.getString(14));
                equipo.setMAREQ(rs.getString(15));
                equipo.setMODEQ(rs.getString(16));
                
                detalle.setIDVENDET(rs.getInt(17));
                detalle.setCNTVEN(rs.getInt(18));
                detalle.setTODETVEN(rs.getFloat(19));
                
                empleado.setPersona(vendedor);
                empleado.setSucursal(sucursal);
                login.setTrabajador(empleado);
                venta.setVendedor(login);
                venta.setComprador(comprador);
                
                detalle.setEquipo(equipo);
                
                detalle.setVenta(venta);
                lista.add(detalle);
            }
            rs.clearWarnings();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return lista;
    }
    
}
