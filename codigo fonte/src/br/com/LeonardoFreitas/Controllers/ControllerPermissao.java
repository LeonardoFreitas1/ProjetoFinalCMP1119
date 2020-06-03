package br.com.LeonardoFreitas.Controllers;

import java.sql.SQLException;
import java.util.List;

import br.com.LeonardoFreitas.DAO.PermissaoDAO;
import br.com.LeonardoFreitas.Modelo.Permissao;

public class ControllerPermissao {
	
	public List<Permissao> getAll() throws SQLException, ClassNotFoundException{
		
		PermissaoDAO permissaoDAO = new PermissaoDAO();
		List<Permissao> lista = null;
		try {
			
			 lista = permissaoDAO.getAllPermissoes();
			
		}catch(SQLException e) {
			throw e;
		}
		return lista;
	}
	
	public boolean create(String nome_permissao) throws SQLException, ClassNotFoundException{
		
		PermissaoDAO permissaoDAO = new PermissaoDAO();
		boolean completo = false;
		
		try {
			
			completo = permissaoDAO.CreatePermissao(nome_permissao);
			
		}catch(SQLException e) {
			throw e;
		}
		return completo;
	}
	
	public boolean update(int id, String nome_permissao) throws SQLException, ClassNotFoundException{
		
		PermissaoDAO permissaoDAO = new PermissaoDAO();
		boolean completo = false;
		
		try {

			completo = permissaoDAO.UpdatePermissao(id,nome_permissao);

		}catch(SQLException e) {
			throw e;
		}
		return completo;
	}
	
	public boolean delete(int id) throws SQLException, ClassNotFoundException{
		
		PermissaoDAO permissaoDAO = new PermissaoDAO();
		boolean completo = false;
		
		try {
			
			completo = permissaoDAO.DeletePermissao(id);
			
		}catch(SQLException e) {
			throw e;
		}
		return completo;
	}
	
}
