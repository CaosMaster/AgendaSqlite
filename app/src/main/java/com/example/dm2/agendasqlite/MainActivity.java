package com.example.dm2.agendasqlite;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private EditText nom;
    private EditText num;
   // private EditText dni;
    private TextView todo;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nom=(EditText) findViewById(R.id.txtnombre);
        num=(EditText) findViewById(R.id.txtnumero);
       // dni=(EditText) findViewById(R.id.txtdni);
        todo=(TextView) findViewById(R.id.txttodo);


    }

    public void anadir (View view){

        if (!(nom.getText().toString().equals("") || num.getText().toString().equals(""))) {
            //abrimos la base de datos en modo escritura
            AgendaSqlite agdbh = new AgendaSqlite(this, "DBHagenda3", null, 1);

            db = agdbh.getWritableDatabase();

            //si hemos abierto correctamente la base de datos
            if (db != null) {
                //insertamos los usuarios

                try {
                    db.execSQL("INSERT INTO agenda3 (dni,nombre,numero)" + " VALUES ('" + "','" + nom.getText().toString() + "'," + num.getText().toString() + ")");
                } catch (SQLException e) {
                    Toast.makeText(this, "el dni introducido ya existe", Toast.LENGTH_SHORT).show();
                }
            }

            db.close();
        }else {
            Toast.makeText(this, "Introduzca todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void consultar(View view){
        //abrimos la base de datos en modo escritura
        AgendaSqlite agdbh= new AgendaSqlite(this,"DBHagenda3",null,1);

        db=agdbh.getWritableDatabase();

        //si hemos abierto correctamente la base de datos
        if (db!=null){
            //insertamos los usuarios
            Cursor c=db.rawQuery("SELECT dni,nombre,numero FROM agenda2",null);
            todo.setText("");
            if (c.moveToFirst()){

                do{
                    String dni=c.getString(0);
                    String nomb=c.getString(1);
                    int nume=c.getInt(2);

                    todo.setText(todo.getText()+"\n dni: "+dni+" nombre: "+nomb+" numero: "+nume);
                }while(c.moveToNext());
            }
        }

        db.close();
    }

    public void eliminar(View view){

        if (!(nom.getText().toString().equals("") || num.getText().toString().equals(""))) {
            //abrimos la base de datos en modo escritura
            AgendaSqlite agdbh = new AgendaSqlite(this, "DBHagenda3", null, 1);

            db = agdbh.getWritableDatabase();

            db.execSQL("DELETE FROM agenda3 WHERE dni='" + "'");

            db.close();
        }else {
            Toast.makeText(this, "Introduzca todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

    public void editar(View view){

        if (!(nom.getText().toString().equals("") || num.getText().toString().equals(""))) {
            //abrimos la base de datos en modo escritura
            AgendaSqlite agdbh = new AgendaSqlite(this, "DBHagenda3", null, 1);

            db = agdbh.getWritableDatabase();

            db.execSQL("UPDATE agenda3 set nombre='" + nom.getText().toString() + "'" + "," + "numero=" + num.getText().toString() + " WHERE dni='"  + "'");

            db.close();
        } else {
            Toast.makeText(this, "Introduzca todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

    public void borrarBD(View view){
        File f = new File("/data/data/com.example.dm2.agendasqlite/databases/DBHagenda3");
       // File f2 = new File("/data/data/com.example.dm2.agendasqlite/databases/DBHagedna-journal");


        f.delete();
       // f2.delete();
    }




}
