package com.unamjorge.practicas.atividadesarrollodeunaactividad;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Actividad2 extends AppCompatActivity {
    TextView txtnombre,txtTelefono,txtEmail,txtDescripcion,txtFecha;
    int Anio=-1;
    int Mes=-1;
    int Dia=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);
       Inicializar();
    }

    private void Inicializar() {
        Bundle Elementos =getIntent().getExtras();
        String Nombre=Elementos.getString("Nombre");
        String Telefono=Elementos.getString("Telefono");
        String Email=Elementos.getString("Email");
        String Descripcion=Elementos.getString("Descripcion");
         Anio= Elementos.getInt("anio");
         Mes= Elementos.getInt("mes");
         Dia= Elementos.getInt("dia");

        txtnombre=(TextView)findViewById(R.id.txtNombre);
        txtTelefono=(TextView)findViewById(R.id.txttelefono);
        txtEmail=(TextView)findViewById(R.id.txtemail);
        txtDescripcion=(TextView)findViewById(R.id.txtdescripcion);
        txtFecha=(TextView)findViewById(R.id.txtFecha);
        txtnombre.setText(Nombre);
        txtTelefono.setText(Telefono);
        txtEmail.setText(Email);
        txtDescripcion.setText(Descripcion);
        txtFecha.setText(Dia+"/"+Mes+"/"+Anio);

    }

@Override
    public  boolean onKeyDown(int keyCode, KeyEvent event){

        if (keyCode==KeyEvent.KEYCODE_BACK){

            Intent intent=new Intent(Actividad2.this,MainActivity.class);
            startActivity(intent);

        }
        return  super.onKeyDown(keyCode, event);

    }


    public void  editar(View v){

        Intent intent=new Intent(Actividad2.this,MainActivity.class);
        String   Nombre      =txtnombre.getText()+""
                ,telefono    =txtTelefono.getText()+""
                ,email       =txtEmail.getText()+""
                ,descripcion = txtDescripcion.getText()+""
                ;
        intent.putExtra("Nombre",Nombre);
        intent.putExtra("Telefono",telefono);
        intent.putExtra("Email",email);
        intent.putExtra("Descripcion",descripcion);
        intent.putExtra("mes",Mes);
        intent.putExtra("anio",Anio);
        intent.putExtra("dia",Dia);
        startActivity(intent);
        finish();
    }
}
