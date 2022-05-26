/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;
import Telas.Funcionario;
import java.io.IOException;

/**
 *
 * @author mello
 */
public class Comandos {
    private final Connection conexao;

    public Comandos() throws SQLException, IOException {
        this.conexao = new ConnectionFactory().getConnection();
        conexao.setAutoCommit(false);
    }
    
    
    public void insertFuncionario(String nome, String senha, String cargo, String cpf) throws SQLException {
        String SQL = "INSERT INTO DEV.VENDEDOR (NOME, SENHA, CPF, CARGO) VALUES(?, ?, ?, ?)";
        
        try (PreparedStatement execQuery = conexao.prepareStatement(SQL)) {
            execQuery.setString(1, nome);
            execQuery.setString(2, senha);
            execQuery.setString(3, cpf);
            execQuery.setString(4, cargo);
            
            execQuery.execute();
            conexao.commit();
        }
    }
    
    public Funcionario login(String cpf, String senha) throws SQLException {
        String SQL = "SELECT SENHA, NOME, CPF, CARGO, MATRICULA FROM DEV.VENDEDOR WHERE CPF = ?";
        Funcionario funcionario = null;
        
        try (PreparedStatement execQuery = conexao.prepareStatement(SQL)) {
            execQuery.setString(1, cpf);
            
            ResultSet senhaDB = execQuery.executeQuery();
            while (senhaDB.next()){
                if (senhaDB.getString(1).equals(senha)) {
                    funcionario = new Funcionario(senhaDB.getString(2), senhaDB.getString(3), senhaDB.getString(4), senhaDB.getString(5));
                }
            }
        }
        return funcionario;
    }
    
    public ArrayList<String> getFuncionario(String cpf) throws SQLException{
        String nome = new String();
        String matricula = new String();
        String cargo = new String();
        
        String SQL = "SELECT NOME, MATRICULA, CARGO FROM DEV.VENDEDOR WHERE CPF = ?";
        
        try(PreparedStatement execQuery = conexao.prepareStatement(SQL)){
            execQuery.setString(1, cpf);
            
            ResultSet rs = execQuery.executeQuery();
            while (rs.next()){
                nome = rs.getString(1);
                matricula = rs.getString(2);
                cargo = rs.getString(3);
            }
        }
        
        return new ArrayList<>(Arrays.asList(nome, matricula, cargo));
    }
    
    public DefaultTableModel getPedidosVendedor(String cpf, String idProduto, String idPedido, String nomeCliente, String dtIni, String dtFim, javax.swing.JTable table, String[] colunas) throws SQLException{     
        String SQL = "SELECT PEDIDO.COD_PEDIDO, PRODUTO.NOME PRODUTO, PP.QUANTIDADE, CLIENTE.NOME CLIENTE, TO_CHAR(PEDIDO.DATE_CREATED, 'DD/MM/YYYY'), PRODUTO.VALOR, TO_CHAR((PRODUTO.VALOR * PP.QUANTIDADE), '9,999,999,999.99'), VENDEDOR.NOME VENDEDOR FROM DEV.PEDIDO PEDIDO JOIN DEV.CLIENTE CLIENTE ON CLIENTE.COD_CLIENTE = PEDIDO.COD_CLIENTE JOIN DEV.VENDEDOR VENDEDOR ON VENDEDOR.COD_VENDEDOR = PEDIDO.COD_VENDEDOR JOIN DEV.PEDIDO_PRODUTO PP ON PP.COD_PEDIDO = PEDIDO.COD_PEDIDO JOIN DEV.PRODUTO PRODUTO ON PRODUTO.COD_PRODUTO = PP.COD_PRODUTO WHERE VENDEDOR.CPF = NVL(?, VENDEDOR.CPF) AND PRODUTO.COD_PRODUTO = NVL(?, PRODUTO.COD_PRODUTO) AND PEDIDO.COD_PEDIDO = NVL(?, PEDIDO.COD_PEDIDO) AND CLIENTE.NOME = NVL(?, CLIENTE.NOME) AND TRUNC(PEDIDO.DATE_CREATED) BETWEEN NVL(?, TRUNC(PEDIDO.DATE_CREATED)) AND NVL(?, TO_CHAR(SYSDATE - 3/24, 'DD/MON/YYYY')) ORDER BY PEDIDO.DATE_CREATED DESC";
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(colunas);
        model.setRowCount(0);
        
        try(PreparedStatement execQuery = conexao.prepareStatement(SQL)){
            execQuery.setString(1, cpf);
            execQuery.setString(2, idProduto);
            execQuery.setString(3, idPedido);
            execQuery.setString(4, nomeCliente);
            execQuery.setString(5, dtIni);
            execQuery.setString(6, dtFim);
            
            ResultSet rs = execQuery.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int colNo = rsmd.getColumnCount();
            while (rs.next()){
                Object[] objects = new Object[colNo];
                
                for (int i = 0; i < colNo; i++){
                    objects[i] = rs.getObject(i + 1);
                }
                model.addRow(objects);
            }
        }
        
        return model;
    }
    
    public ArrayList<String> getCliente() throws SQLException{
        String SQL = "SELECT NOME FROM DEV.CLIENTE";
        ArrayList<String> items = new ArrayList<>();
        
        try(PreparedStatement execQuery = conexao.prepareStatement(SQL)){
            ResultSet rs = execQuery.executeQuery();
            
            while (rs.next()){
                String item = rs.getString(1);
                items.add(item);
            }
        }
        return items;
    }
    
    public ArrayList<String> getVendedor() throws SQLException{
        String SQL = "SELECT NOME FROM DEV.VENDEDOR";
        ArrayList<String> items = new ArrayList<>();
        
        try(PreparedStatement execQuery = conexao.prepareStatement(SQL)){
            ResultSet rs = execQuery.executeQuery();
            
            while (rs.next()){
                String item = rs.getString(1);
                items.add(item);
            }
        }
        return items;
    }
    
    public ArrayList<ArrayList<String>> getProduto() throws SQLException{
        String SQL = "SELECT COD_PRODUTO, NOME, VALOR FROM DEV.PRODUTO ORDER BY COD_PRODUTO";
        ArrayList<ArrayList<String>> itens = new ArrayList<>();

        try(PreparedStatement execQuery = conexao.prepareStatement(SQL)){
            ResultSet rs = execQuery.executeQuery();

            while (rs.next()){
                ArrayList<String> item = new ArrayList<>();
                
                String id = rs.getString(1);
                String nome = rs.getString(2);
                String valor = rs.getString(3);
                
                item.add(id);
                item.add(nome);
                item.add(valor);
                
                itens.add(item);
            }
        }
        
        return itens;
    }
    
    public String getCargo(String cpf) throws SQLException {
        String SQL = "SELECT CARGO FROM DEV.VENDEDOR WHERE CPF = ?";
        String cargo = "";
        
        try(PreparedStatement execQuery = conexao.prepareStatement(SQL)){
            execQuery.setString(1, cpf);
            
            ResultSet rs = execQuery.executeQuery();
            while(rs.next()){
                cargo = rs.getString(1);
            }
        }
        
        return cargo;
    }
    
    public void closeConnection() throws SQLException{
        conexao.close();
    }
}
