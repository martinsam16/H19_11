package dao;

import java.util.List;

public interface ICrud<T> {

    public void registrar(T modelo) throws Exception;

    public void editar(T modelo) throws Exception;

    public void eliminar(T modelo) throws Exception;

    public T obtenerModelo(T modelo) throws Exception;

    public List<T> listar() throws Exception;

    public List<T> listar(T modelo) throws Exception;

    // Validaciones -- En este caso no es necesario crear una interface para las validaciones
    public boolean existe(T modelo, List<T> listaModelo);
}
