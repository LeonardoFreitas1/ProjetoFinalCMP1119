package br.com.LeonardoFreitas.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.LeonardoFreitas.Conexao.Conexao;
import br.com.LeonardoFreitas.Modelo.Frequencia;

/*
 * Classe DAO Frequencia
 * 
 * Esta classe será responsável pela comunicação
 * entre a aplicação e o banco de dados 
 * referente a tabela frequencias
 * 
 * */

public class FrequenciaDAO extends Conexao{
	
	//Listar todas as frequencias existentes no banco
	public List<Frequencia> getAllFrequencia() throws SQLException, ClassNotFoundException{
		
		PreparedStatement pstmt = null;
		List<Frequencia> lista = new ArrayList<>();
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM frequencias");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int id_frequencia = rs.getInt("id_frequencia");
				int usuario = rs.getInt("usuario");
				int turma = rs.getInt("turma");
				int usuario_turma = rs.getInt("id_frequencia");
				String data = rs.getString("data");
				boolean frequente = rs.getBoolean("frequente");
				
				lista.add(new Frequencia(id_frequencia,usuario,turma,data,frequente,usuario_turma));
				
			}
			
		}catch(SQLException e) {
			throw e;
		}
		return lista;
		
	}
	
	//listar frequencias por id
	public Frequencia getFrequenciaId(int id_frequencia) throws SQLException, ClassNotFoundException {
		
		PreparedStatement pstmt = null;
		
		Frequencia frequencia = null;
		
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM frequencias where id_frequencia=?");
			
			pstmt.setInt(1, id_frequencia);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {

				int usuario = rs.getInt("usuario");
				int turma = rs.getInt("turma");
				int usuario_turma = rs.getInt("id_frequencia");
				String data = rs.getString("data");
				boolean frequente = rs.getBoolean("frequente");
				
				frequencia = new Frequencia(id_frequencia, usuario, turma, data, frequente, usuario_turma);
				
			}
			
		}catch(SQLException e) {
			throw e;
		}
		return frequencia;
	}
	
	public Frequencia getFrequenciaIdData(int id_turma, int id_usuario, String data) throws SQLException, ClassNotFoundException {
		
		PreparedStatement pstmt = null;
		
		Frequencia frequencia = null;
		
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM frequencias where turma=? and usuario=? and data like ?");
			
			pstmt.setInt(1, id_turma);
			pstmt.setInt(2, id_usuario);
			pstmt.setString(3, data);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int id_frequencia = rs.getInt("id_frequencia");
				int usuario = rs.getInt("usuario");
				int turma = rs.getInt("turma");
				int usuario_turma = rs.getInt("id_frequencia");
				boolean frequente = rs.getBoolean("frequente");
				
				frequencia = new Frequencia(id_frequencia, usuario, turma, data, frequente, usuario_turma);
				
			}
			
		}catch(SQLException e) {
			throw e;
		}
		return frequencia;
	}
	
	//Criar uma nova frequencia no banco de dados
	public boolean CreateFrequencia(int usuario, int turma, String data, boolean frequente, int usuario_turma) throws SQLException, ClassNotFoundException {
		
		PreparedStatement pstmt = null;
		boolean completo = false;
		
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO frequencias(usuario, turma, data, frequente, usuario_turma) values(?,?,?,?,?)");
			
			pstmt.setInt(1, usuario);
			pstmt.setInt(2, turma);
			pstmt.setString(3, data);
			pstmt.setBoolean(4, frequente);
			pstmt.setInt(5, usuario_turma);
			
			pstmt.execute();
			
			
			completo = true;
		}catch(SQLException e) {
			throw e;
		}
		return completo;
	}
	
	//Atualizar uma frequencia existente no banco de dados
	public boolean UpdateFrequencia(int id_frequencia, int usuario, int turma, String data, boolean frequente, int usuario_turma) throws SQLException, ClassNotFoundException {
		System.out.println(id_frequencia);
	
		PreparedStatement pstmt = null;
		boolean completo = false;
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("UPDATE frequencias SET usuario=?, turma=?, data=?, frequente=?, usuario_turma=? where id_frequencia=?");
			
			
			pstmt.setInt(1, usuario);
			pstmt.setInt(2, turma);
			pstmt.setString(3, data);
			pstmt.setBoolean(4, frequente);
			pstmt.setInt(5, usuario_turma);
			pstmt.setInt(6, id_frequencia);
			
			pstmt.execute();
			
			
			completo = true;
		}catch(SQLException e) {
			throw e;
		}
		return completo;
		
	}
	
	public boolean UpdateOnlyFrequencia(int id_frequencia, boolean frequente) throws SQLException, ClassNotFoundException {
		System.out.println(id_frequencia);
	
		PreparedStatement pstmt = null;
		boolean completo = false;
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("UPDATE frequencias SET frequente=? where id_frequencia=?");
			
		
			pstmt.setBoolean(1, frequente);
			pstmt.setInt(2, id_frequencia);
	
			
			pstmt.execute();
			
			
			completo = true;
		}catch(SQLException e) {
			throw e;
		}
		return completo;
		
	}
	
	//Deletar uma frequencia existente no banco de dados
	public boolean DeleteFrequencia(int id_frequencia)  throws SQLException, ClassNotFoundException {
		
		PreparedStatement pstmt = null;
		boolean completo = false;
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("DELETE FROM frequencias WHERE id_frequencia=?");
			pstmt.setInt(1, id_frequencia);
			
			pstmt.execute();
			
			
			completo = true;
		}catch(SQLException e) {
			throw e;
		}
		return completo;
		
	}
	
	public boolean DeleteFrequenciaUsuario(int id_usuario)  throws SQLException, ClassNotFoundException {
		
		PreparedStatement pstmt = null;
		boolean completo = false;
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("DELETE FROM frequencias WHERE usuario=?");
			pstmt.setInt(1, id_usuario);
			
			pstmt.execute();
			
			
			completo = true;
		}catch(SQLException e) {
			throw e;
		}
		return completo;
		
	}
	
}
