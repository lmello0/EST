/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author mello
 */
public class Produto {
    private int codigo;
    private String nome;
    private int quantidade;
    private double valor;
    private String descricao;
    
    public Produto(){}
    
    public Produto(int codigo, String nome, int quantidade, double valor, String descricao){
        this.codigo = codigo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
        this.descricao = descricao;
    }
    
    public Produto(String nome, int quantidade, double valor, String descricao){
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
        this.descricao = descricao;
    }

    public void setCodigo(int codigo){
        this.codigo = codigo;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }
    
    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }
    
    public double getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }
    
}
