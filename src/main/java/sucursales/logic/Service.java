package sucursales.logic;

import sucursales.data.Data;
import sucursales.data.XmlPersister;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private static Service theInstance;

    public static Service instance(){
        if (theInstance == null){
            theInstance = new Service();
        }
        return theInstance;
    }

    private Data data;
    private Service(){

        try{
            data = XmlPersister.instance().load();
        }
        catch(Exception e){
            data =  new Data();
        }
    }

    public List<Empleado> empleadosSearch(String filtro){
        return data.getEmpleados().stream()
                .filter(e->e.getNombre().contains(filtro))
                .sorted(Comparator.comparing(e -> e.getCedula()))
                .collect(Collectors.toList());
    }

    public Empleado empleadoGet(String cedula) throws Exception{
        Empleado result = data.getEmpleados().stream().filter(e->e.getCedula().equals(cedula)).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Empleado no existe");
    }

    public Sucursales sucursalGet(String codigo) throws Exception{
        Sucursales result = data.getSucursales().stream().filter(e->e.getCodigo().equals(codigo)).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Sucursal no existe");
    }

    public void empleadoAdd(Empleado empleado) throws Exception{
        Empleado result = data.getEmpleados().stream().filter(e->e.getCedula().equals(empleado.getCedula())).findFirst().orElse(null);
        if (result==null) data.getEmpleados().add(empleado);
        else throw new Exception("Empleado ya existe");
    }

    public void sucursalAdd(Sucursales sucursal) throws Exception{
        Sucursales result = data.getSucursales().stream().filter(e->e.getCodigo().equals(sucursal.getCodigo())).findFirst().orElse(null);
        if (result==null) data.getSucursales().add(sucursal);
        else throw new Exception("Sucursal ya existe");
    }

    public void empleadoUpdate(Empleado empleado) throws Exception{
        Empleado result;
        try{
            result = this.empleadoGet(empleado.cedula);
            data.getEmpleados().remove(result);
            data.getEmpleados().add(empleado);
        }catch (Exception e) {
            throw new Exception("Empleado no existe");
        }
    }

    public void sucursalUpdate(Sucursales sucursal) throws Exception{
        Sucursales result;
        try{
            result = this.sucursalGet(sucursal.getCodigo());
            data.getSucursales().remove(result);
            data.getSucursales().add(sucursal);
        }catch (Exception e) {
            throw new Exception("Sucursal no existe");
        }
    }

   public List<Sucursales> sucursalesSearch(String filtro){
        return data.getSucursales().stream()
                .filter(e->e.getNombre().contains(filtro))
                .sorted(Comparator.comparing(e -> e.getCodigo()))
                .collect(Collectors.toList());
    }

    public void store(){
        try {
            XmlPersister.instance().store(data);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void empleadoDelete(Empleado empleado) throws Exception{
        Empleado result = data.getEmpleados().stream().filter(e->e.getCedula().equals(empleado.getCedula())).findFirst().orElse(null);
        if (result!=null) data.getEmpleados().remove(empleado);
        else throw new Exception("Empleado no existe");
    }

    public void sucursalDelete(Sucursales sucursal) throws Exception{
        Sucursales result = data.getSucursales().stream().filter(e->e.getCodigo().equals(sucursal.getCodigo())).findFirst().orElse(null);
        if (result!=null) data.getSucursales().remove(sucursal);
        else throw new Exception("Sucursal no existe");
    }
}
