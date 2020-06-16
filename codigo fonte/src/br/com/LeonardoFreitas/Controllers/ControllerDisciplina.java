package br.com.LeonardoFreitas.Controllers;

import java.sql.SQLException;
import java.util.List;

import br.com.LeonardoFreitas.DAO.DisciplinaDAO;
import br.com.LeonardoFreitas.Modelo.Disciplina;

public class ControllerDisciplina {
	
	public List<Disciplina> getAll() throws SQLException, ClassNotFoundException{
		
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		List<Disciplina> lista = null;
		try {
			
			 lista = disciplinaDAO.getAllDisciplinas();
			
		}catch(SQLException e) {
			throw e;
		}
		return lista;
	}
	
	public Disciplina getById(int id) throws SQLException, ClassNotFoundException {
		
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		Disciplina disciplina = null;
		
		try {
			disciplina = disciplinaDAO.getDisciplinaId(id);
			
		}catch(SQLException e){
			throw e;
		}
		
		return disciplina;
		
	}	
	public List<Disciplina> getListByName(String nome) throws SQLException, ClassNotFoundException {
		
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		List<Disciplina> disciplinas = null;
		
		try {
			disciplinas = disciplinaDAO.getListaDisciplinaNome(nome);
			
		}catch(SQLException e){
			throw e;
		}
		
		return disciplinas;
		
	}
	
	public Disciplina getByName(String nome) throws SQLException, ClassNotFoundException {
		
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		Disciplina disciplina = null;
		
		try {
			disciplina = disciplinaDAO.getDisciplinaNome(nome);
			
		}catch(SQLException e){
			throw e;
		}
		
		return disciplina;
		
	}
	
	public boolean create(String nome_disciplina, String codigo_disciplina) throws SQLException, ClassNotFoundException{
		
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		boolean completo = false;
		
		try {
			
			completo = disciplinaDAO.CreateDisciplina(nome_disciplina, codigo_disciplina);
			
		}catch(SQLException e) {
			throw e;
		}
		return completo;
	}
	
	public boolean update(int id, String nome_disciplina, String codigo_disciplina) throws SQLException, ClassNotFoundException{
		
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		boolean completo = false;
		
		try {

			completo =disciplinaDAO.UpdateDisciplina(id,nome_disciplina, codigo_disciplina);

		}catch(SQLException e) {
			throw e;
		}
		return completo;
	}
	
	public boolean delete(int id) throws SQLException, ClassNotFoundException{
		
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		boolean completo = false;
		
		try {
			
			completo = disciplinaDAO.DeleteDisciplina(id);
			
		}catch(SQLException e) {
			throw e;
		}
		return completo;
	}
}
