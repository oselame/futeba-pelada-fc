package br.com.softal.pfc.relatorio;

import java.io.OutputStream;
import java.util.List;

import javax.swing.text.Style;

import br.com.softal.pfc.dto.RelatorioDto;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class RelSociosAtrazados extends PfcRelatorio {

	private PdfPTable table;
	private List<RelatorioDto> dados;

	public PdfPTable getTable() {
		return table;
	}

	public void setTable(PdfPTable table) {
		this.table = table;
	}

	public RelSociosAtrazados(OutputStream out, List<RelatorioDto> dados)
			throws DocumentException {
		super(out);
		this.dados = dados;
		init();
		imprimir();
		end();
	}

	private void imprimirCabecalho() throws DocumentException {
		table = new PdfPTable(new float[] { 100 });
		table.addCell("Pelada Futebol Clube");
		document.add(table);
	}

	private void imprimirMes(String mes) throws DocumentException {
		table = new PdfPTable(new float[] { 100 });
		table.addCell("Mês: " + mes);
		document.add(table);
	}

	private void imprimirDadosMes(RelatorioDto dto) throws DocumentException {
		List<RelatorioDto> socios = dto.getSocios();
		table = new PdfPTable(new float[] { 10, 17, 10, 63 });
		
		Cell cellDados = new Cell();
		cellDados.setFont(new Font(FontFamily.COURIER,8,Font.NORMAL));
		table.addCell(cellDados.print("Seq."));
		table.addCell(cellDados.print("Data"));
		table.addCell(cellDados.print("Placar"));
		table.addCell(cellDados.print("Atrazados"));
		int i = 0;
		for (RelatorioDto d : socios) {
			table.addCell(++i + "");
			table.addCell(d.getDtPartidastring());
			table.addCell(d.getNuGolvencedor() + " x " + d.getNuGolperdedor());
			table.addCell(d.getNmApelido());
		}
		document.add(table);
	}

	private void imprimirDados() throws DocumentException {
		for (RelatorioDto dto : dados) {
			this.imprimirMes(dto.getDsMes());
			this.imprimirDadosMes(dto);
		}
	}

	@Override
	public void imprimir() throws DocumentException {
		try {
			this.imprimirCabecalho();
			this.imprimirDados();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

}
