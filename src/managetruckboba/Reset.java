/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managetruckboba;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static managetruckboba.UIBobaTruck.DelayMethod;

/**
 *
 * @author pokec
 */
public class Reset extends Stats {
    
    final int transition_duration = 10;

    private static JPanel Main;
    private static JPanel Menu;
    private static JPanel RoundTransition;
    private static JLabel TransitionInfo;
    private static JPanel ResetTransition;
    private static JButton Reset_Round_Button;
    private static JLabel End_Round_Display;
    
    public void shareData(JPanel menu, JPanel main, JPanel round, JLabel info, JPanel resetT, JButton resetB, JLabel resetD) {
        this.Menu = menu;
        this.Main = main;
        this.RoundTransition = round;
        this.TransitionInfo = info;
        this.ResetTransition = resetT;
        this.Reset_Round_Button = resetB;
        this.End_Round_Display = resetD;
    }
    

    public void MessageTypeWriter(String message) {
        for(int i = 0; i < message.length(); i++) {
            final int index = i;
            DelayMethod(0.4, () -> {  // Changed delay to 0.1 second per character
                String current = message.substring(0, index + 1);
                TransitionInfo.setText(current);
            });
        }

        final String message2 = "Day " + getDaysSurvived();
        DelayMethod(2, () -> {  // Adjusted delay for the start of the second message
            for(int i = 0; i < message2.length(); i++) {
                final int index = i;
                DelayMethod(1, () -> {  // Changed delay to 0.1 second per character
                    String current2 = message2.substring(0, index + 1);
                    TransitionInfo.setText(current2);
                });
            }
        });
        
    }
    
    
    public void ContinueButtonPressed() {
         SoundHandler sound = new SoundHandler();
         startNewRound(); //reset timer 
         updateObjective();
            
         //resume background sound
         sound.playSound("src/SoundAssets/background_music.wav"); 
         Main.setVisible(true);
         RoundTransition.setVisible(false);
         
         TransitionInfo.setText("");
    }

    public void initiate_display(String status) {
       if(status.equals("survived")) {
         //PLAY new Round Music
        SoundHandler sound = new SoundHandler();
       
        //pause background music
        sound.pauseBackgroundAudio();
        
        sound.playSound("src/SoundAssets/round_winning.wav");

        
        System.out.println("..New Round Reset..");
        UIBobaTruck main = new UIBobaTruck();
        //Hide Panel
        Main.setVisible(false);
        RoundTransition.setVisible(true);
 
        //reset stats + update notepad 
        resetRound("survived"); 
               
        MessageTypeWriter("You Survived Another Day!");        
     
       } else if(status.equals("redo")) {
            //Redo Round 
            //PLAY new Round Music
            SoundHandler sound = new SoundHandler();

            //pause background music
            sound.pauseBackgroundAudio();

            sound.playSound("src/SoundAssets/round_winning.wav");


            System.out.println("..Redoing Round..");
            UIBobaTruck main = new UIBobaTruck();
            //Hide Panel
            Main.setVisible(false);
            RoundTransition.setVisible(true);

            //reset stats + update notepad 
            resetRound("nil"); 

            MessageTypeWriter("So close. Try again!");        
           
            
       }
    }

    public void Start_New_Round() {
     
      System.out.println(getCompletedObjective() + "/" + returnObjective());
      
      if(getCompletedObjective() >= returnObjective()) {
          initiate_display("survived");
      } else {
          
          //check health
          if(getCurrentHealth() > 0 ) {
              System.out.println("Didn't survive, redoing round");
              initiate_display("redo");
          } else {
            //reset round
            System.out.println("Lives all used up! Resetting Game!");
            Reset_Game();
          }
      }
      
      NPC_Spawning npc = new NPC_Spawning();
  
      npc.NPC_Respawn(1);  
      npc.NPC_Respawn(2);
      npc.NPC_Respawn(3);
                
    }

    
    
    public void Reset_Game() {
        SoundHandler sound = new SoundHandler();
       
        //pause background music
        sound.pauseBackgroundAudio();
        
        sound.playSound("src/SoundAssets/round_winning.wav");
             
        Main.setVisible(false);
        ResetTransition.setVisible(true);
      
        End_Round_Display.setText(
           "Total Orders: "+getCompletedOrders() +
           "Total Earnings: $"+getTotalEarnings() +
           "Days Survived: "+getDaysSurvived() + 
           "Final Rating: <"+getRating()+">"
        );
         
        //reset isVisible customers and rushHour
        NPC_Spawning npcspawn = new NPC_Spawning();
        npcspawn.ResetGame();
        DelayMethod(2, () -> {  
           //reset positon + stats +   //clear all pending orders and completed orders + button pressables
           PlayerMovement resetPos = new PlayerMovement();
           resetPos.RestToDefaultPos();
           
           TransitionRound trR = new TransitionRound();
           trR.ResetToDefault();
           
           resetObjective();
           
        });
        
        Reset_Round_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundHandler sound = new SoundHandler();     
                sound.playSound("src/SoundAssets/round_winning.wav"); 
                             
                sound.playSound("src/SoundAssets/background_music.wav"); 
                
                ResetTransition.setVisible(false);
                Menu.setVisible(true);
           
            }
        });
        
    }
}
