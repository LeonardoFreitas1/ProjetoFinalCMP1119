package br.com.LeonardoFreitas.Controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.ClickEvent;
import org.zkoss.zul.Textbox;
import br.com.LeonardoFreitas.Modelo.Disciplina;
import br.com.LeonardoFreitas.Modelo.Turma;
import br.com.LeonardoFreitas.Modelo.Usuario;

@SuppressWarnings("serial")
public class ControllerMenuTurma extends SelectorComposer<Component>{
	
	@Wire
	Textbox CadastroNome, PesquisaParametros, CadastroId, CadastroIdUsuario, CadastroIdTurma, CadastroIdUsuarioTurma;
	@Wire
	Combobox CodigoDisciplinas, PesquisaTipo, ListaAluno, ListaProfessor;
	@Wire
	Listbox lsbPesqSecao;
	
	private ListModelList<Usuario> listaDireita;
	private ListModelList<Usuario> listaEsquerda;
	
	public ControllerMenuTurma() throws ClassNotFoundException, SQLException{
		
		this.listaDireita = criarListaDireita(0);
		this.listaEsquerda = criarListaEsquerda(0);
	}
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);

        IniciarComboboxDisciplina();
    	
    }
	
	@Listen("onClick=#PesquisaSubmit")
	public void ListarTurmas() throws ClassNotFoundException {
		
		lsbPesqSecao.getItems().clear();
		
		int selecao = PesquisaTipo.getSelectedIndex();
		ControllerTurma controller = new ControllerTurma();
		
		switch(selecao) {
		
		case 0:
			String codigoTurma = PesquisaParametros.getText();
			
			
			try {
				
				List<Turma> lista = controller.getByCode(codigoTurma);
				
				
				for(Turma turma: lista) {
					
					if(turma.getUsuarios().size() > 0) {
					
						for(Usuario usuario: turma.getUsuarios()) {
							
							Listitem listitem = new Listitem();
							
							Listcell id = new Listcell();
							Listcell codigo = new Listcell();
							Listcell cellUsuario = new Listcell();
							Listcell TipoUsuario = new Listcell();
							
							id.setAttribute("id", String.valueOf(turma.getId_turma()));
							id.setLabel(String.valueOf(turma.getId_turma()));
							
							codigo.setAttribute("codigo", String.valueOf(turma.getNome_turma()));
							codigo.setLabel(String.valueOf(turma.getNome_turma()));
			
							cellUsuario.setAttribute("usuario", String.valueOf(usuario.getId_usuario()));
							cellUsuario.setLabel(String.valueOf(usuario.getNome()));
							
							switch(usuario.getTipo_pessoa()) {
							
							case 1:
								TipoUsuario.setLabel("Professor");
								TipoUsuario.setAttribute("TipoUsuario", 1);
								break;
							case 2:
								TipoUsuario.setLabel("Aluno");
								TipoUsuario.setAttribute("TipoUsuario", 2);
								break;
								
							}
								
							listitem.appendChild(id);
							listitem.appendChild(codigo);
							listitem.appendChild(cellUsuario);
							listitem.appendChild(TipoUsuario);
							
							lsbPesqSecao.appendChild(listitem);
							
						}
					
					}else {
						
						Listitem listitem = new Listitem();
						
						Listcell id = new Listcell();
						Listcell codigo = new Listcell();
						Listcell cellUsuario = new Listcell();
						Listcell TipoUsuario = new Listcell();
						
						id.setAttribute("id", String.valueOf(turma.getId_turma()));
						id.setLabel(String.valueOf(turma.getId_turma()));
						
						codigo.setAttribute("codigo", String.valueOf(turma.getNome_turma()));
						codigo.setLabel(String.valueOf(turma.getNome_turma()));
		
						cellUsuario.setAttribute("usuario", String.valueOf("SEM USUARIO"));
						cellUsuario.setLabel(String.valueOf("SEM USUARIO"));
						TipoUsuario.setLabel("SEM USUARIO");

						listitem.appendChild(id);
						listitem.appendChild(codigo);
						listitem.appendChild(cellUsuario);
						listitem.appendChild(TipoUsuario);
						
						lsbPesqSecao.appendChild(listitem);
						
					}
				}
				LimparDadosLista();
				
			}catch(SQLException e) {
				Messagebox.show(String.valueOf(e));
			}
			
			break;
		case 1:
			
			String nomeAluno = PesquisaParametros.getText();
			
			try {
				
				List<Turma> lista = controller.getByUser(nomeAluno);
				
					for(Turma turma: lista) {
					
					for(Usuario usuario: turma.getUsuarios()) {
						
						Listitem listitem = new Listitem();
						
						Listcell id = new Listcell();
						Listcell codigo = new Listcell();
						Listcell cellUsuario = new Listcell();
						Listcell TipoUsuario = new Listcell();
						
						id.setAttribute("id", String.valueOf(turma.getId_turma()));
						id.setLabel(String.valueOf(turma.getId_turma()));
						
						codigo.setAttribute("codigo", String.valueOf(turma.getNome_turma()));
						codigo.setLabel(String.valueOf(turma.getNome_turma()));
		
						cellUsuario.setAttribute("usuario", String.valueOf(usuario.getId_usuario()));
						cellUsuario.setLabel(String.valueOf(usuario.getNome()));
						
						switch(usuario.getTipo_pessoa()) {
						
						case 1:
							TipoUsuario.setLabel("Professor");
							TipoUsuario.setAttribute("TipoUsuario", 1);
							break;
						case 2:
							TipoUsuario.setLabel("Aluno");
							TipoUsuario.setAttribute("TipoUsuario", 2);
							break;
							
						}
							
						listitem.appendChild(id);
						listitem.appendChild(codigo);
						listitem.appendChild(cellUsuario);
						listitem.appendChild(TipoUsuario);
						
						lsbPesqSecao.appendChild(listitem);
						
					}
					 
				}
				LimparDadosLista();
				
			}catch(SQLException e) {
				Messagebox.show(String.valueOf(e));
			}
			
			break;
		case 2:
			
			String nomeDisciplina = PesquisaParametros.getText();
			
				try {
				
				List<Turma> lista = controller.getByDisciplina(nomeDisciplina);

				for(Turma turma: lista) {
					
					for(Usuario usuario: turma.getUsuarios()) {
						
						Listitem listitem = new Listitem();
						
						Listcell id = new Listcell();
						Listcell codigo = new Listcell();
						Listcell cellUsuario = new Listcell();
						Listcell TipoUsuario = new Listcell();
						
						id.setAttribute("id", String.valueOf(turma.getId_turma()));
						id.setLabel(String.valueOf(turma.getId_turma()));
						
						codigo.setAttribute("codigo", String.valueOf(turma.getNome_turma()));
						codigo.setLabel(String.valueOf(turma.getNome_turma()));
		
						cellUsuario.setAttribute("usuario", String.valueOf(usuario.getId_usuario()));
						cellUsuario.setLabel(String.valueOf(usuario.getNome()));
						
						switch(usuario.getTipo_pessoa()) {
							
						case 1:
							TipoUsuario.setLabel("Professor");
							TipoUsuario.setAttribute("TipoUsuario", 1);
							break;
						case 2:
							TipoUsuario.setLabel("Aluno");
							TipoUsuario.setAttribute("TipoUsuario", 2);
							break;
							
						}
							
						listitem.appendChild(id);
						listitem.appendChild(codigo);
						listitem.appendChild(cellUsuario);
						listitem.appendChild(TipoUsuario);
						
						lsbPesqSecao.appendChild(listitem);
						
					}
					
				
				}
				LimparDadosLista();
				
			}catch(SQLException e) {
				Messagebox.show(String.valueOf(e));
			}
			
			break;
		}		
			
	}
	
	@Listen("onClick=#CadastroSubmit")
	public void adicionarTurma() throws WrongValueException, ClassNotFoundException {
		
		ControllerTurma controller = new ControllerTurma();
		
		try {
			
			int id_disciplina = Integer.parseInt(CodigoDisciplinas.getSelectedItem().getValue());
			
			List<Integer> lista = new ArrayList<>();
			lista.add(Integer.parseInt(ListaAluno.getSelectedItem().getValue()));
			lista.add(Integer.parseInt(ListaProfessor.getSelectedItem().getValue()));
			
			int id_turma = Integer.parseInt(CadastroId.getText());
			String nome = CadastroNome.getText();
			
			boolean resp = controller.create(id_turma, nome, id_disciplina, lista);
			
			if(resp)
				Messagebox.show("Turma Adicionada!");
			else
				Messagebox.show("Turma n�o adicionada!");
			
			this.LimparDadosForm();
		
		}catch(SQLException e) {
			Messagebox.show(String.valueOf(e));
		}
		
	}
	
	
	@Listen("onClick=#CadastroAlterar")
	public void AlterarTurma() throws ClassNotFoundException {
		
		ControllerTurma controller = new ControllerTurma();
		
		int id = Integer.parseInt(CadastroId.getText());
		String nome = CadastroNome.getText();

		
		try {
			boolean result = controller.update(id, nome, 2);
			
			if(result)
				Messagebox.show("Turma alterada com sucesso!");
			else
				Messagebox.show("N�o foi possivel alterar a turma!");
			
			this.LimparDadosForm();
			this.LimparDadosLista();
			
		}catch(SQLException e) {
			Messagebox.show(String.valueOf(e));
		}
		
	}
	@Listen("onClick=#CadastroExcluir")
	public void LimparFormulario() throws ClassNotFoundException {
		
		ControllerTurma controller = new ControllerTurma();
		
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
	
	
	@Listen("onClick=#PesquisaLimpar")
	public void LimparLista() throws ClassNotFoundException {
		lsbPesqSecao.getItems().clear();
		
	} 
	
	@Listen("onClick=#lsbPesqSecao")
	public void CarregarDados() throws ClassNotFoundException, SQLException {
		
		Listitem indice = lsbPesqSecao.getSelectedItem();
		
		String id = String.valueOf(indice.getChildren().get(0).getAttribute("id"));
		String codigo = String.valueOf(indice.getChildren().get(1).getAttribute("codigo"));
		
		CadastroId.setText(id);
		CadastroNome.setText(codigo);

		Sessions.getCurrent().setAttribute("turma", id);

		
	}
	
	@Listen("onClick=#CadastroIdSubmit")
	public void adicionarUsuarioTurma() throws WrongValueException, ClassNotFoundException {
		
		ControllerUsuarioTurma controller = new ControllerUsuarioTurma();
		
		
		try {
			
			
			int id_usuario = Integer.parseInt(CadastroIdUsuario.getText());
			int id_turma = Integer.parseInt(CadastroIdTurma.getText());
			
			boolean resp = controller.create(id_usuario, id_turma);
			
			if(resp)
				Messagebox.show("Usuario adicionado na Turma!");
			else
				Messagebox.show("Usuario n�o adicionado na Turma!");
			
			this.LimparDadosForm();
		
		}catch(SQLException e) {
			Messagebox.show(String.valueOf(e));
		}
		
	}
	@Listen("onClick=#CadastroIdAlterar")
	public void alterarUsuarioTurma() throws WrongValueException, ClassNotFoundException {
		
		ControllerUsuarioTurma controller = new ControllerUsuarioTurma();
		
		
		try {
			
			int id_usuario_turmas = Integer.parseInt(CadastroIdUsuarioTurma.getText());
			int id_usuario = Integer.parseInt(CadastroIdUsuario.getText());
			int id_turma = Integer.parseInt(CadastroIdTurma.getText());
			
			boolean resp = controller.update(id_usuario, id_turma, id_usuario_turmas);
			
			if(resp)
				Messagebox.show("Turma Alterada!");
			else
				Messagebox.show("N�o Alterado!");
			
			this.LimparDadosForm();
		
		}catch(SQLException e) {
			Messagebox.show(String.valueOf(e));
		}
		
	}
	@Listen("onClick=#CadastroIdExcluir")
	public void excluirUsuarioTurma() throws WrongValueException, ClassNotFoundException {
		
		ControllerUsuarioTurma controller = new ControllerUsuarioTurma();
			
		try {
			
			
			int id_usuario_turma = Integer.parseInt(CadastroIdUsuarioTurma.getText());
			
			boolean resp = controller.delete(id_usuario_turma);
			
			if(resp)
				Messagebox.show("Usuario deletado em Turma!");
			else
				Messagebox.show("Usuario n�o deletado em Turma!");
			
			this.LimparDadosForm();
		
		}catch(SQLException e) {
			Messagebox.show(String.valueOf(e));
		}
		
	}
	@Listen("onClick=#CadastroLimpar")
	public void limparTurma() throws WrongValueException, ClassNotFoundException {		
		
		CadastroNome.setText("");
		CadastroId.setText("");
		
	}
	
	private void LimparDadosForm() {
		CadastroNome.setValue("");
	}
	
	private void LimparDadosLista() {
		PesquisaParametros.setValue("");
	}
	
	private void IniciarComboboxDisciplina() throws WrongValueException, ClassNotFoundException {
		
		ControllerDisciplina controller = new ControllerDisciplina();
		
		try {
			
			List<Disciplina> lista = controller.getAll();
			
			for(Disciplina disciplina: lista) {
				
				Comboitem comboitem = new Comboitem();
				
				comboitem.setLabel(disciplina.getNome_disciplina());
				comboitem.setValue(String.valueOf(disciplina.getId_disciplina()));
				
				CodigoDisciplinas.appendChild(comboitem);
				
			}
			
			CodigoDisciplinas.setText(lista.get(0).getNome_disciplina());
			
		}catch(SQLException e) {
			Messagebox.show(String.valueOf(e));

		}
		
	}
	 	
	 	@Command
	    public void adicionarUsuarios() throws ClassNotFoundException {
		 
	 		 ControllerUsuarioTurma controller = new ControllerUsuarioTurma();

			 try {
				 
				 ListModelList<Usuario> listaEsquerda = getListaEsquerda();
				 ListModelList<Usuario> listaDireita = getListaDireita();
				 int id_turma = Integer.parseInt((String) Sessions.getCurrent().getAttribute("turma"));
				 Set<Usuario> usuarioSelecionado = listaEsquerda.getSelection();
				 
				 for(Iterator<Usuario> it = usuarioSelecionado.iterator(); it.hasNext();) {
					 
					 Usuario usuario = it.next();
					 
					 controller.create(usuario.getId_usuario(), id_turma);
					 
				 }
				 
				 mover(listaEsquerda,listaDireita, "Por favor selecione um registro para adicionar");
				 
			 }catch(SQLException e) {
				 
				 Messagebox.show(String.valueOf(e));
				 
			 }
		 
	    }
	 
	    @Command
	    public void RemoverUsuarios() throws ClassNotFoundException {

	    	 ControllerUsuarioTurma controller = new ControllerUsuarioTurma();
	    	 
			 try {
				 
				 ListModelList<Usuario> listaEsquerda = getListaEsquerda();
				 ListModelList<Usuario> listaDireita = getListaDireita();
				 
				 Set<Usuario> usuarioSelecionado = listaDireita.getSelection();
				 
				 for(Iterator<Usuario> it = usuarioSelecionado.iterator(); it.hasNext();) {
					 
					 Usuario usuario = it.next();
					 
					 controller.delete(usuario.getId_usuario());
					 
				 }
				 
				 mover(listaDireita, listaEsquerda, "Por favor, selecione um aluno ou professor.");
				 
			 }catch(SQLException e) {
				 
				 EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
			            public void onEvent(ClickEvent event) throws Exception {
			                if(Messagebox.Button.YES.equals(event.getButton())) {
			                   ControllerFrequencia controller = new ControllerFrequencia();
			                   
				  				Set<Usuario> usuarioSelecionado = listaDireita.getSelection();
				  				 
				  				for(Iterator<Usuario> it = usuarioSelecionado.iterator(); it.hasNext();) {
									 
									 Usuario usuario = it.next();
									 
									 controller.deleteUserFrequency(usuario.getId_usuario());
									 
								 }
				                   
				  				RemoverUsuarios();
			                }
			            }
			        };
			        Messagebox.show("Alunos com frequencias nesta tuma, para poder remover ser� deletado todas suas frequ�ncias", "Frequ�ncias dependentes", new Messagebox.Button[]{
			                Messagebox.Button.YES, Messagebox.Button.NO }, Messagebox.QUESTION, clickListener);
			        
			        
			    }
				 
	    }
	 
	    public void mover(ListModelList<Usuario> origem, ListModelList<Usuario> destino, String MensagemFalha) {
	    	
	        Set<Usuario> selecao = origem.getSelection();
	        
	        if (selecao.isEmpty()) {
	            Clients.showNotification(MensagemFalha, "info", null, null, 2000, true);
	        } else {
	        	destino.addAll(selecao);
	            origem.removeAll(selecao);
	           
	        }
	    }
	    
	    
	    public ListModelList<Usuario> getListaEsquerda() throws ClassNotFoundException, SQLException {
	    	return listaEsquerda;
	    }
	    

	    public ListModelList<Usuario> getListaDireita() throws ClassNotFoundException {
	    	return listaDireita;
	    }
	    
	    
	    private ListModelList<Usuario> criarListaEsquerda(int id_turma) throws ClassNotFoundException, SQLException {
	    	
	    	ControllerUsuario controller = new ControllerUsuario();
    		ListModelList<Usuario> lista = new ListModelList<>();
    		List<Usuario> Todos = controller.getAll();
    		
    		
			 try { 
			
				 for(int i = Todos.size() -1; i >= 0; i--) {
					 
					 for(Usuario matriculados: controller.getUsersByTurma(id_turma)) {
						
						 if(Todos.get(i).getId_usuario() == matriculados.getId_usuario()) {
							 
							 Todos.remove(i);
							
						 }
						 
					 }

				 }
				 
				 for(Usuario naoMatriculados: Todos) {
					 
					lista.add(naoMatriculados);
						 
				 }
				 
				 return lista;
				 
			 }catch(Exception e) {

				return null;
				 
			 }
	    	
	    	
	    }
	    
	    private ListModelList<Usuario> criarListaDireita(int id_turma) throws ClassNotFoundException {
	    	
	    	ControllerUsuario controller = new ControllerUsuario();
    		ListModelList<Usuario> lista = new ListModelList<>();
    		
		 try {
			 

			 for(Usuario usuario: controller.getUsersByTurma(id_turma)) {
				 
				 lista.add(usuario);

			 }
			 
			 
			 return lista;
			 
		 }catch(Exception e) {

			return null;
			 
		 }
	    	
	    }
	    
	    @Command
	    public void load() throws ClassNotFoundException, SQLException {
	    	
	    	for(int i = this.listaDireita.size() -1; i >= 0; i--) {
	    		this.listaDireita.remove(i);
	    	}
	    	
	    	for(int i = this.listaEsquerda.size() -1; i >= 0; i--) {
	    		this.listaEsquerda.remove(i);
	    	}
	    	
	    	int id_turma = Integer.parseInt((String) Sessions.getCurrent().getAttribute("turma"));
	    	
	    	this.listaDireita.addAll(this.criarListaDireita(id_turma));
	    	this.listaEsquerda.addAll(this.criarListaEsquerda(id_turma));
			
			
			
	    }
	
}
