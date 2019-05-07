package com.example.controledecursosonline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroAluno extends AppCompatActivity {

    BDHelper helper = new BDHelper(this);
    private EditText edtNome, edtEmail, edtTelefone, edtCurso;
    private Button btnCadastra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        edtNome = findViewById(R.id.edtNome);
        edtEmail= findViewById(R.id.edtEmail);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtCurso = findViewById(R.id.edtCurso);
        btnCadastra = findViewById(R.id.btnCadastra);

        Bundle args = getIntent().getExtras();
        if(args != null){
            btnCadastra.setText("EDITAR");
            String nome = args.getString("ch_nome");
            String email = args.getString("ch_email");
            String telefone = args.getString("ch_telefone");

            edtNome.setText(nome);
            edtEmail.setText(email);
            edtTelefone.setText(telefone);

        }
    }

    public void cadastrarAluno(View view){
        String nomeAluno = edtNome.getText().toString();
        String emailAluno = edtEmail.getText().toString();
        String telefoneAluno = edtTelefone.getText().toString();
        String nomeCurso = edtCurso.getText().toString();

        String idCurso = helper.buscarCurso(nomeCurso);
        if(btnCadastra.getText().toString().equalsIgnoreCase("EDITAR")){
            Aluno a = new Aluno(nomeAluno, emailAluno, telefoneAluno, idCurso);
            helper.alterarAluno(a);
            helper.close();
            Toast toast = Toast.makeText(CadastroAluno.this, "Aluno editado com sucesso", Toast.LENGTH_SHORT);
            toast.show();
            finish();
        }else{
            if(idCurso != null){
                String id = idCurso;
                Aluno a = new Aluno(nomeAluno, emailAluno, telefoneAluno, id);
                helper.insereAluno(a);
                helper.close();
                Toast toast = Toast.makeText(CadastroAluno.this, "Aluno cadastrado com sucesso", Toast.LENGTH_SHORT);
                toast.show();
                finish();
            }else{
                Toast toast = Toast.makeText(CadastroAluno.this, "Curso inexistente, cadastre-o primeiro para inserir o aluno",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}
