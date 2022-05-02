/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.OracleConnection;
import java.sql.DatabaseMetaData;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;

/**
 *
 * @author mello
 */
public class ConnectionFactory {
    public DataSource dataSource;
    final static String DB_URL = "jdbc:oracle:thin:@est02_high?TNS_ADMIN=C://Wallet_est02";
    final static String DB_USER = "app";
    final static String DB_PASS = "javaJDBCmod1";
    
    public ConnectionFactory() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl(DB_URL);
        comboPooledDataSource.setUser(DB_USER);
        comboPooledDataSource.setPassword(DB_PASS);
        comboPooledDataSource.setMaxPoolSize(6);
        
        this.dataSource = comboPooledDataSource;
    }
    
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
