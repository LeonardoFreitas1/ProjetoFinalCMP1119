package br.com.LeonardoFreitas.Controllers;

import java.sql.SQLException;
import java.util.List;

import br.com.LeonardoFreitas.DAO.TurmaDAO;
import br.com.LeonardoFreitas.Modelo.Turma;

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
	
	public boolean create(String nome_turma, int disciplina) throws SQLException, ClassNotFoundException{
		
		TurmaDAO TurmaDAO = new TurmaDAO();
		boolean completo = false;
		
		try {
			
			completo = TurmaDAO.CreateTurma(nome_turma, disciplina);
			
		}catch(SQLException e) {
			throw e;
		}
		return completo;
	}
	
	public boolean update(int id, String nome_turma, int disciplina) throws SQLException, ClassNotFoundException{
		
		TurmaDAO TurmaDAO = new TurmaDAO();
		boolean completo = false;
		
		try {

			completo = TurmaDAO.UpdateTurma(id, nome_turma, disciplina);

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
