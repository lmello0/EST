/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Imagens;

import javax.swing.ImageIcon;
import java.awt.Image;

/**
 *
 * @author mello
 */
public class Imagem {

    /**
     * @param args the command line arguments
     */
    
    public Image getImage(String imageName){
        Image image = new ImageIcon((getClass().getResource(imageName))).getImage();
        return image;
    }
    
}
