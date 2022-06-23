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
    private int codPedido = 0;
    private int codCliente;
    private int codVendedor;
    private ArrayList<Produto> produtos = new ArrayList<>();
    private double valor;
    
    public int getCodPedido(){
        return codPedido;
    }
    
    public void setCodPedido(int codPedido){
        this.codPedido = codPedido;
    }
    
    public void setNewCodPedido() {
        this.codPedido = new Random().nextInt(Integer.SIZE - 1);
    }
    
    public int getCodCliente(){
        return codCliente;
    }
    
    public void setCodCliente(int codCliente){
        this.codCliente = codCliente;
    }
    
    public int getCodVendedor(){
        return codVendedor;
    }
    
    public void setCodVendedor(int codVendedor){
        this.codVendedor = codVendedor;
    }
    
    public double getValor(){
        return valor;
    }
    
    public void setValor(double valor){
        this.valor = valor;
    }
    
    public ArrayList<Produto> getProdutos(){
        return produtos;
    }
    
    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }
    
    public void setProduto(Produto produtoAdd){
        for (int i = 0; i < produtos.size(); i++){
            if (produtos.get(i).getCodigo() == produtoAdd.getCodigo()) {
                produtos.set(i, produtoAdd);
                break;
            }
        }
    }
    
    public void addProduto(Produto produtoAdd){
        boolean found = false;
        for (Produto produto : produtos){
            if (produto.getCodigo() == produtoAdd.getCodigo()){
                found = true;
                produto.setQuantidade(produto.getQuantidade() + produtoAdd.getQuantidade());
            }
        }
        
        if (!found){
            produtos.add(produtoAdd);
        }
    }
}