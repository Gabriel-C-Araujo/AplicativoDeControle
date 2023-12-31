package com.example.trabalhofaculdade.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.trabalhofaculdade.Model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class LoginDAO implements ITarefaDAO{
    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public LoginDAO(Context context){
        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Tarefa tarefa) {

        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa() );

        try {
            escreve.insert(DbHelper.TABELA_TAREFAS, null, cv );
            Log.i("INFO", "Tarefa salva com sucesso!");
        }catch (Exception e){
            Log.e("INFO", "Erro ao salvar tarefa " + e.getMessage() );
            return false;
        }

        return true;
    }


    public boolean teste(){
        return true;
    }

    public boolean teste(boolean oi){
        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {

        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa() );

        try {
            String[] args = {tarefa.getId().toString()};
            escreve.update(DbHelper.TABELA_TAREFAS, cv, "id=?", args );
            Log.i("INFO", "Tarefa atualizada com sucesso!");
        }catch (Exception e){
            Log.e("INFO", "Erro ao atualizada tarefa " + e.getMessage() );
            return false;
        }

        return true;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {

        try {
            String[] args = { tarefa.getId().toString() };
            escreve.delete(DbHelper.TABELA_TAREFAS, "id=?", args );
            Log.i("INFO", "Tarefa removida com sucesso!");
        }catch (Exception e){
            Log.e("INFO", "Erro ao remover tarefa " + e.getMessage() );
            return false;
        }

        return true;
    }

    @Override
    public List<Tarefa> listar() {

        List<Tarefa> tarefas = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABELA_TAREFAS + " ;";
        Cursor c = le.rawQuery(sql, null);

        while ( c.moveToNext() ){

            Tarefa tarefa = new Tarefa();

            Long id = c.getLong(c.getColumnIndexOrThrow("id") );
            String nomeTarefa = c.getString(c.getColumnIndexOrThrow("nome"));

            tarefa.setId( id );
            tarefa.setNomeTarefa( nomeTarefa );

            tarefas.add( tarefa );
            Log.i("tarefaDao", tarefa.getNomeTarefa() );
        }

        return tarefas;

    }
}
