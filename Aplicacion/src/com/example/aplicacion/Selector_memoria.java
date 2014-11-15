package com.example.aplicacion;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Selector_memoria extends ListActivity{
	private Button raiz;
	private Button memInt;
	private Button memSD;
	public static String ruta;
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
					
				}
			});
	          
	          memInt = (Button)findViewById(R.id.btnMemInterna);
	          memInt.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ruta = "/";
					Intent selector = new Intent(Selector_memoria.this, FileExplorerActivity.class);
					startActivityForResult(selector,  30);
					
				}
			});  
	          
	          memSD = (Button)findViewById(R.id.btnMemSD);
	          memSD.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ruta = "/";
					Intent selector = new Intent(Selector_memoria.this, FileExplorerActivity.class);
					startActivityForResult(selector,  30);
					
				}
			});
	   }

}
