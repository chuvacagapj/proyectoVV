package vista;

import javax.swing.*;

import modelo.VO.*;

public class PanelConsultaFaltasAlumnos extends JPanel{
	private JLabel etiquetaFaltas, etiquetaJustificadas, etiquetaNoJustificadas, etiquetaMax, etiquetaN, etiquetaM, etiquetaNM, etiquetaF;
	private JTable tabla;
	private JButton verMas;
	private ConcentradoFaltasVO informacion;
	private FaltaVO lista;
	private JScrollPane barraTabla;
	
	public PanelConsultaFaltasAlumnos(ConcentradoFaltasVO informacion){
		this.informacion = informacion;
		
		this.etiquetaF = new JLabel(informacion.getNumeroFaltas().toString());
		this.etiquetaFaltas = new JLabel(" Faltas: ");
		this.etiquetaJustificadas = new JLabel(" Justificadas: ");
		this.etiquetaM = new JLabel(informacion.getNumeroJustificadas().toString());
		this.etiquetaMax = new JLabel(" Maximo de faltas: ");
		this.etiquetaN = new JLabel(informacion.getNumeroFaltas().toString());
		this.etiquetaNM = new JLabel(informacion.getNoJustificadas().toString());
		this.etiquetaNoJustificadas = new JLabel(" No Justificadas: ");
	}

	@Override
	public String toString() {
		return this.informacion.getMateria();
	}
	
	
}
