package br.com.LeonardoFreitas.Controllers;

import java.sql.SQLException;
import java.util.List;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import br.com.LeonardoFreitas.Modelo.Disciplina;

@SuppressWarnings("serial")
public class ControllerMenuDisciplina extends SelectorComposer<Component>{
	
	@Wire
	Textbox PesquisaParametros, CadastroNome, CadastroCodigo, CadastroId;
	@Wire
	Listbox lsbPesqSecao;
	
	@Listen("onClick=#CadastroSubmit")
	public void adicionarDisciplina() throws WrongValueException, ClassNotFoundException {
		
		ControllerDisciplina controller = new ControllerDisciplina();
		
		try {
			
			
			boolean resp = controller.create(CadastroNome.getValue(), CadastroCodigo.getValue());
			
			if(resp)
				Messagebox.show("Disciplina Adicionada!");
			else
				Messagebox.show("Disciplina n�o adicionada!");
			
			this.LimparDadosForm();
		
		}catch(SQLException e) {
			Messagebox.show(String.valueOf(e));
		}
		
	}
	
	@Listen("onClick=#PesquisaSubmit")
	public void ListarUsuarios() throws ClassNotFoundException {
		
		lsbPesqSecao.getItems().clear();
		
		String nomeDisiciplina = PesquisaParametros.getText();

		ControllerDisciplina controller = new ControllerDisciplina();
		
		try {
			
			List<Disciplina> lista = controller.getByName(nomeDisiciplina);
			
			for(Disciplina disciplina: lista) {
				
			Listcell id = new Listcell();
			Listcell nome = new Listcell();
			Listcell codigo = new Listcell();
			
			id.setAttribute("id", String.valueOf(disciplina.getId_disciplina()));
			id.setLabel(String.valueOf(disciplina.getId_disciplina()));
			
			nome.setAttribute("nome", String.valueOf(disciplina.getNome_disciplina()));
			nome.setLabel(String.valueOf(disciplina.getNome_disciplina()));
			
			codigo.setAttribute("codigo", String.valueOf(disciplina.getCodigo_disciplina()));
			codigo.setLabel(String.valueOf(disciplina.getCodigo_disciplina()));
			
			Listitem listitem = new Listitem();
			
			listitem.appendChild(id);
			listitem.appendChild(nome);
			listitem.appendChild(codigo);
			
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
		
		ControllerDisciplina controller = new ControllerDisciplina();
		
		int id = Integer.parseInt(CadastroId.getText());
		String nome = CadastroNome.getText();
		String codigo = CadastroCodigo.getText();
		
		
		try {
			boolean result = controller.update(id, nome, codigo);
			
			if(result)
				Messagebox.show("Disciplina alterada com sucesso!");
			else
				Messagebox.show("N�o foi possivel alterar a Disciplina!");
			
			this.LimparDadosForm();
			this.LimparDadosLista();
			
		}catch(SQLException e) {
			Messagebox.show(String.valueOf(e));
		}
		
	}
	@Listen("onClick=#CadastroExcluir")
	public void LimparFormulario() throws ClassNotFoundException {
		
		ControllerDisciplina controller = new ControllerDisciplina();
		
		int id = Integer.parseInt(CadastroId.getText());
		
		try {
			
		boolean result = controller.delete(id);
			
			if(result)
				Messagebox.show("Disciplina Deletada com sucesso!");
			else
				Messagebox.show("N�o foi possivel deletar a disciplina!");
			
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
	
	@Listen("onClick=#PesquisaCarregar")
	public void CarregarDados() {
		
		Listitem indice = lsbPesqSecao.getSelectedItem();
		
		String id = String.valueOf(indice.getChildren().get(0).getAttribute("id"));
		String nome = String.valueOf(indice.getChildren().get(1).getAttribute("nome"));
		String codigo = String.valueOf(indice.getChildren().get(2).getAttribute("codigo"));
		
		CadastroId.setText(id);
		CadastroNome.setText(nome);
		CadastroCodigo.setText(codigo);
	}
	private void LimparDadosForm() {
		CadastroId.setValue("");
		CadastroCodigo.setValue("");
		CadastroNome.setValue("");
	}
	
	private void LimparDadosLista() {
		PesquisaParametros.setValue("");
	}
	
}