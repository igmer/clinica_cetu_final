package com.example.edwin.clinicacetu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedList;

import Modelos.Usuarios;
import Negocios.UsuariosBL;

public class MainActivity extends AppCompatActivity {

    EditText edtusuario, edtPassword;
Button btnIngresar, btnSalir;
ClinicaDB data;
    static final int ID_REGISTRAR =1;
    private UsuariosBL objUsuarioBL;
    private Usuarios objUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtusuario=(EditText) findViewById(R.id.edtUsuario);
        edtPassword=(EditText) findViewById(R.id.edtClave);
        btnIngresar=(Button) findViewById(R.id.btnIngresar);
        btnSalir=(Button) findViewById(R.id.btnSalir);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String elUsuario = edtusuario.getText().toString().trim();
                String laClave = edtPassword.getText().toString().trim();
                Integer existe=0;

                if(TextUtils.isEmpty(elUsuario)){
                    edtusuario.setError("DATO REQUERIDO");
                    edtusuario.requestFocus();
                }else if(TextUtils.isEmpty(laClave)){
                    edtPassword.setError("DATO REQUERIDO");
                    edtPassword.requestFocus();
                }else{
                    data = new ClinicaDB(getApplicationContext());
                    LinkedList listDatosUsuario;
                    objUsuarioBL = new UsuariosBL( getApplicationContext(),new Usuarios(elUsuario,laClave));
                    listDatosUsuario =objUsuarioBL.getByUSUARIO();



                    if(data.validaIngresousuario(elUsuario,laClave)==0 ){
                        Toast.makeText(getApplicationContext(),"Usuario no Registrado",Toast.LENGTH_LONG).show();
                    }else{
                        objUsuario = (Usuarios) listDatosUsuario.get(0);
                        Toast.makeText(getApplicationContext(),"Bienvenido",Toast.LENGTH_LONG).show();
                        Intent principal= new Intent(getApplicationContext(),MenuPrincipal.class);
                        principal.putExtra("DatosUsuario",objUsuario);
                        startActivity(principal);
                    }
                }
            }
        });
    }

    public void onclickRegistrar(View v){
        Intent iRegistrar = new Intent(getApplicationContext(),RegistrarUsuario.class);
        startActivityForResult(iRegistrar,ID_REGISTRAR);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == ID_REGISTRAR && resultCode == RESULT_OK){
            edtusuario.setText(data.getStringExtra("usuarioRegistro"));
            edtusuario.requestFocus();
        }

    }
}
