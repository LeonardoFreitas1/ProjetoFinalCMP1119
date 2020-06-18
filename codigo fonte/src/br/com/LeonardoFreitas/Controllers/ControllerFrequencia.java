package br.com.LeonardoFreitas.Controllers;

import java.sql.SQLException;
import java.util.List;

import br.com.LeonardoFreitas.DAO.FrequenciaDAO;
import br.com.LeonardoFreitas.Modelo.Frequencia;


/*
 * Classe Controller Frequencias
 * 
 * Esta classe será responsável pelas solicitações e validações
 * entre a aplicação e a classe FrequenciaDAO 
 * 
 * */

public class ControllerFrequencia {
		
		public List<Frequencia> getAll() throws SQLException, ClassNotFoundException{
			
			FrequenciaDAO dao = new FrequenciaDAO();
			List<Frequencia> lista = null;
			try {
				
				 lista = dao.getAllFrequencia();
				
			}catch(SQLException e) {
				throw e;
			}
			return lista;
		}
		
		public boolean create(int usuario, int turma,String data, boolean frequente, int usuario_turma) throws SQLException, ClassNotFoundException{
			
			FrequenciaDAO dao = new FrequenciaDAO();
			boolean completo = false;
			
			try {
				
				completo = dao.CreateFrequencia(usuario, turma, data, frequente, usuario_turma);
				
			}catch(SQLException e) {
				throw e;
			}
			return completo;
		}
		
		public Frequencia getById(int id) throws SQLException, ClassNotFoundException {
			
			FrequenciaDAO dao = new FrequenciaDAO();
			Frequencia frequencia = null;
			
			try {
				
				frequencia = dao.getFrequenciaId(id);
				
			}catch(SQLException e) {
				throw e;
			}
			
			return frequencia;
			
		}
		public Frequencia getByIdAndDate(int id, int id_usuario, String data) throws SQLException, ClassNotFoundException {
			
			FrequenciaDAO dao = new FrequenciaDAO();
			Frequencia frequencia = null;
			
			try {
				
				frequencia = dao.getFrequenciaIdData(id,id_usuario, data);
				
			}catch(SQLException e) {
				throw e;
			}
			
			return frequencia;
			
		}
		
		public boolean update(int id, int usuario, int turma,String data, boolean frequente, int usuario_turma) throws SQLException, ClassNotFoundException{
			
			FrequenciaDAO dao = new FrequenciaDAO();
			boolean completo = false;
			
			try {

				completo = dao.UpdateFrequencia(id, usuario, turma, data, frequente, usuario_turma);

			}catch(SQLException e) {
				throw e;
			}
			return completo;
		}
		
		public boolean updateFrequente(int id, boolean frequente) throws SQLException, ClassNotFoundException{
			
			FrequenciaDAO dao = new FrequenciaDAO();
			boolean completo = false;
			
			try {

				completo = dao.UpdateOnlyFrequencia(id, frequente);

			}catch(SQLException e) {
				throw e;
			}
			return completo;
		}
		

		public boolean delete(int id) throws SQLException, ClassNotFoundException{
			
			FrequenciaDAO dao = new FrequenciaDAO();
			boolean completo = false;
			
			try {
				
				completo = dao.DeleteFrequencia(id);
				
			}catch(SQLException e) {
				throw e;
			}
			return completo;
		
	}
	
		public boolean deleteUserFrequency(int id_usuario) throws SQLException, ClassNotFoundException{
			
			FrequenciaDAO dao = new FrequenciaDAO();
			boolean completo = false;
			
			try {
				
				completo = dao.DeleteFrequenciaUsuario(id_usuario);
				
			}catch(SQLException e) {
				throw e;
			}
			return completo;
		
	}
}
