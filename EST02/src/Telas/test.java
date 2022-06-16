/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Telas;

import Classes.Cliente;
import DAO.Comandos;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


/**
 *
 * @author mello
 */
public class test {
    
    public static void main(String[] args) throws SQLException, IOException {
        Random r = new Random();
        Comandos comandos = new Comandos();
        
        for (int i = 0; i <= 1000; i++){
            StringBuilder sb = new StringBuilder(20);
            for (int j = 0; j < 20; j++){
                char tmp = (char) ('a' + r.nextInt('z' - 'a'));
                sb.append(tmp);
            }
            
            StringBuilder docNum = new StringBuilder(14);
            int randomOfTwoInts = new Random().nextBoolean() ? 11 : 14;
            
            for(int j = 0; j < randomOfTwoInts; j++){
                String tmp = String.valueOf(r.nextInt(10));
                docNum.append(tmp);
            }
            
            String docType = new String();
            switch (randomOfTwoInts){
                case 11:
                    docType = "CPF";
                    break;
                case 14:
                    docType = "CNPJ";
                    break;
            }
            
            Cliente cliente = new Cliente(sb.toString().toUpperCase(), docType, docNum.toString());
            
            comandos.insertCliente(cliente);
        }
    }
}
