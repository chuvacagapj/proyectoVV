package controlador;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;

public class Limitador extends PlainDocument {

	private JTextField  campo;
	private Restriccion restriccion;
	private int numeroMaximoCaracteres;
	private EscudarCampos escuchador;
	
	public Limitador(JTextField a, Restriccion r, int max, EscudarCampos listener){
		this.campo = a;
		this.restriccion = r;
		this.numeroMaximoCaracteres = max;
		this.escuchador = listener;
	}
	
	public void insertString(int arg0, String arg1, AttributeSet arg2) throws BadLocationException 
    {
		switch(this.restriccion){
			case NUMERICO:    if(arg1.matches("[^0-9]")) return;
				 		      break;
			case ALFABETICO:  if(arg1.matches("[^a-zA-ZñÑàÀèÈìÌòÒùÙ ]")) return;
		     				  break;
			case ALFANUMERICO:if(arg1.matches("[^0-9a-zA-ZñÑàÀèÈìÌòÒùÙ ]")) return;
			 				  break;
		}
		
        if ((campo.getText().length()+arg1.length())>this.numeroMaximoCaracteres) 
            return; 
        super.insertString(arg0, arg1, arg2);
        this.escuchador.campoCambio(campo);   
    }
	
	public void remove (int offs, int len) throws BadLocationException{
		super.remove(offs, len);
		this.escuchador.campoCambio(campo);
	}
}
