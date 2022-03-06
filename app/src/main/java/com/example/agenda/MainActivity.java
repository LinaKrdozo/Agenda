package com.example.agenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_idAgenda, et_asunto, et_actividad;
    CalendarView calendarView;
    private TextView tv_fecha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = (CalendarView)findViewById(R.id.calendarView);
        tv_fecha=(TextView) findViewById(R.id.tv_Mifecha);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int i, int i1, int i2) {
                String fecha = (i1 +1) +"/" +i2 + "/" + i;
                tv_fecha.setText(fecha);
            }
        });

        et_idAgenda=(EditText)findViewById(R.id.txt_idAgenda);
        et_asunto=(EditText)findViewById(R.id.txt_asunto);
        et_actividad=(EditText)findViewById(R.id.txt_actividad);
    }

    public void Registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "agenda.db", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String idAgenda = et_idAgenda.getText().toString();
        String fecha = tv_fecha.getText().toString();
        String asunto = et_asunto.getText().toString();
        String actividad = et_actividad.getText().toString();

        if (!fecha.isEmpty() && !asunto.isEmpty() && !actividad.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("fecha", fecha);
            registro.put("asunto", asunto);
            registro.put("actividad", actividad);

            BaseDeDatos.insert("agenda", null, registro);

            BaseDeDatos.close();
            et_idAgenda.setText("");
            tv_fecha.setText("");
            et_asunto.setText("");
            et_actividad.setText("");

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    //metodo para consultar un recordatorio
    public void Buscar(View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"agenda.db", null, 1);
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();

        String id_Agenda = et_idAgenda.getText().toString();

        if (!id_Agenda.isEmpty()){
            Cursor fila = BaseDeDatabase.rawQuery
                    ("select fecha, asunto, actividad from agenda where id =" + id_Agenda, null);

            if (fila.moveToFirst()){
                tv_fecha.setText(fila.getString(0));
                et_asunto.setText(fila.getString(1));
                et_actividad.setText(fila.getString(2));
                BaseDeDatabase.close();
            }else{
                Toast.makeText(this, "no existe el recordatorio", Toast.LENGTH_SHORT).show();
            }
            BaseDeDatabase.close();
        }else{
            Toast.makeText(this, "Debes introducir el numero del recordatorio", Toast.LENGTH_SHORT).show();
        }
    }


}