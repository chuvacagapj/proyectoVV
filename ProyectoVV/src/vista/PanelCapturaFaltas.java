package vista;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import modelo.Conexion;
import modelo.VO.*;
import modelo.DAO.*;
import controlador.*;

public class PanelCapturaFaltas extends JPanel implements ActionListener{

	//componentes logicos de la clase
	
	private Controlador control;             
	private FaltaVO[]   faltas;              
	private ArrayList   <AlumnoVO> alumnos;
	private MateriaVO[] materias;
	private Boolean[]   entrada;  
	
	//componentes graficos de la clase
	
	private JLabel            etiquetaMes, etiquetaDia, etiquetaGrupo, etiquetaMateria, etiquetaLista;
	private JComboBox         <Integer> listaDia, listaGrupo;
	private JComboBox         <String>  listaMes, listaMateria;
	private JTable            tabla;
	private DefaultTableModel modelo;
	private JScrollPane       barra;
	private JButton           guardar; 
	
	public PanelCapturaFaltas(Controlador control){
		this.control = control;
		
		String [] meses  = {null, "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
		String [] titulo = {"matricula", "nombre", "Falta"};
		GridBagConstraints posicion = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		
		this.etiquetaDia     = new JLabel("Dia");
		this.etiquetaGrupo   = new JLabel("Grupo");
		this.etiquetaLista   = new JLabel("Lista");
		this.etiquetaMateria = new JLabel("Materia");
		this.etiquetaMes     = new JLabel("Mes");
		
		this.listaDia     = new <Integer> JComboBox();
		this.listaGrupo   = new <Integer> JComboBox(control.getGrupos());
		this.listaMateria = new <String>  JComboBox();
		this.listaMes     = new <String>  JComboBox(meses);
		
		this.listaDia.addActionListener    (this);
		this.listaGrupo.addActionListener  (this);
		this.listaMateria.addActionListener(this);
		this.listaMes.addActionListener    (this);
		
		this.modelo = new DefaultTableModel(null, titulo){
			public boolean isCellEditable(int rowIndex,int columnIndex){
				return columnIndex == 2;
			}
		};
		this.tabla = new JTable(this.modelo);
		this.barra = new JScrollPane(this.tabla);
		this.tabla.setFillsViewportHeight(true);
		TableColumn columna =  this.tabla.getColumnModel().getColumn(2);
		columna.setCellEditor(this.tabla.getDefaultEditor(Boolean.class));
		columna.setCellRenderer(this.tabla.getDefaultRenderer(Boolean.class));
		columna.setHeaderRenderer(new CheckBoxHeader(new MyItemListener()));
		
		this.guardar = new JButton("Guardar");
		this.guardar.addActionListener(this);
		
		posicion.gridx      = 0;
		posicion.gridy      = 0;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.anchor     = GridBagConstraints.WEST;
		this.add(this.etiquetaMes, posicion);
		
		posicion.gridx      = 1;
		posicion.gridy      = 0;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.anchor     = GridBagConstraints.WEST;
		this.add(this.etiquetaDia, posicion);
		
		posicion.gridx      = 2;
		posicion.gridy      = 0;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.anchor     = GridBagConstraints.WEST;
		this.add(this.etiquetaGrupo, posicion);
		
		posicion.gridx      = 3;
		posicion.gridy      = 0;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.anchor     = GridBagConstraints.WEST;
		this.add(this.etiquetaMateria, posicion);
		
		posicion.gridx      = 0;
		posicion.gridy      = 1;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.anchor     = GridBagConstraints.WEST;
		posicion.fill       = GridBagConstraints.HORIZONTAL;
		this.add(this.listaMes , posicion);
		
		posicion.gridx      = 1;
		posicion.gridy      = 1;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.anchor     = GridBagConstraints.WEST;
		posicion.fill       = GridBagConstraints.HORIZONTAL;
		this.add(this.listaDia, posicion);
		
		posicion.gridx      = 2;
		posicion.gridy      = 1;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.anchor     = GridBagConstraints.WEST;
		posicion.fill       = GridBagConstraints.HORIZONTAL;
		this.add(this.listaGrupo, posicion);
		
		posicion.gridx      = 3;
		posicion.gridy      = 1;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.anchor     = GridBagConstraints.WEST;
		posicion.fill       = GridBagConstraints.HORIZONTAL;
		this.add(this.listaMateria, posicion);
		
		posicion.gridx      = 0;
		posicion.gridy      = 2;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.anchor     = GridBagConstraints.WEST;
		posicion.fill       = GridBagConstraints.NONE;
		this.add(this.etiquetaLista, posicion);
		
		posicion.gridx      = 0;
		posicion.gridy      = 3;
		posicion.gridwidth  = 4;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.weighty    = 1.0;
		posicion.anchor     = GridBagConstraints.WEST;
		posicion.fill       = GridBagConstraints.BOTH;
		this.add(this.barra, posicion);
		
		posicion.gridx      = 3;
		posicion.gridy      = 4;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.weighty    = 0.0;
		posicion.anchor     = GridBagConstraints.CENTER;
		posicion.fill       = GridBagConstraints.NONE;
		this.add(this.guardar, posicion);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.listaMes && this.listaMes.getSelectedIndex() != 0){
			Date año = new Date();
			int dia, y = año.getYear();

			switch(this.listaMes.getSelectedIndex()){
				case  1: dia = 31; break;
				case  2: if((y%400 == 0 || y%100 != 0)&& y%4 == 0) dia = 29; else dia = 28; break;
				case  3: dia = 31; break;
				case  4: dia = 30; break;
				case  5: dia = 31; break;
				case  6: dia = 30; break;
				case  7: dia = 31; break;
				case  8: dia = 31; break;
				case  9: dia = 30; break;
				case 10: dia = 31; break;
				case 11: dia = 30; break;
				case 12: dia = 31; break;
				default: return;
			}
			
			while (this.listaDia.getModel().getSize() > 0){
				this.listaDia.removeItemAt(0);
			}
			
			this.listaDia.addItem(null);
			for(int i = 1; i<= dia; i++){
				this.listaDia.addItem(new Integer (i));
			}
			this.actualizar();
		} else if (e.getSource() == this.listaGrupo && this.listaGrupo.getSelectedIndex() != 0){
			AlumnoVO a = new AlumnoVO();
			Integer grupo = (Integer)this.listaGrupo.getSelectedItem();
			a.setGrupo(grupo);
			this.alumnos = new ArrayList <AlumnoVO> (Arrays.asList(control.getAlumnos(a)));
			this.materias = control.getMaterias(grupo.intValue());
			
			while (this.listaMateria.getModel().getSize() > 0){
				this.listaMateria.remove(0);
			}
			
			this.listaMateria.addItem(null);
			for(MateriaVO i: this.materias){
				this.listaMateria.addItem(i.getNombre());
			}
			this.actualizar();
		} else if((e.getSource() == this.listaDia && this.listaDia.getSelectedIndex() != 0)||(e.getSource() == this.listaMateria && this.listaMateria.getSelectedIndex() != 0)){
			actualizar();
		} else if(e.getSource() == this.guardar){
			System.out.println("Boton guardar!");
			System.out.println(entrada);
			for(int i=0; i < this.entrada.length; i++){
				boolean v = (Boolean)this.modelo.getValueAt(i, 2);
				System.out.println(v);
				if(entrada[i] && !v){
					this.control.eliminarFalta(this.faltas[i]);
				}
				
				else if(!entrada[i] && v){
					FaltaVO falta = new FaltaVO();
					Date fecha = new Date();
					fecha.setMonth(this.listaMes.getSelectedIndex());
					fecha.setDate(this.listaDia.getSelectedIndex());
					falta.setAlumno(this.alumnos.get(i).getMatricula());
					falta.setFecha(fecha);
					falta.setMateria(this.materias[this.listaMateria.getSelectedIndex()-1].getIdMaterias());
					this.control.InsertarFalta(falta);
					
				}
			}
		}else this.borrarTabla();
		
	}
	
	public void actualizar(){

		if(this.listaDia.getSelectedIndex() > 0 && this.listaGrupo.getSelectedIndex() > 0 && this.listaMateria.getSelectedIndex() > 0 && this.listaMes.getSelectedIndex() > 0){
			
			this.faltas = this.control.getFalta((Integer)this.listaGrupo.getSelectedItem(), this.materias[this.listaMateria.getSelectedIndex()-1].getIdMaterias(), (Integer)this.listaDia.getSelectedItem(), this.listaMes.getSelectedIndex());
			System.out.println(this.faltas.length);
			int numero = this.alumnos.size();
			this.entrada = new Boolean[numero];
			System.out.println(entrada != null);
			
			for(FaltaVO i: this.faltas){
				numero = this.alumnos.indexOf(new AlumnoVO(i.getAlumno()));
				this.entrada[numero] = new Boolean(true);
			}
			
			for(numero = 0; this.entrada.length > numero; numero++){
				if(entrada[numero] ==  null){
					entrada[numero] = new Boolean(false);
				}
			}
			
			this.borrarTabla();
			
			for(numero = 0; this.entrada.length > numero; numero++){
				AlumnoVO alumno = this.alumnos.get(numero);
				Object[] fila = {alumno.getMatricula(), alumno.getApellidoPaterno()  + " " +  alumno.getApellidoMaterno()  + " " + alumno.getNombre(), entrada[numero]};
				this.modelo.addRow(fila);
			}
		}			
	}
	
	@Override
	public String toString() {
		return "Captura";
	}
	
	protected void borrarTabla(){
		while(this.modelo.getRowCount() > 0){
			this.modelo.removeRow(0);
		}
	}
	

	public static void main(String[] args){
		Conexion.setInfo("root", "control", "chocolate4194", "localhost");
		Controlador control = new Controlador();
		FaltaDAO    faltas  = new FaltaDAO ();
		
		control.setAlumnos(new AlumnoDAO());
		control.setFaltas (faltas);
		control.setGrupos (new GrupoDAO ());
		control.setMaterias(new MateriaDAO());
		PanelCapturaFaltas prueva = new PanelCapturaFaltas (control);
		
		Principal vista = new Principal(prueva);
		vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		vista.setVisible(true);
		vista.setSize(500, 500);
	}
	
	class MyItemListener implements ItemListener  
	  {  
	    public void itemStateChanged(ItemEvent e) {  
	      Object source = e.getSource();  
	      if (source instanceof AbstractButton == false) return;  
	      boolean checked = e.getStateChange() == ItemEvent.SELECTED;  
	      for(int x = 0, y = tabla.getRowCount(); x < y; x++)  
	      {  
	        tabla.setValueAt(new Boolean(checked),x,2);  
	      }  
	    }  
	  }    
	}  

	class CheckBoxHeader extends JCheckBox  implements TableCellRenderer, MouseListener {
		
	  protected CheckBoxHeader rendererComponent;  
	  protected int column;  
	  protected boolean mousePressed = false;  
	  public CheckBoxHeader(ItemListener itemListener) {  
	    rendererComponent = this;  
	    rendererComponent.addItemListener(itemListener);  
	  }  
	  public Component getTableCellRendererComponent(  
	      JTable table, Object value,  
	      boolean isSelected, boolean hasFocus, int row, int column) {  
	    if (table != null) {  
	      JTableHeader header = table.getTableHeader();  
	      if (header != null) {  
	        rendererComponent.setForeground(header.getForeground());  
	        rendererComponent.setBackground(header.getBackground());  
	        rendererComponent.setFont(header.getFont());  
	        header.addMouseListener(rendererComponent);  
	      }  
	    }  
	    setColumn(column);  
	    rendererComponent.setText("Faltas");  
	    setBorder(UIManager.getBorder("TableHeader.cellBorder"));  
	    return rendererComponent;  
	  }  
	  protected void setColumn(int column) {  
	    this.column = column;  
	  }  
	  public int getColumn() {  
	    return column;  
	  }  
	  protected void handleClickEvent(MouseEvent e) {  
	    if (mousePressed) {  
	      mousePressed=false;  
	      JTableHeader header = (JTableHeader)(e.getSource());  
	      JTable tableView = header.getTable();  
	      TableColumnModel columnModel = tableView.getColumnModel();  
	      int viewColumn = columnModel.getColumnIndexAtX(e.getX());  
	      int column = tableView.convertColumnIndexToModel(viewColumn);  
	   
	      if (viewColumn == this.column && e.getClickCount() == 1 && column != -1) {  
	        doClick();  
	      }  
	    }  
	  }  
	  public void mouseClicked(MouseEvent e) {  
	    handleClickEvent(e);  
	    ((JTableHeader)e.getSource()).repaint();  
	  }  
	  public void mousePressed(MouseEvent e) {  
	    mousePressed = true;  
	  }  
	  public void mouseReleased(MouseEvent e) {  
	  }  
	  public void mouseEntered(MouseEvent e) {  
	  }  
	  public void mouseExited(MouseEvent e) {  
	  }  

}
