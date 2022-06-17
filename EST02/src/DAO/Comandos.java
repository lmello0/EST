/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import Classes.Funcionario;
import Classes.Pedido;
import Classes.Cliente;
import Classes.Produto;
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
    
    
    public void insertFuncionario(Funcionario funcionario) throws SQLException {
        String SQL = "INSERT INTO DEV.VENDEDOR (NOME, SENHA, CPF, CARGO) VALUES(?, ?, ?, ?)";
        
        try (PreparedStatement execQuery = conexao.prepareStatement(SQL)) {
            execQuery.setString(1, funcionario.getNome().toUpperCase());
            execQuery.setString(2, funcionario.getSenha());
            execQuery.setString(3, funcionario.getCpf());
            execQuery.setString(4, funcionario.getCargo());
            
            execQuery.execute();
            conexao.commit();
        }
    }
    
    public Funcionario login(String cpf, String senha) throws SQLException {
        String SQL = "SELECT NOME, CPF, CARGO, MATRICULA, SENHA FROM DEV.VENDEDOR WHERE CPF = ?";
        Funcionario funcionario = null;
        
        try (PreparedStatement execQuery = conexao.prepareStatement(SQL)) {
            execQuery.setString(1, cpf);
            
            ResultSet result = execQuery.executeQuery();
            if (result.next() && result.getString(5).equals(senha)){
                funcionario = new Funcionario(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
            }
        }
        return funcionario;
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
    
    public ArrayList<Cliente> getCliente() throws SQLException{
        String SQL = "SELECT COD_CLIENTE, NOME, DOC_TYPE, DOC_NUM FROM DEV.CLIENTE ORDER BY COD_CLIENTE";
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        try(PreparedStatement execQuery = conexao.prepareStatement(SQL)){
            ResultSet rs = execQuery.executeQuery();
            
            while (rs.next()){
                int codigo = rs.getInt(1);
                String nome = rs.getString(2);
                String docType = rs.getString(3);
                String docNum = rs.getString(4);
                
                clientes.add(new Cliente(codigo, nome, docType, docNum));
            }
        }
        
        return clientes;
    }
    
    public ArrayList<Funcionario> getFuncionario() throws SQLException{
        String SQL = "SELECT COD_VENDEDOR, NOME, CPF, MATRICULA, CARGO FROM DEV.VENDEDOR ORDER BY COD_VENDEDOR";
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        
        try(PreparedStatement execQuery = conexao.prepareStatement(SQL)){
            ResultSet rs = execQuery.executeQuery();
            
            while (rs.next()){
                int codigo = rs.getInt(1);
                String nome = rs.getString(2);
                String cpf = rs.getString(3);
                String matricula = rs.getString(4);
                String cargo = rs.getString(5);
                
                funcionarios.add(new Funcionario(codigo, nome, cpf, cargo, matricula, ""));
            }
        }
        return funcionarios;
    }
    
    public ArrayList<Produto> getProduto() throws SQLException{
        String SQL = "SELECT COD_PRODUTO, NOME, VALOR, QUANTIDADE, DESCRICAO FROM DEV.PRODUTO ORDER BY COD_PRODUTO";
        ArrayList<Produto> itens = new ArrayList<>();

        try(PreparedStatement execQuery = conexao.prepareStatement(SQL)){
            ResultSet rs = execQuery.executeQuery();

            while (rs.next()){
                int codigo = rs.getInt(1);
                String nome = rs.getString(2);
                double valor = rs.getDouble(3);
                int quantidade = rs.getInt(4);
                String descricao = rs.getString(5);
                
                itens.add(new Produto(codigo, nome, quantidade, valor, descricao));
            }
        }
        
        return itens;
    }
    
    public long insertProduto(Produto produto) throws SQLException {
        String pk[] = {"COD_PRODUTO"};
        String SQL = "INSERT INTO DEV.PRODUTO(NOME, QUANTIDADE, VALOR, DESCRICAO) VALUES(?, ?, ?, ?)";
        long codigoProduto = 0;
        
        try (PreparedStatement execQuery = conexao.prepareStatement(SQL, pk)){
            execQuery.setString(1, produto.getNome());
            execQuery.setInt(2, produto.getQuantidade());
            execQuery.setDouble(3, produto.getValor());
            execQuery.setString(4, produto.getDescricao());
            
            execQuery.executeUpdate();
            
            ResultSet rs = execQuery.getGeneratedKeys();
            if (rs.next())
                codigoProduto = rs.getLong(1);
        }
        
        conexao.commit();
        return codigoProduto;
    }
    
    public void insertPedido(Pedido pedido) throws SQLException {
        String SQL = "{CALL DEV.SP_EXECUTA_PEDIDO(?, ?, ?, ?, ?, ?)}";
        
        try (PreparedStatement execQuery = conexao.prepareCall(SQL)){
            for (Produto produto : pedido.getProdutos()){
                execQuery.setInt(1, produto.getCodigo());
                execQuery.setInt(2, produto.getQuantidade());
                execQuery.setString(3, pedido.getCliente());
                execQuery.setString(4, pedido.getVendedor());
                execQuery.setDouble(5, pedido.getValor());
                execQuery.setInt(6, pedido.getCodPedido());
                
                execQuery.executeUpdate();
            }
        }
        
        conexao.commit();
    }
    
    public void insertCliente(Cliente cliente) throws SQLException {
        String SQL = "INSERT INTO DEV.CLIENTE (NOME, DOC_TYPE, DOC_NUM) VALUES(?, ?, ?)";
        
        try(PreparedStatement execQuery = conexao.prepareStatement(SQL)){
            execQuery.setString(1, cliente.getNome());
            execQuery.setString(2, cliente.getDocType());
            execQuery.setString(3, cliente.getDocNum());
            
            execQuery.executeUpdate();
            
        }
        
        conexao.commit();
     }

    public void updateCliente(Cliente cliente) throws SQLException {
        String SQL = "UPDATE DEV.CLIENTE SET NOME = ?, DOC_TYPE = ?, DOC_NUM = ? WHERE COD_CLIENTE = ?";
        
        try(PreparedStatement execQuery = conexao.prepareStatement(SQL)){
            execQuery.setString(1, cliente.getNome());
            execQuery.setString(2, cliente.getDocType());
            execQuery.setString(3, cliente.getDocNum());
            execQuery.setInt(4, cliente.getCodigo());
            
            execQuery.executeUpdate();
        }
        
        conexao.commit();
    }
    
    public void updateFuncionario(Funcionario funcionario) throws SQLException {
        String SQL = "UPDATE DEV.VENDEDOR SET NOME = ?, CPF = ?, MATRICULA = ?, CARGO = ? WHERE COD_VENDEDOR = ?";
        
        try(PreparedStatement execQuery = conexao.prepareStatement(SQL)) {
            execQuery.setString(1, funcionario.getNome());
            execQuery.setString(2, funcionario.getCpf());
            execQuery.setString(3, funcionario.getMatricula());
            execQuery.setString(4, funcionario.getCargo());
            execQuery.setInt(5, funcionario.getCodigo());
            
            execQuery.executeUpdate();
        }
        
        conexao.commit();
    }
    
    public void deleteCliente(Cliente cliente) throws SQLException {
        String SQL = "DELETE DEV.CLIENTE WHERE COD_CLIENTE = ?";
        
        try (PreparedStatement execQuery = conexao.prepareStatement(SQL)) {
            execQuery.setInt(1, cliente.getCodigo());
            
            execQuery.executeUpdate();
        }
        
        conexao.commit();
    }
    
    public void deleteFuncionario(Funcionario funcionario) throws SQLException {
        String SQL = "DELETE DEV.VENDEDOR WHERE COD_VENDEDOR = ?";
        
        try (PreparedStatement execQuery = conexao.prepareStatement(SQL)){
            execQuery.setInt(1, funcionario.getCodigo());
            
            execQuery.executeUpdate();
        }
        
        conexao.commit();
    }
    
    public void closeConnection() throws SQLException{
        conexao.close();
    }

}
