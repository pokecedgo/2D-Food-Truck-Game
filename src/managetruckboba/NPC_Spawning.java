/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managetruckboba;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static managetruckboba.UIBobaTruck.DelayMethod;

/**
 *
 * @author pokec
 */
public class NPC_Spawning extends Stats {
    //Avatar Option [Mechanics]
    private static int selected_avatar = 0; 
    
    private Map<String, String> avatarTable = new HashMap<>();
    {
        avatarTable.put("Default", "src/Assets/Characters/Character_Steve.png"); //0
        avatarTable.put("Chef", "src/Assets/Characters/Character_Chef.png"); //1
        avatarTable.put("Farmer", "src/Assets/Characters/Character_Farmer.png");
        avatarTable.put("Pirate", "src/Assets/Characters/Character_Pirate.png");
         

    }
    
    private static Map<String, String> npcs = new HashMap<>();
      static {
          npcs.put("npc1", "src/Assets/Characters/npc_chineseV1.png");
          npcs.put("npc2", "src/Assets/Characters/npc_chineseV2.png");
          npcs.put("npc3", "src/Assets/Characters/npc_fisherV1.png");
          npcs.put("npc4", "src/Assets/Characters/npc_fisherV2.png");
          npcs.put("npc5", "src/Assets/Characters/npc_malay.png");
          npcs.put("npc6", "src/Assets/Characters/npc_old.png");
          npcs.put("npc7", "src/Assets/Characters/npc_villager.png");
      }

    ArrayList<String> avatarList = new ArrayList<>(avatarTable.values());
    
    ArrayList<String> npcsList = new ArrayList<>(npcs.values());
    
    
    //Ensure randomness of Customers
    java.util.List<String> current_loaded_npcs = new java.util.ArrayList<>();
    
    
    //Handling NPC Respawning / Spawning
    private java.util.List<String> unloaded_npcs = new ArrayList<>(npcsList);
    //Labels
    
    private static JLabel Order_Display_1;
    private static JLabel Order_Display_2;
    private static JLabel Order_Display_3;
    
    private static JLabel npc_slot1;
    private static JLabel npc_slot2;
    private static JLabel npc_slot3;
    
    
    private static JButton Order_Bubble1;
    private static JButton Order_Bubble2;
    private static JButton Order_Bubble3;
    
    private static JPanel Main;
    
    private boolean rushHour = false;
    //NPC Customers System

    boolean isVisible_Customer1 = true;
    boolean isVisible_Customer2= true;
    boolean isVisible_Customer3 = true;
    

  
    //Game Menu Functions 
    
    private static JButton StartGame_Button;
    private static JPanel Menu_Panel1;
    private static JPanel HowToPanel;
    private static JLabel background_game_menu;
    private static JPanel loading_phases;
    
    private static JLabel load1;
    private static JLabel load2;
    private static JLabel load3;
    private static JLabel load4;
    
    private static JButton Select_Left;
    private static JButton Select_Right;
    
    private static JLabel StarterCharacter;
    private static JLabel Main_StarterCharacter;
    private static JPanel Menu;
    private static JLabel Countdown_Display;
    

    public void share_data(JPanel main, JLabel d1, JLabel d2, JLabel d3, JLabel slot1, JLabel slot2, JLabel slot3, JButton b1, JButton b2, JButton b3, JLabel cd) {
        this.Order_Display_1 = d1;
        this.Order_Display_2 = d2;
        this.Order_Display_3 = d3;

        this.npc_slot1 = slot1;
        this.npc_slot2 = slot2;
        this.npc_slot3 = slot3;

        this.Order_Bubble1 = b1;
        this.Order_Bubble2 = b2;
        this.Order_Bubble3 = b3;
        
        this.Main = main;
        this.Countdown_Display = cd;
           
    }
    
    public void ResetGame() {
        rushHour = false;
        isVisible_Customer1 = true;
        isVisible_Customer2= true;
        isVisible_Customer3 = true;
    }

    public String returnAvatarList() {
        return avatarList.get(selected_avatar);
    }
    
    public String getImageUrl() {
         NPC_Spawning getAvatar = new NPC_Spawning();
         return getAvatar.returnAvatarList();
     }
     
    public void spawnNPC(boolean isVisible, JLabel npc_slot, JLabel orderDisplay) {   
       
        
        if (isVisible && npc_slot.getIcon() == null) {
            // active npc 
            return;
        }

      
        // Hide all respective labels first
        if (orderDisplay.equals(Order_Display_1)) {
            // slot 1
            Order_Display_1.setVisible(false);
            npc_slot1.setVisible(false);
            Order_Bubble1.setVisible(false);
            
        } else if (orderDisplay.equals(Order_Display_2)) {
            // slot 2 
            Order_Display_2.setVisible(false);
            npc_slot2.setVisible(false);
            Order_Bubble2.setVisible(false);
        } else if (orderDisplay.equals(Order_Display_3)) {                                                                                                                                                                                                                                                        
            // slot 3
            Order_Display_3.setVisible(false);
            npc_slot3.setVisible(false);
            Order_Bubble3.setVisible(false);
        }
        Main.revalidate();
        Main.repaint();
        // inactive npc, spawn npc (Can Spawn)
        Random random = new Random();
        int randomWait = rushHour ? 1 : 1 + random.nextInt(5); // Adjusted delay range to 1-5 seconds
        DelayMethod(randomWait, () -> {
            if (unloaded_npcs.isEmpty()) {
                unloaded_npcs.addAll(npcsList); // Reset the list if all NPCs have been loaded
            }

            int randomIndex = random.nextInt(unloaded_npcs.size());
            String randomImage = unloaded_npcs.get(randomIndex);
            unloaded_npcs.remove(randomIndex); // Remove the selected NPC from the list

            System.out.println("Generated NPC " + randomImage + " in " + npc_slot);

            npc_slot.setIcon(new ImageIcon(randomImage));

            // Add the NPC to the loaded list
            current_loaded_npcs.add(randomImage);

            randomIndex = random.nextInt(getPossibleOrders().length);
            String random_order = getPossibleOrders()[randomIndex];
            orderDisplay.setText(random_order);
            
            System.out.println("Order selected: "+random_order);
            Main.revalidate();
            Main.repaint();
            

            //make button pressable true
            if (orderDisplay.equals(Order_Display_1)) {
                setButton1Pressable(true);
            } else if (orderDisplay.equals(Order_Display_2)) {
                setButton2Pressable(true);
            } else if (orderDisplay.equals(Order_Display_3)) {
                setButton3Pressable(true);
            }   
            
            
                //conditoning statements to make respective labels visible = true
            if(orderDisplay.equals(Order_Display_1)) {
                //slot 1 
                System.out.println("Displaying NPC Slot 1!!");
                
                /* where we left off
                    debugging NPC Spawning display
                */
                Order_Display_1.setVisible(true);
                npc_slot1.setVisible(true);
                Order_Bubble1.setVisible(true);
                
                setButton1Pressable(true);

            } else if(orderDisplay.equals(Order_Display_2)) {
                //slot 2
                Order_Display_2.setVisible(true);
                npc_slot2.setVisible(true);
                Order_Bubble2.setVisible(true);
                
                setButton2Pressable(true);
                
            } else if(orderDisplay.equals(Order_Display_3)) {
                //slot 3
                Order_Display_3.setVisible(true);
                npc_slot3.setVisible(true);
                Order_Bubble3.setVisible(true);
                
                setButton3Pressable(true);
            }
            });
        
        if (unloaded_npcs.isEmpty()) {
            unloaded_npcs.addAll(npcsList); // Reset the list if all NPCs have been loaded
        }
        
        System.out.println("NPC Respawning Complete!");
        
    }
        //Respawning Specific Slots (Everytime a Order is completed)
    public void NPC_Respawn(int slot) {
       if(slot == 1) {
         spawnNPC(isVisible_Customer1, npc_slot1, Order_Display_1);
       } else if (slot == 2) { 
         spawnNPC(isVisible_Customer2, npc_slot2, Order_Display_2);
       } else if (slot == 3) {
         spawnNPC(isVisible_Customer3, npc_slot3, Order_Display_3);
       } else {
         System.out.println("Invalid NPC Slot to respawn!");
       }
    }

    public void NPC_Randomization() {
        spawnNPC(isVisible_Customer1, npc_slot1, Order_Display_1);
        spawnNPC(isVisible_Customer2, npc_slot2, Order_Display_3);
        spawnNPC(isVisible_Customer3, npc_slot3, Order_Display_2);   
    }
    
    
    

    
    public void StartTheGame(JLabel starter_c, JLabel startermain, JPanel xMenu, JButton startgame,JPanel m1, JPanel howto, JLabel background, JPanel loading, JLabel l1, JLabel l2, JLabel l3, JLabel l4, JButton left1, JButton right1) {
         //StartGame_Button
         this.Menu = xMenu;
         this.StarterCharacter = startermain;
         this.StartGame_Button = startgame;
         this.Main_StarterCharacter = starter_c;
         this.Menu_Panel1 = m1;
         this.HowToPanel = howto;
         this.background_game_menu = background;
         this.loading_phases = loading;
         
         this.load1 = l1;
         this.load2 = l2;
         this.load3 = l3;
         this.load4 = l4;
         
         this.Select_Left = left1;
         this.Select_Right = right1;
         
         SoundHandler playSound = new SoundHandler();
         
        StartGame_Button.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) { 
                 //play click sound 
                
                 playSound.playSound("src/SoundAssets/click.wav");
                 //hide the panel (button, info, etc.)
                 Menu_Panel1.setVisible(false);
                 loading_phases.setVisible(true);
                 HowToPanel.setVisible(false);
                 background_game_menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/transition_background.png")));
                  
                 load1.setVisible(false);
                 load2.setVisible(false);
                 load3.setVisible(false);
                 load4.setVisible(false);

                 int[] n = {1}; 
                 // Set initial visibility and start the sequence
                 playSound.playSound("src/SoundAssets/loading_pop.wav");
                 load1.setVisible(true);
                 sequenceVisibility(n);
                 
                
             }
         });
         
       
        //Avatar Editor Buttons
          
        Select_Left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {  
                //play click sound 
                playSound.playSound("src/SoundAssets/click.wav");

                //Rely on selected 
                if (selected_avatar >= 0 && selected_avatar < avatarList.size()) {
                   // Increment selected_avatar and use modulo to wrap around
                   selected_avatar = (selected_avatar + 1) % avatarList.size();

                   // Get imageUrl and set the icon
                   String imageUrl = getImageUrl();
                   StarterCharacter.setIcon(new ImageIcon(imageUrl));
               }

            }
        });


         
        Select_Right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {  
                // play click sound 
                playSound.playSound("src/SoundAssets/click.wav");
                // Rely on selected 

                if (selected_avatar >= 0 && selected_avatar < avatarList.size()) {
                    // Decrement selected_avatar and handle wrapping manually
                    selected_avatar = (selected_avatar - 1 + avatarList.size()) % avatarList.size();

                    // Get imageUrl and set the icon
                    String imageUrl = avatarList.get(selected_avatar);
                    StarterCharacter.setIcon(new ImageIcon(imageUrl));
                }
            }
        });

     }
    
    
     
    public void sequenceVisibility(int[] n) {
             
         SoundHandler playSound = new SoundHandler();
         //Preload spawn initial customers During Transition
       
         NPC_Randomization();
         
         if (n[0] <= 0) return;
          // End the sequence when countdown reaches zero
         playSound.playSound("src/SoundAssets/loading_pop.wav");
         DelayMethod(1, () -> {
             playSound.playSound("src/SoundAssets/loading_pop.wav");
             load2.setVisible(true);
             DelayMethod(1, () -> {
                 playSound.playSound("src/SoundAssets/loading_pop.wav");
                 load3.setVisible(true);
                 DelayMethod(1, () -> {
                     playSound.playSound("src/SoundAssets/loading_pop.wav");
                     load4.setVisible(true);
                     // Reset visibility for next cycle and decrement countdown
                     DelayMethod(1, () -> {
                        load1.setVisible(false);
                        load2.setVisible(false);
                        load3.setVisible(false);
                        load4.setVisible(false);
                        n[0]--;
                     // Start the next cycle
                        if (n[0] > 0) {
                            load1.setVisible(true);
                            sequenceVisibility(n); // Recursive call for the next cycle
                        } else {
                            //Game Begin
  
                            Menu.setVisible(false);
                            Main.setVisible(true);
                            
                     
                            updateRespawnBoolean(true);

                            
                            //enable movement and change booleans
                            PlayerMovement updateMove = new PlayerMovement();
                            updateMove.updateCanMove(true);
          
                            //Update hasStartedPlaying
                          
                            updateRespawnBoolean(true); //hasstartedplaying = true
                            //update avatar (Main Game) 
    
                            String imageUrl = getImageUrl();
                            Main_StarterCharacter.setIcon(new ImageIcon(imageUrl));
                                             
                            //start timer
                            RoundTimer(Countdown_Display);
                            
                        }
                     });
                 });
             });
         });
     }   
    
}
