package vista;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import modelo.VO.*;
import controlador.*;

public class PanelCapturaFaltas extends JPanel implements ActionListener{

	//componentes logicos de la clase
	
	private Controlador control;             
	private FaltaVO[]   faltas;              
	private ArrayList   <AlumnoVO> alumnos;  
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
		this.add(this.listaMes, posicion);
		
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
		posicion.gridheight = 4;
		posicion.weightx    = 1.0;
		posicion.anchor     = GridBagConstraints.WEST;
		posicion.fill       = GridBagConstraints.BOTH;
		this.add(this.barra, posicion);
		
		posicion.gridx      = 3;
		posicion.gridy      = 4;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.anchor     = GridBagConstraints.CENTER;
		posicion.fill       = GridBagConstraints.NONE;
		this.add(this.guardar, posicion);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void actualizar(){}

}
