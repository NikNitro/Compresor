package com.example.aplicacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;

import android.os.Environment;
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
		Log.i("std", std);
		switch(aux) {
			case 0 : Huffman(std, c); break;
			default: 
				throw new InputMismatchException("Opción no soportada");
		}
	}
	
	private void Huffman(String std, int c)  {
		Huffman h = new Huffman();
		String nombre, extension;
		File primero;
		long tamano, time1, time2,dif, tamanoC;
		File myFile;
		if(c == 1){
			try {
				Log.i("tag", "comprimir");
				//nombre = std.substring(0, std.length()-4);//vale si termina .txt
				extension = std.substring(std.lastIndexOf(".") +1);
				nombre = std.substring(0,std.lastIndexOf("."));
				nombre = nombre+"-compress."+extension;
				primero = new File(std);
				
				myFile = new File(nombre);
				/*
				primero = new File(std);
				nombre = "/"+primero.getName().substring(0, primero.getName().length()-4);//vale si termina .txt
				
				nombre = "storage/sdcard0/Compresor"+nombre;
				Log.i("nombre", nombre);
				//nombre = "/mnt/extSdCard/Alice";
				myFile = new File(nombre+"-compress.txt");
			*/
				try {
					myFile.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Log.i("no se puede crear", e.getMessage());
				}

				time1 = System.nanoTime();
				h.compress(std, nombre);
				time2 = System.nanoTime();
				dif = time2 - time1;
				tamano =primero.length();
				tamanoC = myFile.length();
				MainActivity.informacion.setText("Comprimido: " + myFile.getAbsolutePath());
				MainActivity.informacion.append("\nTiempo: " + dif+ " nanosegundos");	
				MainActivity.informacion.append("\nTamaño original: "+ tamano);
				MainActivity.informacion.append("\nTamaño comprimido: "+ tamanoC);
				MainActivity.informacion.append("\nRatio: "+ ((double)tamanoC)/((double)tamano));
				
			} catch (FileNotFoundException e) {
				Log.i("fichero no existe", e.getMessage());
				
			}
		}else{
			try {
				Log.i("tag", "descomprimir");
			
				//nombre = std.substring(0, std.length()-4);//vale si termina .txt
				extension = std.substring(std.lastIndexOf(".") +1);
				nombre = std.substring(0,std.lastIndexOf("."));
				nombre = nombre+"-decompress."+extension;
				primero = new File(std);
				 myFile = new File(nombre);
				try {
					myFile.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				time1 = System.nanoTime();
				h.expand(std, nombre);
				time2 = System.nanoTime();
				dif = time2 - time1;
				tamano =primero.length();
				tamanoC = myFile.length();
				MainActivity.informacion.setText("Descomprimido: " + myFile.getAbsolutePath());
				MainActivity.informacion.append("\nTiempo: " + dif+ " nanosegundos");	
				MainActivity.informacion.append("\nTamaño comprimido: "+ tamano);
				MainActivity.informacion.append("\nTamaño descomprimido: "+ tamanoC);
				MainActivity.informacion.append("\nRatio: "+ ((double)tamano)/((double)tamanoC));

				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				Log.i("fichero no existe", std);
			}
		}

	}
	

	
	
	
	public static void main(String[] args) {}

}

