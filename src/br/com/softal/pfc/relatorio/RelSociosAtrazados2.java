package br.com.softal.pfc.relatorio;

import java.io.OutputStream;
import java.util.List;

import br.com.softal.pfc.dto.RelatorioDto;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPTable;

public class RelSociosAtrazados2 extends ItextUtil {

	private PdfPTable table;
	private List<RelatorioDto> dados;

	public PdfPTable getTable() {
		return table;
	}

	public void setTable(PdfPTable table) {
		this.table = table;
	}

	public RelSociosAtrazados2(OutputStream out, String logo, List<RelatorioDto> dados)
			throws DocumentException {
		super(out, logo);
		this.dados = dados;
		this.imprimir();
	}
	
	protected void imprimirDados() throws DocumentException {
		//imprimirCabecalho();
		for (RelatorioDto dto : dados) {
			this.imprimirMes(dto.getDsMes());
			this.imprimirDadosMes(dto);
			document.add(new Paragraph(" "));
		}	
	}
	
	
	@Override
	protected void imprimir() throws DocumentException {
		try {
			document.open();
			
			this.imprimirDados();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
		}
	}
	
	private void imprimirCabecalho() throws DocumentException {
		table = new PdfPTable(new float[] { 100 });
		table.setWidthPercentage(100);
		table.addCell("");
		getInstance().add(table);
	}

	private void imprimirMes(String mes) throws DocumentException {
		table = new PdfPTable(new float[] { 100 });
		table.setWidthPercentage(100);
		table.getDefaultCell().setBorder(Cell.NO_BORDER);
		table.addCell(new Phrase("Mês: " + mes, FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD)));
		getInstance().add(table);
	}

	private void imprimirDadosMes(RelatorioDto dto) throws DocumentException {
		List<RelatorioDto> socios = dto.getSocios();
		table = new PdfPTable(new float[] { 10, 17, 10, 63 });
		table.setWidthPercentage(100);
		
		table.addCell(new Phrase("Seq.", FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD)));
		table.addCell(new Phrase("Data", FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD)));
		table.addCell(new Phrase("Placar", FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD)));
		table.addCell(new Phrase("Atrasados", FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD)));
		int i = 0;
		for (RelatorioDto d : socios) {
			table.addCell(new Phrase(++i + "", FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL)));
			table.addCell(new Phrase(d.getDtPartidastring(), FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL)));
			table.addCell(new Phrase(d.getNuGolvencedor() + " x " + d.getNuGolperdedor(), FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL)));
			table.addCell(new Phrase(d.getNmApelido(), FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL)));
		}
		getInstance().add(table);
	}
	
}
