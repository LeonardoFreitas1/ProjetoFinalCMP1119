package br.com.LeonardoFreitas.Modelo;
 
public abstract class Pagina {
    private String titulo;
    private String url;
    private String subTitulo;
    private Object data;
     
    public Pagina(String titulo, String subTitulo, String url, Object data) {
        super();
        this.titulo = titulo;
        this.subTitulo = subTitulo;
        this.url = url;
        this.data = data;
    } 
 
    public abstract boolean isSelected();
 
    public String getTitulo() {
        return titulo;
    }
    public String getSubTitulo() { 
        return subTitulo;
    }
    public String getUrl() {
        return url;
    }
    public Object getData() {
        return data;
    }
}