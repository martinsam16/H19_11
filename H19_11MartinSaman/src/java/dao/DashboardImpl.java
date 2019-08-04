package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import modelo.Equipo;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

public class DashboardImpl extends Conexion {

    // Decisión si crear una interfaz para estadística de datos (pendiente)
    public BarChartModel barVentas(int idsucursal) throws Exception {

        List<Equipo> lista = new ArrayList<>();
        try {

            String sql = null;
            if (idsucursal == 0) {
                sql = "SELECT eq.IDEQ, eq.NOMEQ as nombre,\n"
                        + "SUM(detalle.CNTVEN) as cntTotalVendida, \n"
                        + "DATENAME(MONTH, STR(MONTH(venta.FECVEN))+'/1/11') as mes \n"
                        + "FROM VENTA venta \n"
                        + "INNER JOIN VENTA_DETALLE detalle \n"
                        + "ON venta.IDVEN = detalle.IDVEN \n"
                        + "INNER JOIN EQUIPO eq \n"
                        + "ON detalle.IDEQ = eq.IDEQ  \n"
                        + "WHERE venta.ETSVEN = 'A' \n"
                        + "AND YEAR(venta.FECVEN) = YEAR(GETDATE()) \n"
                        + "AND MONTH(venta.FECVEN) BETWEEN 1 AND 12\n"
                        + "GROUP BY eq.IDEQ, eq.NOMEQ, MONTH(venta.FECVEN)";
            } else {
                sql = "SELECT detalle.IDEQ, eq.NOMEQ as nombre,\n"
                        + "SUM(detalle.CNTVEN) as cntTotalVendida, \n"
                        + "DATENAME(MONTH, STR(MONTH(venta.FECVEN))+'/1/11') as mes \n"
                        + "FROM VENTA venta \n"
                        + "INNER JOIN VENTA_DETALLE detalle \n"
                        + "ON venta.IDVEN = detalle.IDVEN \n"
                        + "INNER JOIN EQUIPO eq \n"
                        + "ON detalle.IDEQ = eq.IDEQ \n"
                        + "INNER JOIN LOGIN login \n"
                        + "ON venta.IDLOG = login.IDLOG \n"
                        + "INNER JOIN TRABAJADOR empleado \n"
                        + "ON login.IDTRAB = empleado.IDTRAB \n"
                        + "INNER JOIN SUCURSAL sucursal \n"
                        + "ON empleado.IDSUC = sucursal.IDSUC \n"
                        + "WHERE venta.ETSVEN = 'A' \n"
                        + "AND YEAR(venta.FECVEN) = YEAR(GETDATE()) \n"
                        + "AND MONTH(venta.FECVEN) BETWEEN 1 AND 12 \n"
                        + "AND sucursal.IDSUC = " + idsucursal + " \n"
                        + "GROUP BY detalle.IDEQ, eq.NOMEQ, MONTH(venta.FECVEN)";
            }
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);

            while (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setIDEQ(rs.getInt(1));
                equipo.setNOMEQ(rs.getString(2));
                equipo.setCantidadInventario(rs.getInt(3));
                equipo.setESTEQ(rs.getString(4)); // Mes
                lista.add(equipo);
            }
            rs.clearWarnings();
            rs.close();
            System.out.println("Listó");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        BarChartModel model = new BarChartModel();
        model.setTitle("Ventas por Equipos Informáticos");
        model.setLegendPosition("ne");
        model.setAnimate(true);
        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setLabel("Mes");

        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad Vendida");
        yAxis.setMin(0);

        String[] meses = {
            "January", "February", "March", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"
        };
        lista.stream().map((Equipo tmp) -> {
            ChartSeries eq = new ChartSeries();
            Map<Object, Number> datos = new HashMap<>();
            eq.setLabel(tmp.getNOMEQ());
            for (String mes : meses) {
                lista.stream().filter((tmp2) -> (tmp2.getESTEQ() == null ? mes == null : tmp2.getESTEQ().equals(mes))).filter((tmp2) -> (tmp2.getIDEQ() == tmp.getIDEQ())).forEachOrdered((_item) -> {
                    datos.put(mes, _item.getCantidadInventario());
                });
            }
            eq.setData(datos);
            return eq;
        }).forEachOrdered((eq) -> {
            model.addSeries(eq);
        });

        return model;
    }
}
