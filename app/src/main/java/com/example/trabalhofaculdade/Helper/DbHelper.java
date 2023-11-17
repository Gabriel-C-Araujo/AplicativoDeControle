package com.example.trabalhofaculdade.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {
    public static int VERSION = 1;
    public static String NOME_DB = "DB_TAREFAS";
    public static String TABELA_TAREFAS = "tarefas";
    public static String TABELA_LOGIN = "login";
    public DbHelper(Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql_tarefa = "CREATE TABLE IF NOT EXISTS " + TABELA_TAREFAS
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " nome TEXT NOT NULL ); ";

        String sql_login = "CREATE TABLE IF NOT EXISTS " + TABELA_LOGIN
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " nome TEXT NOT NULL, " +
                " senha TEXT NOT NULL)";
        try {
            db.execSQL( sql_tarefa );
            db.execSQL( sql_login);
            Log.i("INFO DB", "Sucesso!" );
        }catch (Exception e){
            Log.i("INFO DB", "Erro!" + e.getMessage() );
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql_tarefa = "DROP TABLE IF EXISTS " + TABELA_TAREFAS + " ;" ;
        String sql_login = "DROP TABLE IF EXISTS " + TABELA_LOGIN + " ;" ;

        try {
            db.execSQL( sql_tarefa );
            db.execSQL( sql_login);
            onCreate(db);
            Log.i("INFO DB", "Sucesso!" );
        }catch (Exception e){
            Log.i("INFO DB", "Erro!" + e.getMessage() );
        }

    }

    private void inserirDadosTemp() {

        /*try{
            bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);
            String sql = "INSERT INTO login (usuarios, senhas, admin) VALUES (?, ?, ?)";
            SQLiteStatement stmt = bancoDados.compileStatement(sql);

            stmt.bindString(1, "Gabriel");
            stmt.bindString(2, "123");
            stmt.bindString(3, "0");
            stmt.executeInsert();
            stmt.bindString(1, "Marlon");
            stmt.bindString(2, "123");
            stmt.bindString(3, "0");
            stmt.executeInsert();
            stmt.bindString(1, "Admin");
            stmt.bindString(2, "123");
            stmt.bindString(3, "1");
            stmt.executeInsert();

            bancoDados.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e + "ERRO!! Inserir");
        }*/

    }
}
