package com.example.aplicacion;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	private Button open_file_explorer;
	private Button comprimir;
	private Button descomprimir;
	public static TextView ruta;
	private String std;
	public static  String prueba;
	public static TextView informacion;
	private Button abrir;
	private RadioGroup rg;
	 private RadioButton rb;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
              
        ruta = (TextView)findViewById(R.id.txtNombreArchivo);
        informacion = (TextView)findViewById(R.id.textView2);
        informacion.setMovementMethod(new ScrollingMovementMethod());
        
        rg = (RadioGroup) findViewById(R.id.rdgGrupo);
        
        
        comprimir = (Button)findViewById(R.id.btnComprimir);
        comprimir.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				std = ruta.getText().toString();

			

				if(std.equals(getResources().getString(R.string.rutaDelArchivo))){
					Toast  texto1 = Toast.makeText(getBaseContext(), "Seleccione archivo", Toast.LENGTH_SHORT);
		        	texto1.show();
	        	}else{
					informacion.setText("Información: " +std);
					int seleccionado = rg.getCheckedRadioButtonId();
					rb = (RadioButton) findViewById(seleccionado);
					new Comprimir(std, 0, 1, rb.getText().toString());
	        	}

			}
		});
        
        descomprimir = (Button)findViewById(R.id.btnDescomprimir);
        descomprimir.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				std = ruta.getText().toString();
				if(std.equals(getResources().getString(R.string.rutaDelArchivo))){
					Toast  texto1 = Toast.makeText(getBaseContext(), "Seleccione archivo", Toast.LENGTH_SHORT);
		        	texto1.show();
	        	}else{
					informacion.setText("Información: " +std);
					int seleccionado = rg.getCheckedRadioButtonId();
					rb = (RadioButton) findViewById(seleccionado);
					new Comprimir(std, 0, 0, rb.getText().toString());
	        	}

			}
		});
        
        open_file_explorer = (Button)findViewById(R.id.btnBuscar);
        open_file_explorer.setOnClickListener(new OnClickListener() {      
          public void onClick(View v) {
            
        	  
        	  Intent file_explorer = new Intent(MainActivity.this,Selector_memoria.class);
        	 
            startActivityForResult(file_explorer, 555);// <-- ¿?
       
          }
          
        });
        
        abrir = (Button)findViewById(R.id.btnAbrir);
        abrir.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Este boton mostrará el archivo.
	        	std = ruta.getText().toString();
	        	
	        	if(std.equals(getResources().getString(R.string.rutaDelArchivo))){
	        		Toast  texto1 = Toast.makeText(getBaseContext(), "Seleccione archivo", Toast.LENGTH_SHORT);
		        	texto1.show();
	        	}else{
		        	try {
						BinaryIn In = new BinaryIn(std);
						informacion.setText(In.readString());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Log.i("error", e.getMessage());
					}	
	        	}

	        	


	        	
				
			}
		});
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
