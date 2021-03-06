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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.sql.DataSource;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;


/**
 *
 * @author mello
 */
public final class ConnectionFactory {
    public DataSource dataSource;
    
    public ConnectionFactory() throws FileNotFoundException, IOException {
        Properties props = new Properties();
        
        
        try {
            props.load(new FileInputStream("config.properties"));
        } catch (FileNotFoundException ex){
            createConfig(props);
            props.load(new FileInputStream("config.properties"));
        }
        
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl(props.getProperty("DB_URL"));
        comboPooledDataSource.setUser(props.getProperty("DB_USER"));
        comboPooledDataSource.setPassword(props.getProperty("DB_PASS"));
        comboPooledDataSource.setMaxPoolSize(3);
        
        this.dataSource = comboPooledDataSource;
    }

    public void createConfig(Properties props) throws FileNotFoundException, IOException{
        try (PrintWriter inputStream = new PrintWriter("config.properties")) {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            
            props.setProperty("DB_USER", JOptionPane.showInputDialog("USER"));
            
            JPasswordField password = new JPasswordField();
            int jopt = JOptionPane.showConfirmDialog(null, password, "SENHA", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if (jopt == JOptionPane.OK_OPTION)
                props.setProperty("DB_PASS", new String(password.getPassword()));
            
            if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION){
                String wallet = chooser.getSelectedFile().toString().replace("\\", "//");
                
                props.setProperty("DB_URL", wallet);
            }
            
            inputStream.append("DB_USER=" + props.getProperty("DB_USER") + "\n");
            inputStream.append("DB_PASS=" + props.getProperty("DB_PASS") + "\n");
            inputStream.append("DB_URL=jdbc:oracle:thin:@estocagem01_high?TNS_ADMIN=" + props.getProperty("DB_URL") + "\n");
            
        }
    }
    
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
