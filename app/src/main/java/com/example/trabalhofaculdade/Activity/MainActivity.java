package com.example.trabalhofaculdade.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabalhofaculdade.Helper.DbHelper;
import com.example.trabalhofaculdade.Login.Administrador;
import com.example.trabalhofaculdade.Login.Login;
import com.example.trabalhofaculdade.Login.LoginGeneral;
import com.example.trabalhofaculdade.Login.Usuario;
import com.example.trabalhofaculdade.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase bancoDados;
    private TextInputEditText textInputUsuario;
    private EditText editTextPassword;
    private Login realizaLogin;
    private Usuario loginUsuario;
    private Administrador loginAdmin;
    private Button Login;


    ;@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Login = findViewById(R.id.buttonLogin);
        textInputUsuario = findViewById(R.id.textInputUsuario);
        editTextPassword = findViewById(R.id.editTextPassword);

        inserirDados(getApplicationContext());

        //inserirDadosTemp();

    }

    public void criarBanco(){

        try{
            bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS login ( usuarios VARCHAR, senhas VARCHAR, admin VARCHAR)");
            bancoDados.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e + "ERRO!! Criar");
        }

    }

    public void inserirDados(Context context){
        DbHelper db = new DbHelper(context);
        SQLiteDatabase escreve;
        SQLiteDatabase le;
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", "Gabriel");
        cv.put("senha", "123");

        try{
            escreve.insert(DbHelper.TABELA_LOGIN, null, cv);
        }catch (Exception e){
            Log.e("INFO","ERRO" + e.getMessage());
        }

    }


    public void Login(View view){
        DbHelper db = new DbHelper();
        SQLiteDatabase escreve;
        SQLiteDatabase le;
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();

        String sql = "SELECT * FROM " + DbHelper.TABELA_LOGIN + " ;";
        Cursor c = le.rawQuery(sql, null);

        while(c.moveToNext()){
            realizaLogin = new Login();

            String nomeUsuario = c.getString(c.getColumnIndexOrThrow("nome"));
            String senhaUsuario = c.getString(c.getColumnIndexOrThrow("senha"));

            if(nomeUsuario == textInputUsuario.getText().toString()){
                if(senhaUsuario == editTextPassword.getText().toString()){
                    Toast.makeText(getApplicationContext(), "LOGIN EFETUADO COM SUCESSO", Toast.LENGTH_SHORT);
                    Intent intent = new Intent(getApplicationContext(), ToDoList.class);
                    startActivity(intent);
                }
            }
        }
        //Toast.makeText(getApplicationContext(), "LOGIN NÃO REALIZADO, TROCANDO DE TELA PARA TESTES", Toast.LENGTH_LONG);
        Intent intent = new Intent(getApplicationContext(), ToDoList.class);
        startActivity(intent);


    }
}