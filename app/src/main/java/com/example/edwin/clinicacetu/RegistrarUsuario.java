package com.example.edwin.clinicacetu;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.regex.Pattern;

import Modelos.Usuarios;
import Negocios.UsuariosBL;


public class RegistrarUsuario extends AppCompatActivity {

    EditText edtNombre, edtDireccion, edttelefono, edtCorreo, edtClave, edtClave2, edtFechaNac;
    EditText edtUsuario;
    final Calendar myCalendar = Calendar.getInstance();
    private Usuarios objUsuario;
    private UsuariosBL objUsuarioBL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        edtNombre = (EditText) findViewById(R.id.edtNombres);
        edtDireccion = (EditText) findViewById(R.id.edtDireccion);
        edtFechaNac = (EditText) findViewById(R.id.edtFechaNacimiento);
        edttelefono = (EditText) findViewById(R.id.edtCelular);
        edtCorreo = (EditText) findViewById(R.id.edtCorreo);
        edtClave = (EditText) findViewById(R.id.edtContrasenia);
        edtClave2 = (EditText) findViewById(R.id.edtConfirmContrasenia);
        edtUsuario = (EditText) findViewById(R.id.edtUsuarioR);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        edtFechaNac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog( v.getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }


    private void updateLabel() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        edtFechaNac.setText(sdf.format(myCalendar.getTime()));
    }

    public boolean isDataValid(){


        if(TextUtils.isEmpty(edtNombre.getText())){
            edtNombre.setError("Nombres requeridos");
            edtNombre.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(edtFechaNac.getText())){
            Toast.makeText(this, "Fecha Nacimiento requerida, favor verifique" , Toast.LENGTH_SHORT).show();
            return false;
        }

        String sFechaPivote;
        long valFechaPivote,valFechaSeleccionada;


        Date date = new Date();
        DateFormat hourdateFormat;
        hourdateFormat= new SimpleDateFormat("yyyyMMdd");
        sFechaPivote =   hourdateFormat.format(date).toString();

        valFechaPivote = Integer.parseInt(sFechaPivote.substring(0,4)) ;
        valFechaPivote = valFechaPivote -18;

        sFechaPivote = String.valueOf(valFechaPivote) + sFechaPivote.substring(4,8);
        valFechaPivote = Long.parseLong(sFechaPivote);

        sFechaPivote =edtFechaNac.getText().toString().replace("-","");
        valFechaSeleccionada = Long.parseLong(sFechaPivote);

        if(valFechaPivote < valFechaSeleccionada){
            Toast.makeText(getApplicationContext(), "Para registrarte debes ser mayor de 18 años, favor verifique ", Toast.LENGTH_SHORT).show();
            return false;
        }


        if(TextUtils.isEmpty(edtCorreo.getText())){
            edtCorreo.setError("Correo Electrónico, requerido");
            edtCorreo.requestFocus();
            return false;
        }
        else
        {
            Pattern pattern =  Patterns.EMAIL_ADDRESS;

            if(!pattern.matcher(edtCorreo.getText().toString()).matches()){
                edtCorreo.setError("Correo Electrónico inválido, favor verifique");
                edtCorreo.requestFocus();
                return false;
            }
        }

        if(TextUtils.isEmpty(edtUsuario.getText())){
            edtUsuario.setError("Usuario requerido");
            edtUsuario.requestFocus();
            return false;
        }

        Usuarios objUsuValCorreo= new Usuarios();
        objUsuValCorreo.setCorreoElectronico(edtCorreo.getText().toString());

        UsuariosBL objUsuVal = new UsuariosBL(getApplicationContext(), objUsuValCorreo );

        LinkedList listDatosUsuario;

        listDatosUsuario =objUsuVal.getByID();
        if(listDatosUsuario.size() > 0){
            edtCorreo.setError("El Correo Electrónico "+ edtCorreo.getText().toString() +" ya ha sido registrado, favor verifique");
            edtCorreo.requestFocus();
            return false;
        }

        objUsuValCorreo= new Usuarios();
        objUsuValCorreo.setUsuario(edtUsuario.getText().toString());

        objUsuVal = new UsuariosBL(getApplicationContext(), objUsuValCorreo );
        listDatosUsuario =objUsuVal.getByUSUARIO();
        if(listDatosUsuario.size() > 0){
            edtUsuario.setError("El Usuario "+ edtUsuario.getText().toString() +" ya ha sido registrado, favor verifique");
            edtUsuario.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(edtDireccion.getText())){
            edtDireccion.setError("Dirección de residencia requerida");
            edtDireccion.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(edttelefono.getText())){
            edttelefono.setError("Teléfono requerido");
            edttelefono.requestFocus();
            return false;

        }



        if(TextUtils.isEmpty(edtClave.getText())){
            edtClave.setError("Contraseña requerida");
            edtClave.requestFocus();
            return false;
        }
        else{
            if(edtClave.getText().toString().length() < 5){
                edtClave.setError("Contraseña debe poseer al menos 5 caracteres");
                edtClave.requestFocus();
                return false;
            }
        }

        if(TextUtils.isEmpty(edtClave2.getText())){
            edtClave2.setError("Confirmación de Contraseña requerida");
            edtClave2.requestFocus();
            return false;
        }
        if(!edtClave.getText().toString().equals(edtClave2.getText().toString())){
            edtClave2.setError("Las contraseñas no coinciden, favor verique");
            edtClave2.requestFocus();
            return false;
        }



        return true;

    }

    public void onclickRegistrar(View v) throws Exception {
        if(isDataValid()){

            AlertDialog.Builder dialogoRegistro = new AlertDialog.Builder(this);
            dialogoRegistro.setTitle("Notificación");
            dialogoRegistro.setMessage("¿Esta Seguro de Registrarse a la Aplicación?");
            dialogoRegistro.setCancelable(false);

            dialogoRegistro.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    if(ConfirmarInsertRegistro()>0){
                        Toast.makeText(getApplicationContext(), "Se ha Registrado Exitosamente.", Toast.LENGTH_SHORT).show();

                        Intent iLogin = new Intent(getApplicationContext(),MainActivity.class);
                        iLogin.putExtra("usuarioRegistro",objUsuario.getUsuario());
                        setResult(RESULT_OK, iLogin);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Ocurrió un error al intentar Registrarse, favor intente nuevamente.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            dialogoRegistro.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    Toast.makeText(getApplicationContext(), "Operación cancelada por el usuario.", Toast.LENGTH_SHORT).show();
                }
            });

            dialogoRegistro.show();
        }
    }

    public int ConfirmarInsertRegistro() {
        objUsuario = new Usuarios();
        objUsuario.setNombres(edtNombre.getText().toString());
        objUsuario.setCorreoElectronico(edtCorreo.getText().toString());
        objUsuario.setDireccion(edtDireccion.getText().toString());
        objUsuario.setTelefono(edttelefono.getText().toString());
        objUsuario.setActivo(1);
        objUsuario.setFechaNacimiento(edtFechaNac.getText().toString());
        objUsuario.setPassword(edtClave.getText().toString());
        objUsuario.setUsuario(edtUsuario.getText().toString());

        objUsuarioBL = new UsuariosBL(getApplicationContext(),objUsuario);

        return objUsuarioBL.insertUsuario();
    }
}
