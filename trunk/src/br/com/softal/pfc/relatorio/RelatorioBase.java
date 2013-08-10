package br.com.softal.pfc.relatorio;

import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public abstract class RelatorioBase {
	protected Document document;
	
	protected Document getInstance() {
		if (document == null) {
			document = new Document(PageSize.A4, 30, 30, 120, 100);
		} 
		return this.document;
	}
	
	public RelatorioBase(OutputStream out) throws DocumentException {
		PdfWriter.getInstance(getInstance(), out);
	}
	
	public void init() {
		document.open();
	}
	
	public void end() {
		document.close();
	}
	
	public abstract void imprimir() throws DocumentException;

}
