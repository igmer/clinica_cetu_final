package com.example.edwin.clinicacetu;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Modelos.Citas_Medicas;
import Modelos.Doctores;
import Modelos.Usuarios;
import Negocios.Citas_Medicas_BL;
import Negocios.UsuariosBL;

public class NuevaCita extends AppCompatActivity {

    final Calendar myCalendar = Calendar.getInstance();
    EditText edtFechaCita,edtHoraCita, edtComentario;
    Spinner spDoctor;
    Button btnCita;
    private Usuarios objUsuario;

    private Citas_Medicas objCitaPaciente;
    private Citas_Medicas_BL objCitasBL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_cita);

        objUsuario =(Usuarios)getIntent().getExtras().getSerializable("DatosUsuario");

        edtComentario=(EditText) findViewById(R.id.edtComentario);
        spDoctor=(Spinner) findViewById(R.id.spMedico);
        btnCita=(Button) findViewById(R.id.btnRegistrarCita);
        edtFechaCita=(EditText) findViewById(R.id.edtFechaCita);
        edtHoraCita=(EditText) findViewById(R.id.edtHoraCita);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                actualizaFecha();
            }

        };

        edtFechaCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog( v.getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        edtHoraCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int horas = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minutos = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        if(selectedHour < 10) {
                            if (selectedMinute < 10)
                                edtHoraCita.setText("0" + selectedHour + ":0" + selectedMinute + ":00");
                            else
                                edtHoraCita.setText("0" + selectedHour + ":" + selectedMinute + ":00");
                        }
                        else{
                            if (selectedMinute < 10)
                                edtHoraCita.setText(selectedHour + ":0" + selectedMinute + ":00" );
                            else
                                edtHoraCita.setText(selectedHour + ":" + selectedMinute + ":00" );
                        }

                    }
                }, horas, minutos, true);
                mTimePicker.setTitle("Seleccione la Hora a Programar la Cita");
                mTimePicker.show();

            }
        });

        //LLENO EL SPINER DE LA LISTA DE USUARIOS CATALOGADOS COMO DOCTORES
        UsuariosBL objDoctores = new UsuariosBL(getApplicationContext());
        spDoctor.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, objDoctores.getDoctoresSpinner()));
    }

    private void actualizaFecha() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        edtFechaCita.setText(sdf.format(myCalendar.getTime()));
    }

    public boolean DataValida() {
      //  tvDatosRegistroCita.setText("");
        if (TextUtils.isEmpty(edtFechaCita.getText())) {
            Toast.makeText(getApplicationContext(),"Fecha de Cita Requerida.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(edtComentario.getText().toString())){
            edtComentario.setError("Debe especificar un comentario");
            edtComentario.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(edtHoraCita.getText())) {
            Toast.makeText(getApplicationContext(), "Hora de Cita Requerida.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            String sFechaPivote;
            Date date = new Date();
            long valFechaActual,valFechaSeleccionada;

            DateFormat hourdateFormat;
            hourdateFormat= new SimpleDateFormat("yyyyMMdd");

            sFechaPivote =   hourdateFormat.format(date).toString();
            valFechaActual=Long.parseLong(sFechaPivote);
            valFechaActual = valFechaActual + 1;

            sFechaPivote =edtFechaCita.getText().toString().replace("-","");
            valFechaSeleccionada = Long.parseLong(sFechaPivote);

            objCitaPaciente = new Citas_Medicas();
            objCitaPaciente.setid_estado_cita(0);
            objCitaPaciente.setid_empleado_medico(((Doctores) spDoctor.getItemAtPosition(spDoctor.getSelectedItemPosition())).getId());
            sFechaPivote =edtFechaCita.getText().toString() + " " + edtHoraCita.getText().toString();
            objCitaPaciente.setFecha(sFechaPivote);
            objCitaPaciente.setid_usuario(objUsuario.getID());
            objCitaPaciente.setComentario(edtComentario.getText().toString().trim());

            if(valFechaActual > valFechaSeleccionada){
                Toast.makeText(getApplicationContext(), "Debe Programar la Cita para un día despues de la fecha actual", Toast.LENGTH_SHORT).show();
                return false;
            }
        }


        return true;
    }

    public void onclickRegistrarCita(View v) throws Exception {
        if (DataValida()) {
            AlertDialog.Builder dialogoRegistro = new AlertDialog.Builder(this);
            dialogoRegistro.setTitle("Notificación");
            dialogoRegistro.setMessage("¿Esta Seguro de realizar la cita para la fecha " + edtFechaCita.getText().toString() + " a las " + edtHoraCita.getText().toString() + " Hrs ?");
            dialogoRegistro.setCancelable(false);
            dialogoRegistro.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    if(ConfirmarInsertRegistroCita()>0){
                        Toast.makeText(getApplicationContext(), "Cita realizada exitosamente.", Toast.LENGTH_SHORT).show();
                        Intent iPrincipal = new Intent(getApplicationContext(),MenuPrincipal.class);
                        iPrincipal.putExtra("DatosUsuario",objUsuario);
                        startActivity(iPrincipal);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Ocurrió un error al intentar realizar la reserva, favor intente nuevamente.", Toast.LENGTH_SHORT).show();
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
    public int ConfirmarInsertRegistroCita() {
        objCitasBL = new Citas_Medicas_BL(getApplicationContext());
        return  objCitasBL.insertCita(objCitaPaciente);
    }

}
