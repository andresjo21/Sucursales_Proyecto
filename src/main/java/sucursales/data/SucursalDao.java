package sucursales.data;

import sucursales.logic.Sucursales;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SucursalDao {
    Database db;

    public SucursalDao() {
        db = Database.instance();
    }

    public void create(Sucursales e) throws Exception {
        String sql = "insert into " +
                "Sucursal " +
                "(codigo, nombre, direccion, porcZonaje, ubicacionX, ubicacionY) " +
                "values(?,?,?,?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getCodigo());
        stm.setString(2, e.getNombre());
        stm.setString(3, e.getDireccion());
        stm.setDouble(4, e.getZonaje());
        stm.setInt(5, e.getX());
        stm.setInt(6, e.getY());

        db.executeUpdate(stm);
    }

    public Sucursales read(String codigo) throws Exception {
        String sql = "select " +
                "* " +
                "from  Sucursal s " +
                "where s.codigo=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, codigo);
        ResultSet rs = db.executeQuery(stm);
        if (rs.next()) {
            return from(rs, "s");
        } else {
            throw new Exception("SUCURSAL NO EXISTE");
        }
    }

    public void update(Sucursales e) throws Exception {
        String sql = "update " +
                "Sucursal " +
                "set nombre=?, direccion=?, porcZonaje=?, ubicacionX=?, ubicacionY=?  " +
                "where codigo=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getNombre());
        stm.setString(2, e.getDireccion());
        stm.setDouble(3, e.getZonaje());
        stm.setInt(4, e.getX());
        stm.setInt(5, e.getY());
        stm.setString(6, e.getCodigo());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("SUCURSAL NO EXISTE");
        }
    }

    public void delete(Sucursales e) throws Exception {
        String sql = "delete " +
                "from Sucursal " +
                "where codigo=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getCodigo());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("SUCURSAL NO EXISTE");
        }
    }

    public List<Sucursales> findByReferencia(String nombre) throws Exception {
        List<Sucursales> resultado = new ArrayList<Sucursales>();
        String sql = "select * " +
                "from " +
                "sucursal s " +
                "where s.nombre like ?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, "%" + nombre + "%");
        ResultSet rs = db.executeQuery(stm);
        while (rs.next()) {
            resultado.add(from(rs, "s"));
        }
        return resultado;
    }

    public Sucursales from(ResultSet rs, String alias) throws Exception {
        Sucursales e = new Sucursales();
        e.setCodigo(rs.getString(alias + ".codigo"));
        e.setNombre(rs.getString(alias + ".nombre"));
        e.setDireccion(rs.getString(alias + ".direccion"));
        e.setZonaje(rs.getFloat(alias + ".porcZonaje"));
        e.setX(rs.getInt(alias + ".ubicacionX"));
        e.setY(rs.getInt(alias + ".ubicacionY"));
        return e;
    }

}
