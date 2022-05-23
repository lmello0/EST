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

/**
 *
 * @author mello
 */
public class Comandos {
    private final Connection conexao;

    public Comandos() throws SQLException {
        this.conexao = new ConnectionFactory().getConnection();
        conexao.setAutoCommit(false);
    }
    
    
    public void insertFuncionario(String nome, String senha, String cargo, String cpf, String matricula) throws SQLException {
        String SQL = "INSERT INTO DEV.VENDEDORES (NOME, SENHA, CPF, CARGO, MATRICULA) VALUES(?, ?, ?, ?, ?)";
        
        try (PreparedStatement execQuery = conexao.prepareStatement(SQL)) {
            execQuery.setString(1, nome);
            execQuery.setString(2, senha);
            execQuery.setString(3, cpf);
            execQuery.setString(4, cargo);
            execQuery.setString(5, matricula);
            
            execQuery.execute();
            conexao.commit();
        }
    }
    
    public Funcionario login(String cpf, String senha) throws SQLException {
        String SQL = "SELECT SENHA, NOME, CPF, CARGO, MATRICULA FROM DEV.VENDEDORES WHERE CPF = ?";
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
        
        String SQL = "SELECT NOME, MATRICULA, CARGO FROM DEV.VENDEDORES WHERE CPF = ?";
        
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
        String SQL = "SELECT A.ID, B.NOME AS \"PRODUTO\", A.QUANTIDADE, C.NOME AS \"CLIENTE\", TO_CHAR(A.DT_COMPRA, 'DD/MM/YYYY'), B.VALOR AS \"VALOR UNITARIO\", TO_CHAR((B.VALOR * A.QUANTIDADE), '9,999,999,999,999.99'), D.NOME AS \"VENDEDOR\" FROM DEV.PEDIDOS A JOIN DEV.CLIENTES C ON C.ID = A.ID_CLIENTE JOIN DEV.VENDEDORES D ON D.ID = A.ID_VENDEDOR JOIN DEV.PEDIDO_PRODUTO E ON E.ID_PEDIDO = A.ID JOIN DEV.PRODUTOS B ON B.ID = E.ID_PRODUTO WHERE D.CPF = NVL(?, D.CPF) AND B.ID = NVL(?, B.ID) AND A.ID = NVL(?, A.ID) AND C.NOME = NVL(C.NOME, ?) AND TRUNC(DT_COMPRA) BETWEEN NVL(?, TRUNC(DT_COMPRA)) AND NVL(?, TO_CHAR(SYSDATE - 3/24, 'DD/MON/YYYY')) ORDER BY A.DT_COMPRA DESC";
        
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
        String SQL = "SELECT NOME FROM DEV.CLIENTES";
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
        String SQL = "SELECT NOME FROM DEV.VENDEDORES";
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
        String SQL = "SELECT ID, NOME, VALOR FROM DEV.PRODUTOS ORDER BY ID";
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
        String SQL = "SELECT CARGO FROM DEV.VENDEDORES WHERE CPF = ?";
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
