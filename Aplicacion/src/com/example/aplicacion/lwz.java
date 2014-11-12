package com.example.aplicacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class lwz {
	private String texto;
	private List<Integer> codificacion;
	private List<String> diccionario;
	private String descodificacion;
	
	public lwz (String nombreArchivo) {
		codificacion = new ArrayList<Integer>();
		diccionario  = new ArrayList<String>(); 
		try {
			BinaryIn In = new BinaryIn(nombreArchivo);
			texto = In.readString();
		} catch (FileNotFoundException e) {e.printStackTrace();}
		
		
		//hay que meter comas, puntos y demás... Y MAYUSCULAS
		
		for(char c = 'a'; c <= 'z'; c++ ) {
			diccionario.add(String.valueOf(c));
		}
		diccionario.add("0");
		/*
		diccionario.add("a");
		diccionario.add("b");
		diccionario.add("c");
		diccionario.add("d");
		diccionario.add("r");
		*/
		
	}
	
	
	public void Comprimir() {
		char[] array = texto.toCharArray();
		String auxiliar;
		int contadorParaElTexto = 0, maximoTamano = 2;
		
		while(contadorParaElTexto<array.length) {
			int tamano;
			tamano= maximoTamano;
			if(contadorParaElTexto+maximoTamano>= array.length) tamano = array.length - contadorParaElTexto;
			auxiliar = take(tamano, contadorParaElTexto, array);
			boolean seguir = true;

			//Ya tenemos la siguiente subCadena. Ahora buscaremos si está en el diccionario.
			while(seguir) {
				System.out.println(auxiliar);/////////////
				if(!diccionario.contains(auxiliar)) {
					tamano--;
					System.out.println("\t lo añade");
					diccionario.add(auxiliar);
					auxiliar = auxiliar.substring(0, auxiliar.length()-1);
					
				} else {
					seguir = false;
					if(contadorParaElTexto + tamano < array.length){
						String aux = take(tamano+1, contadorParaElTexto, array );
						if(!diccionario.contains(aux)) diccionario.add(aux);
					}
					codificacion.add(diccionario.indexOf(auxiliar)+1);//El +1 es para que nos guarde un numero empezando por 1
					
					contadorParaElTexto += tamano;
					if(auxiliar.length()>=maximoTamano)
						maximoTamano++;
					
				}
			}
			System.out.println("    Sale del bucle.");/////////////
			
			
			
		}
		
		for(int i = 0; i < codificacion.size(); i++) {
			System.out.print(codificacion.get(i)+ ", ");
		}
		System.out.println();
		for(int i = 0; i < diccionario.size(); i++){
			System.out.print(diccionario.get(i)+ ", ");
		}
		
		//Hay que hacer un if contadorPar...<array.length-1 por si nos dejamos alguno

	}
	
	/**
	 * Coge i caracteres de un array desde principio
	 * @param i
	 * @return
	 */
	private String take(int i, int principio, char[] array) {
		char[] res = new char[i];
		System.arraycopy(array, principio, res, 0, i);
		return String.copyValueOf(res);
	}
	
	public void Descomprimir() {
		//char[] array= texto.toCharArray()	;
		int [] array = {20, 15, 2, 5, 15, 18, 14, 15, 20, 28, 30, 32, 37, 31, 33, 35};
		int posicion= 1;

		System.out.println(array[0]);
		int p = ((int) array[0])-1;
			//	p= p-48; usar en caso de de que array sea de char
		System.out.println(p);
		String v = diccionario.get(p);
		System.out.println(v);
		descodificacion+=v;;
		char caracter = v.charAt(0);
		System.out.println(caracter);
		
		while(posicion < array.length){
			String cadena;
			int n = ( (int) array[posicion])-1;
			//n = n-48; usar en caso de de que array sea de char
			if(diccionario.size() <= n){
	            cadena = v+caracter;
			}else{
				cadena = diccionario.get(n);
			}
			descodificacion+=cadena;
			caracter = cadena.charAt(0);
			 
			 diccionario.add(v+caracter);

			v = diccionario.get(n);
			posicion++;
		}
		
		for(int i = 0; i < descodificacion.length(); i++) {
			System.out.print(descodificacion+=i);
		}
		System.out.println();
		for(int i = 0; i < diccionario.size(); i++){
			System.out.print(diccionario.get(i)+ ", ");
		}
		
	}
	
	public static void main(String[] std) {
		lwz l = new lwz("prueba2.txt");
		l.Comprimir();
	}
	

}
