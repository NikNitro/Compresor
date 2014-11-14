package com.example.aplicacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;

import android.util.Log;

public class Comprimir {

	private File archivo;
	private File archivoComprimido;
	
	/**
	 * 
	 * @param std
	 * @param aux  0 para Huffman, 1 para...
	 */
	public Comprimir(String std, int aux, int c) {
		
		switch(aux) {
			case 0 : Huffman(std, c); break;
			default: 
				throw new InputMismatchException("Opción no soportada");
		}
	}
	
	private void Huffman(String std, int c)  {
		Huffman h = new Huffman();
		String nombre;
		File primero;
		long tamano, time1, time2,dif, tamanoC;
		File myFile;
		if(c == 1){
			try {
				Log.i("tag", "comprimir");
				nombre = std.substring(0, std.length()-4);//vale si termina .txt
				primero = new File(std);
				
				myFile = new File(nombre+"-compress.txt");

				try {
					myFile.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				time1 = System.nanoTime();
				h.compress(std, nombre+"-compress.txt");
				time2 = System.nanoTime();
				dif = time2 - time1;
				tamano =primero.length();
				tamanoC = myFile.length();
				MainActivity.informacion.setText("Comprimido");
				MainActivity.informacion.append("\nTiempo: " + dif+ " nanosegundos");	
				MainActivity.informacion.append("\nTamaño original: "+ tamano);
				MainActivity.informacion.append("\nTamaño comprimido: "+ tamanoC);
				MainActivity.informacion.append("\nRatio: "+ ((double)tamanoC)/((double)tamano));
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				Log.i("fichero", "fichero no existe");
			}
		}else{
			try {
				Log.i("tag", "descomprimir");
				nombre = std.substring(0, std.length()-4);//vale si termina .txt
				primero = new File(std);
				 myFile = new File(nombre+"-descompress.txt");
				try {
					myFile.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				time1 = System.nanoTime();
				h.expand(std, nombre+"-descompress.txt");
				time2 = System.nanoTime();
				dif = time2 - time1;
				tamano =primero.length();
				tamanoC = myFile.length();
				MainActivity.informacion.setText("Descomprimido");
				MainActivity.informacion.append("\nTiempo: " + dif+ " nanosegundos");	
				MainActivity.informacion.append("\nTamaño comprimido: "+ tamano);
				MainActivity.informacion.append("\nTamaño descomprimido: "+ tamanoC);
				MainActivity.informacion.append("\nRatio: "+ ((double)tamano)/((double)tamanoC));

				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				Log.i("fichero", "fichero no existe");
			}
		}

	}
	
	
	
	
	
	public static void main(String[] args) {}

}
