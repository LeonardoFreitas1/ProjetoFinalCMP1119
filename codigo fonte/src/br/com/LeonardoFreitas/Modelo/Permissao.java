package br.com.LeonardoFreitas.Modelo;

public class Permissao {
	
	private int id_permissao;
	private String nome;
	
	public Permissao(int id_permissao, String nome) {
		
		this.id_permissao = id_permissao;
		this.nome = nome;
	
	}
	
	public int getId_permissao() {
		return id_permissao;
	}
	public void setId_permissao(int id_permissao) {
		this.id_permissao = id_permissao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
