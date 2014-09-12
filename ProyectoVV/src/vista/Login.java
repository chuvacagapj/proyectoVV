package vista;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controlador.Iniciadora;
import modelo.Conexion;

public class Login extends JFrame implements ActionListener{
	private JLabel usuarioEtiqueta, passwordEtiqueta;
	private JTextField usuarioCampo;
	private JPasswordField passwordCampo;
	private JButton entrarBoton;
	private JComboBox tipoUsuario;
	
	public Login (){
		super("Login");
		
		Container contenedor = this.getContentPane();
		contenedor.setLayout(new FlowLayout());
		
		//this.tipoUsuario      = ne
		this.usuarioEtiqueta  = new JLabel   ("Usuario");
		this.passwordEtiqueta = new JLabel("contraseña");
		this.usuarioCampo     = new JTextField      (20);
		this.passwordCampo    = new JPasswordField  (20);
		this.entrarBoton      = new JButton   ("entrar");
		
		contenedor.add(this.usuarioEtiqueta );
		contenedor.add(this.usuarioCampo    );
		contenedor.add(this.passwordEtiqueta);
		contenedor.add(this.passwordCampo   );
		contenedor.add(this.entrarBoton     );
		
		this.setSize(250, 150);
		this.entrarBoton.addActionListener(this);
		
	}
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent evento){
		
		if(evento.getSource() == this.entrarBoton){
			Conexion.setInfo(this.usuarioCampo.getText(), "contro_escolar", this.passwordCampo.getText(), "localhost");
			Conexion a = Conexion.getConexion();
			if(a != null){
				 JOptionPane.showMessageDialog(null, "Entro a la base de datos", null, JOptionPane.INFORMATION_MESSAGE);
			}
			Iniciadora.fiinal();
		}
	}
}
