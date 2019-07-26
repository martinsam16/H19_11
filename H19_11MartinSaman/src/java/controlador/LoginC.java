package controlador;

import dao.LoginImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Login;
import modelo.Trabajador;

@Named(value = "loginC")
@SessionScoped
public class LoginC implements Serializable {

    Login login, loginSeleccionado, loginSesion;
    LoginImpl daoLogin;

    public LoginC() {
        login = new Login();
        loginSesion = new Login();
        daoLogin = new LoginImpl();
    }

    public void iniciarSesion() throws Exception {
        try {
            loginSesion = daoLogin.obtenerModelo(loginSesion);
            if (loginSesion.getIDLOG() > 0 ) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/H19_11MartinSaman/faces/Home.xhtml");
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error credenciales"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void cerrarSesion() throws Exception{
        try {
            loginSesion.clear();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/H19_11MartinSaman");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void seguridadSesion() {
        try {
            if (loginSesion.getIDLOG() == 0) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/H19_11MartinSaman/faces/login.xhtml");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrar(Trabajador trabajador) throws Exception {
        try {
            login.setTrabajador(trabajador);
            daoLogin.registrar(login);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editar(Trabajador trabajador) throws Exception {
        try {
            login.setTrabajador(trabajador);
            daoLogin.editar(login);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar(Trabajador trabajador) throws Exception {
        try {
            login.setTrabajador(trabajador);
            daoLogin.eliminar(login);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Login getLoginSeleccionado() {
        return loginSeleccionado;
    }

    public void setLoginSeleccionado(Login loginSeleccionado) {
        this.loginSeleccionado = loginSeleccionado;
    }

    public Login getLoginSesion() {
        return loginSesion;
    }

    public void setLoginSesion(Login loginSesion) {
        this.loginSesion = loginSesion;
    }

}
