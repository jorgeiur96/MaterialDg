package com.unamjorge.practicas.atividadesarrollodeunaactividad;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {
    TextInputEditText txtNombre,txtTelefono,txtEmail,txtDescripcion;

    DatePicker datefecha;
    int Anio=-1;
    int Mes=-1;
    int Dia=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Inicializar();
    }

    private void Inicializar() {
        txtNombre=(TextInputEditText)findViewById(R.id.txtNombre);
        txtTelefono=(TextInputEditText)findViewById(R.id.txtTelefono);
        txtEmail=(TextInputEditText)findViewById(R.id.txtEmail);
        txtDescripcion=(TextInputEditText)findViewById(R.id.txtdescripcion);
        datefecha=(DatePicker)findViewById(R.id.pdDatosFecha);

        res();
    }

    public  void bntConfimarfecha(View btn){
        if(datefecha.getYear()< 2004 && datefecha.getYear()>1926){
            mensaje(btn,"Fecha asignada y valida ");
        } else{

            mensaje(btn,"Fecha  no puede ser asignada  debe ser mayor a 13 años y menos a 100 años");
        }

    }
    public  void  btnCancelarfecha(View btn){

        mensaje(btn,"Fecha Reiniciada");

        datefecha.init(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            }
        });



    }
    public void res(){
        Bundle Elementos =getIntent().getExtras();


        if(Elementos != null){
            String Nombre=Elementos.getString("Nombre");
            String Telefono=Elementos.getString("Telefono");
            String Email=Elementos.getString("Email");
            String Descripcion=Elementos.getString("Descripcion");
            Anio= Elementos.getInt("anio");
            Mes= Elementos.getInt("mes");
            Dia= Elementos.getInt("dia");

            txtNombre.setText(Nombre);
            txtTelefono.setText(Telefono);
            txtEmail.setText(Email);
            txtDescripcion.setText(Descripcion);

            datefecha.init(Anio, Mes-1, Dia, new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                }
            });


        }
    }

    @Override
    public  boolean onKeyDown(int keyCode, KeyEvent event){

        if (keyCode==KeyEvent.KEYCODE_BACK){

          finish();
        }
        return  super.onKeyDown(keyCode, event);

    }


    public  void B1clik1(View v){

        Intent intent=new Intent(MainActivity.this,Actividad2.class);
        String   Nombre      =txtNombre.getText()+""
                ,telefono    =txtTelefono.getText()+""
                ,email       =txtEmail.getText()+""
                ,descripcion = txtDescripcion.getText()+""
                ;

        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
        if(Nombre.equals("")){
            mensaje(v,"Nombre Se Requiere");
        }else if(telefono.equals("")){

            mensaje(v,"Telefono Se Requiere");
        }else if(email.equals("")){

            mensaje(v,"Email Se Requiere o  no tiene  el  formato adecuado");
        } else if(descripcion.equals("")){
            mensaje(v,"Descripcion Se Requiere");

        }else if(datefecha.getYear()>2004 && datefecha.getYear()>1926){
            mensaje(v,"No  puede  Ser Menor de  13 años ni mayor  a 100 años ");

        }else if(mather.find() == false) {
            mensaje(v,"El email ingresado es inválido.");
        }else {

            
            intent.putExtra("Nombre",Nombre);
            intent.putExtra("Telefono",telefono);
            intent.putExtra("Email",email);
            intent.putExtra("Descripcion",descripcion);
            intent.putExtra("mes",datefecha.getMonth()+1);
            intent.putExtra("anio",datefecha.getYear());
            intent.putExtra("dia",datefecha.getDayOfMonth());
            startActivity(intent);
            finish();
        }

    }

   public  void mensaje (View v ,String menajae){
       Snackbar.make(v,menajae,Snackbar.LENGTH_LONG)
               .setActionTextColor(getResources().getColor(R.color.colorblanco))
               .show();
   }




}
