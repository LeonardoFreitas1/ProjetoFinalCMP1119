package br.com.LeonardoFreitas.Controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.LeonardoFreitas.DAO.TurmaDAO;
import br.com.LeonardoFreitas.DAO.UsuarioTurmaDAO;
import br.com.LeonardoFreitas.Modelo.Disciplina;
import br.com.LeonardoFreitas.Modelo.Turma;
import br.com.LeonardoFreitas.Modelo.Usuario;
import br.com.LeonardoFreitas.Modelo.UsuarioTurma;

/*
 * Classe Controller Turma
 * 
 * Esta classe será responsável pelas solicitações e validações
 * entre a aplicação e a classe TurmaDAO 
 * 
 * */

public class ControllerTurma {
	
	
	public List<Turma> getAll() throws SQLException, ClassNotFoundException{
		
		TurmaDAO TurmaDAO = new TurmaDAO();
		List<Turma> lista = null;
		try {
			
			 lista = TurmaDAO.getAllTurmas();
			
		}catch(SQLException e) {
			throw e;
		}
		return lista;
	}
	
	public List<Turma> getByCode(String codigoTurma) throws SQLException, ClassNotFoundException{
		
		TurmaDAO TurmaDAO = new TurmaDAO();
		List<Turma> lista = null;
		try {
			
			 lista = TurmaDAO.getTurmasCodigo(codigoTurma);
			
		}catch(SQLException e) {
			throw e;
		}
		return lista;
	}
	
	public List<Turma> getUsersByCode(String codigoTurma) throws SQLException, ClassNotFoundException{
		
		TurmaDAO TurmaDAO = new TurmaDAO();
		List<Turma> lista = null;
		
		try {
			
			 lista = TurmaDAO.getUsuariosCodigoTurma(codigoTurma);
			
		}catch(SQLException e) {
			throw e;
		}
		return lista;
	}
	
	public List<Turma> getByUser(String nome) throws SQLException, ClassNotFoundException{
		
		TurmaDAO TurmaDAO = new TurmaDAO();
		UsuarioTurmaDAO usuarioDAO = new UsuarioTurmaDAO();
		ControllerUsuario controllerUsuario = new ControllerUsuario();
		
		List<Turma> lista = new ArrayList<>();
		
		try {
			 List<Usuario> usuarios = controllerUsuario.getAlunosByName(nome);
			 
			 for(Usuario usuario: usuarios) {
				 
				 List<UsuarioTurma> list = usuarioDAO.getUsuarioTurmaIdUsuario(usuario.getId_usuario());
				 
				 for(UsuarioTurma usuarioTurma: list) {
					 
					 lista.add(TurmaDAO.getTurmaId(usuarioTurma.getId_turma()));
					 
				 }
			 }
		}catch(SQLException e) {
			throw e;
		}
		return lista;
	}
	
	public List<Turma> getByDisciplina(String nomeDisciplina) throws SQLException, ClassNotFoundException{
		
		TurmaDAO TurmaDAO = new TurmaDAO();
		ControllerDisciplina controllerDisciplina = new ControllerDisciplina();
		
		List<Turma> lista = new ArrayList<>();
		
		try {
			 Disciplina disciplina = controllerDisciplina.getByName(nomeDisciplina);
			 lista = TurmaDAO.getTurmasDisciplina(disciplina.getId_disciplina());
			 
		}catch(SQLException e) {
			throw e;
		}
		return lista;
	}
	
	public boolean create(int id_turma, String codigo_turma, int disciplina, List<Integer> usuarios) throws SQLException, ClassNotFoundException{
		
		TurmaDAO TurmaDAO = new TurmaDAO();
		boolean completo = false;
		
		try {
			
			completo = TurmaDAO.CreateTurma(id_turma, codigo_turma, disciplina, usuarios);
			
		}catch(SQLException e) {
			throw e;
		}
		return completo;
	}
	
	public boolean update(int id, String codigo_turma, int disciplina) throws SQLException, ClassNotFoundException{
		
		TurmaDAO TurmaDAO = new TurmaDAO();
		boolean completo = false;
		
		try {

			completo = TurmaDAO.UpdateTurma(id, codigo_turma, disciplina);

		}catch(SQLException e) {
			throw e;
		}
		return completo;
	}
	
	public boolean delete(int id) throws SQLException, ClassNotFoundException{
		
		TurmaDAO TurmaDAO = new TurmaDAO();
		boolean completo = false;
		
		try {
			
			completo = TurmaDAO.DeleteTurma(id);
			
		}catch(SQLException e) {
			throw e;
		}
		return completo;
	}
	
}
