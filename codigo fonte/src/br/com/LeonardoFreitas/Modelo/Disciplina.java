package br.com.LeonardoFreitas.Modelo;

/*
 * Classe Disciplina
 * 
 * Esta classe será responsável por armazenar
 * de forma estruturada a tabela disciplinas do banco
 * 
 * */

public class Disciplina {

	private int id_disciplina;
	private String nome_disciplina;
	private String codigo_disciplina;
	
	public Disciplina(int id_disciplina, String nome_disciplina, String codigo_disciplina) {

		this.id_disciplina = id_disciplina;
		this.nome_disciplina = nome_disciplina;
		this.codigo_disciplina = codigo_disciplina;
	}
	
	public int getId_disciplina() {
		return id_disciplina;
	}
	public void setId_disciplina(int id_disciplina) {
		this.id_disciplina = id_disciplina;
	}
	public String getNome_disciplina() {
		return nome_disciplina;
	}
	public void setNome_disciplina(String nome_disciplina) {
		this.nome_disciplina = nome_disciplina;
	}
	public String getCodigo_disciplina() {
		return codigo_disciplina;
	}
	public void setCodigo_disciplina(String codigo_disciplina) {
		this.codigo_disciplina = codigo_disciplina;
	}
	
	
	
}
