package controlador;

import dao.DashboardImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import org.primefaces.model.chart.BarChartModel;

@Named(value = "dashboardC")
@SessionScoped
public class DashboardC implements Serializable {

    DashboardImpl daoDashboard;
    BarChartModel barVentas;
    int idsuc;

    public DashboardC() {
        daoDashboard = new DashboardImpl();
        barVentas = new BarChartModel();
    }

    @PostConstruct
    public void onInit() {
        listar();
    }

    public void listar() {
        try {
            barVentas = daoDashboard.barVentas(idsuc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BarChartModel getBarVentas() {
        return barVentas;
    }

    public void setBarVentas(BarChartModel barVentas) {
        this.barVentas = barVentas;
    }

    public int getIdsuc() {
        return idsuc;
    }

    public void setIdsuc(int idsuc) {
        this.idsuc = idsuc;
    }

}
