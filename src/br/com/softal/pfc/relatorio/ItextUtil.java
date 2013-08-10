package br.com.softal.pfc.relatorio;

import java.io.OutputStream;
import java.net.URL;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class ItextUtil extends PdfPageEventHelper {
	
	protected Document document;
	protected static String logo;
	
	public Document getInstance() {
		if (document == null) {
			document = new Document(PageSize.A4, 30, 30, 120, 100);
		}
		return document;
	}
	
	
	/** * Declare variable of type BaseFont */
	public BaseFont helv;
	
	/** Creates a new instance of ITextUtil*/
	public ItextUtil() {}
	
	public ItextUtil(OutputStream out, String logo) { 
		// step1
		this.document = getInstance();
		this.logo = logo;
		boolean isException = false; 
		try {
			// step2
			PdfWriter writer = PdfWriter.getInstance(document, out);
			writer.setPageEvent(new ItextUtil());
			// step3
			//document.open();
			//generate the table
			
			//imprimir();
			
			//document.add(getYourSpecialTable());
		} catch (Exception de) {
			de.printStackTrace();
			isException = true;
		} finally {
			//Close the document object
			//document.close();
			if (isException == false) {
				//if there has been no exception print the success message
			} 
		}
	}
	

	private PdfPTable getHeader(PdfWriter writer) throws DocumentException {
		Image image=null;		
		if (this.logo != null) {
			try {
				image = Image.getInstance( this.logo );
				image.scaleToFit(53, 54);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		PdfPTable headTable = new PdfPTable(2);
		headTable.setWidths(new int[] { 11, 89 });
		headTable.setWidthPercentage(100);
		//headTable.getDefaultCell().setBorder(Cell.NO_BORDER);
		headTable.getDefaultCell().setPadding(2);
		headTable.getDefaultCell().setBorderWidth(0);

		headTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		if (image != null) {
			headTable.addCell( new Phrase(new Chunk(image, 0, 0)) );
		} else {
			headTable.addCell(" ");
		}
		
		headTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		headTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		headTable.addCell("Pelada Futebol Clube");

		//headTable.addCell(getYourNameInHeader());

		return headTable;
	}

	private PdfPTable getYourNameInHeader() throws DocumentException {
		PdfPTable headerTable = new PdfPTable(3);
		headerTable.setWidths(new int[] { 35, 35, 30 });
		headerTable.setWidthPercentage(100);
		headerTable.getDefaultCell().setPadding(2);
		headerTable.getDefaultCell().setBorderWidth(0);
		headerTable.addCell(new Phrase("Name: My Name", FontFactory.getFont(
				FontFactory.HELVETICA, 9, Font.BOLD)));
		headerTable.addCell(new Phrase("Company: My Company", FontFactory
				.getFont(FontFactory.HELVETICA, 9, Font.BOLD)));
		headerTable.addCell(new Phrase("Designation: My Designation",
				FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD)));
		return headerTable;
	}

	public void onEndPage(PdfWriter writer, Document document) {
		try {
			Rectangle page = document.getPageSize();
			// we will print the header using the code below
			PdfPTable headTable = getHeader(writer);
			// total width of the table is set
			headTable.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
			// the table is printed at the spcific location
			headTable.writeSelectedRows(0, -1, document.leftMargin(), 800, writer.getDirectContent());
			// we will print the footer using the code below
			PdfPTable footTable = getFooterSignatures();
			footTable.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
			footTable.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(), writer.getDirectContent());
		} catch (DocumentException ex) {
			ex.printStackTrace();
		}
	}
	
	private PdfPTable getFooterSignatures() throws DocumentException {
		Font fontStyleFooters = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
		//the following code will create a table with 2 columns
		PdfPTable footTable = new PdfPTable(2);
		//now we set the widths of each of the columns
		footTable.setWidths(new int[]{50, 50});
		//set the width of the table
		footTable.setWidthPercentage(100);
		//set the padding
		footTable.getDefaultCell().setPadding(2);
		//the border width is set below. it can be any floating point value
		//you could use 0.5f for a thin border that gives a good look to the table
		//since we are using 0 border width, the border wont apppear on this particular table
		footTable.getDefaultCell().setBorderWidth(0);
		//below we set the alignment of the cell values
		//each time you change the alignment, it applies to all the cells coming after it
		footTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		//the following line will add a cell to the table with value "Signature1"
		//notice fontStyleFooters? that is the font that we created in the beginning of this method
		footTable.addCell(new Phrase("Diversão antistress", fontStyleFooters));
		//this cell will be blank. Yes! we can directly type in a string too above, but in order to
		//use special fonts for special cell values, we are using a Phrase above
		footTable.addCell(" ");
		return footTable; 
	}
	
	protected void imprimir() throws DocumentException {
	}
		
}
