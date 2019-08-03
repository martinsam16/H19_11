package controlador;

import dao.EquipoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Equipo;

@Named(value = "equipoC")
@SessionScoped
public class EquipoC implements Serializable {

    Equipo equipo, equipoSeleccionado;
    List<Equipo> listaEquipo, listaEquipoFiltrado;
    EquipoImpl daoEquipo;

    public EquipoC() {
        daoEquipo = new EquipoImpl();
        equipo = new Equipo();
        listaEquipo = new ArrayList<>();
    }

    @PostConstruct
    public void onInit() {
        try {
            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrar() throws Exception {
        try {
            if (daoEquipo.existe(equipo, listaEquipo) == false) {
                daoEquipo.registrar(equipo);
                listar();
                equipo.clear();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Equipo guardado correctamente.",
                                null));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL,
                                "El equipo al que intentaste registrar, ya existe.",
                                null));
            }

        } catch (Exception e) {
        }
    }

    public void editar() throws Exception {
        try {
            daoEquipo.editar(equipoSeleccionado);
            listar();
            equipoSeleccionado.clear();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Equipo modificado correctamente.",
                            null));
        } catch (Exception e) {
        }
    }

    public void eliminar() throws Exception {
        try {
            daoEquipo.eliminar(equipoSeleccionado);
            listar();
            equipoSeleccionado.clear();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Equipo eliminado correctamente.",
                            null));
        } catch (Exception e) {
        }
    }

    public void listar() throws Exception {
        try {
            listaEquipo = daoEquipo.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Equipo> getListaEquipoFiltrado() {
        return listaEquipoFiltrado;
    }

    public void setListaEquipoFiltrado(List<Equipo> listaEquipoFiltrado) {
        this.listaEquipoFiltrado = listaEquipoFiltrado;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Equipo getEquipoSeleccionado() {
        return equipoSeleccionado;
    }

    public void setEquipoSeleccionado(Equipo equipoSeleccionado) {
        this.equipoSeleccionado = equipoSeleccionado;
    }

    public List<Equipo> getListaEquipo() {
        return listaEquipo;
    }

    public void setListaEquipo(List<Equipo> listaEquipo) {
        this.listaEquipo = listaEquipo;
    }

}
