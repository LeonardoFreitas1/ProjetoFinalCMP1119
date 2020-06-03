package br.com.LeonardoFreitas.Modelo;

public class TipoPessoa {
	
	private int id_tipo;
	private String nome;
	
	public TipoPessoa(int id_tipo, String nome) {
		
		this.id_tipo = id_tipo;
		this.nome = nome;

	}
	
	public int getId_tipo() {
		return id_tipo;
	}
	public void setId_tipo(int id_tipo) {
		this.id_tipo = id_tipo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
