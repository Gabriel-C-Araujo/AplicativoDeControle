package com.example.trabalhofaculdade.Login;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Administrador extends Login implements LoginGeneral{

    @Override
    public void verificaLogin() {
        try{
            SQLiteDatabase bancoDados = SQLiteDatabase.openOrCreateDatabase("app", null);

            String consulta = "SELECT usuario, senha, admin FROM login WHERE 1=1";
            Cursor cursor = bancoDados.rawQuery(consulta, null);
        }catch (Exception e){
            e.getStackTrace();
            System.out.println(e + "parou pq deus quis");
        }
    }
}
