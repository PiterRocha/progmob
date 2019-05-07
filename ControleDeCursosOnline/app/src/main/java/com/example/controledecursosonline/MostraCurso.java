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

public class MostraCurso extends AppCompatActivity {

    private ListView listaCursos;
    private ArrayList<Curso> arrayListCurso;
    private ArrayAdapter<Curso> arrayAdapterCurso;
    private Curso curso = new Curso();
    private int idEditar, idExcluir, idCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_curso);
        listaCursos = findViewById(R.id.listaCursos);
        registerForContextMenu(listaCursos);
        preencheLista();

        listaCursos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                curso = arrayAdapterCurso.getItem(position);
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
                param.putString("ch_nome", curso.getNomeCurso());
                param.putString("ch_horas", curso.getQtdeHoras());
                Intent it = new Intent(MostraCurso.this, CadastroCurso.class);
                it.putExtras(param);
                startActivity(it);
                return false;
            }
        });

        mExcluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                long retornoDB;
                BDHelper helper = new BDHelper(MostraCurso.this);
                retornoDB = helper.excluirCurso(curso);
                if(retornoDB == -1)
                    Toast.makeText(MostraCurso.this, "Erro ao excluir curso", Toast.LENGTH_SHORT).show();
                else{
                    Toast.makeText(MostraCurso.this, "Excluído com sucesso!", Toast.LENGTH_SHORT).show();
                    preencheLista();
                }
                return false;
            }
        });
    }

    public void preencheLista(){
        BDHelper cursoBD = new BDHelper(MostraCurso.this);
        arrayListCurso = cursoBD.selectAllCursos();
        cursoBD.close();
        if(listaCursos != null){
            arrayAdapterCurso = new ArrayAdapter<Curso>(MostraCurso.this, android.R.layout.simple_list_item_1, arrayListCurso);
            listaCursos.setAdapter(arrayAdapterCurso);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        preencheLista();
    }
}
