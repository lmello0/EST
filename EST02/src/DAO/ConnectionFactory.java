/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;

import javax.swing.JFileChooser;


/**
 *
 * @author mello
 */
public final class ConnectionFactory {
    public DataSource dataSource;
    
    public ConnectionFactory() throws FileNotFoundException, IOException {
        String configFilePath = "src/config.properties";
        Properties props = new Properties();
        props.load(new FileInputStream(configFilePath));
        
        getDbUrl(props);
        
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl(props.getProperty("DB_URL"));
        comboPooledDataSource.setUser(props.getProperty("DB_USER"));
        comboPooledDataSource.setPassword(props.getProperty("DB_PASS"));
        comboPooledDataSource.setMaxPoolSize(3);
        
        this.dataSource = comboPooledDataSource;
    }

    public void getDbUrl(Properties props) throws FileNotFoundException, IOException{
        if (props.getProperty("DB_URL").isBlank()){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            
            if (fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION){
                String wallet = "jdbc:oracle:thin:@estocagem01_high?TNS_ADMIN=" + fileChooser.getSelectedFile();
                wallet = wallet.replace("\\", "//");
                
                try (FileOutputStream out = new FileOutputStream("src/config.properties")) {
                    props.setProperty("DB_URL", wallet);
                    props.store(out, null);
                }
            }
        }
    }
    
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    
    public static void main(String[] args) throws IOException, SQLException {
        ConnectionFactory cf = new ConnectionFactory();
        cf.getConnection();
        
        
    }
}
