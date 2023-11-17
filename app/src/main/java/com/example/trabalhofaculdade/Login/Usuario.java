package com.example.trabalhofaculdade.Login;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.android.material.textfield.TextInputLayout;


public class Usuario extends Login implements LoginGeneral{
    private TextInputLayout textInputUsuario;
    private SQLiteDatabase bancoDados;

    @Override
    public void verificaLogin() {
        try{
            System.out.println("Iniciei");
            String inputUsuario = textInputUsuario.getEditText().getText().toString();
            System.out.println("Usuario" + inputUsuario);
            String consulta = "SELECT admin FROM login WHERE usuarios = " + inputUsuario;
            System.out.println("Consulta" + consulta);
            Cursor cursor = bancoDados.rawQuery(consulta, null);
            System.out.println(cursor);

        }catch (Exception e){
            e.getStackTrace();
            System.out.println(e + "parou pq deus quis");
        }
    }
}
