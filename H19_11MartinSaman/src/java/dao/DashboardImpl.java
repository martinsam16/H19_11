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

    public BarChartModel barVentas(int idsucursal) throws Exception {
        BarChartModel model = new BarChartModel();
        model.setTitle("Ventas por Equipos Informáticos");
        model.setLegendPosition("ne");
        model.setAnimate(true);
        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setLabel("Mes");

        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad Vendida");
        yAxis.setMin(0);
        yAxis.setMax(10);
        try {

            List<Equipo> listaEquipos = new ArrayList<>();

            String sql = "SELECT eq.IDEQ, eq.NOMEQ, MAX(inv.CNTINV) as cantidadMaxima FROM EQUIPO eq "
                    + "INNER JOIN INVENTARIO inv "
                    + "ON eq.IDEQ = inv.IDEQ "
                    + "WHERE inv.TIPINV='S' "
                    + "GROUP BY eq.IDEQ, eq.NOMEQ";
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            while (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setIDEQ(rs.getInt(1));
                equipo.setNOMEQ(rs.getString(2));
                equipo.setCantidadInventario(rs.getInt(3));
                listaEquipos.add(equipo);
            }

            for (Equipo equipoT : listaEquipos) {
                ChartSeries eq = new ChartSeries();
                
                eq.setLabel(equipoT.getNOMEQ());
                if (equipoT.getCantidadInventario() > (int) yAxis.getMax()) {
                    yAxis.setMax(equipoT.getCantidadInventario()+2);
                }

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
                    if (idsucursal == 0) {
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
                    } else {
                        sql = "SELECT detalle.IDEQ, SUM(detalle.CNTVEN) as cntTotal FROM VENTA venta "
                                + "INNER JOIN VENTA_DETALLE detalle "
                                + "ON venta.IDVEN = detalle.IDVEN "
                                + "INNER JOIN EQUIPO eq "
                                + "ON detalle.IDEQ = eq.IDEQ "
                                + "INNER JOIN LOGIN login "
                                + "ON venta.IDLOG = login.IDLOG "
                                + "INNER JOIN TRABAJADOR empleado "
                                + "ON login.IDTRAB = empleado.IDTRAB "
                                + "INNER JOIN SUCURSAL sucursal "
                                + "ON empleado.IDSUC = sucursal.IDSUC "
                                + "WHERE venta.ETSVEN LIKE 'A' "
                                + "AND YEAR(venta.FECVEN) = YEAR(GETDATE()) "
                                + "AND MONTH(venta.FECVEN) = ? "
                                + "AND detalle.IDEQ = ? "
                                + "AND sucursal.IDSUC = ? "
                                + "GROUP BY detalle.IDEQ, eq.NOMEQ";
                    }

                    PreparedStatement ps = this.conectar().prepareStatement(sql);
                    ps.setInt(1, i);
                    ps.setInt(2, equipoT.getIDEQ());
                    if (idsucursal != 0) {
                        ps.setInt(3, idsucursal);
                    }
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

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return model;
    }
}
