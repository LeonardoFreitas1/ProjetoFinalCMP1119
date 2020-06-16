package br.com.LeonardoFreitas.Controllers;
 
import java.util.LinkedHashMap;
import java.util.Map;
 
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;

import br.com.LeonardoFreitas.Modelo.Pagina;
 
public class ControllerMenuBar {
    Pagina paginaAtual; 
    private Map<String, Map<String, Pagina>> pageMap;
     
    //Carrega pagina padrao
    @Init
    public void init() {
        initPageMap();
        paginaAtual = pageMap.get("Usuarios").get("Aluno");
    }
    
    //Carrega pagina solicitada pelo usuario
    @Command
    public void navigatePage(@BindingParam("target") Pagina novaPagina) {
        BindUtils.postNotifyChange(null, null, paginaAtual, "selected");
        paginaAtual = novaPagina;
        BindUtils.postNotifyChange(null, null, this, "paginaAtual");
    }
    
    //Retorna pagina atual
    public Pagina getpaginaAtual() {
        return paginaAtual;
    }
    
    //Retorna mapa de paginas
    public Map<String, Map<String, Pagina>> getPageMap() {
        return pageMap;
    }
    
    //Adiciona Menus
    private void initPageMap() {
        pageMap = new LinkedHashMap<String, Map<String, Pagina>>();
         
        addMenu("Usuarios", "Aluno", "Aluno.zul");
        addMenu("Usuarios", "Professor", "Professor.zul");
        addMenu("Academico", "Disciplina", "Disciplina.zul");
        addMenu("Academico", "Turmas", "Turma.zul");
        addMenu("Academico", "Frequencias", "Frequencia.zul");
        addMenu("Relatorios", "Relatorio simples", "RelatorioSimples.zul");
        addMenu("Relatorios", "Relatorio complexo", "RelatorioComplexo.zul");
        
    }
    //Adicionar Menu sem valor imbutido
    private void addMenu(String titulo, String subTitulo, String url) {
    	addMenu(titulo, subTitulo, url, null);
    }

    //Adicionar Menu
    private void addMenu(String titulo, String subTitulo, String url, String data) {
        String folder = "./";
        Map<String, Pagina> subPageMap = pageMap.get(titulo);
        if(subPageMap == null) {
            subPageMap = new LinkedHashMap<String, Pagina>();
            pageMap.put(titulo, subPageMap);
        }
        Pagina navigationPage = new Pagina(titulo, subTitulo,
                folder + url + "?random=" + Math.random(), data) {
            @Override
            public boolean isSelected() {
                return paginaAtual == this;
            }
        };
        subPageMap.put(subTitulo, navigationPage);
    }
}