package sucursales.presentation.sucursales;

import sucursales.logic.Sucursales;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModel extends AbstractTableModel implements javax.swing.table.TableModel {

    List<Sucursales> rows;
    int[] cols;

    public TableModel(int[] cols, List<Sucursales> rows){
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
        Sucursales sucursales = rows.get(row);
        switch (cols[col]){
            case CODIGO: return sucursales.getCodigo();
            case REFERENCIA: return sucursales.getNombre();
            case DIRECCION: return sucursales.getDireccion();
            case ZONAJE: return sucursales.getZonaje();
            default: return "";
        }
    }

    public static final int CODIGO=0;
    public static final int REFERENCIA=1;
    public static final int DIRECCION = 2;
    public static final int ZONAJE = 3;

    String[] colNames = new String[4];
    private void initColNames(){
        colNames[CODIGO]= "Codigo";
        colNames[REFERENCIA]= "Referencia";
        colNames[DIRECCION] = "Direccion";
        colNames[ZONAJE] = "Zonaje";
    }

}

