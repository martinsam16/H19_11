package controlador;

import dao.EquipoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import modelo.Equipo;

@Named(value = "equipoC")
@SessionScoped
public class EquipoC implements Serializable {

    Equipo equipo, equipoSeleccionado;
    List<Equipo> listaEquipo;
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
            daoEquipo.registrar(equipo);
            listar();
            equipo.clear();
        } catch (Exception e) {
        }
    }

    public void editar() throws Exception {
        try {
            daoEquipo.editar(equipoSeleccionado);
            listar();
            equipoSeleccionado.clear();
        } catch (Exception e) {
        }
    }

    public void eliminar() throws Exception {
        try {
            daoEquipo.eliminar(equipoSeleccionado);
            listar();
            equipoSeleccionado.clear();
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
