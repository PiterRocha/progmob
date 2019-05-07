package com.example.controledecursosonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cadastrarCurso(View view){
        Intent it = new Intent(this, CadastroCurso.class);
        startActivity(it);
    }

    public void cadastrarAluno(View view){
        Intent it = new Intent(this, CadastroAluno.class);
        startActivity(it);
    }

    public void listarCursos(View view){
        Intent it = new Intent(this, MostraCurso.class);
        startActivity(it);
    }

    public void listarAlunos(View view){
        Intent it = new Intent(this, MostraAluno.class);
        startActivity(it);
    }


    public void sobre(View view){
        Intent it = new Intent(this, Sobre.class);
        startActivity(it);
    }

    public void sair(View view){
        finishAndRemoveTask();
    }
}
