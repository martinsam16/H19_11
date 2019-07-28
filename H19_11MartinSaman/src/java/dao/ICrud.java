package dao;

import java.util.List;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.model.StreamedContent;

public interface ICrud<T> {

    public void registrar(T modelo) throws Exception;

    public void editar(T modelo) throws Exception;

    public void eliminar(T modelo) throws Exception;

    public T obtenerModelo(T modelo) throws Exception;

    public List<T> listar() throws Exception;

    public StreamedContent generarReporte(T modelo) throws JRException, Exception;

}
