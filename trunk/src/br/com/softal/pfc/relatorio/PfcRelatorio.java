package br.com.softal.pfc.relatorio;

import java.io.OutputStream;

import com.itextpdf.text.DocumentException;

public abstract class PfcRelatorio extends RelatorioBase {

	public PfcRelatorio(OutputStream out) throws DocumentException {
		super(out);
	}

}
