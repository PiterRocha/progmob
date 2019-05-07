package com.example.controledecursosonline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class BDHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "controledecursoonline.db";

    private static final String TABLE_ALUNO = "aluno";
    private static final String COLUMN_ALUNO_ID = "alunoId";
    private static final String COLUMN_ALUNO_NOME = "nomeAluno";
    private static final String COLUMN_ALUNO_EMAIL = "emailAluno";
    private static final String COLUMN_ALUNO_TELEFONE = "telefoneAluno";

    private static final String TABLE_CURSO = "curso";
    private static final String COLUMN_CURSO_ID = "cursoId";
    private static  final String COLUMN_CURSO_NOME = "nomeCurso";
    private static final String COLUMN_CURSO_HORAS = "qtdeHoras";

    private static final String TABLE_ALUNO_CREATE =
            "create table aluno (" +
                    "alunoId integer primary key autoincrement," +
                    "cursoId integer not null," +
                    "nomeAluno text not null," +
                    "emailAluno text not null," +
                    "telefoneAluno text not null," +
                    "foreign key(cursoId) references curso(cursoId));";

    private static final String TABLE_CURSO_CREATE =
            "create table curso (" +
                    "cursoId integer primary key autoincrement," +
                    "nomeCurso text not null," +
                    "qtdeHoras integer not null);";

    public BDHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CURSO_CREATE);
        db.execSQL(TABLE_ALUNO_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        bd.execSQL("DROP TABLE IF EXISTS " + TABLE_CURSO);
        bd.execSQL("DROP TABLE IF EXISTS " + TABLE_ALUNO);
        this.onCreate(bd);
    }

    public void insereCurso(Curso c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CURSO_NOME, c.getNomeCurso());
        values.put(COLUMN_CURSO_HORAS, c.getQtdeHoras());
        db.insert(TABLE_CURSO, null, values);
        db.close();
    }

    public void insereAluno(Aluno a){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CURSO_ID, a.getCursoId());
        values.put(COLUMN_ALUNO_NOME, a.getNomeAluno());
        values.put(COLUMN_ALUNO_EMAIL, a.getEmailAluno());
        values.put(COLUMN_ALUNO_TELEFONE, a.getTelefoneAluno());
        db.insert(TABLE_ALUNO, null, values);
        db.close();
    }

    public long alterarCurso(Curso c){
        long retornoBD;
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CURSO_NOME, c.getNomeCurso());
        values.put(COLUMN_CURSO_HORAS, c.getQtdeHoras());
        String[] args = {String.valueOf(c.getNomeCurso())};
        retornoBD = db.update(TABLE_CURSO, values, COLUMN_CURSO_NOME + "=?", args);
        db.close();
        return retornoBD;
    }

    public String buscarCurso(String curso){
        db = this.getReadableDatabase();
        String query = "select cursoId, nomeCurso from " + TABLE_CURSO;
        Cursor cursor = db.rawQuery(query, null);
        String nomeCurso;
        String cursoId = null;
        if(cursor.moveToFirst()){
            do{
                nomeCurso = cursor.getString(1);
                if(nomeCurso.equalsIgnoreCase(curso)) {
                    cursoId = cursor.getString(0);
                    break;
                }
            }while (cursor.moveToNext());
        }

        return cursoId;
    }

    public long alterarAluno(Aluno a){
        long retornoBD;
        db =  this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ALUNO_NOME, a.getNomeAluno());
        values.put(COLUMN_ALUNO_EMAIL, a.getEmailAluno());
        values.put(COLUMN_ALUNO_TELEFONE, a.getTelefoneAluno());
        values.put(COLUMN_CURSO_ID, a.getCursoId());
        String[] args = {String.valueOf(a.getNomeAluno())};
        retornoBD = db.update(TABLE_ALUNO, values, COLUMN_ALUNO_NOME + "=?", args);
        db.close();
        return retornoBD;
    }

    public long excluirCurso(Curso c){
        long retornoBD;
        db = this.getWritableDatabase();
        String[] args = {String.valueOf(c.getNomeCurso())};
        retornoBD = db.delete(TABLE_CURSO, COLUMN_CURSO_NOME + "=?", args);
        db.close();
        return retornoBD;
    }

    public long excluirAluno(Aluno a){
        long retornoBD;
        db = this.getWritableDatabase();
        String[] args = {String.valueOf(a.getNomeAluno())};
        retornoBD = db.delete(TABLE_ALUNO, COLUMN_ALUNO_NOME + "=?", args);
        db.close();
        return retornoBD;
    }

    public ArrayList<Curso> selectAllCursos(){
        String[] columns = {COLUMN_CURSO_NOME, COLUMN_CURSO_HORAS};
        Cursor cursor = getWritableDatabase().query(TABLE_CURSO, columns, null, null,
                null, null, "upper(nomeCurso)", null);
        ArrayList<Curso> listaCurso = new ArrayList<>();
        while(cursor.moveToNext()){
            Curso c = new Curso();
            c.setNomeCurso(cursor.getString(0));
            c.setQtdeHoras(cursor.getString(1));
            listaCurso.add(c);
        }

        return listaCurso;
    }

    public ArrayList<Aluno> selectAllAlunos(){
        String[] columns = {COLUMN_ALUNO_NOME, COLUMN_ALUNO_EMAIL, COLUMN_ALUNO_TELEFONE, COLUMN_CURSO_ID};
        Cursor cursor = getWritableDatabase().query(TABLE_ALUNO, columns, null, null,
                null, null, "upper(nomeAluno)", null);
        ArrayList<Aluno> listaAluno = new ArrayList<>();
        while (cursor.moveToNext()){
            Aluno a = new Aluno();
            a.setNomeAluno(cursor.getString(0));
            a.setEmailAluno(cursor.getString(1));
            a.setTelefoneAluno(cursor.getString(2));
            a.setCursoId(cursor.getString(3));
            listaAluno.add(a);
        }

        return listaAluno;
    }




}
