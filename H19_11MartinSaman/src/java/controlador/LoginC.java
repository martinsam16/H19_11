package controlador;

import dao.LoginImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
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

    public void registrar(Trabajador trabajador) throws Exception {
        try {
            login.setTrabajador(trabajador);
            daoLogin.registrar(login);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void editar(Trabajador trabajador) throws Exception{
        try {
            login.setTrabajador(trabajador);
            daoLogin.editar(login);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void eliminar(Trabajador trabajador) throws Exception{
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
