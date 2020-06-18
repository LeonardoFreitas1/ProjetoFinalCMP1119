package br.com.LeonardoFreitas.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.LeonardoFreitas.Conexao.Conexao;
import br.com.LeonardoFreitas.Modelo.TipoPessoa;

/*
 * Classe DAO TipoPessoa
 * 
 * Esta classe será responsável pela comunicação
 * entre a aplicação e o banco de dados 
 * referente a tabela tipo_pessoa
 * 
 * */

public class TipoPessoaDAO extends Conexao {
	
	//Listar todos os tipos de pessoas existente no banco
	public List<TipoPessoa> getAllTipoPessoas() throws SQLException, ClassNotFoundException{
		
		PreparedStatement pstmt = null;
		List<TipoPessoa> lista = new ArrayList<>();
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM tipo_pessoa");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int id = rs.getInt("id_tipo_pessoa");
				String nome_tipo = rs.getString("nome_tipo");
				
				lista.add(new TipoPessoa(id, nome_tipo));
				
			}
			
		}catch(SQLException e) {
			throw e;
		}
		return lista;
		
	}
	
	//Criar um novo tipo de pessoa
	public boolean CreateTipoPessoa(String nome_tipo) throws SQLException, ClassNotFoundException {
		
		PreparedStatement pstmt = null;
		boolean completo = false;
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO tipo_pessoa(nome_tipo) values(?)");
			
			pstmt.setString(1, nome_tipo);
			
			pstmt.execute();
			
			
			completo = true;
		}catch(SQLException e) {
			throw e;
		}
		return completo;
	}
	
	//Atualizar um tipo de pessoa existente no banco de dados
	public boolean UpdateTipoPessoa(int id_tipo_pessoa, String nome_tipo) throws SQLException, ClassNotFoundException {
		

		PreparedStatement pstmt = null;
		boolean completo = false;
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("UPDATE tipo_pessoa SET nome_tipo=? where id_tipo_pessoa=?");
			
			pstmt.setString(1, nome_tipo);
			pstmt.setInt(2, id_tipo_pessoa);
			
			pstmt.execute();
			
			
			completo = true;
		}catch(SQLException e) {
			throw e;
		}
		return completo;
		
	}
	
	//Deletar um tipo de pessoa existente no banco
	public boolean DeleteTipoPessoa(int id_tipo_pessoa)  throws SQLException, ClassNotFoundException {
		
		PreparedStatement pstmt = null;
		boolean completo = false;
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("DELETE FROM tipo_pessoa WHERE id_tipo_pessoa=?");
			pstmt.setInt(1, id_tipo_pessoa);
			
			pstmt.execute();
			
			
			completo = true;
		}catch(SQLException e) {
			throw e;
		}
		return completo;
		
	}
	
}
