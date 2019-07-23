package controlador;

import dao.SucursalImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import modelo.Sucursal;

@Named(value = "sucursalC")
@SessionScoped
public class SucursalC implements Serializable {

    Sucursal sucursal, sucursalSeleccionada;
    List<Sucursal> listaSucursal;
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
            daoSucursal.registrar(sucursal);
            listar();
            sucursal.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editar() throws Exception {
        try {
            daoSucursal.editar(sucursalSeleccionada);
            listar();
            sucursalSeleccionada.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar() throws Exception {
        try {
            daoSucursal.eliminar(sucursalSeleccionada);
            listar();
            sucursalSeleccionada.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
