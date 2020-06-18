package br.com.LeonardoFreitas.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.LeonardoFreitas.Conexao.Conexao;
import br.com.LeonardoFreitas.Modelo.Disciplina;

/*
 * Classe DAO Disciplina
 * 
 * Esta classe será responsável pela comunicação
 * entre a aplicação e o banco de dados 
 * referente a tabela disciplinas
 * 
 * */

public class DisciplinaDAO extends Conexao{
	
	//Listar todas as disciplinas existentes no banco
	public List<Disciplina> getAllDisciplinas() throws SQLException, ClassNotFoundException{
			
			PreparedStatement pstmt = null;
			List<Disciplina> lista = new ArrayList<>();
			try {
			
				Connection conn = this.getConnection();
				pstmt = conn.prepareStatement("SELECT * FROM disciplinas");
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					int id_disciplina = rs.getInt("id_disciplina");
					String nome_dsiciplina = rs.getString("nome_disciplina");
					String codigo_disiciplina = rs.getString("codigo_disciplina");
					
					lista.add(new Disciplina(id_disciplina, nome_dsiciplina, codigo_disiciplina));
					
				}
				
			}catch(SQLException e) {
				throw e;
			}
			return lista;
			
		}
	
	//Listar apenas uma disciplina pelo nome
	public Disciplina getDisciplinaNome(String nome) throws ClassNotFoundException, SQLException {
		
		PreparedStatement pstmt = null;
		Disciplina disciplina = null;
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM disciplinas where nome_disciplina LIKE ?");
			
			pstmt.setString(1, nome + "%");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int id = rs.getInt("id_disciplina");
				String nomeQuery = rs.getString("nome_disciplina");
				String codigo = rs.getString("codigo_disciplina");
				
				disciplina = new Disciplina(id, nomeQuery, codigo);
				
			}
			
		}catch(SQLException e) {
			throw e;
		}
		return disciplina;
	}
	
	//Listar diversar disciplinas pelo nome
	public List<Disciplina> getListaDisciplinaNome(String nome) throws ClassNotFoundException, SQLException {
		
		PreparedStatement pstmt = null;
		List<Disciplina> disciplinas = new ArrayList<>();
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM disciplinas where nome_disciplina LIKE ?");
			
			pstmt.setString(1, nome + "%");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int id = rs.getInt("id_disciplina");
				String nomeQuery = rs.getString("nome_disciplina");
				String codigo = rs.getString("codigo_disciplina");
				
				disciplinas.add(new Disciplina(id, nomeQuery, codigo));
				
			}
			
		}catch(SQLException e) {
			throw e;
		}
		return disciplinas;
	}
	
	//Buscar disciplina pelo id
	public Disciplina getDisciplinaId(int id_disciplina) throws SQLException, ClassNotFoundException {
		
		PreparedStatement pstmt = null;
		
		Disciplina turma = null;
		
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM disciplinas where id_disciplina=?");
			
			pstmt.setInt(1, id_disciplina);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String nome_disciplina = rs.getString("nome_disciplina");
				String codigo_disciplina = rs.getString("codigo_disciplina");
				
				turma = new Disciplina(id_disciplina, nome_disciplina, codigo_disciplina);
				
			}
			
		}catch(SQLException e) {
			throw e;
		}
		return turma;
	}
	
	//Adicionar uma nova disciplina no banco de dados
	public boolean CreateDisciplina(String nome_disciplina, String codigo_disciplina) throws SQLException, ClassNotFoundException {
		
		PreparedStatement pstmt = null;
		boolean completo = false;
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO disciplinas(nome_disciplina, codigo_disciplina) values(?,?)");
			
			pstmt.setString(1, nome_disciplina);
			pstmt.setString(2, codigo_disciplina);
			
			pstmt.execute();
			
			
			completo = true;
		}catch(SQLException e) {
			throw e;
		}
		return completo;
	}
	
	//Atualizar uma disciplina existente no banco de dados
	public boolean UpdateDisciplina(int id_disciplina, String nome_disciplina, String codigo_disciplina) throws SQLException, ClassNotFoundException {
		

		PreparedStatement pstmt = null;
		boolean completo = false;
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("UPDATE disciplinas SET nome_disciplina=?, codigo_disciplina=? where id_disciplina=?");
			
			pstmt.setString(1, nome_disciplina);
			pstmt.setString(2, codigo_disciplina);
			pstmt.setInt(3, id_disciplina);
			
			pstmt.execute();
			
			
			completo = true;
		}catch(SQLException e) {
			throw e;
		}
		return completo;
		
	}
	
	//Deletar uma disciplina existente no banco de dados
	public boolean DeleteDisciplina(int id_disciplina)  throws SQLException, ClassNotFoundException {
		
		PreparedStatement pstmt = null;
		boolean completo = false;
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("DELETE FROM disciplinas WHERE id_disciplina=?");
			pstmt.setInt(1, id_disciplina);
			
			pstmt.execute();
			
			
			completo = true;
		}catch(SQLException e) {
			throw e;
		}
		return completo;
		
	}
	
}
