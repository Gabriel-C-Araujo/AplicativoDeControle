package com.example.trabalhofaculdade.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.trabalhofaculdade.Adapter.TarefaAdapter;
import com.example.trabalhofaculdade.Helper.RecyclerItemClickListener;
import com.example.trabalhofaculdade.Helper.TarefaDAO;
import com.example.trabalhofaculdade.Model.Tarefa;
import com.example.trabalhofaculdade.R;

import java.util.ArrayList;
import java.util.List;

public class ToDoList extends AppCompatActivity {

    private Button adicionaTarefa;
    private RecyclerView recyclerBox;
    private TarefaAdapter tarefaAdapter;
    private List<Tarefa> listaTarefas = new ArrayList<>();
    private Tarefa tarefaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        adicionaTarefa = findViewById(R.id.buttonNovaTarefa);
        recyclerBox = findViewById(R.id.recyclerBox);
        recyclerBox.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerBox,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Tarefa tarefaSelecionada = listaTarefas.get( position );

                                //Envia tarefa para tela adicionar tarefa
                                Intent intent = new Intent(ToDoList.this, AdicionarTarefaActivity.class);
                                intent.putExtra("tarefaSelecionada", tarefaSelecionada );

                                startActivity( intent );
                                Log.i("click", "onItemClick");
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                                tarefaSelecionada = listaTarefas.get( position );

                                AlertDialog.Builder dialog = new AlertDialog.Builder(ToDoList.this);

                                //Configura título e mensagem
                                dialog.setTitle("Confirmar exclusão");
                                dialog.setMessage("Deseja excluir a tarefa: " + tarefaSelecionada.getNomeTarefa() + " ?" );

                                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
                                        if ( tarefaDAO.deletar(tarefaSelecionada) ){

                                            carregarBoxs();
                                            Toast.makeText(getApplicationContext(),
                                                    "Sucesso ao excluir tarefa!",
                                                    Toast.LENGTH_SHORT).show();

                                        }else {
                                            Toast.makeText(getApplicationContext(),
                                                    "Erro ao excluir tarefa!",
                                                    Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });

                                dialog.setNegativeButton("Não", null );

                                //Exibir dialog
                                dialog.create();
                                dialog.show();
                                Log.i("click", "onLongItemClick");
                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )
        );

    }

    public void carregarBoxs(){
        TarefaDAO tarefaDAO = new TarefaDAO( getApplicationContext() );
        listaTarefas = tarefaDAO.listar();
        tarefaAdapter = new TarefaAdapter( listaTarefas );

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerBox.setLayoutManager(layoutManager);
        recyclerBox.setHasFixedSize(true);
        recyclerBox.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerBox.setAdapter(tarefaAdapter);
    }
    @Override
    protected void onStart() {
        carregarBoxs();
        super.onStart();
        //checkProgressBar();
    }


    public void adicionarTarefa(View view){
        Intent intent = new Intent(getApplicationContext(), AdicionarTarefaActivity.class);
        startActivity(intent);
    }

}