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

public class GenerarPdfBoxes {

	public static String horaTriaje = null;
	public static String horaActivacionCodigoSEPSIS = null;
	public static String horaAtencionMedica = null;
	public static String horaAdministracionAntibiotico = null;
	public static String horaAdministracionSuero = null;
	public static String horaAltaUrgencias = null;
	public static String antibioticosSuministrados = null;
	public static String observaciones = null;
	public static String firma = null;

	public static void generarHojaDatosBoxes() {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("boxes.pdf"));
			document.open();
			PdfPTable table = new PdfPTable(3);
			//Path path = Paths.get(ClassLoader.getSystemResource("resources/hospital_logo.svg").toURI());
			//Image img = Image.getInstance(path.toAbsolutePath().toString());
			//img.scalePercent(10);
			//PdfPCell imageCell = new PdfPCell(img);
			//table.addCell(imageCell);
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
		Stream.of("Hora de triaje", "Hora de activación Código SEPSIS", "Hora de atención médica")
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
		Stream.of("Hora de administración de antibióticos", "Hora de administración de suero",
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
		Stream.of("Antibióticos suministrados", "Observaciones", "Firma").forEach(columnTitle -> {
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
