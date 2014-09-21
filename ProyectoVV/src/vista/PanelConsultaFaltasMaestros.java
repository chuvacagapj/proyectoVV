package vista;

import java.awt.*;

import javax.swing.*;

import controlador.Controlador;

public class PanelConsultaFaltasMaestros extends JPanel{
	
	private JLabel      etiquetaGrupo, etiquetaMateria, etiquetaMes, etiquetaLista;
	private JComboBox   <Integer> listaGrupo;
	private JComboBox   <String>  listaMateria, listaMes;
	private JTable      tabla;
	private JScrollPane barraTabla;
	private Controlador  control;
	
	public PanelConsultaFaltasMaestros(Controlador control){
		this.control = control;
		
		this.setLayout(new GridBagLayout());
		
		this.etiquetaGrupo   = new JLabel("Grupo:");
		this.etiquetaLista   = new JLabel("Lista:");
		this.etiquetaMateria = new JLabel("Materia:");
		this.etiquetaMes     = new JLabel("Mes:");
		
		this.listaGrupo = new JComboBox <Integer> (control.getGrupos());
		
	}

	@Override
	public String toString() {
		return "Consulta";
	}
	
	public static void main(String[] args) {
		Principal vista = new Principal(new PanelConsultaFaltasMaestros());
		vista.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		vista.setSize(500, 500);
		vista.setVisible(true);
	}

}
