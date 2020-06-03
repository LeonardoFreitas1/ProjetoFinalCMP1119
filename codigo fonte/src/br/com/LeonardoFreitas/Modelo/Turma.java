package br.com.LeonardoFreitas.Modelo;

public class Turma {
	
	private int id_turma;
	private String nome_turma;
	private int disciplina;
	
	public Turma(int id_turma, String nome_turma, int disciplina) {
		
		this.id_turma = id_turma;
		this.nome_turma = nome_turma;
		this.disciplina = disciplina;
	
	}
	
	public int getId_turma() {
		return id_turma;
	}
	public void setId_turma(int id_turma) {
		this.id_turma = id_turma;
	}
	public String getNome_turma() {
		return nome_turma;
	}
	public void setNome_turma(String nome_turma) {
		this.nome_turma = nome_turma;
	}
	public int getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(int disciplina) {
		this.disciplina = disciplina;
	}
	
	
	
	
}
