package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Equipo;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

public class DashboardImpl extends Conexion {

    public BarChartModel barVentas() throws Exception {
        BarChartModel model = new BarChartModel();
        try {

            List<Equipo> listaEquipos = new ArrayList<>();

            String sql = "SELECT IDEQ, NOMEQ FROM EQUIPO";
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            while (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setIDEQ(rs.getInt(1));
                equipo.setNOMEQ(rs.getString(2));
                listaEquipos.add(equipo);
            }

            for (Equipo equipoT : listaEquipos) {
                ChartSeries eq = new ChartSeries();
                eq.setLabel(equipoT.getNOMEQ());
                for (int i = 1; i <= 12; i++) {
                    String mes = null;
                    switch (i) {
                        case 1:
                            mes = "Enero";
                            break;
                        case 2:
                            mes = "Febrero";
                            break;
                        case 3:
                            mes = "Marzo";
                            break;
                        case 4:
                            mes = "Abril";
                            break;
                        case 5:
                            mes = "Mayo";
                            break;
                        case 6:
                            mes = "Junio";
                            break;
                        case 7:
                            mes = "Julio";
                            break;
                        case 8:
                            mes = "Agosto";
                            break;
                        case 9:
                            mes = "Setiembre";
                            break;
                        case 10:
                            mes = "Octubre";
                            break;
                        case 11:
                            mes = "Noviembre";
                            break;
                        case 12:
                            mes = "Diciembre";
                            break;
                    }
                    sql = "SELECT detalle.IDEQ, SUM(detalle.CNTVEN) as cntTotal FROM VENTA venta "
                            + "INNER JOIN VENTA_DETALLE detalle "
                            + "ON venta.IDVEN = detalle.IDVEN "
                            + "INNER JOIN EQUIPO eq "
                            + "ON detalle.IDEQ = eq.IDEQ "
                            + "WHERE venta.ETSVEN LIKE 'A' "
                            + "AND YEAR(venta.FECVEN) = YEAR(GETDATE()) "
                            + "AND MONTH(venta.FECVEN) = ? "
                            + "AND detalle.IDEQ =? "
                            + "GROUP BY detalle.IDEQ, eq.NOMEQ";

                    PreparedStatement ps = this.conectar().prepareStatement(sql);
                    ps.setInt(1, i);
                    ps.setInt(2, equipoT.getIDEQ());
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        eq.set(mes, rs.getInt(2));
                    }
                    ps.clearParameters();
                    ps.close();
                }
                model.addSeries(eq);
            }
            rs.clearWarnings();
            rs.close();

            model.setTitle("Ventas por Equipos Informáticos");
            model.setLegendPosition("ne");

            Axis xAxis = model.getAxis(AxisType.X);
            xAxis.setLabel("Equipos Informáticos");

            Axis yAxis = model.getAxis(AxisType.Y);
            yAxis.setLabel("Cantidad Vendida");
            yAxis.setMin(0);
            yAxis.setMax(500);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return model;
    }
}
