/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author mello
 */
public class Cliente {
    private String nome;
    private String docType;
    private String docNum;

    public Cliente(String nome, String docType, String docNum) {
        this.nome = nome;
        this.docType = docType;
        this.docNum = docNum;
    }

    public String getNome() {
        return nome;
    }

    public String getDocType() {
        return docType;
    }

    public String getDocNum() {
        return docNum;
    }
    
    
}
