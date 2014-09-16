package vista;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

import modelo.Conexion;
import modelo.DAO.GrupoDAO;
import modelo.VO.AlumnoVO;
import controlador.BloqueadorTablas;
import controlador.Controlador;
import controlador.Limitador;
import controlador.Restriccion;

public class BuscadorAlumnos extends JDialog implements ActionListener, KeyListener{
	private JLabel etiquetaMatricula, etiquetaApellidoPaterno, etiquetaApellidoMaterno, etiquetaGrupo, etiquetaLista, etiquetaAgregados, etiquetaNombre;
	private JTextField cajaMatricula, cajaApellidoPaterno, cajaApellidoMaterno, cajaNombre;
	private JComboBox listaGrupo;
	private JButton botonSeleccion, botonSeleccionTodo, botonBorrar, botonBorrarTodo, botonAceptar;
	private JTable tablaLista, tablaAgreagados;
	private JScrollPane barraLista, barraAgregados;
	private Controlador control;
	private DefaultTableModel modeloLista, modeloAgregados;
	private AlumnoVO   consulta;
	private AlumnoVO[] respuesta, envio;
	
	public BuscadorAlumnos(JFrame parent, boolean modal, Controlador control){
		super(parent,"Buscar Alumnos!", modal); //configurar ventana
		this.setSize(500, 500);
		this.control = control;
		this.getContentPane().setLayout (new GridBagLayout());
		String [] titulo = {"matricula", "Apellidos", "Nombre", "Grupo"};
		this.consulta = new AlumnoVO();
		System.out.print(this.consulta);
		
		this.modeloAgregados = new BloqueadorTablas(null, titulo);
		this.modeloLista     = new BloqueadorTablas(null, titulo);
		
		this.etiquetaAgregados       = new JLabel("Agregados al Documento:");  //Creando las etiquetas
		this.etiquetaApellidoMaterno = new JLabel("Apellido Materno:"      );
		this.etiquetaApellidoPaterno = new JLabel("Apellido Paterno:"      );
		this.etiquetaGrupo           = new JLabel("Grupo:"                 );
		this.etiquetaLista           = new JLabel("Lista:"                 );
		this.etiquetaMatricula       = new JLabel("Matricula:"             );
		this.etiquetaNombre          = new JLabel("Nombre:"                );
		
		this.cajaApellidoMaterno = new JTextField();  //Creando los campos
		this.cajaApellidoPaterno = new JTextField();
		this.cajaMatricula       = new JTextField();
		this.cajaNombre          = new JTextField();
		
		this.cajaApellidoMaterno.addKeyListener(this); //Agregando el escuchador
		this.cajaApellidoPaterno.addKeyListener(this);
		this.cajaMatricula.addKeyListener      (this);
		this.cajaNombre.addKeyListener         (this);
		
		this.cajaApellidoMaterno.setDocument(new Limitador(this.cajaApellidoMaterno, Restriccion.ALFABETICO, 20)); //configurando restriccion campos
		this.cajaApellidoPaterno.setDocument(new Limitador(this.cajaApellidoPaterno, Restriccion.ALFABETICO, 20));
		this.cajaMatricula.setDocument      (new Limitador(this.cajaMatricula,       Restriccion.NUMERICO,   8 ));
		this.cajaNombre.setDocument         (new Limitador(this.cajaNombre,          Restriccion.ALFABETICO, 40));
		
		JComboBox jComboBox = new JComboBox(control.getGrupos());
		this.listaGrupo = jComboBox;
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
		
		this.botonBorrarTodo.setSize(100, 100);
		
		this.tablaAgreagados = new JTable(this.modeloAgregados);
		this.tablaLista      = new JTable(this.modeloLista    );
		
		this.barraAgregados  = new JScrollPane(this.tablaAgreagados);
		this.barraLista      = new JScrollPane(this.tablaLista     );
		
		this.tablaAgreagados.setFillsViewportHeight(true);
		this.tablaLista.setFillsViewportHeight     (true);
		
		GridBagConstraints posicion = new GridBagConstraints();
		
		posicion.gridx      = 0;
		posicion.gridy      = 0;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 0.0;
		posicion.anchor     = GridBagConstraints.WEST;
		this.getContentPane().add(this.etiquetaMatricula, posicion);
		
		posicion.gridx      = 0;
		posicion.gridy      = 1;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		this.getContentPane().add(this.etiquetaApellidoPaterno, posicion);
		
		posicion.gridx      = 0;
		posicion.gridy      = 2;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		this.getContentPane().add(this.etiquetaApellidoMaterno, posicion);
		
		posicion.gridx      = 0;
		posicion.gridy      = 3;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		this.getContentPane().add(this.etiquetaNombre, posicion);
		
		posicion.gridx      = 0;
		posicion.gridy      = 4;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		this.getContentPane().add(this.etiquetaGrupo, posicion);
		
		posicion.gridx      = 1;
		posicion.gridy      = 0;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.fill       = GridBagConstraints.HORIZONTAL;
		this.getContentPane().add(this.cajaMatricula, posicion);
		
		posicion.gridx      = 1;
		posicion.gridy      = 1;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		this.getContentPane().add(this.cajaApellidoPaterno, posicion);
		
		posicion.gridx      = 1;
		posicion.gridy      = 2;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		this.getContentPane().add(this.cajaApellidoMaterno, posicion);
		
		posicion.gridx      = 1;
		posicion.gridy      = 3;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		this.getContentPane().add(this.cajaNombre, posicion);
		
		posicion.gridx      = 1;
		posicion.gridy      = 4;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		this.getContentPane().add(this.listaGrupo, posicion);
		
		posicion.gridx      = 0;
		posicion.gridy      = 5;
		posicion.gridwidth  = 2;
		posicion.gridheight = 1;
		this.getContentPane().add(this.etiquetaLista, posicion);
		
		posicion.gridx      = 0;
		posicion.gridy      = 6;
		posicion.gridwidth  = 2;
		posicion.gridheight = 2;
		posicion.weighty    = 0.0;
		posicion.fill       = GridBagConstraints.BOTH;
		this.getContentPane().add(this.barraLista, posicion);
		
		posicion.gridx      = 2;
		posicion.gridy      = 6;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 0.0;
		posicion.weighty    = 0.0;
		posicion.fill       = GridBagConstraints.HORIZONTAL;
		posicion.anchor     = GridBagConstraints.NORTHWEST;
		this.getContentPane().add(this.botonSeleccion, posicion);
		
		posicion.gridx      = 2;
		posicion.gridy      = 7;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weighty    = 1.0;
		this.getContentPane().add(this.botonSeleccionTodo, posicion);
		
		posicion.gridx      = 0;
		posicion.gridy      = 8;
		posicion.gridwidth  = 3;
		posicion.gridheight = 1;
		posicion.weighty    = 0.0;
		posicion.weightx    = 0.0;
		posicion.fill       = GridBagConstraints.NONE;
		posicion.anchor     = GridBagConstraints.WEST;
		this.getContentPane().add(this.etiquetaAgregados, posicion);
		
		posicion.gridx      = 0;
		posicion.gridy      = 9;
		posicion.gridwidth  = 2;
		posicion.gridheight = 2;
		posicion.weightx    = 0.0;
		posicion.weighty    = 0.0;
		posicion.fill       = GridBagConstraints.BOTH;
		this.getContentPane().add(this.barraAgregados, posicion);
		
		posicion.gridx      = 2;
		posicion.gridy      = 9;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 0.0;
		posicion.weighty    = 0.0;
		posicion.fill       = GridBagConstraints.HORIZONTAL;
		posicion.anchor     = GridBagConstraints.NORTHWEST;
		this.getContentPane().add(this.botonBorrar, posicion);
		
		posicion.gridx      = 2;
		posicion.gridy      = 10;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weighty    = 1.0;
		this.getContentPane().add(this.botonBorrarTodo, posicion);
		
		posicion.gridx      = 1;
		posicion.gridy      = 11;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.weighty    = 0.0;
		posicion.fill       = GridBagConstraints.NONE;
		posicion.anchor     = GridBagConstraints.CENTER;
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
		if(e.getSource() == this.botonBorrar){}
		else if(e.getSource() == this.botonAceptar){}
		else if(e.getSource() == this.botonBorrarTodo){}
		else if(e.getSource() == this.botonSeleccion){}
		else if(e.getSource() == this.botonSeleccionTodo){}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getComponent() == this.cajaApellidoMaterno){
			if(this.cajaApellidoMaterno.getText().equals("")) this.consulta.setApellidoMaterno(null);
			else this.consulta.setApellidoMaterno(this.cajaApellidoMaterno.getText());
			this.respuesta = control.getAlumnos(this.consulta);
			this.actualizar(this.tablaLista);
			this.modeloLista.fireTableDataChanged();
		}
		else if(e.getComponent() == this.cajaApellidoPaterno){
			if(this.cajaApellidoPaterno.getText().equals("")) this.consulta.setApellidoPaterno(null);
			else this.consulta.setApellidoPaterno(this.cajaApellidoPaterno.getText());
			this.respuesta = control.getAlumnos(this.consulta);
			this.actualizar(this.tablaLista);
			this.modeloLista.fireTableDataChanged();
		}
		else if(e.getComponent() == this.cajaMatricula){
			if(this.cajaMatricula.getText().equals("")) this.consulta.setMatricula(null);
			else this.consulta.setMatricula(new Integer(this.cajaMatricula.getText()));
			this.respuesta = control.getAlumnos(this.consulta);
			this.actualizar(this.tablaLista);
			this.modeloLista.fireTableDataChanged();
		}
		else if(e.getComponent() == this.cajaNombre){
			if(this.cajaNombre.getText().equals("")) this.consulta.setNombre(null);
			else this.consulta.setNombre(this.cajaNombre.getText());
			this.respuesta = control.getAlumnos(this.consulta);
			this.actualizar(this.tablaLista);
			this.modeloLista.fireTableDataChanged();
		}
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void actualizar (JTable a){
		if(a == this.tablaLista && this.respuesta != null){
			if (this.modeloLista.getRowCount() > 0)
				this.modeloLista.fireTableRowsDeleted(0, this.modeloLista.getRowCount()-1);
			for(AlumnoVO i: this.respuesta){
				if(i != null){
					Object fila [] = {i, i.getNombre(), (i.getApellidoPaterno() + " " + i.getApellidoMaterno()), i.getGrupo()};
					this.modeloLista.addRow(fila);
				}
			}
		}
	}
	

}
