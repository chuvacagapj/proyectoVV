package vista;

import java.awt.*;

import javax.swing.*;

import modelo.VO.*;

public class Principal extends JFrame {
	
	private JButton     botonCerrarSesion;
	private JLabel      etiquetaSaludo;
	private JPanel      panelAcciones, panelSesion;
	private JTabbedPane pestannias;
	private AlumnoVO    usuarioAlumno;
	private MaestroVO   usuarioMaestro;
	
	public Principal(){
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints posicion = new GridBagConstraints();
		
		this.botonCerrarSesion = new JButton("Cerrar Sesion");
		this.etiquetaSaludo    = new JLabel("Bienvenido!");
		this.panelAcciones     = new JPanel();
		this.panelSesion       = new JPanel();
		this.pestannias        = new JTabbedPane();
		
		this.panelSesion.add(this.etiquetaSaludo);
		this.panelSesion.add(this.botonCerrarSesion);
		
		this.panelAcciones.add(new JButton("Faltas"));
		
		posicion.gridx      = 0;
		posicion.gridy      = 0;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weighty    = 1.0;
		posicion.fill       = GridBagConstraints.BOTH;
		this.getContentPane().add(this.panelAcciones, posicion);
		
		posicion.gridx      = 1;
		posicion.gridy      = 0;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.weightx    = 1.0;
		posicion.weighty    = 1.0;
		posicion.fill       = GridBagConstraints.BOTH;
		this.getContentPane().add(this.pestannias, posicion);
		
		posicion.gridx      = 1;
		posicion.gridy      = 1;
		posicion.gridwidth  = 1;
		posicion.gridheight = 2;
		posicion.weightx    = 0;
		posicion.weighty    = 0;
		posicion.fill       = GridBagConstraints.BOTH;
		this.getContentPane().add(this.panelSesion, posicion);
		
	}
}
