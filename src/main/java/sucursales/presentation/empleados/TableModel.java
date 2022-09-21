package sucursales.presentation.empleados;

import sucursales.logic.Empleado;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModel extends AbstractTableModel implements javax.swing.table.TableModel {
    List<Empleado> rows;
    int[] cols;

    public TableModel(int[] cols, List<Empleado> rows){
        initColNames();
        this.cols=cols;
        this.rows=rows;
    }

    public int getColumnCount() {
        return cols.length;
    }

    public String getColumnName(int col){
        return colNames[cols[col]];
    }

    public Class<?> getColumnClass(int col){
        switch (cols[col]){
            default: return super.getColumnClass(col);
        }
    }

    public int getRowCount() {
        return rows.size();
    }

    public Object getValueAt(int row, int col) {
        Empleado empleado = rows.get(row);
        switch (cols[col]){
            case CEDULA: return empleado.getCedula();
            case NOMBRE: return empleado.getNombre();
            default: return "";
        }
    }

    public static final int CEDULA=0;
    public static final int NOMBRE=1;

    String[] colNames = new String[2];
    private void initColNames(){
        colNames[CEDULA]= "Cedula";
        colNames[NOMBRE]= "Nombre";
    }

}
