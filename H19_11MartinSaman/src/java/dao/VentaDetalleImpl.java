package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.Equipo;
import modelo.Login;
import modelo.Persona;
import modelo.Sucursal;
import modelo.Trabajador;
import modelo.Venta;
import modelo.VentaDetalle;
import org.primefaces.model.StreamedContent;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<VentaDetalle> listar(Login loginSesion, Date... fechas) throws Exception {
        List<VentaDetalle> lista = new ArrayList<>();
        try {
            String adicional = null;
            List<Date> listaFechas = new ArrayList<>();
            for (Date date : fechas) {
                if (date != null) {
                    listaFechas.add(date);
                }
            }
            String sql = "SELECT idven, fecven, estven, totven, "
                    + "	idComprador, nomComprador, apeComprador, "
                    + "	idVendedor, nomVendedor, apeVendedor, "
                    + "	idsuc, nomsuc, "
                    + "	ideq, nomeq, mareq, modeq, preeq, "
                    + "	detId, detalleCntV, detalleTotVen "
                    + "FROM VW_VENTAS WHERE "
                            .concat(listaFechas.size() == 1 ? "fecven = '" + new java.sql.Date(listaFechas.get(0).getTime()) + "'"
                                    : listaFechas.size() == 2 ? "fecven >= '" + new java.sql.Date(listaFechas.get(0).getTime()) + "'"
                                    + " AND fecven <= '" + new java.sql.Date(listaFechas.get(1).getTime()) + "'" : "")
                    + " AND idsuc = " + loginSesion.getTrabajador().getSucursal().getIDSUC()
                    + " ORDER BY idven DESC";
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
                equipo.setPREEQ(rs.getFloat(17));

                detalle.setIDVENDET(rs.getInt(18));
                detalle.setCNTVEN(rs.getInt(19));
                detalle.setTODETVEN(rs.getFloat(20));

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

    @Override
    public StreamedContent generarReporte(VentaDetalle modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existe(VentaDetalle modelo, List<VentaDetalle> listaModelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
