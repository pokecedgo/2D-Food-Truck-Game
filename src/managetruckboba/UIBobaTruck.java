/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package managetruckboba;


import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.IOException;

//SOUND Imports
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled;
import java.util.concurrent.TimeUnit;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;


import javax.swing.Timer;

/**
 *
 * @author pokec
 */
public class UIBobaTruck extends javax.swing.JFrame {

    /**
     * Creates new form UIBobaTruck
     */
    
    private int selected_avatar = 0; 
    private boolean hasStartedPlaying = false;
    //Game Stats
    private int default_max_health = 3;
    
    private int max_health = 3;
    private int current_health = 3; 
    private int days_survived;
    private double day_earnings;
    private double total_earnings;
    private int rating = 1; //default 1
    private int missed_customers;
    private int max_missed_customers = 4; //default 4 
    private double tips; //resets each new day
    
    private String[] current_orders = {};
    private String[] ready_orders = {};
    
            //Station Stats
            private double DEFAULT_DURATION = 5;
            private double Crepe_Station= DEFAULT_DURATION; //Seconds
            private double BAKERY_STATION = DEFAULT_DURATION; //Seconds
            private double BOBA_STATION = DEFAULT_DURATION; //Seconds
            
            
            
    //Avatar Option [Mechanics]
    private Map<String, String> avatarTable = new HashMap<>();
    {
        avatarTable.put("Default", "src/Assets/Characters/Character_Steve.png"); //0
        avatarTable.put("Chef", "src/Assets/Characters/Character_Chef.png"); //1
        avatarTable.put("Farmer", "src/Assets/Characters/Character_Farmer.png");
         

    }
    
    ArrayList<String> avatarList = new ArrayList<>(avatarTable.values());
    
    
     //Movement
    private int playerX = 50; 
    private int playerY = 50;
    private int speed = 8; //stud increment
    
    public void MovementSystem() {
       addKeyListener(new KeyAdapter() {
           @Override 
           public void keyPressed(KeyEvent e) {
               int newX = playerX;
               int newY = playerY;
               
               
               switch(e.getKeyCode()) {
                   case KeyEvent.VK_W: //Move Up
                      if (hasStartedPlaying == true) 
                       newY -= speed;
                       break;
                   case KeyEvent.VK_S: //Move Down
                      if (hasStartedPlaying == true) 
                       newY += speed;
                       break;    
                   case KeyEvent.VK_A: //Move Left
                      if (hasStartedPlaying == true) 
                       newX -= speed;
                       break;
                   case KeyEvent.VK_D: //Move Right
                      if (hasStartedPlaying == true) 
                       newX += speed;
                       break;
               }
               
               //Within Panel Borders
              if (newX >= 0 && newX <= Character_Walking_Area.getWidth() - Main_StarterCharacter.getWidth() &&
                    newY >= 0 && newY <= Character_Walking_Area.getHeight() - Main_StarterCharacter.getHeight()) {
                    // Update player position
                    playerX = newX;
                    playerY = newY;
                    Main_StarterCharacter.setLocation(playerX, playerY);
                }
           }
           
       });
       setFocusable(true);
    }
    // 
    public UIBobaTruck() {
        initComponents();
        //initialize panels that need to be hidden on game start
        loading_phases.setVisible(false);
        
        //initialzie game button
        Main.setVisible(false);
        StartTheGame();
        //initialize movement system
        MovementSystem();       
        playerX = Main_StarterCharacter.getX(); //initial x
        playerY = Main_StarterCharacter.getY(); //initial y
        //background menu music
        playSound("src/SoundAssets/background_music.wav");
        
        ManageTruckBoba otherFile = new ManageTruckBoba();
        otherFile.AvatarEditor();
       
    }
    //Delay wait() method
    public static void DelayMethod(int seconds, Runnable callback) {
            int delay = seconds * 1000; // Convert seconds to milliseconds
            Timer timer = new Timer(delay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    callback.run();
                }
            });
            timer.setRepeats(false); // Ensure the timer only runs once
            timer.start(); // Start the timer
     }
    
     //Game Menu Functions
    public void StartTheGame() {
         //StartGame_Button
         StartGame_Button.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) { 
                 //play click sound 
                 playSound("src/SoundAssets/click.wav");
                 //hide the panel (button, info, etc.)
                 Menu_Panel1.setVisible(false);
                 loading_phases.setVisible(true);
                 HowToPanel.setVisible(false);
                 background_game_menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/transition_background.png")));
                  
                 load1.setVisible(false);
                 load2.setVisible(false);
                 load3.setVisible(false);
                 load4.setVisible(false);

                 int[] n = {3}; 
                 // Set initial visibility and start the sequence
                 playSound("src/SoundAssets/loading_pop.wav");
                 load1.setVisible(true);
                 sequenceVisibility(n);
                 
                
             }
         });
         
       
        //Avatar Editor Buttons
          
        Select_Left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {  
                //play click sound 
                playSound("src/SoundAssets/click.wav");

                //Rely on selected 
                if (selected_avatar >= 0 && selected_avatar < avatarList.size()) {
                   // Increment selected_avatar and use modulo to wrap around
                   selected_avatar = (selected_avatar + 1) % avatarList.size();

                   // Get imageUrl and set the icon
                   String imageUrl = avatarList.get(selected_avatar);
                   StarterCharacter.setIcon(new ImageIcon(imageUrl));
               }

            }
        });


         
        Select_Right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {  
                // play click sound 
                playSound("src/SoundAssets/click.wav");
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
         if (n[0] <= 0) return;
          // End the sequence when countdown reaches zero
         playSound("src/SoundAssets/loading_pop.wav");
         DelayMethod(1, () -> {
             playSound("src/SoundAssets/loading_pop.wav");
             load2.setVisible(true);
             DelayMethod(1, () -> {
                 playSound("src/SoundAssets/loading_pop.wav");
                 load3.setVisible(true);
                 DelayMethod(1, () -> {
                     playSound("src/SoundAssets/loading_pop.wav");
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
                            hasStartedPlaying = true;
                            
                            
                            //update avatar (Main Game) 
                              
                            String imageUrl = avatarList.get(selected_avatar);
                            Main_StarterCharacter.setIcon(new ImageIcon(imageUrl));
                        }
                     });
                 });
             });
         });
     }

    
     public static void playSound(String soundFilePath) {
        try {
            // Open an audio input stream from the sound file
            File soundFile = new File(soundFilePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            
            // Get a sound clip resource
            Clip clip = AudioSystem.getClip();
            
            // Open audio clip and load samples from the audio input stream
            clip.open(audioIn);
            
            // Add a line listener that closes the clip when the clip has finished playing
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        event.getLine().close();
                    }
                }
            });
            
            // Start playing the sound
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
     /* This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Menu = new javax.swing.JPanel();
        HowToPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        description = new javax.swing.JLabel();
        description1 = new javax.swing.JLabel();
        description2 = new javax.swing.JLabel();
        Select_Right = new javax.swing.JButton();
        Select_Left = new javax.swing.JButton();
        StarterCharacter = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        loading_phases = new javax.swing.JPanel();
        load1 = new javax.swing.JLabel();
        load3 = new javax.swing.JLabel();
        load4 = new javax.swing.JLabel();
        load2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Menu_Panel1 = new javax.swing.JPanel();
        StartGame_Button = new javax.swing.JButton();
        background_game_menu = new javax.swing.JLabel();
        Main = new javax.swing.JPanel();
        Main_Panel = new javax.swing.JPanel();
        StatsPanel = new javax.swing.JPanel();
        Sales_Display = new javax.swing.JLabel();
        Station_Boba = new javax.swing.JButton();
        Tip_Jar = new javax.swing.JButton();
        Station_Bakery1 = new javax.swing.JButton();
        Station_Crepes2 = new javax.swing.JButton();
        Registers = new javax.swing.JLabel();
        Character_Walking_Area = new javax.swing.JPanel();
        Main_StarterCharacter = new javax.swing.JLabel();
        background_kitchen = new javax.swing.JLabel();
        background_outside = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Menu.setOpaque(false);
        Menu.setLayout(new javax.swing.OverlayLayout(Menu));

        HowToPanel.setForeground(new java.awt.Color(193, 146, 96));
        HowToPanel.setAlignmentX(0.0F);
        HowToPanel.setAlignmentY(0.4F);
        HowToPanel.setMaximumSize(new java.awt.Dimension(1200, 500));
        HowToPanel.setMinimumSize(new java.awt.Dimension(1200, 150));
        HowToPanel.setOpaque(false);
        HowToPanel.setPreferredSize(new java.awt.Dimension(500, 200));

        jLabel2.setFont(new java.awt.Font("BLOXAT", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(137, 122, 107));
        jLabel2.setText("__Customers_________________________________Rush__Hours_________________________________________Manage___");

        description.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        description.setForeground(new java.awt.Color(112, 140, 101));
        description.setText("<html>Upgrade Machines!<br>Unlock Customers!<br>Go!</html>");
        description.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        description.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        description1.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        description1.setForeground(new java.awt.Color(112, 140, 101));
        description1.setText("<html>Surive the day!<br>Watch the clock<br>No Mistakes!</html>");
        description1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        description1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        description2.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        description2.setForeground(new java.awt.Color(112, 140, 101));
        description2.setText("<html>Take Customer Orders!<br>Build up your Rating!<br>Hustle!</html>");
        description2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        description2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        Select_Right.setBackground(new java.awt.Color(102, 94, 132));
        Select_Right.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        Select_Right.setText("Next");

        Select_Left.setBackground(new java.awt.Color(102, 94, 132));
        Select_Left.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        Select_Left.setText("Back");
        Select_Left.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Select_LeftActionPerformed(evt);
            }
        });

        StarterCharacter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Characters/Character_Chef.png"))); // NOI18N
        StarterCharacter.setText("jLabel6");

        jLabel4.setFont(new java.awt.Font("BLOXAT", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(221, 192, 105));
        jLabel4.setText("Choose__Avatar");

        javax.swing.GroupLayout HowToPanelLayout = new javax.swing.GroupLayout(HowToPanel);
        HowToPanel.setLayout(HowToPanelLayout);
        HowToPanelLayout.setHorizontalGroup(
            HowToPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HowToPanelLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(Select_Left)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(HowToPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(StarterCharacter, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(Select_Right)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HowToPanelLayout.createSequentialGroup()
                .addContainerGap(568, Short.MAX_VALUE)
                .addGroup(HowToPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(HowToPanelLayout.createSequentialGroup()
                        .addComponent(description2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(description1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );
        HowToPanelLayout.setVerticalGroup(
            HowToPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HowToPanelLayout.createSequentialGroup()
                .addGroup(HowToPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HowToPanelLayout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(Select_Left))
                    .addGroup(HowToPanelLayout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(Select_Right))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HowToPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(StarterCharacter)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(HowToPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(description2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(description1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4))
        );

        Menu.add(HowToPanel);

        loading_phases.setAlignmentX(0.0F);
        loading_phases.setAlignmentY(0.2F);
        loading_phases.setMaximumSize(new java.awt.Dimension(1500, 1500));
        loading_phases.setMinimumSize(new java.awt.Dimension(400, 250));
        loading_phases.setOpaque(false);
        loading_phases.setPreferredSize(new java.awt.Dimension(1200, 1000));

        load1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        load1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Loading_Images/load1.png"))); // NOI18N
        load1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        load3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        load3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Loading_Images/load3.png"))); // NOI18N
        load3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        load4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        load4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Loading_Images/load4.png"))); // NOI18N
        load4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        load4.setMaximumSize(new java.awt.Dimension(100, 100));
        load4.setMinimumSize(new java.awt.Dimension(100, 100));
        load4.setPreferredSize(new java.awt.Dimension(100, 100));
        load4.setVerifyInputWhenFocusTarget(false);

        load2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        load2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Loading_Images/load2.png"))); // NOI18N
        load2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel3.setFont(new java.awt.Font("BLOXAT", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(164, 48, 48));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("MANAGE___A__");

        jLabel5.setFont(new java.awt.Font("BLOXAT", 1, 65)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(81, 71, 62));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("BOBA__TRUCK");

        javax.swing.GroupLayout loading_phasesLayout = new javax.swing.GroupLayout(loading_phases);
        loading_phases.setLayout(loading_phasesLayout);
        loading_phasesLayout.setHorizontalGroup(
            loading_phasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loading_phasesLayout.createSequentialGroup()
                .addContainerGap(564, Short.MAX_VALUE)
                .addGroup(loading_phasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loading_phasesLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(252, 252, 252))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loading_phasesLayout.createSequentialGroup()
                        .addGroup(loading_phasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(loading_phasesLayout.createSequentialGroup()
                                .addComponent(load1)
                                .addGap(48, 48, 48)
                                .addComponent(load2)
                                .addGap(54, 54, 54)
                                .addComponent(load3)
                                .addGap(43, 43, 43)
                                .addComponent(load4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(231, 231, 231))))
        );
        loading_phasesLayout.setVerticalGroup(
            loading_phasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loading_phasesLayout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loading_phasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(load3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(load2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(load1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(load4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(256, Short.MAX_VALUE))
        );

        Menu.add(loading_phases);

        Menu_Panel1.setAlignmentX(-0.2F);
        Menu_Panel1.setMaximumSize(new java.awt.Dimension(1200, 700));
        Menu_Panel1.setOpaque(false);
        Menu_Panel1.setPreferredSize(new java.awt.Dimension(800, 700));

        StartGame_Button.setBackground(new java.awt.Color(153, 135, 155));
        StartGame_Button.setFont(new java.awt.Font("BLOXAT", 3, 36)); // NOI18N
        StartGame_Button.setForeground(new java.awt.Color(255, 255, 255));
        StartGame_Button.setText("Start_____Game");
        StartGame_Button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        StartGame_Button.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        StartGame_Button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout Menu_Panel1Layout = new javax.swing.GroupLayout(Menu_Panel1);
        Menu_Panel1.setLayout(Menu_Panel1Layout);
        Menu_Panel1Layout.setHorizontalGroup(
            Menu_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Menu_Panel1Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(StartGame_Button)
                .addContainerGap(839, Short.MAX_VALUE))
        );
        Menu_Panel1Layout.setVerticalGroup(
            Menu_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Menu_Panel1Layout.createSequentialGroup()
                .addContainerGap(534, Short.MAX_VALUE)
                .addComponent(StartGame_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );

        Menu.add(Menu_Panel1);

        background_game_menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/intro_background.png"))); // NOI18N
        Menu.add(background_game_menu);

        Main.setMaximumSize(new java.awt.Dimension(1280, 667));
        Main.setLayout(new javax.swing.OverlayLayout(Main));

        Main_Panel.setAlignmentX(0.0F);
        Main_Panel.setMaximumSize(new java.awt.Dimension(1280, 667));
        Main_Panel.setMinimumSize(new java.awt.Dimension(1280, 667));
        Main_Panel.setOpaque(false);

        StatsPanel.setOpaque(false);
        StatsPanel.setPreferredSize(new java.awt.Dimension(1200, 672));
        StatsPanel.setLayout(null);

        Sales_Display.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        Sales_Display.setForeground(new java.awt.Color(153, 178, 126));
        Sales_Display.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Sales_Display.setText("$20.00");
        StatsPanel.add(Sales_Display);
        Sales_Display.setBounds(660, 440, 60, 20);

        Station_Boba.setBackground(new java.awt.Color(51, 51, 51));
        Station_Boba.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N
        Station_Boba.setText("Bubble Tea");
        Station_Boba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Station_BobaActionPerformed(evt);
            }
        });
        StatsPanel.add(Station_Boba);
        Station_Boba.setBounds(1160, 360, 110, 24);

        Tip_Jar.setBackground(new java.awt.Color(204, 200, 122));
        Tip_Jar.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        Tip_Jar.setForeground(new java.awt.Color(51, 51, 51));
        Tip_Jar.setText("$67");
        Tip_Jar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tip_JarActionPerformed(evt);
            }
        });
        StatsPanel.add(Tip_Jar);
        Tip_Jar.setBounds(500, 310, 60, 20);

        Station_Bakery1.setBackground(new java.awt.Color(51, 51, 51));
        Station_Bakery1.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N
        Station_Bakery1.setText("Bakery");
        Station_Bakery1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Station_Bakery1ActionPerformed(evt);
            }
        });
        StatsPanel.add(Station_Bakery1);
        Station_Bakery1.setBounds(220, 290, 80, 24);

        Station_Crepes2.setBackground(new java.awt.Color(51, 51, 51));
        Station_Crepes2.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N
        Station_Crepes2.setText("Crepes");
        Station_Crepes2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Station_Crepes2ActionPerformed(evt);
            }
        });
        StatsPanel.add(Station_Crepes2);
        Station_Crepes2.setBounds(330, 390, 80, 24);

        Registers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Main_Game_Assets/register.png"))); // NOI18N
        StatsPanel.add(Registers);
        Registers.setBounds(550, 421, 340, 245);

        Character_Walking_Area.setAlignmentX(0.0F);
        Character_Walking_Area.setAlignmentY(5.0F);
        Character_Walking_Area.setMaximumSize(new java.awt.Dimension(1500, 300));
        Character_Walking_Area.setMinimumSize(new java.awt.Dimension(1500, 300));
        Character_Walking_Area.setOpaque(false);
        Character_Walking_Area.setPreferredSize(new java.awt.Dimension(1500, 300));

        Main_StarterCharacter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Characters/Character_Farmer.png"))); // NOI18N

        javax.swing.GroupLayout Character_Walking_AreaLayout = new javax.swing.GroupLayout(Character_Walking_Area);
        Character_Walking_Area.setLayout(Character_Walking_AreaLayout);
        Character_Walking_AreaLayout.setHorizontalGroup(
            Character_Walking_AreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Character_Walking_AreaLayout.createSequentialGroup()
                .addContainerGap(917, Short.MAX_VALUE)
                .addComponent(Main_StarterCharacter)
                .addGap(263, 263, 263))
        );
        Character_Walking_AreaLayout.setVerticalGroup(
            Character_Walking_AreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Character_Walking_AreaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Main_StarterCharacter)
                .addGap(33, 33, 33))
        );

        StatsPanel.add(Character_Walking_Area);
        Character_Walking_Area.setBounds(0, 408, 1280, 260);

        javax.swing.GroupLayout Main_PanelLayout = new javax.swing.GroupLayout(Main_Panel);
        Main_Panel.setLayout(Main_PanelLayout);
        Main_PanelLayout.setHorizontalGroup(
            Main_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Main_PanelLayout.createSequentialGroup()
                .addComponent(StatsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1274, Short.MAX_VALUE)
                .addContainerGap())
        );
        Main_PanelLayout.setVerticalGroup(
            Main_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(StatsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
        );

        Main.add(Main_Panel);

        background_kitchen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Main_Game_Assets/main_background.png"))); // NOI18N
        background_kitchen.setMaximumSize(new java.awt.Dimension(1280, 667));
        Main.add(background_kitchen);

        background_outside.setBackground(new java.awt.Color(130, 114, 104));
        background_outside.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Main.add(background_outside);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Menu, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Select_LeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Select_LeftActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Select_LeftActionPerformed

    private void Station_BobaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Station_BobaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Station_BobaActionPerformed

    private void Tip_JarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Tip_JarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tip_JarActionPerformed

    private void Station_Bakery1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Station_Bakery1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Station_Bakery1ActionPerformed

    private void Station_Crepes2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Station_Crepes2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Station_Crepes2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UIBobaTruck.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UIBobaTruck.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UIBobaTruck.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UIBobaTruck.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UIBobaTruck().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Character_Walking_Area;
    private javax.swing.JPanel HowToPanel;
    private javax.swing.JPanel Main;
    private javax.swing.JPanel Main_Panel;
    private javax.swing.JLabel Main_StarterCharacter;
    private javax.swing.JPanel Menu;
    private javax.swing.JPanel Menu_Panel1;
    private javax.swing.JLabel Registers;
    private javax.swing.JLabel Sales_Display;
    private javax.swing.JButton Select_Left;
    private javax.swing.JButton Select_Right;
    private javax.swing.JButton StartGame_Button;
    private javax.swing.JLabel StarterCharacter;
    private javax.swing.JButton Station_Bakery1;
    private javax.swing.JButton Station_Boba;
    private javax.swing.JButton Station_Crepes2;
    private javax.swing.JPanel StatsPanel;
    private javax.swing.JButton Tip_Jar;
    private javax.swing.JLabel background_game_menu;
    private javax.swing.JLabel background_kitchen;
    private javax.swing.JPanel background_outside;
    private javax.swing.JLabel description;
    private javax.swing.JLabel description1;
    private javax.swing.JLabel description2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel load1;
    private javax.swing.JLabel load2;
    private javax.swing.JLabel load3;
    private javax.swing.JLabel load4;
    private javax.swing.JPanel loading_phases;
    // End of variables declaration//GEN-END:variables
}
