package br.com.LeonardoFreitas.Modelo;

import java.util.List;

/*
 * Classe Turma
 * 
 * Esta classe será responsável por armazenar
 * de forma estruturada a tabela turmas do banco
 * 
 * */

public class Turma {
	
	private int id_turma;
	private String codigo_turma;
	private int disciplina;
	private List<Usuario> usuarios;
	private int id_usuarioTurma;
	
	public Turma(int id_turma, String codigo_turma, int disciplina, List<Usuario> usuarios, int id_usuarioTurma) {
		
		this.id_turma = id_turma;
		this.codigo_turma = codigo_turma;
		this.disciplina = disciplina;
		this.usuarios = usuarios;
		this.id_usuarioTurma= id_usuarioTurma;
	}
	
	public Turma(int id_turma, String codigo_turma, int disciplina, List<Usuario> usuarios) {
		
		this.id_turma = id_turma;
		this.codigo_turma = codigo_turma;
		this.disciplina = disciplina;
		this.usuarios = usuarios;
		this.id_usuarioTurma = 0;
	}

	
	
	public int getId_usuarioTurma() {
		return id_usuarioTurma;
	}
	public void setId_usuarioTurma(int id_usuarioTurma) {
		this.id_usuarioTurma = id_usuarioTurma;
	}
	public int getId_turma() {
		return id_turma;
	}
	public void setId_turma(int id_turma) {
		this.id_turma = id_turma;
	}
	public String getNome_turma() {
		return codigo_turma;
	}
	public void setNome_turma(String nome_turma) {
		this.codigo_turma = nome_turma;
	}
	public int getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(int disciplina) {
		this.disciplina = disciplina;
	}

	public String getCodigo_turma() {
		return codigo_turma;
	}

	public void setCodigo_turma(String codigo_turma) {
		this.codigo_turma = codigo_turma;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
}
