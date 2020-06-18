package br.com.LeonardoFreitas.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.LeonardoFreitas.Conexao.Conexao;
import br.com.LeonardoFreitas.Modelo.Usuario;

/*
 * Classe DAO Usuario
 * 
 * Esta classe será responsável pela comunicação
 * entre a aplicação e o banco de dados 
 * referente a tabela usuarios
 * 
 * */

public class UsuarioDAO extends Conexao{
	
	//Listar todos os usuarios
	public List<Usuario> getAllUsuarios() throws SQLException, ClassNotFoundException{
		
		PreparedStatement pstmt = null;
		List<Usuario> lista = new ArrayList<>();
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM usuario");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int id = rs.getInt("id_usuario");
				String nome = rs.getString("nome");
				String senha = rs.getString("senha");
				String matricula = rs.getString("matricula");
				int tipo_pessoa = rs.getInt("tipo_pessoa");
				int permissoes= rs.getInt("permissoes");
				
				lista.add(new Usuario(id, nome, senha, matricula, tipo_pessoa,  permissoes));
				
			}
			
		}catch(SQLException e) {
			throw e;
		}
		return lista;
		
	}
	
	//Pegar usuarios cadastrados em alguma turma
	public List<Usuario> getUsuariosCadastrados(int id_turma) throws SQLException, ClassNotFoundException{
		
		PreparedStatement pstmt = null;
		
		List<Usuario> lista = new ArrayList<>();
		
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("select * from usuario inner join usuario_turmas, turmas where usuario.id_usuario = usuario_turmas.id_usuario and usuario_turmas.id_turma = ? and turmas.id_turma = ?");
			pstmt.setInt(1, id_turma);
			pstmt.setInt(2, id_turma);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int id = rs.getInt("id_usuario");
				String nome = rs.getString("nome");
				String matricula = rs.getString("matricula");
				String senha = rs.getString("senha");
				int tipo_pessoa = rs.getInt("tipo_pessoa");
				int permissao = rs.getInt("permissoes");
				
				lista.add(new Usuario(id, nome, matricula, senha, tipo_pessoa, permissao));
				
			}
			
		}catch(SQLException e) {
			throw e;
		}
		return lista;
		
	}
	
	//listar todos os usuarios do tipo aluno
	public List<Usuario> getAllAlunos() throws SQLException, ClassNotFoundException{
		
		PreparedStatement pstmt = null;
		List<Usuario> lista = new ArrayList<>();
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM usuario WHERE tipo_pessoa=?");
			pstmt.setInt(1, 2);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int id = rs.getInt("id_usuario");
				String nome = rs.getString("nome");
				String senha = rs.getString("senha");
				String matricula = rs.getString("matricula");
				int tipo_pessoa = rs.getInt("tipo_pessoa");
				int permissoes= rs.getInt("permissoes");
				
				lista.add(new Usuario(id, nome, senha, matricula, tipo_pessoa,  permissoes));
				
			}
			
		}catch(SQLException e) {
			throw e;
		}
		return lista;
		
	}
	
	//Listar todos os usuarios do tipo professor
	public List<Usuario> getAllProfessor() throws SQLException, ClassNotFoundException{
	
		PreparedStatement pstmt = null;
		List<Usuario> lista = new ArrayList<>();
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM usuario WHERE tipo_pessoa=?");
			pstmt.setInt(1, 1);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int id = rs.getInt("id_usuario");
				String nome = rs.getString("nome");
				String senha = rs.getString("senha");
				String matricula = rs.getString("matricula");
				int tipo_pessoa = rs.getInt("tipo_pessoa");
				int permissoes= rs.getInt("permissoes");
				
				lista.add(new Usuario(id, nome, senha, matricula, tipo_pessoa,  permissoes));
				
			}
			
		}catch(SQLException e) {
			throw e;
		}
		return lista;
	
}
	//listar usuario por id
	public Usuario getUsuarioId(int id) throws SQLException, ClassNotFoundException {
		
		PreparedStatement pstmt = null;
		
		Usuario usuario = null;
		
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM usuario where id_usuario=?");
			
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String nome = rs.getString("nome");
				String senha = rs.getString("senha");
				String matricula = rs.getString("matricula");
				int tipo_pessoa = rs.getInt("tipo_pessoa");
				int permissoes= rs.getInt("permissoes");
				
				usuario = new Usuario(id, nome, senha, matricula, tipo_pessoa,  permissoes);
				
			}
			
		}catch(SQLException e) {
			throw e;
		}
		return usuario;
	}
	
	//listar usuario por matricula
	public Usuario getUsuarioMatricula(String matricula) throws SQLException, ClassNotFoundException {
		
		PreparedStatement pstmt = null;
		
		Usuario usuario = null;
		
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM usuario where matricula=?");
			
			pstmt.setString(1, matricula);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String nome = rs.getString("nome");
				String senha = rs.getString("senha");
				int id = rs.getInt("id_usuario");
				int tipo_pessoa = rs.getInt("tipo_pessoa");
				int permissoes= rs.getInt("permissoes");
				
				usuario = new Usuario(id, nome, senha, matricula, tipo_pessoa,  permissoes);
				
			}
			
		}catch(SQLException e) {
			throw e;
		}
		return usuario;
	}
	
	//Inserir um novo usuario no banco
	public boolean CreateUsuario(String nome, String senha, String matricula, int tipo_pessoa, int permissoes) throws SQLException, ClassNotFoundException {
		
		PreparedStatement pstmt = null;
		boolean completo = false;
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO usuario(nome, senha, matricula, tipo_pessoa, permissoes) values(?,?,?,?,?)");
			
			pstmt.setString(1, nome);
			pstmt.setString(2, senha);
			pstmt.setString(3, matricula);
			pstmt.setInt(4, tipo_pessoa);
			pstmt.setInt(5, permissoes);
			
			pstmt.execute();
			
			completo = true;
			
		}catch(SQLException e) {
			throw e;
		}
		return completo;
	}
		
	//listar diversos usuarios por nome
	public List<Usuario> getAlunosNome(String nome) throws ClassNotFoundException, SQLException {
		
		PreparedStatement pstmt = null;
		List<Usuario> lista = new ArrayList<>();
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM usuario where tipo_pessoa=2 AND nome LIKE ?");
			
			pstmt.setString(1, nome + "%");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int id = rs.getInt("id_usuario");
				String nomeQuery = rs.getString("nome");
				String senha = rs.getString("senha");
				String matricula = rs.getString("matricula");
				int tipo_pessoa = rs.getInt("tipo_pessoa");
				int permissoes= rs.getInt("permissoes");
				
				lista.add(new Usuario(id, nomeQuery, senha, matricula, tipo_pessoa,  permissoes));
				
				
			}
			
		}catch(SQLException e) {
			throw e;
		}
		return lista;
	}
	
	//listar diversos usuarios do tipo professor pelo nome
	public List<Usuario> getProfessorNome(String nome) throws ClassNotFoundException, SQLException {
		
		PreparedStatement pstmt = null;
		List<Usuario> lista = new ArrayList<>();
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM usuario where tipo_pessoa=1 AND nome LIKE ?");
			
			pstmt.setString(1, nome + "%");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int id = rs.getInt("id_usuario");
				String nomeQuery = rs.getString("nome");
				String senha = rs.getString("senha");
				String matricula = rs.getString("matricula");
				int tipo_pessoa = rs.getInt("tipo_pessoa");
				int permissoes= rs.getInt("permissoes");
				
				lista.add(new Usuario(id, nomeQuery, senha, matricula, tipo_pessoa,  permissoes));
				
				
			}
			
		}catch(SQLException e) {
			throw e;
		}
		return lista;
	}
	
	//Atualizar um usuario no banco
	public boolean UpdateUsuario(int id, String nome, String senha, String matricula, int tipo_pessoa, int permissoes) throws SQLException, ClassNotFoundException {
		

		PreparedStatement pstmt = null;
		boolean completo = false;
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("UPDATE usuario SET nome=?, senha=?, matricula=?, tipo_pessoa=?, permissoes=? where id_usuario=?");
			
			
			pstmt.setString(1, nome);
			pstmt.setString(2, senha);
			pstmt.setString(3, matricula);
			pstmt.setInt(4, tipo_pessoa);
			pstmt.setInt(5, permissoes);
			pstmt.setInt(6, id);
			pstmt.execute();
			
			
			completo = true;
		}catch(SQLException e) {
			throw e;
		}
		return completo;
		
	}
	
	//Deletar um usuario no banco
	public boolean DeleteUsuario(int id)  throws SQLException, ClassNotFoundException {
		
		PreparedStatement pstmt = null;
		boolean completo = false;
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("DELETE FROM usuario WHERE id_usuario=?");
			pstmt.setInt(1, id);
			
			pstmt.execute();
			
			
			completo = true;
		}catch(SQLException e) {
			throw e;
		}
		return completo;
		
	}
	
}
