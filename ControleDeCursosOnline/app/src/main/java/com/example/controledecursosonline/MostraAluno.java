package com.example.controledecursosonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MostraAluno extends AppCompatActivity {

    private ListView listaAlunos;
    private ArrayList<Aluno> arrayListAluno;
    private ArrayAdapter<Aluno> arrayAdapterAluno;
    private Aluno aluno = new Aluno();
    private int idEditar, idExcluir, idCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_aluno);
        listaAlunos = findViewById(R.id.listaAlunos);
        registerForContextMenu(listaAlunos);
        preencheLista();

        listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                aluno = arrayAdapterAluno.getItem(position);
                return false;
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem mEditar = menu.add(Menu.NONE, idEditar, 1, "Editar");
        MenuItem mExcluir = menu.add(Menu.NONE, idExcluir, 2, "Excluir");
        MenuItem mCancelar = menu.add(Menu.NONE, idCancelar, 3, "Cancelar");

        mEditar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Bundle param = new Bundle();
                param.putString("ch_nome", aluno.getNomeAluno());
                param.putString("ch_email", aluno.getEmailAluno());
                param.putString("ch_telefone", aluno.getTelefoneAluno());
                Intent it = new Intent(MostraAluno.this, CadastroAluno.class);
                it.putExtras(param);
                startActivity(it);
                return false;
            }
        });

        mExcluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                long retornoDB;
                BDHelper helper = new BDHelper(MostraAluno.this);
                retornoDB = helper.excluirAluno(aluno);
                if(retornoDB == -1)
                    Toast.makeText(MostraAluno.this, "Erro ao excluir aluno", Toast.LENGTH_SHORT).show();
                else{
                    Toast.makeText(MostraAluno.this, "Excluído com sucesso!", Toast.LENGTH_SHORT).show();
                    preencheLista();
                }
                return false;
            }
        });
    }

    public void preencheLista(){
        BDHelper alunoBD = new BDHelper(MostraAluno.this);
        arrayListAluno = alunoBD.selectAllAlunos();
        alunoBD.close();
        if(listaAlunos != null){
            arrayAdapterAluno = new ArrayAdapter<Aluno>(MostraAluno.this, android.R.layout.simple_list_item_1, arrayListAluno);
            listaAlunos.setAdapter(arrayAdapterAluno);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        preencheLista();
    }
}
