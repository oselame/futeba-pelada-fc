package br.com.softal.pfc.relatorio;

import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;

public class Cell extends PdfPCell {
	private Phrase texto;
	
	public Cell() {
		setPhrase(new Phrase());
	}
	
	public Phrase getTexto() {
		return texto == null ? new Phrase() : texto;
	}

	private void setTexto(Phrase texto) {
		this.texto = texto;
	}

	public Cell print(String text) {
		getTexto().clear();
		getTexto().add( text );
		this.addElement(getTexto());
		return this;
	}
	
	public void setFont(Font font) {
		getTexto().setFont(font);
	}
}
