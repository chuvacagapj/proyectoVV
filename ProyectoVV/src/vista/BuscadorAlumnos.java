package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import controlador.Controlador;
import controlador.Limitador;
import controlador.Restriccion;

public class BuscadorAlumnos extends JDialog implements ActionListener, AncestorListener{
	private JLabel etiquetaMatricula, etiquetaApellidoPaterno, etiquetaApellidoMaterno, etiquetaGrupo, etiquetaLista, etiquetaAgregados;
	private JTextField cajaMatricula, cajaApellidoPaterno, cajaApellidoMaterno;
	private JComboBox listaGrupo;
	private JButton botonSeleccion, botonSeleccionTodo, botonBorrar, botonBorrarTodo, botonAceptar;
	private JTable tablaLista, tablaAgreagados;
	private Controlador control;
	
	public BuscadorAlumnos(JFrame parent, boolean modal, Controlador control){
		super(parent,"Buscar Alumnos!", modal); //configurar ventana
		this.setSize(400, 600);
		this.control = control;
		
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
		
		this.tablaAgreagados = new JTable();
		this.tablaLista      = new JTable();
		
		this.tablaAgreagados.addAncestorListener(this);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ancestorAdded(AncestorEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ancestorRemoved(AncestorEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ancestorMoved(AncestorEvent event) {
		// TODO Auto-generated method stub
		
	}

}
