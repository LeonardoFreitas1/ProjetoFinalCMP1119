package br.com.LeonardoFreitas.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.LeonardoFreitas.Conexao.Conexao;
import br.com.LeonardoFreitas.Modelo.Turma;

public class TurmaDAO extends Conexao{
	
public List<Turma> getAllTurmas() throws SQLException, ClassNotFoundException{
		
		PreparedStatement pstmt = null;
		List<Turma> lista = new ArrayList<>();
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM turmas");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int id_turma = rs.getInt("id_turma");
				String nome_turma = rs.getString("nome_turma");
				int disciplina = rs.getInt("disciplina");
				lista.add(new Turma(id_turma, nome_turma, disciplina));
				
			}
			
		}catch(SQLException e) {
			throw e;
		}
		return lista;
		
	}
	
	public Turma getTurmaId(int id_turma) throws SQLException, ClassNotFoundException {
		
		PreparedStatement pstmt = null;
		
		Turma turma = null;
		
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM turmas where id_turma=?");
			
			pstmt.setInt(1, id_turma);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String nome_turma = rs.getString("nome_turma");
				int disciplina = rs.getInt("disciplina");
				
				turma = new Turma(id_turma, nome_turma, disciplina);
				
			}
			
		}catch(SQLException e) {
			throw e;
		}
		return turma;
	}
	

	public boolean CreateTurma(String nome_turma, int disciplina) throws SQLException, ClassNotFoundException {
		
		PreparedStatement pstmt = null;
		boolean completo = false;
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO turmas(nome_turma, disciplina) values(?,?)");
			
			pstmt.setString(1, nome_turma);
			pstmt.setInt(2, disciplina);
			
			pstmt.execute();
			
			
			completo = true;
		}catch(SQLException e) {
			throw e;
		}
		return completo;
	}
	
	public boolean UpdateTurma(int id_turma, String nome_turma, int disciplina) throws SQLException, ClassNotFoundException {
		

		PreparedStatement pstmt = null;
		boolean completo = false;
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("UPDATE turmas SET nome_turma=?, disciplina=? where id_turma=?");
			
			pstmt.setString(1, nome_turma);
			pstmt.setInt(2, disciplina);
			pstmt.setInt(3, id_turma);
			
			pstmt.execute();
			
			
			completo = true;
		}catch(SQLException e) {
			throw e;
		}
		return completo;
		
	}
	
	public boolean DeleteTurma(int id_turma)  throws SQLException, ClassNotFoundException {
		
		PreparedStatement pstmt = null;
		boolean completo = false;
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("DELETE FROM turmas WHERE id_turma=?");
			pstmt.setInt(1, id_turma);
			
			pstmt.execute();
			
			
			completo = true;
		}catch(SQLException e) {
			throw e;
		}
		return completo;
		
	}
	
}
