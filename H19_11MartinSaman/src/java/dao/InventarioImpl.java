package dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import modelo.Equipo;
import modelo.Inventario;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class InventarioImpl extends Conexion implements ICrud<Inventario>, IReporte<Inventario> {

    @Override
    public void registrar(Inventario modelo) throws Exception {
        try {
            String sql = "INSERT INTO INVENTARIO (FECINV, TIPINV, IDEQ, CNTINV) VALUES (?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setDate(1, new Date(modelo.getFECINV().getTime()));
            ps.setString(2, modelo.getTIPINV());
            ps.setInt(3, modelo.getEquipo().getIDEQ());
            ps.setInt(4, modelo.getCNTINV());
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
    public void editar(Inventario modelo) throws Exception {
        try {
            String sql = "UPDATE INVENTARIO SET FECINV=?, TIPINV=?, IDEQ=?, CNTINV=? WHERE IDINV=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setDate(1, new Date(modelo.getFECINV().getTime()));
            ps.setString(2, modelo.getTIPINV());
            ps.setInt(3, modelo.getEquipo().getIDEQ());
            ps.setInt(4, modelo.getCNTINV());
            ps.setInt(5, modelo.getIDINV());
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
    public void eliminar(Inventario modelo) throws Exception {
        try {
            String sql = "UPDATE INVENTARIO SET ESTINV='I' WHERE IDINV=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getIDINV());
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
    public Inventario obtenerModelo(Inventario modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Inventario> listar() throws Exception {
        List<Inventario> listaInventario = new ArrayList<>();
        try {
            String sql = "SELECT inv.IDINV, inv.FECINV, inv.TIPINV, inv.IDEQ, inv.CNTINV, inv.ESTINV, "
                    + "eq.NOMEQ, eq.MAREQ, eq.MODEQ FROM INVENTARIO inv "
                    + "INNER JOIN EQUIPO eq ON  inv.IDEQ = eq.IDEQ "
                    + "WHERE ESTINV='A'";
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            while (rs.next()) {
                Inventario inventario = new Inventario();
                Equipo equipo = new Equipo();
                inventario.setIDINV(rs.getInt(1));
                inventario.setFECINV(rs.getDate(2));
                inventario.setTIPINV(rs.getString(3));
                equipo.setIDEQ(rs.getInt(4));
                inventario.setCNTINV(rs.getInt(5));
                inventario.setESTINV(rs.getString(6));
                equipo.setNOMEQ(rs.getString(7));
                equipo.setMAREQ(rs.getString(8));
                equipo.setMODEQ(rs.getString(9));
                inventario.setEquipo(equipo);
                listaInventario.add(inventario);
            }
            rs.clearWarnings();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return listaInventario;
    }

    @Override
    public boolean existe(Inventario modelo, List<Inventario> listaModelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Inventario> listar(Inventario modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamedContent generarReporteIndividual(Inventario modelo) throws JRException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamedContent generarReporteGeneral(Inventario modelo) throws JRException, Exception {
        InputStream inputStream = null;

        try {

            ByteArrayOutputStream salida = new ByteArrayOutputStream();
            File jasperReport = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Inventario.jasper"));

            JasperPrint jPrint = JasperFillManager.fillReport(jasperReport.getPath(), null, this.conectar());

            JRExporter exporter = new net.sf.jasperreports.engine.export.JRPdfExporter();

            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, salida);

            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jPrint);

            exporter.setParameter(JRPdfExporterParameter.PDF_JAVASCRIPT, "this.print();");

            exporter.exportReport();

            inputStream = new ByteArrayInputStream(salida.toByteArray());

        } catch (JRException e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }

        return new DefaultStreamedContent(inputStream, "application/pdf", "Inventario");
    }

}
