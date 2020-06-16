package br.com.LeonardoFreitas.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.zul.South;

import br.com.LeonardoFreitas.Conexao.Conexao;
import br.com.LeonardoFreitas.Controllers.ControllerUsuario;
import br.com.LeonardoFreitas.Modelo.Turma;
import br.com.LeonardoFreitas.Modelo.Usuario;

public class TurmaDAO extends Conexao{
	
	public List<Turma> getAllTurmas() throws SQLException, ClassNotFoundException{
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		
		List<Turma> lista = new ArrayList<>();
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM turmas");
			ResultSet rs = pstmt.executeQuery();
			ControllerUsuario controllerUsuario = new ControllerUsuario();
			List<Integer> listaId = new ArrayList<>();
			
			List<Usuario> usuarios = new ArrayList<>();
			while(rs.next()) {
				
				int id_turma = rs.getInt("id_turma");
				String codigo_turma = rs.getString("codigo_turma");
				int disciplina = rs.getInt("disciplina");
				
				pstmt2 = conn.prepareStatement("SELECT * FROM usuario_turmas WHERE id_turma = ?");
				pstmt2.setInt(1, id_turma);
				
				ResultSet rs2 = pstmt2.executeQuery();
				
				while(rs2.next()) {
					
					int id_usuario = rs2.getInt("id_usuario");
					listaId.add(id_usuario);
					
				}
				
				for(Integer id_usuarios: listaId) {
					
					Usuario usuario = controllerUsuario.getById(id_usuarios);
					
					usuarios.add(usuario);
					
				}
				lista.add(new Turma(id_turma, codigo_turma, disciplina, usuarios));
				
			}
			
		}catch(SQLException e) {
			throw e;
		}
		return lista;
		
	}
	
	public List<Turma> getTurmasCodigo(String codigoTurma) throws SQLException, ClassNotFoundException{
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		
		List<Turma> lista = new ArrayList<>();
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM turmas WHERE codigo_turma LIKE ?");
			pstmt.setString(1, codigoTurma);
			ResultSet rs = pstmt.executeQuery();
			ControllerUsuario controllerUsuario = new ControllerUsuario();
			List<Integer> listaId = new ArrayList<>();
			
			List<Usuario> usuarios = new ArrayList<>();
			while(rs.next()) {
				
				int id_turma = rs.getInt("id_turma");
				String codigo_turma = rs.getString("codigo_turma");
				int disciplina = rs.getInt("disciplina");
				
				pstmt2 = conn.prepareStatement("SELECT * FROM usuario_turmas WHERE id_turma = ?");
				pstmt2.setInt(1, id_turma);
				
				ResultSet rs2 = pstmt2.executeQuery();
				
				while(rs2.next()) {
					
					int id_usuario = rs2.getInt("id_usuario");
					listaId.add(id_usuario);
					
				}
				
				for(Integer id_usuarios: listaId) {
					
					Usuario usuario = controllerUsuario.getById(id_usuarios);
					
					usuarios.add(usuario);
					
				}
				lista.add(new Turma(id_turma, codigo_turma, disciplina, usuarios));
				
			}
			
		}catch(SQLException e) {
			throw e;
		}
		return lista;
		
	}
	
	public List<Turma> getUsuariosCodigoTurma(String codigoTurma) throws SQLException, ClassNotFoundException{
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		
		List<Turma> lista = new ArrayList<>();
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM turmas WHERE codigo_turma LIKE ?");
			pstmt.setString(1, codigoTurma);
			ResultSet rs = pstmt.executeQuery();
			ControllerUsuario controllerUsuario = new ControllerUsuario();
			List<Integer> listaId = new ArrayList<>();
			int id_usuario_turmas = 0;
			List<Usuario> usuarios = new ArrayList<>();
			while(rs.next()) {
				
				int id_turma = rs.getInt("id_turma");
				String codigo_turma = rs.getString("codigo_turma");
				int disciplina = rs.getInt("disciplina");
				
				pstmt2 = conn.prepareStatement("SELECT * FROM usuario_turmas inner join usuario WHERE id_turma = ? AND usuario.tipo_pessoa = 2 AND usuario_turmas.id_usuario = usuario.id_usuario");
				pstmt2.setInt(1, id_turma);
				
				ResultSet rs2 = pstmt2.executeQuery();
				
				while(rs2.next()) {
					
					int id_usuario = rs2.getInt("id_usuario");
					listaId.add(id_usuario);
					id_usuario_turmas = rs2.getInt("id_usuario_turmas");
				}
				
				for(Integer id_usuarios: listaId) {
					
					Usuario usuario = controllerUsuario.getById(id_usuarios);
					
					usuarios.add(usuario);
					
				}
				lista.add(new Turma(id_turma, codigo_turma, disciplina, usuarios, id_usuario_turmas));
				
			}
			
		}catch(SQLException e) {
			throw e;
		}
		return lista;
		
	}

	
	public List<Turma> getTurmasDisciplina(int id_disciplina) throws SQLException, ClassNotFoundException{
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		
		List<Turma> lista = new ArrayList<>();
		try {
			
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM turmas WHERE disciplina=?");
			pstmt.setInt(1, id_disciplina);
			
			ResultSet rs = pstmt.executeQuery();
			ControllerUsuario controllerUsuario = new ControllerUsuario();
			
			
			
			while(rs.next()) {
				List<Integer> listaId = new ArrayList<>();
				List<Usuario> usuarios = new ArrayList<>();
				
				int id_turma = rs.getInt("id_turma");
				String codigo_turma = rs.getString("codigo_turma");
				int disciplina = rs.getInt("disciplina");
				
				pstmt2 = conn.prepareStatement("SELECT * FROM usuario_turmas WHERE id_turma=?");
				pstmt2.setInt(1, id_turma);
				
				ResultSet rs2 = pstmt2.executeQuery();
				
				while(rs2.next()) {
					
					int id_usuario = rs2.getInt("id_usuario");
					listaId.add(id_usuario);
					
				}
				
				for(Integer id_usuarios: listaId) {
					System.out.println(listaId);
					Usuario usuario = controllerUsuario.getById(id_usuarios);
					System.out.println(usuario.getNome() + " - " +usuario.getId_usuario());
					usuarios.add(usuario);
					
				}
				lista.add(new Turma(id_turma, codigo_turma, disciplina, usuarios));
				
			}
			
		}catch(SQLException e) {
			throw e;
		}
		return lista;
		
	}
	
	public Turma getTurmaId(int id_turma) throws SQLException, ClassNotFoundException {
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;

		Turma turma = null;
		List<Integer> listaId = new ArrayList<>();
		ControllerUsuario controllerUsuario = new ControllerUsuario();
		List<Usuario> usuarios = new ArrayList<>();
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM turmas where id_turma=?");
			
			pstmt.setInt(1, id_turma);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String codigo_turma = rs.getString("codigo_turma");
				int disciplina = rs.getInt("disciplina");
				
				pstmt2 = conn.prepareStatement("SELECT * FROM usuario_turmas WHERE id_turma=?");
				pstmt2.setInt(1, id_turma);
				
				ResultSet rs2 = pstmt2.executeQuery();
				
				while(rs2.next()) {
					
					int id_usuario = rs2.getInt("id_usuario");
					listaId.add(id_usuario);
					
				}
				
				for(Integer id_usuarios: listaId) {
					
					Usuario usuario = controllerUsuario.getById(id_usuarios);
					
					usuarios.add(usuario);
					
				}
				
				turma = new Turma(id_turma, codigo_turma, disciplina, usuarios);
				
			}
			
		}catch(SQLException e) {
			throw e;
		}
		return turma;
	}
	

	public boolean CreateTurma(int id_turma, String codigo_turma, int disciplina, List<Integer> usuarios) throws SQLException, ClassNotFoundException {
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		boolean completo = false;
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO turmas(codigo_turma, disciplina) values(?,?)");
			
			pstmt.setString(1, codigo_turma);
			pstmt.setInt(2, disciplina);
			
			pstmt.execute();
			
			for(Integer usuario: usuarios) {
				
				pstmt2 = conn.prepareStatement("INSERT INTO usuario_turmas(id_turma, id_usuario) values(?,?)");
				
				pstmt2.setInt(1, id_turma);
				pstmt2.setInt(2, usuario);
				
				pstmt2.execute();
				
			}
			
			
			completo = true;
		}catch(SQLException e) {
			throw e;
		}
		return completo;
	}
	
	public boolean UpdateTurma(int id_turma, String codigo_turma, int disciplina) throws SQLException, ClassNotFoundException {
		

		PreparedStatement pstmt = null;
		boolean completo = false;
		try {
		
			Connection conn = this.getConnection();
			pstmt = conn.prepareStatement("UPDATE turmas SET codigo_turma=?, disciplina=? where id_turma=?");
			
			pstmt.setString(1, codigo_turma);
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
