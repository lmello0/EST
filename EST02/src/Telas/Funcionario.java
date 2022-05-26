/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Telas;

/**
 *
 * @author mello
 */
public class Funcionario {
    private final String nome;
    private final String cpf;
    private final String cargo;
    private final String matricula;
    
    
    public Funcionario(String nome, String cpf, String cargo, String matricula){
        this.nome = capitalize(nome);
        this.cpf = cpf;
        this.cargo = cargo;
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public String getMatricula() {
        return matricula;
    }
    
    private String capitalize(String string){
        char[] charString = string.toLowerCase().toCharArray();
        
        charString[0] = Character.toUpperCase(charString[0]);
        
        for(int i = 0; i < charString.length; i++){
            if (charString[i] == ' '){
                charString[i+1] = Character.toUpperCase(charString[i+1]);
            }
        }
        
        return String.copyValueOf(charString);
    }
}
