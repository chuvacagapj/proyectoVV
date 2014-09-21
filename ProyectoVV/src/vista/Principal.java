package vista;

import java.awt.*;

import javax.swing.*;

import modelo.VO.*;

public class Principal extends JFrame {
	
	private JButton     botonCerrarSesion, botonFaltas, botonReportes, botonJustificantes, botonEncuestas;
	private JLabel      etiquetaSaludo;
	private JPanel      panelSesion;
	private JTabbedPane pestannias;
	private AlumnoVO    usuarioAlumno;
	private MaestroVO   usuarioMaestro;
	
	public Principal(Container a){
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints posicion = new GridBagConstraints();
		
		this.botonCerrarSesion  = new JButton("Cerrar Sesion");
		this.botonEncuestas     = new JButton("Encuestas");
		this.botonFaltas        = new JButton("Faltas");
		this.botonJustificantes = new JButton("Justificantes");
		this.botonReportes      = new JButton("Reportes");
		
		this.etiquetaSaludo    = new JLabel("Bienvenido!");
		this.panelSesion       = new JPanel();
		this.pestannias        = new JTabbedPane();
		
		this.pestannias.addTab(a + "", a);
		
		this.panelSesion.add(this.etiquetaSaludo);
		this.panelSesion.add(this.botonCerrarSesion);
		
		
		posicion.gridx      = 0;
		posicion.gridy      = 0;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 0;
		posicion.weighty    = 0;
		posicion.fill       = GridBagConstraints.HORIZONTAL;
		posicion.anchor     = GridBagConstraints.NORTH;
		this.getContentPane().add(this.botonFaltas, posicion);
		
		posicion.gridy = 1;
		this.getContentPane().add(this.botonReportes, posicion);
		
		posicion.gridy = 3;
		this.getContentPane().add(this.botonJustificantes, posicion);
		
		posicion.gridy = 4;
		this.getContentPane().add(this.botonEncuestas, posicion);
		
		posicion.gridx      = 1;
		posicion.gridy      = 0;
		posicion.gridwidth  = 1;
		posicion.gridheight = 5;
		posicion.weightx    = 1.0;
		posicion.weighty    = 1.0;
		posicion.fill       = GridBagConstraints.BOTH;
		this.getContentPane().add(this.pestannias, posicion);
		
		posicion.gridx      = 1;
		posicion.gridy      = 5;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 0;
		posicion.weighty    = 0;
		posicion.fill       = GridBagConstraints.BOTH;
		this.getContentPane().add(this.panelSesion, posicion);
		
	}

	public Principal(){
		this(null);
	}
}
