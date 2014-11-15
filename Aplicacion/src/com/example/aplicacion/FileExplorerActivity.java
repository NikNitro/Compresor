package com.example.aplicacion;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FileExplorerActivity extends ListActivity {

   private List<String> item = null;
   private List<String> path = null;
   private String root=Selector_memoria.ruta;
   private TextView myPath;
   private String archivo_seleccionado;
   private String archivo_nombre;

   public void finish() {
       Intent data = new Intent();
       data.putExtra("archivo_seleccionado", archivo_seleccionado);
       data.putExtra("archivo_nombre", archivo_nombre);
       setResult(RESULT_OK, data);
       super.finish();
   }

   @Override
   public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.main_file);          
          myPath = (TextView)findViewById(R.id.path);
          
          //Por si no existe tarjeta SD que no explote.
          try {
        	  getDir(root);
          } catch(NullPointerException e) {
        	  Toast texto1 = Toast.makeText(getBaseContext(), "No hay SD", Toast.LENGTH_SHORT);
        	  texto1.show();
          }
   }

   private void getDir(String dirPath){
       myPath.setText(dirPath);
       item = new ArrayList<String>();
       path = new ArrayList<String>();
       File f = new File(dirPath);
       File[] files = f.listFiles();
       
       if(!dirPath.equals(root)){
         item.add(root);
         path.add(root);
         item.add("../");
         path.add(f.getParent());
       }

       for(int i=0; i < files.length; i++){
         File file = files[i];
         path.add(file.getPath());
         if(file.isDirectory())
          item.add(file.getName() + "/");
         else
          item.add(file.getName());
       }

       ArrayAdapter<String> fileList =
          new ArrayAdapter<String>(this, R.layout.row_file, item);

       setListAdapter(fileList);

   }

   @Override
   protected void onListItemClick(ListView l, View v, int position, long id) {
     
    File file = new File(path.get(position));
    if (file.isDirectory())
    {
    if(file.canRead())
       getDir(path.get(position));
      else
      {
      new AlertDialog.Builder(this)
      .setTitle("[" + file.getName() + "] folder no puede ser leido!")
      .setPositiveButton("OK", 
      new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {}
        }).show();
     }
    }
    else
    {
      archivo_seleccionado = file.getAbsolutePath();
      archivo_nombre = file.getName();
      MainActivity.ruta.setText(archivo_seleccionado);
      finish();
    }
  }
   
   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     if (resultCode == RESULT_OK && requestCode == 555) {
       if (data.hasExtra("archivo_seleccionado")) {
         archivo_seleccionado = data.getExtras().getString("archivo_seleccionado");
         archivo_nombre = data.getExtras().getString("archivo_nombre");
       //MainActivity.ruta.setText(archivo_nombre);				//	<--- archivo not in scope
        // MainActivity.ruta = (TextView)archivo_nombre;
        // MainActivity.ruta = (TextView)findViewById(R.id.textView1);

       }
     }
   }
   

}