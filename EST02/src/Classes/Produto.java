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
    private final String nome;
    private final int quantidade;
    private final double valor;
    private final String descricao;
    
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

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }
    
}
