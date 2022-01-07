package org.camunda.bpmn.sepsis.main;

import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GenerarPdfTriaje {

	public static String nombre = "Alejandro";
	public static String apellidos = "Fernandez";
	public static String edad = "24";
	public static String fechaDeNacimiento = "13-10";
	public static String dni = "12324235";
	public static String seguridadSocial = "1232132";
	public static String sexo = "V";
	public static String comunidadAutonoma = "Sevilla";
	public static String pais = "España";
	public static String direccion = "Calle Falsa 123";

	public static void generarHojaDatosTriaje() {
		System.out.println("Hemos accedido a la clase GenerarHojaDatosTriaje");
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("triaje.pdf"));
			document.open();
			PdfPTable table = new PdfPTable(3);
			//Path path = Paths.get(ClassLoader.getSystemResource("C:/Users/aleja/Documents/Otros Repositorios/BPMN_GPS/Taller/Tercer Taller/sepsis/resources/hospital_logo.svg").toURI());
			//Image img = Image.getInstance(path.toAbsolutePath().toString());
			//img.scalePercent(10);
			//PdfPCell imageCell = new PdfPCell(img);
			//table.addCell(imageCell);
			table.addCell(new PdfPCell());
			table.addCell(new PdfPCell());
			addFirstTableHeaderTriaje(table);
			addFirstRowsTriaje(table);
			table.addCell(new PdfPCell());
			table.addCell(new PdfPCell());
			table.addCell(new PdfPCell());
			addSecondTableHeaderTriaje(table);
			addSecondRowsTriaje(table);
			table.addCell(new PdfPCell());
			table.addCell(new PdfPCell());
			table.addCell(new PdfPCell());
			addThirdTableHeaderTriaje(table);
			addThirdRowsTriaje(table);
			table.addCell(new PdfPCell());
			table.addCell(new PdfPCell());
			table.addCell(new PdfPCell());
			addFourthTableHeaderTriaje(table);
			addFourthRowsTriaje(table);
			table.addCell(new PdfPCell());
			table.addCell(new PdfPCell());
			document.add(table);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void addFirstTableHeaderTriaje(PdfPTable table) {
		Stream.of("Nombre", "Apellidos", "Edad").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

	private static void addFirstRowsTriaje(PdfPTable table) {
		table.addCell(nombre);
		table.addCell(apellidos);
		table.addCell(edad);
	}

	private static void addSecondTableHeaderTriaje(PdfPTable table) {
		Stream.of("Fecha de nacimiento", "DNI", "Nº de Seguridad Social").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

	private static void addSecondRowsTriaje(PdfPTable table) {
		table.addCell(fechaDeNacimiento);
		table.addCell(dni);
		table.addCell(seguridadSocial);
	}

	private static void addThirdTableHeaderTriaje(PdfPTable table) {
		Stream.of("Sexo", "Comunidad Autónoma", "País de residencia").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

	private static void addThirdRowsTriaje(PdfPTable table) {
		table.addCell(sexo);
		table.addCell(comunidadAutonoma);
		table.addCell(pais);
	}

	private static void addFourthTableHeaderTriaje(PdfPTable table) {
		Stream.of("Dirección").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

	private static void addFourthRowsTriaje(PdfPTable table) {
		table.addCell(direccion);
	}
}
