package pdfgenerator;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Main {

	static String nombre = "Juan Diego";
	static String apellidos = "Villalobos Quirós";
	static String edad = "22";

	static String fechaDeNacimiento = "11 de marzo de 1999";
	static String dni = "XXXXXXXXX";
	static String seguridadSocial = "XXXXXXXX";

	static String sexo = "Hombre";
	static String comunidadAutonoma = "Andalucía";
	static String pais = "España";
	static String direccion = "Avenida Virgen de la Esperanza, N54, 3Derecha";

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("test.pdf"));
			document.open();
			PdfPTable table = new PdfPTable(3);
			Path path = Paths.get(ClassLoader.getSystemResource("hosp_britanico_logo.svg").toURI());
			Image img = Image.getInstance(path.toAbsolutePath().toString());
			img.scalePercent(10);
			PdfPCell imageCell = new PdfPCell(img);
			table.addCell(imageCell);
			table.addCell(new PdfPCell());
			table.addCell(new PdfPCell());
			addFirstTableHeader(table);
			addFirstRows(table);
			table.addCell(new PdfPCell());
			table.addCell(new PdfPCell());
			table.addCell(new PdfPCell());
			addSecondTableHeader(table);
			addSecondRows(table);
			table.addCell(new PdfPCell());
			table.addCell(new PdfPCell());
			table.addCell(new PdfPCell());
			addThirdTableHeader(table);
			addThirdRows(table);
			table.addCell(new PdfPCell());
			table.addCell(new PdfPCell());
			table.addCell(new PdfPCell());
			addFourthTableHeader(table);
			addFourthRows(table);
			table.addCell(new PdfPCell());
			table.addCell(new PdfPCell());
			document.add(table);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void addFirstTableHeader(PdfPTable table) {
		Stream.of("Nombre", "Apellidos", "Edad").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

	private static void addFirstRows(PdfPTable table) {
		table.addCell(nombre);
		table.addCell(apellidos);
		table.addCell(edad);
	}

	private static void addSecondTableHeader(PdfPTable table) {
		Stream.of("Fecha de nacimiento", "DNI", "Nº de Seguridad Social").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

	private static void addSecondRows(PdfPTable table) {
		table.addCell(fechaDeNacimiento);
		table.addCell(dni);
		table.addCell(seguridadSocial);
	}

	private static void addThirdTableHeader(PdfPTable table) {
		Stream.of("Sexo", "Comunidad Autónoma", "País de residencia").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

	private static void addThirdRows(PdfPTable table) {
		table.addCell(sexo);
		table.addCell(comunidadAutonoma);
		table.addCell(pais);
	}

	private static void addFourthTableHeader(PdfPTable table) {
		Stream.of("Dirección").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

	private static void addFourthRows(PdfPTable table) {
		table.addCell(direccion);
	}

	/*
	 * static String direccion = "Avenida Virgen de la Esperanza, N54, 3Derecha";
	 */

	@SuppressWarnings("unused")
	private static void addCustomRows(PdfPTable table) throws URISyntaxException, BadElementException, IOException {
		Path path = Paths.get(ClassLoader.getSystemResource("Java_logo.png").toURI());
		Image img = Image.getInstance(path.toAbsolutePath().toString());
		img.scalePercent(10);
		PdfPCell imageCell = new PdfPCell(img);
		table.addCell(imageCell);
		PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
		horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(horizontalAlignCell);
		PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
		verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
		table.addCell(verticalAlignCell);
	}
}