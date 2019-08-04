package dao;

import net.sf.jasperreports.engine.JRException;
import org.primefaces.model.StreamedContent;

// La separo para evitar librerias innecesarias y por temas de escalabilidad
// No heredo de ICrud para que no dependa y pueda usarse en otras procesos que no requiera uno
public interface IReporte<T> {

    public StreamedContent generarReporteIndividual(T modelo) throws JRException, Exception;

    public StreamedContent generarReporteGeneral(T modelo) throws JRException, Exception;
}
