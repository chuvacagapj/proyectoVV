package vista;

import java.awt.*;

import javax.swing.*;

import controlador.Controlador;
import modelo.VO.*;

public class PanelConsultaFaltasMaestros extends JPanel{
	
	private JLabel      etiquetaGrupo, etiquetaMateria, etiquetaMes, etiquetaLista;
	private JComboBox   <Integer> listaGrupo;
	private JComboBox   <String>  listaMateria, listaMes;
	private JTable      tabla;
	private JScrollPane barraTabla;
	private Controlador control;
	private MateriaVO[] materias;
	
	public PanelConsultaFaltasMaestros(Controlador control){
		this.control  = control;
		this.materias = control.getMaterias();
		
		this.setLayout(new GridBagLayout());
		
		this.etiquetaGrupo   = new JLabel("Grupo:");
		this.etiquetaLista   = new JLabel("Lista:");
		this.etiquetaMateria = new JLabel("Materia:");
		this.etiquetaMes     = new JLabel("Mes:");
		
		String [] meses    = {null, "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
		String [] materias = new String [this.materias.length];
		
		int i=0;
		for(MateriaVO m: this.materias){
			materias[i] = this.materias[i].getNombre();
		}
		
		this.listaGrupo   = new JComboBox <Integer> (control.getGrupos());
		this.listaMes     = new JComboBox <String>  (meses);
		this.listaMateria = new JComboBox <String>  (materias);
		
	}

	@Override
	public String toString() {
		return "Consulta";
	}
	
	public static void main(String[] args) {
		Principal vista = new Principal(new PanelConsultaFaltasMaestros(new Controlador()));
		vista.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		vista.setSize(500, 500);
		vista.setVisible(true);
	}

}
