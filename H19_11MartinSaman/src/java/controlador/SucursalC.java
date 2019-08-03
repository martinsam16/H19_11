package controlador;

import dao.SucursalImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Sucursal;

@Named(value = "sucursalC")
@SessionScoped
public class SucursalC implements Serializable {

    Sucursal sucursal, sucursalSeleccionada;
    List<Sucursal> listaSucursal, listaSucursalFiltrado;
    SucursalImpl daoSucursal;

    public SucursalC() {
        sucursal = new Sucursal();
        listaSucursal = new ArrayList<>();
        daoSucursal = new SucursalImpl();
    }

    @PostConstruct
    public void onInit() {
        try {
            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listar() throws Exception {
        try {
            listaSucursal = daoSucursal.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrar() throws Exception {
        try {
            if (daoSucursal.existe(sucursal, listaSucursal) == false) {
                daoSucursal.registrar(sucursal);
                listar();
                sucursal.clear();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Sucursal registrada correctamente.",
                                null));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL,
                                "La sucursal a la que intentaste registrar, ya existe.",
                                null));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editar() throws Exception {
        try {
            daoSucursal.editar(sucursalSeleccionada);
            listar();
            sucursalSeleccionada.clear();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Sucursal modificada correctamente.",
                            null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar() throws Exception {
        try {
            daoSucursal.eliminar(sucursalSeleccionada);
            listar();
            sucursalSeleccionada.clear();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Sucursal eliminada correctamente.",
                            null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Sucursal> getListaSucursalFiltrado() {
        return listaSucursalFiltrado;
    }

    public void setListaSucursalFiltrado(List<Sucursal> listaSucursalFiltrado) {
        this.listaSucursalFiltrado = listaSucursalFiltrado;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Sucursal getSucursalSeleccionada() {
        return sucursalSeleccionada;
    }

    public void setSucursalSeleccionada(Sucursal sucursalSeleccionada) {
        this.sucursalSeleccionada = sucursalSeleccionada;
    }

    public List<Sucursal> getListaSucursal() {
        return listaSucursal;
    }

    public void setListaSucursal(List<Sucursal> listaSucursal) {
        this.listaSucursal = listaSucursal;
    }

}
