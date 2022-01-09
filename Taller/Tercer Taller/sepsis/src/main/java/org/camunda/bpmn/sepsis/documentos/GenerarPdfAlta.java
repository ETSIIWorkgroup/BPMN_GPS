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
 * Clase que genere PDFs correspondientes al �rea de Boxes cuando se genera un alta de paciente. 
 * 
 * Taller III - Tema: SEPSIS - Grupo 5 
 * Integrantes:
 *  - Pedro Escobar Rubio
 *  - Alejandro Fern�ndez Trigo
 *  - Juan Diego Villalobos Quir�s
 */

public class GenerarPdfAlta {

	public static String nombre = "";
	public static String horaTriaje = "";
	public static String horaActivacionCodigoSEPSIS = "";
	public static String horaAtencionMedica = "";
	public static String horaAdministracionAntibiotico = "";
	public static String horaAdministracionSuero = "";
	public static String horaAltaUrgencias = "";
	public static String antibioticosSuministrados = "";
	public static String observaciones = "";
	public static String firma = "";

	public static void generarHojaDatosAlta() {
		
		System.out.println("Se ha generado el documento respectivo al alta del paciente " + nombre + ".");
		
		try {
			
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("BBDD/Documento de Alta - " + nombre + ".pdf"));
			document.open();
			PdfPTable table = new PdfPTable(3);
			table.addCell(new PdfPCell());
			table.addCell(new PdfPCell());
			addFirstTableHeaderBoxes(table);
			addFirstRowsBoxes(table);
			table.addCell(new PdfPCell());
			table.addCell(new PdfPCell());
			table.addCell(new PdfPCell());
			addSecondTableHeaderBoxes(table);
			addSecondRowsBoxes(table);
			table.addCell(new PdfPCell());
			table.addCell(new PdfPCell());
			table.addCell(new PdfPCell());
			addThirdTableHeaderBoxes(table);
			addThirdRowsBoxes(table);
			table.addCell(new PdfPCell());
			table.addCell(new PdfPCell());
			table.addCell(new PdfPCell());
			document.add(table);
			document.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
	}

	private static void addFirstTableHeaderBoxes(PdfPTable table) {
		Stream.of("Hora de triaje", "Hora de activaci�n C�digo SEPSIS", "Hora de atenci�n m�dica")
				.forEach(columnTitle -> {
					PdfPCell header = new PdfPCell();
					header.setBackgroundColor(BaseColor.LIGHT_GRAY);
					header.setBorderWidth(2);
					header.setPhrase(new Phrase(columnTitle));
					table.addCell(header);
				});
	}

	private static void addFirstRowsBoxes(PdfPTable table) {
		table.addCell(horaTriaje);
		table.addCell(horaActivacionCodigoSEPSIS);
		table.addCell(horaAtencionMedica);
	}

	private static void addSecondTableHeaderBoxes(PdfPTable table) {
		Stream.of("Hora de administraci�n de antibi�ticos", "Hora de administraci�n de suero",
				"Hora de alta en Urgencias").forEach(columnTitle -> {
					PdfPCell header = new PdfPCell();
					header.setBackgroundColor(BaseColor.LIGHT_GRAY);
					header.setBorderWidth(2);
					header.setPhrase(new Phrase(columnTitle));
					table.addCell(header);
				});
	}

	private static void addSecondRowsBoxes(PdfPTable table) {
		table.addCell(horaAdministracionAntibiotico);
		table.addCell(horaAdministracionSuero);
		table.addCell(horaAltaUrgencias);
	}

	private static void addThirdTableHeaderBoxes(PdfPTable table) {
		Stream.of("Antibi�ticos suministrados", "Observaciones", "Firma").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

	private static void addThirdRowsBoxes(PdfPTable table) {
		table.addCell(antibioticosSuministrados);
		table.addCell(observaciones);
		table.addCell(firma);
	}
	
}
