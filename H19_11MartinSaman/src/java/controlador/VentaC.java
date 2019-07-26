package controlador;

import dao.InventarioImpl;
import dao.VentaDetalleImpl;
import dao.VentaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import modelo.Equipo;
import modelo.Inventario;
import modelo.Login;
import modelo.Venta;
import modelo.VentaDetalle;

@Named(value = "ventaC")
@SessionScoped
public class VentaC implements Serializable {

    VentaImpl daoVenta;
    VentaDetalleImpl daoDetalle;
    VentaDetalle detalle;
    Venta venta;
    List<VentaDetalle> listaDetalle, listaSeleccionada;
    List<Venta> listaVenta;
    List<Equipo> listaEquipoSeleccionado;
    Inventario inventario;
    InventarioImpl daoInventario;

    public VentaC() {
        daoVenta = new VentaImpl();
        daoDetalle = new VentaDetalleImpl();
        detalle = new VentaDetalle();
        listaDetalle = new ArrayList<>();
        listaVenta = new ArrayList<>();
        venta = new Venta();
        inventario = new Inventario();
        daoInventario = new InventarioImpl();
    }

    @PostConstruct
    public void onInit() {
        try {
            listarVenta();
            listarDetalle();
        } catch (Exception e) {
        }
    }

    public void listarVenta() throws Exception {
        try {
            listaVenta = daoVenta.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarDetalle() throws Exception {
        try {
            listaDetalle = daoDetalle.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrar(Login login) throws Exception {
        try {
            if (listaEquipoSeleccionado.size() > 0) {
                venta.setVendedor(login);
                listaEquipoSeleccionado.forEach((eq) -> {
                    venta.setTOTVEN(
                            venta.getTOTVEN() + (eq.getCantidadVender() * eq.getPREEQ())
                    );
                });

                daoVenta.registrar(venta);
                listarVenta();
                venta.setIDVEN(listaVenta.get(listaVenta.size() - 1).getIDVEN());
                detalle.setVenta(venta);
                listaEquipoSeleccionado.forEach((eq) -> {
                    try {
                        detalle.setEquipo(eq);
                        detalle.setTODETVEN(eq.getPREEQ() * eq.getCantidadVender());
                        daoDetalle.registrar(detalle);

                        inventario.setTIPINV("S");
                        inventario.setCNTINV(eq.getCantidadVender());
                        inventario.setEquipo(eq);
                        daoInventario.registrar(inventario);
                        inventario.clear();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                listarDetalle();
                listaEquipoSeleccionado.clear();
//            venta.clear();
//            detalle.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public VentaDetalle getDetalle() {
        return detalle;
    }

    public void setDetalle(VentaDetalle detalle) {
        this.detalle = detalle;
    }

    public List<VentaDetalle> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(List<VentaDetalle> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }

    public List<VentaDetalle> getListaSeleccionada() {
        return listaSeleccionada;
    }

    public void setListaSeleccionada(List<VentaDetalle> listaSeleccionada) {
        this.listaSeleccionada = listaSeleccionada;
    }

    public List<Venta> getListaVenta() {
        return listaVenta;
    }

    public void setListaVenta(List<Venta> listaVenta) {
        this.listaVenta = listaVenta;
    }

    public List<Equipo> getListaEquipoSeleccionado() {
        return listaEquipoSeleccionado;
    }

    public void setListaEquipoSeleccionado(List<Equipo> listaEquipoSeleccionado) {
        this.listaEquipoSeleccionado = listaEquipoSeleccionado;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}
