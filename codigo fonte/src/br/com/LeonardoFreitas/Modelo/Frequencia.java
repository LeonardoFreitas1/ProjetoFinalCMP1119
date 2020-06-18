package br.com.LeonardoFreitas.Modelo;

/*
 * Classe Frequencia
 * 
 * Esta classe será responsável por armazenar
 * de forma estruturada a Frequencia do banco
 * 
 * */

public class Frequencia {
	
	private int id_frequencia;
	private int usuario;
	private int turma;
	private String data;
	private boolean frequente;
	private int usuario_turma;
	
	public Frequencia(int id_frequencia, int usuario, int turma, String data, boolean frequente, int usuario_turma) {
		
		this.id_frequencia = id_frequencia;
		this.usuario = usuario;
		this.turma = turma;
		this.data = data;
		this.frequente = frequente;
		this.usuario_turma = usuario_turma;
	 
	}

	public int getId_frequencia() {
		return id_frequencia;
	}

	public void setId_frequencia(int id_frequencia) {
		this.id_frequencia = id_frequencia;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public int getTurma() {
		return turma;
	}

	public void setTurma(int turma) {
		this.turma = turma;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public boolean isFrequente() {
		return frequente;
	}

	public void setFrequente(boolean frequente) {
		this.frequente = frequente;
	}

	public int getUsuario_turma() {
		return usuario_turma;
	}

	public void setUsuario_turma(int usuario_turma) {
		this.usuario_turma = usuario_turma;
	}
	
	
	
}
