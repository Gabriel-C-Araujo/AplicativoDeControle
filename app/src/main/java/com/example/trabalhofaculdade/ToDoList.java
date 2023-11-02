package com.example.trabalhofaculdade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

public class ToDoList extends AppCompatActivity {

    private Button adicionaTarefa;
    private LinearLayout linearCheckBox;
    private int porcentagemProgresso;
    private ProgressBar progressBar;
    private int progresso = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        adicionaTarefa = findViewById(R.id.buttonNovaTarefa);
        linearCheckBox = findViewById(R.id.linearCheckBox);
        porcentagemProgresso = linearCheckBox.getChildCount();
        if(linearCheckBox.getChildCount() > 0)
            progressBar.setMax(porcentagemProgresso);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //checkProgressBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //checkProgressBar();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //checkProgressBar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void adicionarTarefa(View view){
        CheckBox novaTarefa = new CheckBox(getBaseContext());
        EditText textTarefa = new EditText(getBaseContext());
        linearCheckBox.addView(novaTarefa);
        linearCheckBox.addView(textTarefa);
    }

    public void checkProgressBar(){
        if(linearCheckBox.getChildCount() > 0)
            progressBar.setMax(porcentagemProgresso);

        int teste = linearCheckBox.getChildCount();
        if(teste > 0){
            for(int i = 0; i < teste; i++){
                View elemento = linearCheckBox.getChildAt(i);
                if(elemento instanceof CheckBox){
                    CheckBox checkBox = (CheckBox)elemento;
                    if(checkBox.isChecked()){
                        progresso += 1;
                        progressBar.setProgress(progresso);
                    }
                }
            }
        }
    }
}