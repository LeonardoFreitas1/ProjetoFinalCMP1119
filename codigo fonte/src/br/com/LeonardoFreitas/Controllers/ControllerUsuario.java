package br.com.LeonardoFreitas.Controllers;

import java.sql.SQLException;
import java.util.List;

import br.com.LeonardoFreitas.DAO.UsuarioDAO;
import br.com.LeonardoFreitas.Modelo.Usuario;

public class ControllerUsuario {
	
public List<Usuario> getAll() throws SQLException, ClassNotFoundException{
		
		UsuarioDAO UsuarioDAO = new UsuarioDAO();
		List<Usuario> lista = null;
		try {
			
			 lista = UsuarioDAO.getAllUsuarios();
			
		}catch(SQLException e) {
			throw e;
		}
		return lista;
	}
	
	public boolean create(String nome, String senha, String matricula, int tipo_pessoa, int permissoes) throws SQLException, ClassNotFoundException{
		
		UsuarioDAO UsuarioDAO = new UsuarioDAO();
		boolean completo = false;
		
		try {
			
			completo = UsuarioDAO.CreateUsuario(nome, senha, matricula, tipo_pessoa,  permissoes);
			
		}catch(SQLException e) {
			throw e;
		}
		return completo;
	}
	
	public Usuario getById(int id) throws SQLException, ClassNotFoundException {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = null;
		
		try {
			
			usuario = usuarioDAO.getUsuarioId(id);
			
		}catch(SQLException e) {
			throw e;
		}
		
		return usuario;
		
	}
	
	public Usuario getByMatricula(String matricula) throws SQLException, ClassNotFoundException {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = null;
		
		try {
			
			usuario = usuarioDAO.getUsuarioMatricula(matricula);
			
		}catch(SQLException e) {
			throw e;
		}
		
		return usuario;
		
	}
	
	public List<Usuario> getAlunoByName(String nome) throws SQLException, ClassNotFoundException {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> lista = null;
		
		try {
			
			lista = usuarioDAO.getAlunoNome(nome);
			
		}catch(SQLException e) {
			throw e;
		}
		
		return lista;
		
	}
	
public List<Usuario> getProfessorByName(String nome) throws SQLException, ClassNotFoundException {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> lista = null;
		
		try {
			
			lista = usuarioDAO.getProfessorNome(nome);
			
		}catch(SQLException e) {
			throw e;
		}
		
		return lista;
		
	}
	
	
	
	public boolean update(int id, String nome, String senha, String matricula, int tipo_pessoa, int permissoes) throws SQLException, ClassNotFoundException{
		
		UsuarioDAO UsuarioDAO = new UsuarioDAO();
		boolean completo = false;
		
		try {

			completo = UsuarioDAO.UpdateUsuario(id,nome, senha, matricula, tipo_pessoa,  permissoes);

		}catch(SQLException e) {
			throw e;
		}
		return completo;
	}
	
	public boolean delete(int id) throws SQLException, ClassNotFoundException{
		
		UsuarioDAO UsuarioDAO = new UsuarioDAO();
		boolean completo = false;
		
		try {
			
			completo = UsuarioDAO.DeleteUsuario(id);
			
		}catch(SQLException e) {
			throw e;
		}
		return completo;
	}
	
}
