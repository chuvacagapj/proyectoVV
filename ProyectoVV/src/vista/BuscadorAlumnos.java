package vista;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import modelo.Conexion;
import modelo.DAO.GrupoDAO;
import controlador.Controlador;
import controlador.Limitador;
import controlador.Restriccion;

public class BuscadorAlumnos extends JDialog implements ActionListener{
	private JLabel etiquetaMatricula, etiquetaApellidoPaterno, etiquetaApellidoMaterno, etiquetaGrupo, etiquetaLista, etiquetaAgregados;
	private JTextField cajaMatricula, cajaApellidoPaterno, cajaApellidoMaterno;
	private JComboBox listaGrupo;
	private JButton botonSeleccion, botonSeleccionTodo, botonBorrar, botonBorrarTodo, botonAceptar;
	private JTable tablaLista, tablaAgreagados;
	private JScrollPane barraLista, barraAgregados;
	private Controlador control;
	
	public BuscadorAlumnos(JFrame parent, boolean modal, Controlador control){
		super(parent,"Buscar Alumnos!", modal); //configurar ventana
		this.setSize(600, 600);
		this.control = control;
		this.getContentPane().setLayout (new GridBagLayout());
		String [] titulo = {"matricula", "nombre"};
		String [][] data = new String [2][2];
		
		this.etiquetaAgregados       = new JLabel("Agregados al Documento:");  //Creando las etiquetas
		this.etiquetaApellidoMaterno = new JLabel("Apellido Materno:"      );
		this.etiquetaApellidoPaterno = new JLabel("Apellido Paterno:"      );
		this.etiquetaGrupo           = new JLabel("Grupo:"                 );
		this.etiquetaLista           = new JLabel("Lista:"                 );
		this.etiquetaMatricula       = new JLabel("Matricula:"             );
		
		this.cajaApellidoMaterno = new JTextField();  //Creando los campos
		this.cajaApellidoPaterno = new JTextField();
		this.cajaMatricula       = new JTextField();
		
		this.cajaApellidoMaterno.addActionListener(this); //Agregando el escuchador
		this.cajaApellidoPaterno.addActionListener(this);
		this.cajaMatricula.addActionListener      (this);
		
		this.cajaApellidoMaterno.setDocument(new Limitador(this.cajaApellidoMaterno, Restriccion.ALFABETICO, 20)); //configurando restriccion campos
		this.cajaApellidoPaterno.setDocument(new Limitador(this.cajaApellidoPaterno, Restriccion.ALFABETICO, 20));
		this.cajaMatricula.setDocument      (new Limitador(this.cajaMatricula,       Restriccion.NUMERICO,   40));
		
		this.listaGrupo = new JComboBox(control.getGrupos());
		this.listaGrupo.addActionListener(this);
		
		this.botonAceptar       = new JButton("Aceptar"          );
		this.botonBorrar        = new JButton("Borrar"           );
		this.botonBorrarTodo    = new JButton("Borrar Todos"     );
		this.botonSeleccion     = new JButton("Seleccionar"      );
		this.botonSeleccionTodo = new JButton("Seleccionar Todos");
		
		this.botonAceptar.addActionListener      (this);
		this.botonBorrar.addActionListener       (this);
		this.botonBorrarTodo.addActionListener   (this);
		this.botonSeleccion.addActionListener    (this);
		this.botonSeleccionTodo.addActionListener(this);
		
		this.tablaAgreagados = new JTable(data, titulo);
		this.tablaLista      = new JTable(data, titulo);
		
		this.barraAgregados  = new JScrollPane(this.tablaAgreagados);
		this.barraLista      = new JScrollPane(this.tablaLista);
		
		this.tablaAgreagados.setFillsViewportHeight(true);
		this.tablaLista.setFillsViewportHeight     (true);
		
		GridBagConstraints posicion = new GridBagConstraints();
		
		posicion.gridx      = 1;
		posicion.gridy      = 1;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		this.getContentPane().add(this.etiquetaMatricula, posicion);
		
		posicion.gridx      = 2;
		posicion.gridy      = 1;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		this.getContentPane().add(this.etiquetaApellidoPaterno, posicion);
		
		posicion.gridx      = 3;
		posicion.gridy      = 1;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		this.getContentPane().add(this.etiquetaApellidoMaterno, posicion);
		
		posicion.gridx      = 4;
		posicion.gridy      = 1;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		this.getContentPane().add(this.etiquetaGrupo, posicion);
		
		posicion.gridx      = 1;
		posicion.gridy      = 2;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.fill       = GridBagConstraints.HORIZONTAL;
		this.getContentPane().add(this.cajaMatricula, posicion);
		
		posicion.gridx      = 2;
		posicion.gridy      = 2;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		this.getContentPane().add(this.cajaApellidoPaterno, posicion);
		
		posicion.gridx      = 3;
		posicion.gridy      = 2;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		this.getContentPane().add(this.cajaApellidoMaterno, posicion);
		
		posicion.gridx      = 4;
		posicion.gridy      = 2;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		this.getContentPane().add(this.listaGrupo, posicion);
		
		posicion.gridx      = 1;
		posicion.gridy      = 3;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.fill       = GridBagConstraints.NONE;
		this.getContentPane().add(this.etiquetaLista, posicion);
		
		posicion.gridx      = 1;
		posicion.gridy      = 4;
		posicion.gridwidth  = 4;
		posicion.gridheight = 2;
		posicion.weightx    = 1.0;
		posicion.weighty    = 1.0;
		posicion.fill       = GridBagConstraints.BOTH;
		this.getContentPane().add(this.barraLista, posicion);
		
		posicion.gridx      = 5;
		posicion.gridy      = 4;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 0.0;
		posicion.weighty    = 1.0;
		posicion.fill       = GridBagConstraints.NONE;
		this.getContentPane().add(this.botonSeleccion, posicion);
		
		posicion.gridx      = 5;
		posicion.gridy      = 5;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weighty    = 1.0;
		this.getContentPane().add(this.botonSeleccionTodo, posicion);
		
		posicion.gridx      = 1;
		posicion.gridy      = 6;
		posicion.gridwidth  = 3;
		posicion.gridheight = 1;
		posicion.weighty    = 0.0;
		posicion.weightx    = 1.0;
		this.getContentPane().add(this.etiquetaAgregados, posicion);
		
		posicion.gridx      = 1;
		posicion.gridy      = 7;
		posicion.gridwidth  = 4;
		posicion.gridheight = 2;
		posicion.weightx    = 1.0;
		posicion.weighty    = 1.0;
		posicion.fill       = GridBagConstraints.BOTH;
		this.getContentPane().add(this.barraAgregados, posicion);
		
		posicion.gridx      = 5;
		posicion.gridy      = 7;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 0.0;
		posicion.weighty    = 1.0;
		posicion.fill       = GridBagConstraints.NONE;
		this.getContentPane().add(this.botonBorrar, posicion);
		
		posicion.gridx      = 5;
		posicion.gridy      = 8;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weighty    = 1.0;
		this.getContentPane().add(this.botonBorrarTodo, posicion);
		
		posicion.gridx      = 3;
		posicion.gridy      = 9;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.weighty    = 0.0;
		this.getContentPane().add(this.botonAceptar, posicion);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Conexion.setInfo("root", "control", "chocolate4194", "localhost");
			Controlador control = new Controlador();
			control.setGrupos(new GrupoDAO());
			BuscadorAlumnos vista = new BuscadorAlumnos(null, true, control);
			vista.setVisible(true);
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
