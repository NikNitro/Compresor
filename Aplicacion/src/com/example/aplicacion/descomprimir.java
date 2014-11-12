package com.example.aplicacion;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class descomprimir {
	private String texto;
	private List<String> descodificacion;
	private List<String> diccionario;
	
	/*
	 * Si dejamos el char[] array -> Sólo funciona cuando son del 0 al 9, por ejemplo en el abracadabra
	 * Si dejamos int [] array -> funciona sólo si en cada una de las celdas del array está el número
	 * 								codificado, lo que creo que no es posible por lo que no vale de na ^^
	 */
	public descomprimir (String nombreArchivo) {
		descodificacion = new ArrayList<String>();
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
		descodificacion.add(v);
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
			descodificacion.add(cadena);
			caracter = cadena.charAt(0);
			 
			 diccionario.add(v+caracter);

			v = diccionario.get(n);
			posicion++;
		}
		
		for(int i = 0; i < descodificacion.size(); i++) {
			System.out.print(descodificacion.get(i));
		}
		System.out.println();
		for(int i = 0; i < diccionario.size(); i++){
			System.out.print(diccionario.get(i)+ ", ");
		}
		
	}
	
	
	
	public static void main(String[] std) {
		descomprimir l = new descomprimir("prueba2.txt");
		l.Descomprimir();
	}
	

}