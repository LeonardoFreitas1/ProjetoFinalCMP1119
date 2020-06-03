package br.com.LeonardoFreitas.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.LeonardoFreitas.Conexao.Conexao;
import br.com.LeonardoFreitas.Modelo.Permissao;

public class PermissaoDAO extends Conexao{
	
	public List<Permissao> getAllPermissoes() throws SQLException, ClassNotFoundException{
		
		PreparedStatement pstmt = null;
		List<Permissao> lista = new ArrayList<>();
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM permissoes");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int id = rs.getInt("id_permissao");
				String nome_permissao = rs.getString("nome_permissao");
				
				lista.add(new Permissao(id, nome_permissao));
				
			}
			
		}catch(SQLException e) {
			throw e;
		}
		return lista;
		
	}
	
	public boolean CreatePermissao(String nome_permissao) throws SQLException, ClassNotFoundException {
		
		PreparedStatement pstmt = null;
		boolean completo = false;
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO permissoes(nome_permissao) values(?)");
			
			pstmt.setString(1, nome_permissao);
			
			pstmt.execute();
			
			
			completo = true;
		}catch(SQLException e) {
			throw e;
		}
		return completo;
	}
	
	public boolean UpdatePermissao(int id_permissao, String nome_permissao) throws SQLException, ClassNotFoundException {
		

		PreparedStatement pstmt = null;
		boolean completo = false;
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("UPDATE permissoes SET nome_permissao=? where id_permissao=?");
			
			pstmt.setString(1, nome_permissao);
			pstmt.setInt(2, id_permissao);
			
			pstmt.execute();
			
			
			completo = true;
		}catch(SQLException e) {
			throw e;
		}
		return completo;
		
	}
	
	public boolean DeletePermissao(int id_permissao)  throws SQLException, ClassNotFoundException {
		
		PreparedStatement pstmt = null;
		boolean completo = false;
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("DELETE FROM permissoes WHERE id_permissao=?");
			pstmt.setInt(1, id_permissao);
			
			pstmt.execute();
			
			
			completo = true;
		}catch(SQLException e) {
			throw e;
		}
		return completo;
		
	}
	
}
