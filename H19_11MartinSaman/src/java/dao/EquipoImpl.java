package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Equipo;
import org.primefaces.model.StreamedContent;

public class EquipoImpl extends Conexion implements ICrud<Equipo> {

    @Override
    public void registrar(Equipo modelo) throws Exception {
        try {
            String sql = "INSERT INTO EQUIPO (NOMEQ, PREEQ, MAREQ, MODEQ) VALUES (?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOMEQ());
            ps.setFloat(2, modelo.getPREEQ());
            ps.setString(3, modelo.getMAREQ());
            ps.setString(4, modelo.getMODEQ());
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void editar(Equipo modelo) throws Exception {
        try {
            String sql = "UPDATE EQUIPO SET NOMEQ=?, PREEQ=?, MAREQ=?, MODEQ=? WHERE IDEQ=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOMEQ());
            ps.setFloat(2, modelo.getPREEQ());
            ps.setString(3, modelo.getMAREQ());
            ps.setString(4, modelo.getMODEQ());
            ps.setInt(5, modelo.getIDEQ());
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void eliminar(Equipo modelo) throws Exception {
        try {
            String sql = "UPDATE EQUIPO SET ESTEQ='I' WHERE IDEQ=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getIDEQ());
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Equipo obtenerModelo(Equipo modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Equipo> listar() throws Exception {
        List<Equipo> listaEquipo = new ArrayList<>();
        try {
            String sql = "SELECT IDEQ, NOMEQ, PREEQ, MAREQ, MODEQ ,ESTEQ, CNTINV FROM FT_LISTAR_PRODINV() WHERE ESTEQ='A'";
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            while (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setIDEQ(rs.getInt(1));
                equipo.setNOMEQ(rs.getString(2));
                equipo.setPREEQ(rs.getFloat(3));
                equipo.setMAREQ(rs.getString(4));
                equipo.setMODEQ(rs.getString(5));
                equipo.setESTEQ(rs.getString(6));
                equipo.setCantidadInventario(rs.getInt(7));
                listaEquipo.add(equipo);
            }
            rs.clearWarnings();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return listaEquipo;
    }

    @Override
    public StreamedContent generarReporte(Equipo modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existe(Equipo modelo, List<Equipo> listaModelo) {
        for (Equipo equipo : listaModelo) {
            if (modelo.equals(equipo)) {
                return true;
            }
        }
        return false;
    }

}
