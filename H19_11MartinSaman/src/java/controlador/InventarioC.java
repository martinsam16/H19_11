package controlador;

import dao.InventarioImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Inventario;
import org.primefaces.model.StreamedContent;

@Named(value = "inventarioC")
@SessionScoped
public class InventarioC implements Serializable {

    Inventario inventario, inventarioSeleccionado;
    List<Inventario> listaInventario, listaInventarioFiltrado;
    InventarioImpl daoInventario;
    StreamedContent reporte;

    public InventarioC() {
        inventario = new Inventario();
        inventarioSeleccionado = new Inventario();
        listaInventario = new ArrayList<>();
        daoInventario = new InventarioImpl();
    }

    @PostConstruct
    public void onInit() {
        try {
            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generarReporteGeneral() throws Exception {
        reporte = daoInventario.generarReporteGeneral(null);
    }

    public void listar() throws Exception {
        try {
            listaInventario = daoInventario.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrar() throws Exception {
        try {
            daoInventario.registrar(inventario);
            listar();
            inventario.clear();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Equipo inventariado.",
                            null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editar() throws Exception {
        try {
            daoInventario.editar(inventarioSeleccionado);
            listar();
            inventarioSeleccionado.clear();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Inventario de equipo modificado correctamente.",
                            null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar() throws Exception {
        try {
            daoInventario.eliminar(inventarioSeleccionado);
            listar();
            inventarioSeleccionado.clear();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Eliminación correcta.",
                            null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Inventario> getListaInventarioFiltrado() {
        return listaInventarioFiltrado;
    }

    public void setListaInventarioFiltrado(List<Inventario> listaInventarioFiltrado) {
        this.listaInventarioFiltrado = listaInventarioFiltrado;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public Inventario getInventarioSeleccionado() {
        return inventarioSeleccionado;
    }

    public void setInventarioSeleccionado(Inventario inventarioSeleccionado) {
        this.inventarioSeleccionado = inventarioSeleccionado;
    }

    public List<Inventario> getListaInventario() {
        return listaInventario;
    }

    public void setListaInventario(List<Inventario> listaInventario) {
        this.listaInventario = listaInventario;
    }

    public StreamedContent getReporte() {
        return reporte;
    }

    public void setReporte(StreamedContent reporte) {
        this.reporte = reporte;
    }

}
