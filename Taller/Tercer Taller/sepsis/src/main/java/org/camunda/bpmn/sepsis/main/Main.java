package org.camunda.bpmn.sepsis.main;

import org.camunda.bpmn.sepsis.resources.Datos;

/*
 * Clase principal que ejecuta todo el proceso. Se lanza como: 
 *  - Bot�n derecho > Run As > Java Application 
 * 
 * Taller III - Tema: SEPSIS - Grupo 5 
 * Integrantes:
 *  - Pedro Escobar Rubio
 *  - Alejandro Fern�ndez Trigo
 *  - Juan Diego Villalobos Quir�s
 */

public class Main {

	public static void main(String[] args) {

		/*
		 * Llama a la funci�n ejecuta() de la clase 'Datos' 
		 * para obtener los datos de la ejecuci�n:
		 */
		Datos.ejecuta();

	}

}
