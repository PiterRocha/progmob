package com.example.controledecursosonline;

public class Curso {

    private int cursoId;
    private String nomeCurso;
    private String qtdeHoras;

    public Curso(){

    }

    public Curso(String nomeCurso, String qtdeHoras) {
        this.cursoId = cursoId;
        this.nomeCurso = nomeCurso;
        this.qtdeHoras = qtdeHoras;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getQtdeHoras() {
        return qtdeHoras;
    }

    public void setQtdeHoras(String qtdeHoras) {
        this.qtdeHoras = qtdeHoras;
    }

    @Override
    public String toString() {
        return cursoId + ", " + nomeCurso.toString() + ", " + qtdeHoras.toString()+ " horas";
    }
}
