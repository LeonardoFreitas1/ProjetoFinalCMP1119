package br.com.LeonardoFreitas.Controllers;

import java.sql.SQLException;
import java.util.List;

import br.com.LeonardoFreitas.DAO.UsuarioDAO;
import br.com.LeonardoFreitas.Modelo.Usuario;

/*
 * Classe Controller Usuario
 * 
 * Esta classe será responsável pelas solicitações e validações
 * entre a aplicação e a classe UsuarioDAO 
 * 
 * */

public class ControllerUsuario {
	
	//Solicitar lista de todos os usuários cadastrados no banco
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
	
	//Solicitar usuarios cadastrados em uma determinada turma
	public List<Usuario> getUsersByTurma(int id_turma) throws SQLException, ClassNotFoundException{
		
		UsuarioDAO UsuarioDAO = new UsuarioDAO();
		List<Usuario> lista = null;
		try {
			
			 lista = UsuarioDAO.getUsuariosCadastrados(id_turma);
			
		}catch(SQLException e) {
			throw e;
		}
		return lista;
	}
	
	//Listar todos os usuários do tipo aluno
	public List<Usuario> getAllAluno() throws SQLException, ClassNotFoundException{
		
		UsuarioDAO UsuarioDAO = new UsuarioDAO();
		List<Usuario> lista = null;
		try {
			
			 lista = UsuarioDAO.getAllAlunos();
			
		}catch(SQLException e) {
			throw e;
		}
		return lista;
	}
	
	//Listar todos os usuários do tipo professor
	public List<Usuario> getAllProfessor() throws SQLException, ClassNotFoundException{
	
		UsuarioDAO UsuarioDAO = new UsuarioDAO();
		List<Usuario> lista = null;
		
		try {
			
			 lista = UsuarioDAO.getAllProfessor();
			
		}catch(SQLException e) {
			throw e;
		}
		
		return lista;
	}
	
	//Solicitar adição de um novo usuário no banco
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
	
	//Solicitar usuário baseado no id
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
	
	//Solicitar usuário baseado na sua matricula
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
	
	//Solicitar lista de usuários baseados no seu nome
	public List<Usuario> getAlunosByName(String nome) throws SQLException, ClassNotFoundException {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> lista = null;
		
		try {
			
			lista = usuarioDAO.getAlunosNome(nome);
			
		}catch(SQLException e) {
			throw e;
		}
		
		return lista;
		
	}
	
	//Solicitar lista usuários do tipo professor pelo nome
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
	
	//Solicitar atualização de um usuário no banco
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
	
	//Solicitar a exclusão de um usuário no banco
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
