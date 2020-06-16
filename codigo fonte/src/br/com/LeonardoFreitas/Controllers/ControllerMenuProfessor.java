package br.com.LeonardoFreitas.Controllers;

import java.sql.SQLException;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import br.com.LeonardoFreitas.Modelo.Usuario;

@SuppressWarnings("serial")
public class ControllerMenuProfessor extends SelectorComposer<Component>{
	
	@Wire
	Textbox CadastroMatricula, PesquisaParametros, CadastroSenha, CadastroNome, CadastroId;
	@Wire
	Combobox CadastroPermissao, CadastroTipo;
	@Wire
	Listbox lsbPesqSecao;
	
	@Listen("onClick=#CadastroSubmit")
	public void adicionarUsuario() throws WrongValueException, ClassNotFoundException {
		
		ControllerUsuario controller = new ControllerUsuario();
		
		int permissao = CadastroPermissao.getSelectedItem().getIndex() + 1;
		int tipo_pessoa = 2;
		
		try {
			
			boolean resp = controller.create(CadastroNome.getValue(), CadastroSenha.getValue(), CadastroMatricula.getValue(), tipo_pessoa, permissao);
			
			if(resp)
				Messagebox.show("Usuário Adicionado!");
			else
				Messagebox.show("Usuário não adicionado!");
			
			this.LimparDadosForm();
		
		}catch(SQLException e) {
			Messagebox.show(String.valueOf(e));
		}
		
	}
	
	@Listen("onClick=#PesquisaSubmit")
	public void ListarUsuarios() throws ClassNotFoundException {
		
		lsbPesqSecao.getItems().clear();
		
		String nomeUsuario = PesquisaParametros.getText();

		ControllerUsuario controller = new ControllerUsuario();
		
		try {
			
			List<Usuario> lista = controller.getProfessorByName(nomeUsuario);
			
			for(Usuario usuario: lista) {
				
			Listcell id = new Listcell();
			Listcell nome = new Listcell();
			Listcell matricula = new Listcell();
			Listcell senha = new Listcell();
			Listcell tipo = new Listcell();
			Listcell permissao = new Listcell();
			
		
			
			id.setAttribute("id", String.valueOf(usuario.getId_usuario()));
			id.setLabel(String.valueOf(usuario.getId_usuario()));
			
			nome.setAttribute("nome", String.valueOf(usuario.getNome()));
			nome.setLabel(String.valueOf(usuario.getNome()));
			
			matricula.setAttribute("matricula", String.valueOf(usuario.getMatricula()));
			matricula.setLabel(String.valueOf(usuario.getMatricula()));
			
			senha.setAttribute("senha", String.valueOf(usuario.getSenha()));
			senha.setLabel(String.valueOf(usuario.getSenha()));
			
			permissao.setAttribute("permissao", String.valueOf(usuario.getPermissoes()));
			permissao.setLabel(String.valueOf(usuario.getPermissoes()));
			
			tipo.setAttribute("tipo", String.valueOf(usuario.getTipo_pessoa()));
			tipo.setLabel(String.valueOf(usuario.getTipo_pessoa()));
			
			Listitem listitem = new Listitem();
			
			listitem.appendChild(id);
			listitem.appendChild(nome);
			listitem.appendChild(matricula);
			listitem.appendChild(senha);
			listitem.appendChild(permissao);
			listitem.appendChild(tipo);
			
			
			lsbPesqSecao.appendChild(listitem);
			}
			LimparDadosLista();
			
		}catch(SQLException e) {
			Messagebox.show(String.valueOf(e));
		}
			
	}
	@Listen("onClick=#PesquisaLimpar")
	public void AlterarUsuario() throws ClassNotFoundException {
		
		lsbPesqSecao.getItems().clear();
		
	}
	@Listen("onClick=#CadastroAlterar")
	public void ExcluirUsuario() throws ClassNotFoundException {
		
		ControllerUsuario controller = new ControllerUsuario();
		
		int id = Integer.parseInt(CadastroId.getText());
		String nome = CadastroNome.getText();
		String matricula = CadastroMatricula.getText();
		String senha = CadastroSenha.getText();
		int tipo_pessoa = CadastroTipo.getSelectedIndex() + 1;
		int permissoes = CadastroPermissao.getSelectedIndex() + 1;
		
		
		try {
			boolean result = controller.update(id, nome, senha, matricula, tipo_pessoa, permissoes);
			
			if(result)
				Messagebox.show("Usuario alterado com sucesso!");
			else
				Messagebox.show("Não foi possivel alterar o Usuario!");
			
			this.LimparDadosForm();
			this.LimparDadosLista();
			
		}catch(SQLException e) {
			Messagebox.show(String.valueOf(e));
		}
		
	}
	@Listen("onClick=#CadastroExcluir")
	public void LimparFormulario() throws ClassNotFoundException {
		
		ControllerUsuario controller = new ControllerUsuario();
		
		int id = Integer.parseInt(CadastroId.getText());
		
		try {
		boolean result = controller.delete(id);
			
			if(result)
				Messagebox.show("Usuario Deletado com sucesso!");
			else
				Messagebox.show("Não foi possivel deletar o Usuario!");
			
			this.LimparDadosForm();
			this.LimparDadosLista();
		}catch(SQLException e) {
			Messagebox.show(String.valueOf(e));
		}
		
	}
	
	@Listen("onClick=#CadastroLimpar")
	public void LimparLista() throws ClassNotFoundException {
		
		this.LimparDadosForm();
		
	}
	
	@Listen("onClick=#lsbPesqSecao")
	public void CarregarDados() {
		
		Listitem indice = lsbPesqSecao.getSelectedItem();
		
		String id = String.valueOf(indice.getChildren().get(0).getAttribute("id"));
		String nome = String.valueOf(indice.getChildren().get(1).getAttribute("nome"));
		String matricula = String.valueOf(indice.getChildren().get(2).getAttribute("matricula"));
		String senha = String.valueOf(indice.getChildren().get(3).getAttribute("senha"));
		String auxPermissao = String.valueOf(indice.getChildren().get(4).getAttribute("permissao"));
		String auxTipo = String.valueOf(indice.getChildren().get(5).getAttribute("tipo"));
		
		int permissao = Integer.parseInt(auxPermissao) -1;
		int tipo = Integer.parseInt(auxTipo) -1;
		
		CadastroMatricula.setText(matricula);
		CadastroSenha.setText(senha);
		CadastroNome.setText(nome);
		CadastroId.setText(id);
		CadastroPermissao.setSelectedIndex(permissao);
		CadastroTipo.setSelectedIndex(tipo);
	}
	private void LimparDadosForm() {
		CadastroId.setValue("");
		CadastroMatricula.setValue("");
		CadastroSenha.setValue("");
		CadastroNome.setValue("");
	}
	
	private void LimparDadosLista() {
		PesquisaParametros.setValue("");
	}
	
}
