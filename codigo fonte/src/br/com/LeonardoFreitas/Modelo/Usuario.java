package br.com.LeonardoFreitas.Modelo;

/*
 * Classe Usuario
 * 
 * Esta classe será responsável por armazenar
 * de forma estruturada a tabela usuarios do banco
 * 
 * */

public class Usuario {

	private int id_usuario;
	private String nome;
	private String senha;
	private String matricula;
	private int tipo_pessoa;
	private int permissoes;
	
	
	public Usuario(int id_usuario, String nome, String senha, String matricula, int tipo_pessoa, int permissoes) {
		 
		this.id_usuario = id_usuario;
		this.nome = nome;
		this.senha = senha;
		this.matricula = matricula;
		this.tipo_pessoa = tipo_pessoa;
		this.permissoes = permissoes;
	
	}
	
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public int getTipo_pessoa() {
		return tipo_pessoa;
	}
	public void setTipo_pessoa(int tipo_pessoa) {
		this.tipo_pessoa = tipo_pessoa;
	}
	public int getPermissoes() {
		return permissoes;
	}
	public void setPermissoes(int permissoes) {
		this.permissoes = permissoes;
	}
	
}
