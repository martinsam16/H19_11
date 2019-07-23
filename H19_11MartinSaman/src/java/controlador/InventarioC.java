package controlador;

import dao.InventarioImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import modelo.Inventario;

@Named(value = "inventarioC")
@SessionScoped
public class InventarioC implements Serializable {

    Inventario inventario, inventarioSeleccionado;
    List<Inventario> listaInventario;
    InventarioImpl daoInventario;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editar() throws Exception {
        try {
            daoInventario.editar(inventarioSeleccionado);
            listar();
            inventarioSeleccionado.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar() throws Exception {
        try {
            daoInventario.eliminar(inventarioSeleccionado);
            listar();
            inventarioSeleccionado.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

}
