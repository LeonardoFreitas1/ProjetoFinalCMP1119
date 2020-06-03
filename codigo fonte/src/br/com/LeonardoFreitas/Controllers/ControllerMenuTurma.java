package br.com.LeonardoFreitas.Controllers;

import java.sql.SQLException;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import br.com.LeonardoFreitas.Modelo.Disciplina;

@SuppressWarnings("serial")
public class ControllerMenuTurma extends SelectorComposer<Component>{
	
	@Wire
	Textbox CadastroNome, PesquisaParametros, CadastroId;
	@Wire
	Combobox CodigoDisciplinas;
	@Wire
	Listbox lsbPesqSecao;
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        IniciarCombobox();
    }
	
	@Listen("onClick=#CadastroSubmit")
	public void adicionarTurma() throws WrongValueException, ClassNotFoundException {
		
		ControllerTurma controller = new ControllerTurma();
		
		
		try {
			
			int id_disciplina = Integer.parseInt(CodigoDisciplinas.getSelectedItem().getId());
			String nome = CadastroNome.getText();
			
			boolean resp = controller.create(nome, id_disciplina);
			
			if(resp)
				Messagebox.show("Turma Adicionada!");
			else
				Messagebox.show("Turma não adicionada!");
			
			this.LimparDadosForm();
		
		}catch(SQLException e) {
			Messagebox.show(String.valueOf(e));
		}
		
	}
	@Listen("onClick=#CadastroAlterar")
	public void ExcluirUsuario() throws ClassNotFoundException {
		
		ControllerTurma controller = new ControllerTurma();
		
		int id = Integer.parseInt(CadastroId.getText());
		String nome = CadastroNome.getText();
		int codigo = CodigoDisciplinas.getSelectedIndex();
		
		
		try {
			boolean result = controller.update(id, nome, 2);
			
			if(result)
				Messagebox.show("Turma alterada com sucesso!");
			else
				Messagebox.show("Não foi possivel alterar a turma!");
			
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
				Messagebox.show("Não foi possivel deletar a disciplina!");
			
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
		String auxCodigo = String.valueOf(indice.getChildren().get(2).getAttribute("codigo"));
		
		int codigo = Integer.parseInt(auxCodigo) +1;
		
		CadastroId.setText(id);
		CadastroNome.setText(nome);
		CodigoDisciplinas.setSelectedIndex(codigo);;
	}
	private void LimparDadosForm() {
		CadastroNome.setValue("");
	}
	
	private void LimparDadosLista() {
		PesquisaParametros.setValue("");
	}
	
	private void IniciarCombobox() throws WrongValueException, ClassNotFoundException {
		
		ControllerDisciplina controller = new ControllerDisciplina();
		
		try {
			
			List<Disciplina> lista = controller.getAll();
			
			for(Disciplina disciplina: lista) {
				
				Comboitem comboitem = new Comboitem();
				
				comboitem.setLabel(disciplina.getNome_disciplina());
				comboitem.setId(String.valueOf(disciplina.getId_disciplina()));
				
				CodigoDisciplinas.appendChild(comboitem);
				
			}
			
			CodigoDisciplinas.setText(lista.get(0).getNome_disciplina());
			
		}catch(SQLException e) {
			Messagebox.show(String.valueOf(e));

		}
		
	}
	
	
	
}
