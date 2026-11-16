package model;

/**
 *
 * @author wilky
 */
public class Inscricao {
    
    private int id;
    private Feira feira;
    private Feirante feirante;
    private String statusAprovacao;
    private int colunaBanca;
    private int linhaBanca;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Feira getFeira() {
        return feira;
    }

    public void setFeira(Feira feira) {
        this.feira = feira;
    }

    public Feirante getFeirante() {
        return feirante;
    }

    public void setFeirante(Feirante feirante) {
        this.feirante = feirante;
    }

    public String getStatusAprovacao() {
        return statusAprovacao;
    }

    public void setStatusAprovacao(String statusAprovacao) {
        this.statusAprovacao = statusAprovacao;
    }

    public int getColunaBanca() {
        return colunaBanca;
    }

    public void setColunaBanca(int colunaBanca) {
        this.colunaBanca = colunaBanca;
    }

    public int getLinhaBanca() {
        return linhaBanca;
    }

    public void setLinhaBanca(int linhaBanca) {
        this.linhaBanca = linhaBanca;
    }
    
    
    
    public Inscricao(){
        
    }

    public Inscricao(int id, Feira feira, Feirante feirante, String statusAprovacao, int linhaBanca, int colunaBanca) {
        this.id = id;
        this.feira = feira;
        this.feirante = feirante;
        this.statusAprovacao = statusAprovacao;
        this.linhaBanca = linhaBanca;
        this.colunaBanca = colunaBanca;
    }
    
}
