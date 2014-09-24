package vista;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import modelo.Conexion;
import modelo.VO.*;
import modelo.DAO.*;
import controlador.*;
import vista.*;

public class PanelConsultaFaltasAlumnos extends JPanel implements ActionListener{
	private JLabel etiquetaFaltas, etiquetaJustificadas, etiquetaNoJustificadas, etiquetaMax, etiquetaN, etiquetaM, etiquetaNM, etiquetaF;
	private JTable tabla;
	private DefaultTableModel modeloTabla;
	private JButton verMas;
	private ConcentradoFaltasVO informacion;
	private FaltaVO[] lista;
	private JScrollPane barraTabla;
	private Controlador control;
	private boolean bandera;
	
	public PanelConsultaFaltasAlumnos(ConcentradoFaltasVO informacion, Controlador control){
		this.control = control;
		this.informacion = informacion;
		this.bandera = false;
		this.lista   = null;
		String[] titulo = {"fecha", "Justificado"};
		this.setLayout(new GridBagLayout());
		GridBagConstraints posicion = new GridBagConstraints();
		
		this.etiquetaF = new JLabel(informacion.getMaximoFaltas().toString());
		this.etiquetaFaltas = new JLabel(" Faltas: ");
		this.etiquetaJustificadas = new JLabel(" Justificadas: ");
		this.etiquetaM = new JLabel(informacion.getNumeroJustificadas().toString());
		this.etiquetaMax = new JLabel(" Maximo de faltas: ");
		this.etiquetaN = new JLabel(informacion.getNumeroFaltas().toString());
		this.etiquetaNM = new JLabel(informacion.getNoJustificadas().toString());
		this.etiquetaNoJustificadas = new JLabel(" No Justificadas: ");
		
		this.verMas = new JButton("Ver mas");
		this.verMas.addActionListener(this);
		
		this.modeloTabla = new BloqueadorTablas(null,titulo);
		this.tabla = new JTable(this.modeloTabla);
		this.tabla.setFillsViewportHeight(true);
		this.barraTabla = new JScrollPane(this.tabla);
		
		posicion.gridx      = 0;
		posicion.gridy      = 0;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.anchor     = GridBagConstraints.NORTH;
		this.add(this.etiquetaFaltas, posicion);
		
		posicion.gridx      = 1;
		posicion.gridy      = 0;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.anchor     = GridBagConstraints.NORTH;
		this.add(this.etiquetaJustificadas, posicion);
		
		posicion.gridx      = 2;
		posicion.gridy      = 0;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.anchor     = GridBagConstraints.NORTH;
		this.add(this.etiquetaNoJustificadas, posicion);
		
		posicion.gridx      = 3;
		posicion.gridy      = 0;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.anchor     = GridBagConstraints.NORTH;
		this.add(this.etiquetaMax, posicion);
		
		posicion.gridx      = 0;
		posicion.gridy      = 1;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.anchor     = GridBagConstraints.NORTH;
		this.add(this.etiquetaN, posicion);
		
		posicion.gridx      = 1;
		posicion.gridy      = 1;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.anchor     = GridBagConstraints.NORTH;
		this.add(this.etiquetaM, posicion);
		
		posicion.gridx      = 2;
		posicion.gridy      = 1;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.anchor     = GridBagConstraints.NORTH;
		this.add(this.etiquetaNM, posicion);
		
		posicion.gridx      = 3;
		posicion.gridy      = 1;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.anchor     = GridBagConstraints.NORTH;
		this.add(this.etiquetaF, posicion);
		
		posicion.gridx      = 3;
		posicion.gridy      = 3;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.anchor     = GridBagConstraints.CENTER;
		posicion.fill       = GridBagConstraints.NONE;
		this.add(this.verMas, posicion);
	}

	@Override
	public String toString() {
		return this.informacion.getMateria();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.verMas){
			this.bandera = !this.bandera;
			if(this.bandera){
				this.verMas.setText("Ver menos");
				GridBagConstraints posicion = new GridBagConstraints();
				posicion.gridx      = 0;
				posicion.gridy      = 2;
				posicion.gridwidth  = 4;
				posicion.gridheight = 1;
				posicion.weightx    = 1.0;
				posicion.anchor     = GridBagConstraints.CENTER;
				posicion.fill       = GridBagConstraints.BOTH;
				this.add(this.barraTabla, posicion);
				this.revalidate();
				this.repaint();
			}else{
				this.verMas.setText("Ver mas");
				this.remove(this.barraTabla);
				this.revalidate();
				this.repaint();
			}
			if(this.lista == null){
				System.out.println(this.control == null);
				this.lista = this.control.getFalta(this.informacion.consulta());
				for(FaltaVO i: this.lista){
					if(i != null){
						String[] fila = new  String[2];
						fila[0] = i.getFecha().toString();
						System.out.println(i.getJustificante().toString());
						if(i.getJustificante().intValue() == 0){
							fila[1] = "No";
						}else{
							fila[1] = "Si";
						}
						this.modeloTabla.addRow(fila);
					}
				}
			}
		}
		
	}
	
	public static void main(String[] args){
		Conexion.setInfo("root", "control", "chocolate4194", "localhost");
		Controlador control = new Controlador();
		FaltaDAO    faltas  = new FaltaDAO ();
		
		control.setAlumnos(new AlumnoDAO());
		control.setFaltas (faltas);
		control.setGrupos (new GrupoDAO ());
		PanelConsultaFaltasAlumnos prueva = new PanelConsultaFaltasAlumnos (faltas.faltasPorMateria(12030162)[0], control);
		
		Principal vista = new Principal(prueva);
		vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		vista.setVisible(true);
		vista.setSize(500, 500);
	}
}
