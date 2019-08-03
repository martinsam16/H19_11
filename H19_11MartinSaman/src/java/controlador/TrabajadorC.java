package controlador;

import dao.TrabajadorImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import modelo.Login;
import modelo.Trabajador;

@Named(value = "trabajadorC")
@SessionScoped
public class TrabajadorC implements Serializable {

    Trabajador trabajador, trabajadorSeleccionado;
    TrabajadorImpl daoTrabajador;
    List<Trabajador> listaTrabajador, listaTrabajadorFiltrado;
    @ManagedProperty("#{loginC}")
    LoginC loginC = new LoginC();

    public TrabajadorC() {
        trabajador = new Trabajador();
        daoTrabajador = new TrabajadorImpl();
        trabajadorSeleccionado = new Trabajador();
        listaTrabajador = new ArrayList<>();
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
            listaTrabajador = daoTrabajador.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrar(Login login) throws Exception {
        try {
            if (login.getTIPLOG().equals("J")) {
                trabajador.setSucursal(login.getTrabajador().getSucursal());
            }
            if (daoTrabajador.existe(trabajador, listaTrabajador) == false) {
                daoTrabajador.registrar(trabajador);
                listar();
                loginC.registrar(listaTrabajador.get(listaTrabajador.size() - 1));
                trabajador.clear();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Trabajador registrado correctamente.",
                                null));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL,
                                "El trabajador al que intentaste registrar, ya está laborando.",
                                null));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editar() throws Exception {
        try {
            if (trabajadorSeleccionado.getFECFINTRAB() != null) {
                eliminar();
                return;
            }
            daoTrabajador.editar(trabajadorSeleccionado);
            loginC.editar(trabajadorSeleccionado);
            listar();
            trabajadorSeleccionado.clear();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Modificado correctamente.",
                            null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar() throws Exception {
        try {
            daoTrabajador.eliminar(trabajadorSeleccionado);
            loginC.eliminar(trabajadorSeleccionado);
            listar();
            trabajadorSeleccionado.clear();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Eliminación correcta.",
                            null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Trabajador> getListaTrabajadorFiltrado() {
        return listaTrabajadorFiltrado;
    }

    public void setListaTrabajadorFiltrado(List<Trabajador> listaTrabajadorFiltrado) {
        this.listaTrabajadorFiltrado = listaTrabajadorFiltrado;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public Trabajador getTrabajadorSeleccionado() {
        return trabajadorSeleccionado;
    }

    public void setTrabajadorSeleccionado(Trabajador trabajadorSeleccionado) {
        this.trabajadorSeleccionado = trabajadorSeleccionado;
    }

    public List<Trabajador> getListaTrabajador() {
        return listaTrabajador;
    }

    public void setListaTrabajador(List<Trabajador> listaTrabajador) {
        this.listaTrabajador = listaTrabajador;
    }

    public LoginC getLoginC() {
        return loginC;
    }

    public void setLoginC(LoginC loginC) {
        this.loginC = loginC;
    }

}
