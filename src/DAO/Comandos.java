/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.Random;

/**
 *
 * @author mello
 */
public class Comandos {
    public void insertFuncionario(String nome, String senha, String cargo, String cpf) throws SQLException {
        Connection conexao = new ConnectionFactory().getConnection();
        conexao.setAutoCommit(false);
        
        String SQL = "INSERT INTO DEV.VENDEDORES (NOME, SENHA, CPF, CARGO) VALUES(?, ?, ?, ?)";
        
        try (PreparedStatement execQuery = conexao.prepareStatement(SQL)) {
            execQuery.setString(1, nome);
            execQuery.setString(2, senha);
            execQuery.setString(3, cpf);
            execQuery.setString(4, cargo);
            
            execQuery.execute();
            conexao.commit();
        }
        conexao.close();
    }
}
