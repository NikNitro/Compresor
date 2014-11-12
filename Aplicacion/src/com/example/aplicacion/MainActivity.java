package com.example.aplicacion;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {
	private Button open_file_explorer;
	private Button comprimir;
	public static TextView ruta;
	private String std;
	public static  String prueba;
	private TextView informacion;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
              
        ruta = (TextView)findViewById(R.id.txtNombreArchivo);
        informacion = (TextView)findViewById(R.id.textView2);
        
        comprimir = (Button)findViewById(R.id.btnComprimir);
        comprimir.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				std = ruta.getText().toString();
				informacion.setText("Información: " +std);
				new Comprimir(std, 0);
			}
		});
        
        open_file_explorer = (Button)findViewById(R.id.btnBuscar);
        open_file_explorer.setOnClickListener(new OnClickListener() {      
          public void onClick(View v) {
            Intent file_explorer = new Intent(MainActivity.this,FileExplorerActivity.class);
            startActivityForResult(file_explorer, 555);// <-- ¿?
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
