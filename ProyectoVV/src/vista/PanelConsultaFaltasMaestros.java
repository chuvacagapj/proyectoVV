package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controlador.BloqueadorTablas;
import controlador.Controlador;
import modelo.Conexion;
import modelo.VO.*;
import modelo.DAO.*;

public class PanelConsultaFaltasMaestros extends JPanel implements ActionListener{
	
	private JLabel            etiquetaGrupo, etiquetaMateria, etiquetaMes, etiquetaLista;
	private JComboBox         <Integer> listaGrupo;
	private JComboBox         <String>  listaMateria, listaMes;
	private JTable            tabla;
	private JScrollPane       barraTabla;
	private DefaultTableModel modelo;
	private Controlador       control;
	private MateriaVO[]       materias;
	private ArrayList         <AlumnoVO> alumnos;
	private FaltaVO[]         faltas;
	private Integer           dias;

	public PanelConsultaFaltasMaestros(Controlador control){
		this.control  = control;
		this.dias = null;
		String [] meses    = {null, "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
		GridBagConstraints posicion = new GridBagConstraints();
		
		this.setLayout(new GridBagLayout());
		
		this.etiquetaGrupo   = new JLabel("Grupo:");
		this.etiquetaLista   = new JLabel("Lista:");
		this.etiquetaMateria = new JLabel("Materia:");
		this.etiquetaMes     = new JLabel("Mes:");
		
		this.listaGrupo   = new JComboBox <Integer> (control.getGrupos());
		this.listaMes     = new JComboBox <String>  (meses);
		
		this.listaGrupo.addActionListener(this);
		this.listaMes.addActionListener  (this);
		
		posicion.gridx      = 0;
		posicion.gridy      = 0;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.anchor     = GridBagConstraints.WEST;
		this.add(etiquetaMes, posicion);
		
		posicion.gridx      = 1;
		posicion.gridy      = 0;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.anchor     = GridBagConstraints.WEST;
		this.add(etiquetaGrupo, posicion);
		
		posicion.gridx      = 0;
		posicion.gridy      = 1;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.anchor     = GridBagConstraints.WEST;
		posicion.fill       = GridBagConstraints.HORIZONTAL;
		this.add(listaMes, posicion);
		
		posicion.gridx      = 1;
		posicion.gridy      = 1;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.anchor     = GridBagConstraints.WEST;
		posicion.fill       = GridBagConstraints.HORIZONTAL;
		this.add(listaGrupo, posicion);
	}

	@Override
	public String toString() {
		return "Consulta";
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int y = new Date().getYear();
		if(e.getSource() == this.listaMes){
			switch (this.listaMes.getSelectedIndex()){
				case 1: this.dias = new Integer(31);
				        break;
				case 2: if((y%400 == 0 || y%100 != 0)&& y%4 == 0){
							this.dias = new Integer(29);
						}else this.dias = new Integer(28);
		        		break;
				case 3: this.dias = new Integer(31);
						break;
				case 4: this.dias = new Integer(30);
						break;
				case 5: this.dias = new Integer(31);
						break;
				case 6: this.dias = new Integer(30);
						break;
				case 7: this.dias = new Integer(31);
						break;
				case 8: this.dias = new Integer(31);
						break;
				case 9: this.dias = new Integer(30);
						break;
				case 10: this.dias = new Integer(31);
						break;
				case 11: this.dias = new Integer(30);
						break;
				case 12: this.dias = new Integer(31);
						break;
				default: break;
			}
			
		}
		else if (e.getSource() == this.listaGrupo){
			this.materias = control.getMaterias((Integer)this.listaGrupo.getSelectedItem());
			String[] materias = new String[this.materias.length + 1];
			materias[0] = "";
			for(int i = 0; i < this.materias.length; i++){
				materias[i+1] = this.materias[i].getNombre();
			}
			
			this.listaMateria = new JComboBox <String> (materias);
			GridBagConstraints posicion = new GridBagConstraints();
			
			posicion.gridx      = 2;
			posicion.gridy      = 0;
			posicion.gridwidth  = 1;
			posicion.gridheight = 1;
			posicion.weightx    = 1.0;
			this.add(this.etiquetaMateria, posicion);
			
			posicion.gridx      = 2;
			posicion.gridy      = 1;
			posicion.gridwidth  = 1;
			posicion.gridheight = 1;
			posicion.weightx    = 1.0;
			posicion.fill       = GridBagConstraints.HORIZONTAL;
			this.add(this.listaMateria, posicion);
			this.revalidate();
			this.repaint();
			this.actualizar();
			
		} else if(e.getSource() == this.listaMateria){
			int i = this.listaGrupo.getSelectedIndex();
			if(0 < i){
				this.faltas = control.getFalta((Integer)this.listaGrupo.getSelectedItem(), this.materias[this.listaMateria.getSelectedIndex()-1].getIdMaterias(), this.listaMes.getSelectedIndex());
				this.actualizar();
			}
		}
		
	}
	
	protected void actualizar(){
		if(this.materias != null && this.alumnos != null && this.dias != null && this.faltas != null){
			AlumnoVO[] alumnos;
			alumnos = this.alumnos.toArray(new AlumnoVO[0]);
			String [] titulo =  new String[this.dias + 2];
			String [][] contenido = new String [alumnos.length][this.dias + 2];
			int i, x, y;
			titulo[0] = "Matricula";
			titulo[1] = "Nombre";
			
			for(i = 2; i < this.dias + 2; i++){
				titulo[i] = Integer.toString(i);
			}
			
			for(i = 0;i < alumnos.length ;i++){
				contenido[i][0] = alumnos[i].getMatricula().toString();
				contenido[i][1] = alumnos[i].getApellidoPaterno() + " " + alumnos[i].getApellidoMaterno() + " " + alumnos[i].getNombre();
			}
			
			this.modelo =  new BloqueadorTablas(contenido, titulo);
			
			for(FaltaVO j: this.faltas){
				AlumnoVO a = new AlumnoVO();
				a.setMatricula(j.getAlumno());
				y = this.alumnos.indexOf(a);
				x = j.getFecha().getDay();
				if(j.getJustificante() == 0){
					this.modelo.setValueAt("F", x + 2, y);
				}else{
					this.modelo.setValueAt("J", x + 2, y);
				}
			}
			
			this.tabla = new JTable(this.modelo);
			this.barraTabla = new JScrollPane(this.tabla);
			
		    GridBagConstraints posicion = new GridBagConstraints();
		    
		    posicion.gridx      = 0;
		    posicion.gridy      = 2;
		    posicion.gridwidth  = 1;
		    posicion.gridheight = 1;
		    this.add(this.etiquetaLista, posicion);
		    
		    posicion.gridx      = 0;
		    posicion.gridy      = 3;
		    posicion.gridwidth  = 3;
		    posicion.gridheight = 1;
		    this.add(this.barraTabla, posicion);
		    
		    this.revalidate();
		    this.repaint();
		}
	}
	
	public static void main(String[] args) {
		Conexion.setInfo("root", "control", "chocolate4194", "127.0.0.1");
		Controlador control = new Controlador();
		
		control.setAlumnos (new AlumnoDAO ());
		control.setFaltas  (new FaltaDAO  ());
		control.setGrupos  (new GrupoDAO  ());
		control.setMaterias(new MateriaDAO());
		
		PanelConsultaFaltasMaestros pestanna = new PanelConsultaFaltasMaestros(control);
		Principal vista = new Principal(pestanna);
		
		vista.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		vista.setSize(500, 500);
		vista.setVisible(true);
	}

}
