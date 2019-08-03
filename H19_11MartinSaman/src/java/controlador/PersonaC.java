package controlador;

import dao.PersonaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Persona;

@Named(value = "personaC")
@SessionScoped
public class PersonaC implements Serializable {

    Persona persona, personaSeleccionada;
    PersonaImpl daoPersona;
    List<Persona> listaPrsona, listaPrsonaFiltrado;

    public PersonaC() {
        persona = new Persona();
        daoPersona = new PersonaImpl();
        listaPrsona = new ArrayList<>();
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
            listaPrsona = daoPersona.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrar() throws Exception {
        try {
            if (daoPersona.existe(persona, listaPrsona) == false) {
                daoPersona.registrar(persona);
                listar();
                persona.clear();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Persona registrada correctamente.",
                                null));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL,
                                "La persona a la que intentaste registrar, ya existe.",
                                null));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editar() throws Exception {
        try {
            daoPersona.editar(personaSeleccionada);
            listar();
            personaSeleccionada.clear();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Persona modificada correctamente.",
                            null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar() throws Exception {
        try {
            daoPersona.eliminar(personaSeleccionada);
            listar();
            personaSeleccionada.clear();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Persona eliminada correctamente.",
                            null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Persona> getListaPrsonaFiltrado() {
        return listaPrsonaFiltrado;
    }

    public void setListaPrsonaFiltrado(List<Persona> listaPrsonaFiltrado) {
        this.listaPrsonaFiltrado = listaPrsonaFiltrado;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Persona getPersonaSeleccionada() {
        return personaSeleccionada;
    }

    public void setPersonaSeleccionada(Persona personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
    }

    public List<Persona> getListaPrsona() {
        return listaPrsona;
    }

    public void setListaPrsona(List<Persona> listaPrsona) {
        this.listaPrsona = listaPrsona;
    }

}
