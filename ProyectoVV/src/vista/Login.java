package vista;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import controlador.*;

public class Login extends JFrame implements ActionListener, EscudarCampos{
	
	private JComboBox  <String> listaTipoUsuario;
	private JTextField cajaUsuario;
	private JPasswordField contraseña;
	private JLabel     etiquetaTipoUsuario, etiquetaMatricula, etiquetaContraseña, etiquetaImagen;
	private JButton    botonentrar;
	
	public Login() {
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints posicion = new GridBagConstraints();
		String[] usuarios = {null, "Alumno", "Maestro", "Orientador"};
		 
		ImageIcon logo = new ImageIcon("cobachLogo.gif");
		
		this.etiquetaImagen = new JLabel(logo);
		
		posicion.gridx      = 0;
		posicion.gridy      = 0;
		posicion.gridwidth  = 1;
		posicion.gridheight = 6;
		posicion.fill       = GridBagConstraints.BOTH;
		this.add(this.etiquetaImagen, posicion);
		
		this.etiquetaTipoUsuario = new JLabel("Tipo de usuario:");
		this.listaTipoUsuario    = new JComboBox <String> (usuarios);
		this.listaTipoUsuario.addActionListener(this);
		
		posicion.gridx      = 1;
		posicion.gridy      = 0;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.fill       = GridBagConstraints.NONE;
		posicion.anchor     = GridBagConstraints.WEST;
		this.add(this.etiquetaTipoUsuario, posicion);
		
		posicion.gridx      = 1;
		posicion.gridy      = 1;
		posicion.gridwidth  = 1;
		posicion.gridheight = 1;
		posicion.fill       = GridBagConstraints.HORIZONTAL;
		this.add(this.listaTipoUsuario, posicion);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.listaTipoUsuario){
			if(this.listaTipoUsuario.getSelectedIndex() > 0){
				if(this.listaTipoUsuario.getSelectedIndex() == 1){
					this.etiquetaMatricula =  new JLabel("Matricula:");
				}else{
					this.etiquetaMatricula =  new JLabel("Numero de empleaado:");
				}
				this.etiquetaContraseña = new JLabel("Contraseña");
				
				this.cajaUsuario = new JTextField();
				this.cajaUsuario.setDocument(new Limitador(this.cajaUsuario, Restriccion.NUMERICO, 8, this));
				
				this.botonentrar = new JButton("Entrar!");
				this.botonentrar.addActionListener(this);
			
				this.contraseña = new JPasswordField();
				
				GridBagConstraints posicion = new GridBagConstraints();
				
				posicion.gridx      = 1;
				posicion.gridy      = 2;
				posicion.gridwidth  = 1;
				posicion.gridheight = 1;
				posicion.anchor     = GridBagConstraints.WEST;
				this.add(this.etiquetaMatricula, posicion);
				
				posicion.gridx      = 1;
				posicion.gridy      = 3;
				posicion.gridwidth  = 1;
				posicion.gridheight = 1;
				posicion.fill       = GridBagConstraints.HORIZONTAL;
				posicion.anchor     = GridBagConstraints.WEST;
				this.add(this.cajaUsuario, posicion);
				
				posicion.gridx      = 1;
				posicion.gridy      = 4;
				posicion.gridwidth  = 1;
				posicion.gridheight = 1;
				posicion.fill       = GridBagConstraints.NONE;
				posicion.anchor     = GridBagConstraints.WEST;
				this.add(this.etiquetaContraseña, posicion);
				
				posicion.gridx      = 1;
				posicion.gridy      = 5;
				posicion.gridwidth  = 1;
				posicion.gridheight = 1;
				posicion.fill       = GridBagConstraints.HORIZONTAL;
				posicion.anchor     = GridBagConstraints.WEST;
				this.add(this.contraseña, posicion);
				
				posicion.gridx      = 0;
				posicion.gridy      = 6;
				posicion.gridwidth  = 1;
				posicion.gridheight = 2;
				posicion.fill       = GridBagConstraints.NONE;
				posicion.anchor     = GridBagConstraints.CENTER;
				this.add(this.botonentrar, posicion);
				
				this.revalidate();
				this.repaint();
				
			}else{
				this.remove(this.etiquetaContraseña);
				this.remove(this.etiquetaMatricula);
				this.remove(this.cajaUsuario);
				this.remove(this.contraseña);
				this.remove(this.botonentrar);
				this.revalidate();
				this.repaint();
				
				this.etiquetaContraseña = null;
				this.etiquetaMatricula  = null;
				this.cajaUsuario        = null;
				this.contraseña         = null;
				this.botonentrar        = null;
			}
		}
		
	}

	@Override
	public void campoCambio(JTextField origen) {
		
	}
	
	public static void main(String[] args) {
		Login vista = new Login();
		vista.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		vista.setSize(400, 400);
		vista.setVisible(true);
	}


}
