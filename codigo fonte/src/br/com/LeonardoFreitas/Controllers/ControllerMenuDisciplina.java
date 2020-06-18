 	package br.com.LeonardoFreitas.Controllers;

import java.sql.SQLException;
import java.util.List;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
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
		
		try {
			int tipo_usuario = (int) Sessions.getCurrent().getAttribute("tipo");
			int permissao_usuario = (int) Sessions.getCurrent().getAttribute("permissao");
			
			if(tipo_usuario == 1 && permissao_usuario == 1) {
		
				ControllerDisciplina controller = new ControllerDisciplina();
				
				try {
					
					
					boolean resp = controller.create(CadastroNome.getValue(), CadastroCodigo.getValue());
					
					if(resp)
						Messagebox.show("Disciplina Adicionada!");
					else
						Messagebox.show("Disciplina não adicionada!");
					
					this.LimparDadosForm();
				
				}catch(SQLException e) {
					Messagebox.show(String.valueOf(e));
				}
			}else {
				Messagebox.show("Voce não tem permissao para adicionar uma disciplina!");
			}
		}catch(NullPointerException e) {
			Executions.sendRedirect("http://localhost:8080/ProjetoFinal/index.zul");
		}
		
	}
	
	@Listen("onClick=#PesquisaSubmit")
	public void ListarUsuarios() throws ClassNotFoundException {
		
		try {
			int tipo_usuario = (int) Sessions.getCurrent().getAttribute("tipo");
			
			if(tipo_usuario == 1) {
		
				lsbPesqSecao.getItems().clear();
				
				String nomeDisiciplina = PesquisaParametros.getText();
		
				ControllerDisciplina controller = new ControllerDisciplina();
				
				try {
					
					List<Disciplina> lista = controller.getListByName(nomeDisiciplina);
					
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
			}else {
				Messagebox.show("Você não tem permissão para realizar esta pesquisa");
			}
		}catch(NullPointerException e) {
			Executions.sendRedirect("http://localhost:8080/ProjetoFinal/index.zul");
		}
	}
	@Listen("onClick=#PesquisaLimpar")
	public void AlterarUsuario() throws ClassNotFoundException {
		
		lsbPesqSecao.getItems().clear();
		
	}
	@Listen("onClick=#CadastroAlterar")
	public void ExcluirUsuario() throws ClassNotFoundException {
		
		try {
			int tipo_usuario = (int) Sessions.getCurrent().getAttribute("tipo");
			int permissao_usuario = (int) Sessions.getCurrent().getAttribute("permissao");
			
			if(tipo_usuario == 1 && permissao_usuario == 1) {
		
					ControllerDisciplina controller = new ControllerDisciplina();
					
					int id = Integer.parseInt(CadastroId.getText());
					String nome = CadastroNome.getText();
					String codigo = CadastroCodigo.getText();
					
					
					try {
						boolean result = controller.update(id, nome, codigo);
						
						if(result)
							Messagebox.show("Disciplina alterada com sucesso!");
						else
							Messagebox.show("Não foi possivel alterar a Disciplina!");
						
						this.LimparDadosForm();
						this.LimparDadosLista();
						
					}catch(SQLException e) {
						Messagebox.show(String.valueOf(e));
					}
			}else {
				Messagebox.show("Você não tem permissão para alterar este registro!");
			}
			}catch(NullPointerException e) {
				Executions.sendRedirect("http://localhost:8080/ProjetoFinal/index.zul");
			}
		
	}
	
	@Listen("onClick=#CadastroExcluir")
	public void LimparFormulario() throws ClassNotFoundException {
		
		try {
			int tipo_usuario = (int) Sessions.getCurrent().getAttribute("tipo");
			int permissao_usuario = (int) Sessions.getCurrent().getAttribute("permissao");
			
			if(tipo_usuario == 1 && permissao_usuario == 1) {
					
					ControllerDisciplina controller = new ControllerDisciplina();
					
					int id = Integer.parseInt(CadastroId.getText());
					
					try {
						
					boolean result = controller.delete(id);
						
						if(result)
							Messagebox.show("Disciplina Deletada com sucesso!");
						else
							Messagebox.show("Não foi possivel deletar a disciplina!");
						
						this.LimparDadosForm();
						this.LimparDadosLista();
					}catch(SQLException e) {
						Messagebox.show("Você deve excluir as turmas dependentes dessa disciplina antes!");
					}
			}else {
				Messagebox.show("Você não tem permissão para deletar este registro");
			}
		}catch(NullPointerException e) {
			Executions.sendRedirect("http://localhost:8080/ProjetoFinal/index.zul");
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
