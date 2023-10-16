/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managetruckboba;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author pokec
 */


public class PlayerMovement {
    private boolean canMove = true;
    private boolean hasStartedPlaying = false;
    
    private JPanel Main;
    
    private JLabel Main_StarterCharacter;
    private JPanel Character_Walking_Area;
    //Movement Variables
    private int playerX = 50; 
    private int playerY = 50;
    private int speed = 8; //stud increment    
                
    public void share_data(JPanel main, boolean canMove, boolean game_started) {

        this.canMove = canMove;
        this.hasStartedPlaying = game_started;
        this.Main = main;
    }
    
    public void updateCanMove(boolean data) {
        this.canMove = data;
  
    }
    
    public void MovementSystem(JPanel main, JLabel character, JPanel area,int initial_x, int initial_y) {
        this.Main = main;
        this.Main_StarterCharacter = character;
        this.Character_Walking_Area = area;
        
                
        this.playerX = initial_x;
        this.playerY = initial_y;
        
        Stats hasStarted = new Stats();   
        hasStarted.updateRespawnBoolean(true);
        
        // Define actions for each movement direction
        Action moveUp = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hasStarted.getIsPlaying() == true && canMove == true) {
                    playerY -= speed;
                    updatePlayerPosition();
                }
            }
        };

        Action moveDown = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                if (hasStarted.getIsPlaying() == true && canMove == true) {
                    playerY += speed;
                    updatePlayerPosition();
                }
            }
        };

        Action moveLeft = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hasStarted.getIsPlaying() == true && canMove == true) {
                    playerX -= speed;
                    updatePlayerPosition();
                }
            }
        };

        Action moveRight = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hasStarted.getIsPlaying() == true && canMove == true) {
                    playerX += speed;
                    updatePlayerPosition();
                }
            }
        };
        
 
    
        // Bind actions to keys
        // Ensures I can still move the player when I click other buttons
        Main.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "moveUp");
        Main.getActionMap().put("moveUp", moveUp);

        Main.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "moveDown");
        Main.getActionMap().put("moveDown", moveDown);

        Main.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "moveLeft");
        Main.getActionMap().put("moveLeft", moveLeft);

        Main.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "moveRight");
        Main.getActionMap().put("moveRight", moveRight);

        // setFocusable(true);   // setting this true causes bugs for some reason
    }



    private void updatePlayerPosition() {
        // Calculate the boundaries of the panel
        int panelWidth = Character_Walking_Area.getWidth();
        int panelHeight = Character_Walking_Area.getHeight();

        // Calculate the boundaries of the character
        int characterWidth = Main_StarterCharacter.getWidth();
        int characterHeight = Main_StarterCharacter.getHeight();

        // Calculate the maximum X and Y positions where the character can be placed
        int maxX = panelWidth - characterWidth;
        int maxY = panelHeight - characterHeight;

        // Ensure the playerX and playerY values stay within bounds
        playerX = Math.max(0, Math.min(playerX, maxX));
        playerY = Math.max(0, Math.min(playerY, maxY));

        // Update the player's position
        Main_StarterCharacter.setLocation(playerX, playerY);
    }


            
}
