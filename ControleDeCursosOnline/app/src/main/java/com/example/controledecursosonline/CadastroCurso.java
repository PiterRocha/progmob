package com.example.controledecursosonline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroCurso extends AppCompatActivity {

    BDHelper helper = new BDHelper(this);
    private EditText edtNome, edtQtdeHoras;
    private Button btnCadastra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_curso);

        edtNome = findViewById(R.id.edtNome);
        edtQtdeHoras = findViewById(R.id.edtQtdeHoras);
        btnCadastra = findViewById(R.id.btnCadastra);

        Bundle args = getIntent().getExtras();
        if(args != null){
            btnCadastra.setText("EDITAR");
            String nome = args.getString("ch_nome");
            String horas = args.getString("ch_horas");

            edtNome.setText(nome);
            edtQtdeHoras.setText(horas);
        }
    }

    public void cadastrarCurso(View view){
        String nomeCurso = edtNome.getText().toString();
        String qtdeHoras = edtQtdeHoras.getText().toString();

        String buscaCurso = helper.buscarCurso(nomeCurso);

        if(btnCadastra.getText().toString().equalsIgnoreCase("EDITAR")){
            Curso c = new Curso(nomeCurso, qtdeHoras);
            helper.alterarCurso(c);
            Toast toast = Toast.makeText(CadastroCurso.this, "Curso editado com sucesso", Toast.LENGTH_SHORT);
            toast.show();
            finish();
        }else{
            if (buscaCurso != null){
                Toast toast = Toast.makeText(CadastroCurso.this, "Um curso com este nome já foi cadastrado", Toast.LENGTH_SHORT);
                toast.show();
            }else {
                Curso c = new Curso(nomeCurso, qtdeHoras);
                helper.insereCurso(c);
                helper.close();
                Toast toast = Toast.makeText(CadastroCurso.this, "Curso cadastrado com sucesso", Toast.LENGTH_SHORT);
                toast.show();
                finish();
            }
        }
    }


}
