package br.com.LeonardoFreitas.Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private String login = "root";
	private String senha = "admin";
	private String host = "localhost";
	private String dbName = "trabalhofinal";
	private String url = "jdbc:mysql://" + host + "/" + dbName;
	
	public Connection conexao = null;
	
	public Conexao() { }
	
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
		}catch(ClassNotFoundException CNFEx) {
			throw CNFEx;
		}
		
		try {
			
			this.conexao = (Connection) DriverManager.getConnection(url, login, senha);
			
		} catch(SQLException SQLEx) {
			throw SQLEx;
		}
		
		return this.conexao;
		
	}
	
	
}
