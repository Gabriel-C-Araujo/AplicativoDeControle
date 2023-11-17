package com.example.trabalhofaculdade.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabalhofaculdade.Helper.TarefaDAO;
import com.example.trabalhofaculdade.Model.Tarefa;
import com.example.trabalhofaculdade.R;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private EditText editTarefa;
    private Button buttonSalvar;
    private Tarefa tarefaAtual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        editTarefa = findViewById(R.id.editTextTarefa);

        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");

        if(tarefaAtual != null){
            editTarefa.setText(tarefaAtual.getNomeTarefa());
        }

    }

    public void SalvarTarefa(View view){
        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

        if(tarefaAtual != null){
            String nomeTarefa = editTarefa.getText().toString();
            if(!nomeTarefa.isEmpty()){
                Tarefa tarefa = new Tarefa();
                tarefa.setNomeTarefa(nomeTarefa);
                tarefa.setId(tarefaAtual.getId());

                if(tarefaDAO.atualizar(tarefa)){
                    finish();
                    Toast.makeText(getApplicationContext(),"Sucesso!",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Erro!", Toast.LENGTH_LONG).show();
                }
            }
        }
        else{
            String nomeTarefa = editTarefa.getText().toString();
            if ( !nomeTarefa.isEmpty() ){
                Tarefa tarefa = new Tarefa();
                tarefa.setNomeTarefa( nomeTarefa );

                if ( tarefaDAO.salvar( tarefa ) ){
                    finish();
                    Toast.makeText(getApplicationContext(), "Sucesso!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Erro!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}