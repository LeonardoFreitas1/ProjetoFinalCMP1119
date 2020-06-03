package br.com.LeonardoFreitas.Controllers;

import java.sql.SQLException;
import java.util.List;

import br.com.LeonardoFreitas.DAO.TipoPessoaDAO;
import br.com.LeonardoFreitas.Modelo.TipoPessoa;

public class ControllerTipoPessoa {
	
	public List<TipoPessoa> getAll() throws SQLException, ClassNotFoundException{
		
		TipoPessoaDAO TipoPessoaDAO = new TipoPessoaDAO();
		List<TipoPessoa> lista = null;
		try {
			
			 lista = TipoPessoaDAO.getAllTipoPessoas();
			
		}catch(SQLException e) {
			throw e;
		}
		return lista;
	}
	
	public boolean create(String nome_tipoPessoa) throws SQLException, ClassNotFoundException{
		
		TipoPessoaDAO TipoPessoaDAO = new TipoPessoaDAO();
		boolean completo = false;
		
		try {
			
			completo = TipoPessoaDAO.CreateTipoPessoa(nome_tipoPessoa);
			
		}catch(SQLException e) {
			throw e;
		}
		return completo;
	}
	
	public boolean update(int id, String nome_tipoPessoa) throws SQLException, ClassNotFoundException{
		
		TipoPessoaDAO TipoPessoaDAO = new TipoPessoaDAO();
		boolean completo = false;
		
		try {

			completo = TipoPessoaDAO.UpdateTipoPessoa(id,nome_tipoPessoa);

		}catch(SQLException e) {
			throw e;
		}
		return completo;
	}
	
	public boolean delete(int id) throws SQLException, ClassNotFoundException{
		
		TipoPessoaDAO TipoPessoaDAO = new TipoPessoaDAO();
		boolean completo = false;
		
		try {
			
			completo = TipoPessoaDAO.DeleteTipoPessoa(id);
			
		}catch(SQLException e) {
			throw e;
		}
		return completo;
	}
	
}
