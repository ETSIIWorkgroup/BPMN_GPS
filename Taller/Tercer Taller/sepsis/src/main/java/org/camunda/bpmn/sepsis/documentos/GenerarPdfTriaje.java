package org.camunda.bpmn.sepsis.documentos;

import java.io.FileOutputStream;
import java.util.stream.Stream;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/*
 * Clase que genere PDFs correspondientes al área de Triaje. 
 * 
 * Taller III - Tema: SEPSIS - Grupo 5 
 * Integrantes:
 *  - Pedro Escobar Rubio
 *  - Alejandro Fernández Trigo
 *  - Juan Diego Villalobos Quirós
 */

public class GenerarPdfTriaje {

	public static String nombre = "";
	public static String apellidos = "";
	public static String edad = "";
	public static String fechaDeNacimiento = "";
	public static String seguridadSocial = "";
	public static String dni = "";
	public static String sexo = "";
	public static String comunidadAutonoma = "";
	public static String pais = "";
	public static String direccion = "";

	public static void generarHojaDatosTriaje() {
		
		System.out.println("Se ha generado el documento respectivo al triaje para el paciente " + nombre + ".");
		
		try {
			
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("BBDD/" + nombre + "-Triaje.pdf"));
			document.open();
			PdfPTable table = new PdfPTable(3);
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
