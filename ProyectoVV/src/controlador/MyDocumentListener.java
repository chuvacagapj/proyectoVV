package controlador;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MyDocumentListener implements DocumentListener{

	private JTextField campo;
	private EscudarCampos escuchador;
	
	public MyDocumentListener(JTextField a, EscudarCampos p){
		this.campo = a;
		this.escuchador = p;
	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		escuchador.campoCambio(campo);
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

}
