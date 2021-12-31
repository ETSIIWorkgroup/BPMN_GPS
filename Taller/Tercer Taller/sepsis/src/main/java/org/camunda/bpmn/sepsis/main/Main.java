package org.camunda.bpmn.sepsis.main;

import org.camunda.bpmn.sepsis.resources.Datos;

/*
 * Clase principal que ejecuta todo el proceso. Se lanza como: 
 *  - Botón derecho > Run As > Java Application 
 * 
 * Taller III - Tema: SEPSIS - Grupo 5 
 * Integrantes:
 *  - Pedro Escobar Rubio
 *  - Alejandro Fernández Trigo
 *  - Juan Diego Villalobos Quirós
 */

public class Main {

	public static void main(String[] args) {

		/*
		 * Llama a la función ejecuta() de la clase 'Datos' 
		 * para obtener los datos de la ejecución:
		 */
		Datos.ejecuta();

	}

}
