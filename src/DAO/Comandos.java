/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Telas.TelaPrincipalGerente;
import Telas.TelaPrincipalVendedor;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
    
    
    public void insertFuncionario(String nome, String senha, String cargo, String cpf) throws SQLException {
        String SQL = "INSERT INTO DEV.VENDEDORES (NOME, SENHA, CPF, CARGO) VALUES(?, ?, ?, ?)";
        
        try (PreparedStatement execQuery = conexao.prepareStatement(SQL)) {
            execQuery.setString(1, nome);
            execQuery.setString(2, senha);
            execQuery.setString(3, cpf);
            execQuery.setString(4, cargo);
            
            execQuery.execute();
            conexao.commit();
        }
    }
    
    public ArrayList login(String cpf, String senha) throws SQLException {
        String cargo = "";
        boolean senhaOk = false;
        String SQL = "SELECT SENHA, CARGO FROM DEV.VENDEDORES WHERE CPF = ?";
        
        try (PreparedStatement execQuery = conexao.prepareStatement(SQL)) {
            execQuery.setString(1, cpf);
            
            ResultSet senhaDB = execQuery.executeQuery();
            while (senhaDB.next()){
                if (senhaDB.getString(1).equals(senha)) {
                    senhaOk = true;
                }
                cargo = senhaDB.getString(2);
            }
        }
        return new ArrayList<>(Arrays.asList(senhaOk, cargo));
    }
    
    public ArrayList<String> getFuncionario(String cpf) throws SQLException{
        String nome = "";
        String matricula = "";
        
        String SQL = "SELECT NOME, MATRICULA FROM DEV.VENDEDORES WHERE CPF = ?";
        
        try(PreparedStatement execQuery = conexao.prepareStatement(SQL)){
            execQuery.setString(1, cpf);
            
            ResultSet rs = execQuery.executeQuery();
            while (rs.next()){
                nome = rs.getString(1);
                matricula = rs.getString(2);
            }
        }
        
        return new ArrayList<>(Arrays.asList(nome, matricula));
    }
    
    public DefaultTableModel getPedidosVendedor(String cpf, String idProduto, String idPedido, String nomeCliente, String dtIni, String dtFim, javax.swing.JTable table, String[] colunas) throws SQLException{     
        String SQL = "SELECT A.ID, B.NOME AS \"PRODUTO\", A.QUANTIDADE, C.NOME AS \"CLIENTE\", TO_CHAR(A.DT_COMPRA, 'DD/MM/YYYY') AS DT_COMPRA, B.VALOR AS \"VALOR UNITARIO\", TO_CHAR((B.VALOR * A.QUANTIDADE), '9,999,999,999,999.99'), D.NOME FROM DEV.PEDIDOS A JOIN DEV.PRODUTOS B ON B.ID = A.ID_PRODUTO JOIN DEV.CLIENTES C ON C.ID = A.ID_CLIENTE JOIN DEV.VENDEDORES D ON D.ID = A.ID_VENDEDOR WHERE D.CPF = NVL(?, D.CPF) AND B.ID = NVL(?, B.ID) AND A.ID = NVL(?, A.ID) AND C.NOME = NVL(?, C.NOME) AND TRUNC(DT_COMPRA) BETWEEN NVL(?, TRUNC(DT_COMPRA)) AND NVL(?, TO_CHAR(SYSDATE - 3/24, 'DD/MON/YYYY')) ORDER BY A.DT_COMPRA DESC";
        
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
    
    
    
    public void closeConnection() throws SQLException{
        conexao.close();
    }
}
