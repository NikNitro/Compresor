package com.example.aplicacion;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Selector_memoria extends ListActivity{
	private Button raiz;
	private Button memInt;
	private Button memSD;
	public static String ruta;
	
	//El finish es para que al elegir el archivo vaya directamente a la principal y no pase de nuevo por esta.
	   public void finish() {
	       super.finish();
	   }
	 @Override
	   public void onCreate(Bundle savedInstanceState) {

         super.onCreate(savedInstanceState);
         setContentView(R.layout.selector_memoria);    
         
		 
	          raiz = (Button)findViewById(R.id.btnRaiz);
	          raiz.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ruta = "/";
					Intent selector = new Intent(Selector_memoria.this, FileExplorerActivity.class);
					startActivityForResult(selector,  30);
					finish();					
				}
			});
	          
	          memInt = (Button)findViewById(R.id.btnMemInterna);
	          memInt.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ruta = "/storage/sdcard0/";
					Intent selector = new Intent(Selector_memoria.this, FileExplorerActivity.class);
					startActivityForResult(selector,  30);
					finish();
				}
			});  
	          
	          memSD = (Button)findViewById(R.id.btnMemSD);
	          memSD.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ruta = "/storage/extSdCard/";
					Intent selector = new Intent(Selector_memoria.this, FileExplorerActivity.class);
					startActivityForResult(selector,  30);
					finish();					
				}
			});
	   }

}
