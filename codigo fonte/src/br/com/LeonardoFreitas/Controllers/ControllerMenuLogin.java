package br.com.LeonardoFreitas.Controllers;

import java.sql.SQLException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import br.com.LeonardoFreitas.Modelo.Usuario;

@SuppressWarnings("serial")
public class ControllerMenuLogin extends SelectorComposer<Component>{
	
	@Wire
	Textbox matricula;
	@Wire
	Textbox senha;
	
	@Listen("onClick=#Loginsubmit")
	public void Validar() throws WrongValueException, ClassNotFoundException {
		ControllerUsuario controller = new ControllerUsuario();
		try {
			
			Usuario usuario = controller.getByMatricula(matricula.getValue());
			
			
			
			if(usuario.getSenha().equals(senha.getValue())) {
				
				//Armazenar permissao ao Session Storage
				Sessions.getCurrent().setAttribute("permissao", usuario.getPermissoes());
				
				//Armazenar id ao Session Storage
				Sessions.getCurrent().setAttribute("id", usuario.getId_usuario());
				
				//Armazenar tipo ao Session Storage
				Sessions.getCurrent().setAttribute("tipo", usuario.getTipo_pessoa());
				
				//Redirecionar para pagina principal
				Executions.sendRedirect("http://localhost:8080/ProjetoFinal/home.zul");
			}else {
			Messagebox.show("Senha incorreta!");
			}
		}catch(SQLException e) {
			Messagebox.show("Este usuario nao exite!");
		}catch(NullPointerException e) {
			Messagebox.show("Matricula incorreta!");
		}
		
	}
	
}
