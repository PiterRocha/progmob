package com.example.controledecursosonline;

public class Aluno {

    private String alunoId;
    private String cursoId;
    private String nomeAluno;
    private String emailAluno;
    private String telefoneAluno;

    public Aluno(){

    }

    public Aluno(String nomeAluno, String emailAluno, String telefoneAluno, String cursoId) {
        this.alunoId = alunoId;
        this.nomeAluno = nomeAluno;
        this.emailAluno = emailAluno;
        this.telefoneAluno = telefoneAluno;
        this.cursoId = cursoId;
    }

    public String getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(String alunoId) {
        this.alunoId = alunoId;
    }

    public String getCursoId() {
        return cursoId;
    }

    public void setCursoId(String cursoId) {
        this.cursoId = cursoId;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getEmailAluno() {
        return emailAluno;
    }

    public void setEmailAluno(String emailAluno) {
        this.emailAluno = emailAluno;
    }

    public String getTelefoneAluno() {
        return telefoneAluno;
    }

    public void setTelefoneAluno(String telefoneAluno) {
        this.telefoneAluno = telefoneAluno;
    }

    @Override
    public String toString() {
        return nomeAluno.toString() + ", " + emailAluno.toString() + ", " + telefoneAluno.toString() + ", cursoID: " + cursoId.toString() ;
    }
}
