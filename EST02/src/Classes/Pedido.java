/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author mello
 */
public class Pedido {
    private int codPedido;
    private double valor;
    private String cliente;
    private String vendedor;
    private ArrayList<Produto> produtos = new ArrayList<>();

    public Pedido(){}
    
    public Pedido(int codPedido, double valor, String cliente, String vendedor, Produto produto) {
        this.codPedido = codPedido;
        this.valor = valor;
        this.cliente = cliente;
        this.vendedor = vendedor;
        produtos.add(produto);
    }
    
    public Pedido(double valor, String cliente, String vendedor, Produto produto) {
        this.codPedido = new Random().nextInt();
        this.valor = valor;
        this.cliente = cliente;
        this.vendedor = vendedor;
        produtos.add(produto);
    }

    public int getCodPedido() {
        return codPedido;
    }
    
    public void setNewCodPedido(){
        this.codPedido = new Random().nextInt();
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void addProduto(Produto produto){
        produtos.add(produto);
    }
    
    public int getProdutoCount(){
        return produtos.size();
    }
    
}