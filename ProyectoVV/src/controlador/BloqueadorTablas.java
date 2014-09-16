package controlador;

import javax.swing.table.DefaultTableModel;

public class BloqueadorTablas extends DefaultTableModel{
	public BloqueadorTablas(Object [][] datos,String[] columnas){
		super(datos,columnas);
	}
	
	public boolean isCellEditable(int rowIndex,int columnIndex){
		return false;
	} 

}
