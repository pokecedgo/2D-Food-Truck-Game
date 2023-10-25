/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package managetruckboba;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

import javax.swing.Timer;

/**
 *
 * @author pokec
 */
public class UIBobaTruck extends javax.swing.JFrame {     
    // >>Run this Class To Start Game 

    //General Variables
    private boolean canMove = true;
    private static boolean initialized = false;
   
    NPC_Spawning respawn_handler = new NPC_Spawning();
    
    public void getShareData_Respawn() {

            respawn_handler.share_data(
                Main,
                Order_Display_1,
                Order_Display_2,
                Order_Display_3,
                npc_slot1,
                npc_slot2,
                npc_slot3,
                Order_Bubble1,
                Order_Bubble2,
                Order_Bubble3,
                Countdown_Display
            );
    }
    
    
    public void shareResetData() {
        Reset reset = new Reset();
        reset.shareData(Menu, Main, RoundTransition,TransitionInfo, ResetTransition,  Reset_Round_Button,End_Round_Display);
    }
   

    
    public UIBobaTruck() {
        if (!initialized) {
            initComponents();
     
            // Initialize panels that need to be hidden on game start
            loading_phases.setVisible(false);
            Main.setVisible(false);
            RoundTransition.setVisible(false);
            ResetTransition.setVisible(false);
           
     
            
            //UIs Hidden on Start
            Info_Display.setVisible(false);
            Character_Walking_Area.setLayout(null);

            
            Stats stats = new Stats(); 
            NPCButtons npcButtons = new NPCButtons(stats); 
                
            // Background menu music
            playSound("src/SoundAssets/background_music.wav"); 

            // Initialize Buttons
        
           
            npcButtons.share_data(Info_Display,
                    Tip_Jar,
                Rating_Display,
                Stats_Display,
                Sales_Display,
                Main_StarterCharacter,
                Station_Crepes,
                Station_Bakery,
                Station_Drinks,
                Order_Display_1,
                Order_Display_2,
                Order_Display_3,
                Order_Bubble1,
                Order_Bubble2,
                Order_Bubble3,
                Order_List,
                clock_display1,
                clock_display2,
                clock_display3
            );
            npcButtons.NPC_Buttons();

            // Initialize Movement Class
            PlayerMovement movement_handler = new PlayerMovement();
            movement_handler.share_data(  
                canMove,
                stats.getIsPlaying(),
                 Main,
                 Main_StarterCharacter,
                 Character_Walking_Area
            );

            // Initialize Spawning Class
           getShareData_Respawn();
          
                
            // Initialize Start Game Functions
            respawn_handler.StartTheGame(
                Main_StarterCharacter,
                StarterCharacter,
                Menu,
                StartGame_Button,
                Menu_Panel1,
                HowToPanel,
                background_game_menu,
                loading_phases,
                load1,
                load2,
                load3,
                load4,
                Select_Left,
                Select_Right
            );
            
            shareResetData();
            
            stats.shareData(Stats_Display, Sales_Display, Tip_Jar);
            // Initialize Movement
            PlayerMovement startMovement = new PlayerMovement();
            startMovement.MovementSystem();
            
            //TransitionRound
            TransitionRound round = new TransitionRound() ;
            round.shareData(
                    Spending_Display,
                    Lives_Display,
                    Crepe_Upgrade_Button, 
                    Drinks_Upgrade_Button,
                    Bakery_Upgrade_Button,
                    Continue_Button           
            );
       
            //initial display 
            stats.updateStatsDispay();
                  
            initialized = true; // Set the flag to true to ensure this block runs only once
        }
  
    }
 
    
    //Updating Data
    
 
    public void playSound(String data) {
        SoundHandler send_sound = new SoundHandler();
        send_sound.playSound(data);
    }
    
    //Delay wait() method

    public static void DelayMethod(double seconds, Runnable callback) {
        int delay = (int)(seconds * 1000);  // Convert seconds to milliseconds
        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callback.run();
            }
        });
        timer.setRepeats(false);  // Ensure the timer only runs once
        timer.start();  // Start the timer
    }


    

 
 
     /* This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        ResetTransition = new javax.swing.JPanel();
        Reset_Info = new javax.swing.JLabel();
        Reset_Round_Button = new javax.swing.JButton();
        End_Round_Display = new javax.swing.JLabel();
        EndGameTitle = new javax.swing.JLabel();
        RoundTransition = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TransitionInfo = new javax.swing.JLabel();
        Lives_Display = new javax.swing.JLabel();
        Crepe_Upgrade_Button = new javax.swing.JButton();
        Bakery_Upgrade_Button = new javax.swing.JButton();
        Drinks_Upgrade_Button = new javax.swing.JButton();
        Continue_Button = new javax.swing.JButton();
        note_background = new javax.swing.JLabel();
        Spending_Display = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Main = new javax.swing.JPanel();
        Main_Panel = new javax.swing.JPanel();
        StatsPanel = new javax.swing.JPanel();
        Sales_Display = new javax.swing.JLabel();
        Tip_Jar = new javax.swing.JButton();
        Registers = new javax.swing.JLabel();
        Character_Walking_Area = new javax.swing.JPanel();
        Main_StarterCharacter = new javax.swing.JLabel();
        clock_display1 = new javax.swing.JLabel();
        clock_display2 = new javax.swing.JLabel();
        Info_Display = new javax.swing.JLabel();
        clock_display3 = new javax.swing.JLabel();
        Station_Drinks = new javax.swing.JButton();
        Station_Crepes = new javax.swing.JButton();
        Station_Bakery = new javax.swing.JButton();
        Order_List = new javax.swing.JLabel();
        Orders_Header = new javax.swing.JLabel();
        Notepad = new javax.swing.JLabel();
        Countdown_Display = new javax.swing.JLabel();
        Rating_Display = new javax.swing.JLabel();
        StarIcon = new javax.swing.JLabel();
        Stats_Display = new javax.swing.JLabel();
        background_kitchen = new javax.swing.JLabel();
        background_outside = new javax.swing.JPanel();
        Order_Display_2 = new javax.swing.JLabel() {
            @Override
            public boolean contains(int x, int y) {
                return false;
            }
        };
        Order_Display_3 = new javax.swing.JLabel() {
            @Override
            public boolean contains(int x, int y) {
                return false;
            }
        };
        Order_Display_1 = new javax.swing.JLabel() {
            @Override
            public boolean contains(int x, int y) {
                return false;
            }
        };
        Order_Bubble3 = new javax.swing.JButton();
        Order_Bubble1 = new javax.swing.JButton();
        Order_Bubble2 = new javax.swing.JButton();
        npc_slot1 = new javax.swing.JLabel();
        npc_slot3 = new javax.swing.JLabel();
        npc_slot2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1280, 667));
        setMinimumSize(new java.awt.Dimension(1280, 667));
        setPreferredSize(new java.awt.Dimension(1280, 667));
        setResizable(false);

        Menu.setMaximumSize(new java.awt.Dimension(1280, 1150));
        Menu.setOpaque(false);
        Menu.setPreferredSize(new java.awt.Dimension(1280, 667));
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
        jLabel2.setText("__Customers___________________________________Rush__Hours_________________________________________Manage___");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(StartGame_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        Menu.add(Menu_Panel1);

        background_game_menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/intro_background.png"))); // NOI18N
        Menu.add(background_game_menu);

        getContentPane().add(Menu, java.awt.BorderLayout.CENTER);

        ResetTransition.setBackground(new java.awt.Color(39, 37, 39));
        ResetTransition.setMaximumSize(new java.awt.Dimension(1280, 667));
        ResetTransition.setMinimumSize(new java.awt.Dimension(1280, 667));
        ResetTransition.setPreferredSize(new java.awt.Dimension(1280, 667));
        ResetTransition.setLayout(new java.awt.BorderLayout());

        Reset_Info.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        Reset_Info.setForeground(new java.awt.Color(204, 206, 127));
        Reset_Info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Characters/Character_Pirate.png"))); // NOI18N
        Reset_Info.setText("Game Over");
        ResetTransition.add(Reset_Info, java.awt.BorderLayout.CENTER);

        Reset_Round_Button.setFont(new java.awt.Font("BLOXAT", 1, 36)); // NOI18N
        Reset_Round_Button.setText("Back_To_Menu");
        ResetTransition.add(Reset_Round_Button, java.awt.BorderLayout.PAGE_START);

        End_Round_Display.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        End_Round_Display.setForeground(new java.awt.Color(225, 223, 223));
        End_Round_Display.setText("Game Stats Display...");
        End_Round_Display.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        ResetTransition.add(End_Round_Display, java.awt.BorderLayout.PAGE_END);

        EndGameTitle.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        EndGameTitle.setForeground(new java.awt.Color(255, 204, 102));
        EndGameTitle.setText("Game Stats");
        EndGameTitle.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        ResetTransition.add(EndGameTitle, java.awt.BorderLayout.LINE_END);

        getContentPane().add(ResetTransition, java.awt.BorderLayout.PAGE_END);

        RoundTransition.setBackground(new java.awt.Color(183, 191, 126));
        RoundTransition.setMaximumSize(new java.awt.Dimension(1280, 667));
        RoundTransition.setMinimumSize(new java.awt.Dimension(1280, 667));
        RoundTransition.setPreferredSize(new java.awt.Dimension(1280, 667));
        RoundTransition.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Upgrade!");
        RoundTransition.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, 70, 50));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Loading_Images/load1.png"))); // NOI18N
        RoundTransition.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 330, 70, 120));

        TransitionInfo.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        TransitionInfo.setForeground(new java.awt.Color(76, 61, 43));
        TransitionInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Characters/npc_old.png"))); // NOI18N
        TransitionInfo.setText("<3");
        RoundTransition.add(TransitionInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 400, 310));

        Lives_Display.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        Lives_Display.setForeground(new java.awt.Color(51, 51, 51));
        Lives_Display.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/heart_icon.png"))); // NOI18N
        Lives_Display.setText("3/3");
        RoundTransition.add(Lives_Display, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 440, 220, 40));

        Crepe_Upgrade_Button.setBackground(new java.awt.Color(255, 204, 102));
        Crepe_Upgrade_Button.setFont(new java.awt.Font("BLOXAT", 1, 12)); // NOI18N
        Crepe_Upgrade_Button.setForeground(new java.awt.Color(51, 51, 51));
        Crepe_Upgrade_Button.setText("Crepe");
        RoundTransition.add(Crepe_Upgrade_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 240, 170, 40));

        Bakery_Upgrade_Button.setBackground(new java.awt.Color(255, 204, 102));
        Bakery_Upgrade_Button.setFont(new java.awt.Font("BLOXAT", 1, 12)); // NOI18N
        Bakery_Upgrade_Button.setForeground(new java.awt.Color(51, 51, 51));
        Bakery_Upgrade_Button.setText("Bakery");
        RoundTransition.add(Bakery_Upgrade_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 290, 170, 40));

        Drinks_Upgrade_Button.setBackground(new java.awt.Color(255, 204, 102));
        Drinks_Upgrade_Button.setFont(new java.awt.Font("BLOXAT", 1, 12)); // NOI18N
        Drinks_Upgrade_Button.setForeground(new java.awt.Color(51, 51, 51));
        Drinks_Upgrade_Button.setText("Drinks");
        RoundTransition.add(Drinks_Upgrade_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 340, 170, 40));

        Continue_Button.setBackground(new java.awt.Color(96, 85, 65));
        Continue_Button.setFont(new java.awt.Font("BLOXAT", 1, 12)); // NOI18N
        Continue_Button.setForeground(new java.awt.Color(51, 51, 51));
        Continue_Button.setText("Star__Next__Day");
        Continue_Button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Continue_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Continue_ButtonActionPerformed(evt);
            }
        });
        RoundTransition.add(Continue_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 480, 190, 40));

        note_background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/order_bubble.png"))); // NOI18N
        RoundTransition.add(note_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 116, 470));

        Spending_Display.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        Spending_Display.setForeground(new java.awt.Color(255, 255, 255));
        Spending_Display.setText("$0.00");
        RoundTransition.add(Spending_Display, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 100, 116, 80));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Main_Game_Assets/Order_Notepad.png"))); // NOI18N
        RoundTransition.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 170, 290, 270));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(81, 96, 81));
        jLabel8.setText("Lessen the time on stations!");
        RoundTransition.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 430, 200, 50));

        getContentPane().add(RoundTransition, java.awt.BorderLayout.PAGE_END);

        Main.setMaximumSize(new java.awt.Dimension(1280, 667));
        Main.setPreferredSize(new java.awt.Dimension(1280, 667));
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
        Sales_Display.setText("$0.00");
        StatsPanel.add(Sales_Display);
        Sales_Display.setBounds(660, 440, 60, 20);

        Tip_Jar.setBackground(new java.awt.Color(153, 153, 255));
        Tip_Jar.setFont(new java.awt.Font("Agency FB", 1, 10)); // NOI18N
        Tip_Jar.setForeground(new java.awt.Color(51, 51, 51));
        Tip_Jar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/coin_tips.png"))); // NOI18N
        Tip_Jar.setText("0");
        Tip_Jar.setContentAreaFilled(false);
        Tip_Jar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Tip_Jar.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        Tip_Jar.setIconTextGap(0);
        Tip_Jar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tip_JarActionPerformed(evt);
            }
        });
        StatsPanel.add(Tip_Jar);
        Tip_Jar.setBounds(470, 360, 110, 40);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Character_Walking_AreaLayout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addComponent(Main_StarterCharacter)
                .addGap(33, 33, 33))
        );

        StatsPanel.add(Character_Walking_Area);
        Character_Walking_Area.setBounds(0, 408, 1280, 260);
        StatsPanel.add(clock_display1);
        clock_display1.setBounds(1180, 300, 70, 80);
        StatsPanel.add(clock_display2);
        clock_display2.setBounds(340, 300, 70, 80);

        Info_Display.setFont(new java.awt.Font("Agency FB", 1, 48)); // NOI18N
        Info_Display.setForeground(new java.awt.Color(255, 204, 102));
        Info_Display.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/bubble_tea_truck.png"))); // NOI18N
        StatsPanel.add(Info_Display);
        Info_Display.setBounds(38, 550, 370, 90);
        StatsPanel.add(clock_display3);
        clock_display3.setBounds(250, 220, 70, 80);

        Station_Drinks.setBackground(new java.awt.Color(210, 201, 165));
        Station_Drinks.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N
        Station_Drinks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Drinks_Button.png"))); // NOI18N
        Station_Drinks.setBorder(null);
        Station_Drinks.setBorderPainted(false);
        Station_Drinks.setContentAreaFilled(false);
        Station_Drinks.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Station_Drinks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Station_DrinksActionPerformed(evt);
            }
        });
        StatsPanel.add(Station_Drinks);
        Station_Drinks.setBounds(1150, 380, 120, 30);

        Station_Crepes.setBackground(new java.awt.Color(210, 201, 165));
        Station_Crepes.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N
        Station_Crepes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Crepes_Button.png"))); // NOI18N
        Station_Crepes.setBorder(null);
        Station_Crepes.setBorderPainted(false);
        Station_Crepes.setContentAreaFilled(false);
        Station_Crepes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Station_Crepes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Station_CrepesActionPerformed(evt);
            }
        });
        StatsPanel.add(Station_Crepes);
        Station_Crepes.setBounds(330, 384, 80, 30);

        Station_Bakery.setBackground(new java.awt.Color(210, 201, 165));
        Station_Bakery.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N
        Station_Bakery.setForeground(new java.awt.Color(210, 201, 165));
        Station_Bakery.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Bakery_Button.png"))); // NOI18N
        Station_Bakery.setBorder(null);
        Station_Bakery.setBorderPainted(false);
        Station_Bakery.setContentAreaFilled(false);
        Station_Bakery.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Station_Bakery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Station_BakeryActionPerformed(evt);
            }
        });
        StatsPanel.add(Station_Bakery);
        Station_Bakery.setBounds(220, 310, 80, 30);

        Order_List.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        Order_List.setForeground(new java.awt.Color(96, 91, 54));
        Order_List.setToolTipText("");
        Order_List.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Order_List.setAlignmentY(0.4F);
        StatsPanel.add(Order_List);
        Order_List.setBounds(80, 110, 170, 120);

        Orders_Header.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        Orders_Header.setForeground(new java.awt.Color(204, 204, 204));
        Orders_Header.setText("Orders");
        StatsPanel.add(Orders_Header);
        Orders_Header.setBounds(80, 70, 170, 40);

        Notepad.setForeground(new java.awt.Color(153, 182, 146));
        Notepad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Main_Game_Assets/Order_Notepad.png"))); // NOI18N
        StatsPanel.add(Notepad);
        Notepad.setBounds(40, 20, 250, 280);

        Countdown_Display.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        Countdown_Display.setForeground(new java.awt.Color(255, 255, 255));
        Countdown_Display.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/clock.png"))); // NOI18N
        Countdown_Display.setText("0:00");
        StatsPanel.add(Countdown_Display);
        Countdown_Display.setBounds(1080, 0, 200, 80);

        Rating_Display.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        Rating_Display.setForeground(new java.awt.Color(255, 204, 102));
        Rating_Display.setText("Rating: ");
        StatsPanel.add(Rating_Display);
        Rating_Display.setBounds(330, 20, 170, 40);

        StarIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Main_Game_Assets/star.png"))); // NOI18N
        StatsPanel.add(StarIcon);
        StarIcon.setBounds(290, 20, 30, 40);

        Stats_Display.setFont(new java.awt.Font("Segoe UI Black", 1, 10)); // NOI18N
        Stats_Display.setForeground(new java.awt.Color(255, 204, 51));
        Stats_Display.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/coin_icon.png"))); // NOI18N
        Stats_Display.setText("Stats <>");
        StatsPanel.add(Stats_Display);
        Stats_Display.setBounds(580, 0, 390, 80);

        javax.swing.GroupLayout Main_PanelLayout = new javax.swing.GroupLayout(Main_Panel);
        Main_Panel.setLayout(Main_PanelLayout);
        Main_PanelLayout.setHorizontalGroup(
            Main_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Main_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(StatsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1274, Short.MAX_VALUE))
        );
        Main_PanelLayout.setVerticalGroup(
            Main_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(StatsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
        );

        Main.add(Main_Panel);

        background_kitchen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Main_Game_Assets/main_background.png"))); // NOI18N
        background_kitchen.setFocusable(false);
        background_kitchen.setVerifyInputWhenFocusTarget(false);
        Main.add(background_kitchen);

        background_outside.setBackground(new java.awt.Color(130, 114, 104));
        background_outside.setPreferredSize(new java.awt.Dimension(1998, 800));
        background_outside.setLayout(null);

        Order_Display_2.setFont(new java.awt.Font("Arial Narrow", 1, 12)); // NOI18N
        Order_Display_2.setForeground(new java.awt.Color(109, 86, 77));
        Order_Display_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Order_Display_2.setText("Crepes");
        background_outside.add(Order_Display_2);
        Order_Display_2.setBounds(710, 270, 80, 20);

        Order_Display_3.setFont(new java.awt.Font("Arial Narrow", 1, 12)); // NOI18N
        Order_Display_3.setForeground(new java.awt.Color(109, 86, 77));
        Order_Display_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Order_Display_3.setText("Crepes");
        background_outside.add(Order_Display_3);
        Order_Display_3.setBounds(570, 270, 80, 20);

        Order_Display_1.setFont(new java.awt.Font("Arial Narrow", 1, 12)); // NOI18N
        Order_Display_1.setForeground(new java.awt.Color(109, 86, 77));
        Order_Display_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Order_Display_1.setText("Crepes");
        background_outside.add(Order_Display_1);
        Order_Display_1.setBounds(860, 270, 80, 20);

        Order_Bubble3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/order_bubble.png"))); // NOI18N
        Order_Bubble3.setBorder(null);
        Order_Bubble3.setContentAreaFilled(false);
        Order_Bubble3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Order_Bubble3.setFocusPainted(false);
        Order_Bubble3.setHideActionText(true);
        background_outside.add(Order_Bubble3);
        Order_Bubble3.setBounds(560, 270, 100, 50);

        Order_Bubble1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/order_bubble.png"))); // NOI18N
        Order_Bubble1.setBorder(null);
        Order_Bubble1.setContentAreaFilled(false);
        Order_Bubble1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Order_Bubble1.setFocusPainted(false);
        Order_Bubble1.setHideActionText(true);
        background_outside.add(Order_Bubble1);
        Order_Bubble1.setBounds(850, 270, 100, 50);

        Order_Bubble2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/order_bubble.png"))); // NOI18N
        Order_Bubble2.setBorder(null);
        Order_Bubble2.setContentAreaFilled(false);
        Order_Bubble2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Order_Bubble2.setFocusPainted(false);
        Order_Bubble2.setHideActionText(true);
        background_outside.add(Order_Bubble2);
        Order_Bubble2.setBounds(700, 270, 100, 50);

        npc_slot1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Characters/npc_villager.png"))); // NOI18N
        background_outside.add(npc_slot1);
        npc_slot1.setBounds(820, 300, 100, 130);

        npc_slot3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Characters/npc_chineseV1.png"))); // NOI18N
        background_outside.add(npc_slot3);
        npc_slot3.setBounds(550, 300, 100, 130);

        npc_slot2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Characters/npc_fisherV1.png"))); // NOI18N
        background_outside.add(npc_slot2);
        npc_slot2.setBounds(680, 300, 100, 130);

        Main.add(background_outside);

        getContentPane().add(Main, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Select_LeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Select_LeftActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Select_LeftActionPerformed

    private void Tip_JarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Tip_JarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tip_JarActionPerformed

    private void Station_DrinksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Station_DrinksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Station_DrinksActionPerformed

    private void Station_CrepesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Station_CrepesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Station_CrepesActionPerformed

    private void Station_BakeryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Station_BakeryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Station_BakeryActionPerformed

    private void Continue_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Continue_ButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Continue_ButtonActionPerformed

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
    private javax.swing.JButton Bakery_Upgrade_Button;
    private javax.swing.JPanel Character_Walking_Area;
    private javax.swing.JButton Continue_Button;
    private javax.swing.JLabel Countdown_Display;
    private javax.swing.JButton Crepe_Upgrade_Button;
    private javax.swing.JButton Drinks_Upgrade_Button;
    private javax.swing.JLabel EndGameTitle;
    private javax.swing.JLabel End_Round_Display;
    private javax.swing.JPanel HowToPanel;
    private javax.swing.JLabel Info_Display;
    private javax.swing.JLabel Lives_Display;
    private javax.swing.JPanel Main;
    private javax.swing.JPanel Main_Panel;
    private javax.swing.JLabel Main_StarterCharacter;
    private javax.swing.JPanel Menu;
    private javax.swing.JPanel Menu_Panel1;
    private javax.swing.JLabel Notepad;
    private javax.swing.JButton Order_Bubble1;
    private javax.swing.JButton Order_Bubble2;
    private javax.swing.JButton Order_Bubble3;
    private javax.swing.JLabel Order_Display_1;
    private javax.swing.JLabel Order_Display_2;
    private javax.swing.JLabel Order_Display_3;
    private javax.swing.JLabel Order_List;
    private javax.swing.JLabel Orders_Header;
    private javax.swing.JLabel Rating_Display;
    private javax.swing.JLabel Registers;
    private javax.swing.JPanel ResetTransition;
    private javax.swing.JLabel Reset_Info;
    private javax.swing.JButton Reset_Round_Button;
    private javax.swing.JPanel RoundTransition;
    private javax.swing.JLabel Sales_Display;
    private javax.swing.JButton Select_Left;
    private javax.swing.JButton Select_Right;
    private javax.swing.JLabel Spending_Display;
    private javax.swing.JLabel StarIcon;
    private javax.swing.JButton StartGame_Button;
    private javax.swing.JLabel StarterCharacter;
    private javax.swing.JButton Station_Bakery;
    private javax.swing.JButton Station_Crepes;
    private javax.swing.JButton Station_Drinks;
    private javax.swing.JPanel StatsPanel;
    private javax.swing.JLabel Stats_Display;
    private javax.swing.JButton Tip_Jar;
    private javax.swing.JLabel TransitionInfo;
    private javax.swing.JLabel background_game_menu;
    private javax.swing.JLabel background_kitchen;
    private javax.swing.JPanel background_outside;
    private javax.swing.JLabel clock_display1;
    private javax.swing.JLabel clock_display2;
    private javax.swing.JLabel clock_display3;
    private javax.swing.JLabel description;
    private javax.swing.JLabel description1;
    private javax.swing.JLabel description2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel load1;
    private javax.swing.JLabel load2;
    private javax.swing.JLabel load3;
    private javax.swing.JLabel load4;
    private javax.swing.JPanel loading_phases;
    private javax.swing.JLabel note_background;
    private javax.swing.JLabel npc_slot1;
    private javax.swing.JLabel npc_slot2;
    private javax.swing.JLabel npc_slot3;
    // End of variables declaration//GEN-END:variables
}
