package dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import modelo.Venta;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

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
        } finally {
            this.desconectar();
        }
        return lista;
    }

    @Override
    public StreamedContent generarReporte(Venta modelo) throws Exception {
        InputStream inputStream = null;

        Map parameters = new HashMap();

        parameters.put("idVenta", modelo.getIDVEN());

        try {

            ByteArrayOutputStream Teste = new ByteArrayOutputStream();
            File jasperReport = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Venta_Boleta.jasper"));

            JasperPrint print = JasperFillManager.fillReport(jasperReport.getPath(), parameters, this.conectar());

            JRExporter exporter = new net.sf.jasperreports.engine.export.JRPdfExporter();

            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, Teste);

            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            
            exporter.setParameter(JRPdfExporterParameter.PDF_JAVASCRIPT, "this.print();");
            
            exporter.exportReport();

            inputStream = new ByteArrayInputStream(Teste.toByteArray());

        } catch (JRException e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }

        return new DefaultStreamedContent(inputStream, "application/pdf", "Boleta_" + String.valueOf(modelo.getFECVEN()));

    }

    @Override
    public boolean existe(Venta modelo, List<Venta> listaModelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
