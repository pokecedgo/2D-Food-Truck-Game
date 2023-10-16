/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managetruckboba;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author pokec
 */
public class NPCButtons {
    //Button handler
        
    private JLabel Order_Display_1;
    private JLabel Order_Display_2;
    private JLabel Order_Display_3;
    

    
    private JButton Order_Bubble1;
    private JButton Order_Bubble2;
    private JButton Order_Bubble3;
    
    //handler for order buttons
    private boolean button1_pressable;
    private boolean button2_pressable;
    private boolean button3_pressable;
    
    private JLabel Main_StarterCharacter;
    private JButton Station_Crepes;
    private JButton Station_Drinks;
    private JButton Station_Bakery;
    
    private JLabel Order_List;
    
    private JLabel clock_display1;
    private JLabel clock_display2;
    private JLabel clock_display3;
    private JLabel Sales_Display;
    private JLabel Stats_Display;

    String[] greetings = {"Waiting!", ":)", "<3","Okay!","Sweet!"};
    
    private Stats stats;

    public void share_data(JLabel stats,JLabel sales, JLabel main_c, JButton crepes, JButton bakery, JButton drinks, JLabel d1, JLabel d2, JLabel d3, JButton b1, JButton b2, JButton b3, JLabel orderlist, JLabel cd1, JLabel cd2, JLabel cd3) {
        System.out.println("Data Shared with NPCButtons.java");
        this.Order_Display_1 = d1;
        this.Order_Display_2 = d2;
        this.Order_Display_3 = d3;
        
        this.Order_Bubble1 = b1;
        this.Order_Bubble2 = b2;
        this.Order_Bubble3 = b3;
        
        this.Station_Crepes = crepes;
        this.Station_Drinks = drinks;
        this.Station_Bakery = bakery;
        
        this.Main_StarterCharacter = main_c;
        this.Order_List = orderlist;
        
        this.clock_display1 = cd1;
        this.clock_display2 = cd2;
        this.clock_display3 = cd3;
        
        this.Sales_Display = sales;
        this.Stats_Display = stats;
        
    
    
    }
    
    public NPCButtons(Stats stats) {
        this.stats = stats;
    }
        
    public void NPC_Buttons() {
        //Order Acceptance System
        //button 1
        System.out.println("NPC Order Buttons Initialized!");      
        Random random = new Random();
        
        SoundHandler playSound = new SoundHandler();
        // Button 1
        Order_Bubble1.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) { 
                  // Play click sound 
                  playSound.playSound("src/SoundAssets/click.wav");

                  if (button1_pressable == true) {
                      button1_pressable = false;
                      // Get the slot for this button (you should specify the slot logic)
                      String slot = "Slot1";
                      // Get the order text from the display
                      String order = Order_Display_1.getText();

                      // Add the order to the pending_orders HashMap
                      Stats stats = new Stats();
                      
                      stats.getPendingOrders(slot, order);

                      int randomIndex = random.nextInt(greetings.length);
                      String random_greet = greetings[randomIndex];
                      Order_Display_1.setText(random_greet);
                      updateNotePad();
                      
                      //start timer (Track how long user takes)
                      stats.getOrderStartTime(slot);
                  }
              }
          });

        // Button 2
        Order_Bubble2.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) { 
                  // Play click sound 
                  playSound.playSound("src/SoundAssets/click.wav");

                  if (button2_pressable == true) {
                      button2_pressable = false;
                      // Get the slot for this button (you should specify the slot logic)
                      String slot = "Slot2";
                      // Get the order text from the display
                      String order = Order_Display_2.getText();

                      // Add the order to the pending_orders HashMap
                      Stats stats = new Stats();
                      
                      stats.getPendingOrders(slot, order);


                      int randomIndex = random.nextInt(greetings.length);
                      String random_greet = greetings[randomIndex];
                      Order_Display_2.setText(random_greet);
                      updateNotePad();
                      //start timer (Track how long user takes)
                      stats.getOrderStartTime(slot);
                      
                  }
              }
          });

        // Button 3
        Order_Bubble3.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) { 
                  // Play click sound 
                  playSound.playSound("src/SoundAssets/click.wav");

                  if (button3_pressable == true) {
                      button3_pressable = false;
                      // Get the slot for this button (you should specify the slot logic)
                      String slot = "Slot3";
                      // Get the order text from the display
                      String order = Order_Display_3.getText();

                      // Add the order to the pending_orders HashMap
                      Stats stats = new Stats();
                      
                      stats.getPendingOrders(slot, order);


                      int randomIndex = random.nextInt(greetings.length);
                      String random_greet = greetings[randomIndex];
                      Order_Display_3.setText(random_greet);
                      updateNotePad();
                      
                      //start timer (Track how long user takes)
                      stats.getOrderStartTime(slot);
                  }
              }
          });

        
        
        // Station Buttons (Crepes, Bakery, Drinks)
        Station_Crepes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                // Play click sound 
                playSound.playSound("src/SoundAssets/click.wav");
                System.out.println("Station Pressed: Crepe");
  
                // Check if the user is nearby the Crepe station
                if (isPlayerCloseToStation("Crepe") == true && stats.isCrepeButtonPressable() == true) {
                    System.out.println("Player is Close to Crepe Station");
                    String current_slot = null; // Initialize current_slot as null
                    
                    Stats stats = new Stats();
                      
              
                    for (Map.Entry<String, String> entry : stats.getPendingOrdersEntrySet()) {
                        String slot = entry.getKey();
                        String order = entry.getValue();

                        // Check if the order exists in the possible_orders array
                        if (Arrays.asList(stats.getPossibleOrders()).contains(order)) {
                            System.out.print("     | Initiating process for Station");
                          
                            stats.setCrepeButtonPressable(false);                     
                            // Store the current slot in the current_slot variable
                            current_slot = slot; // slot 1, 2, or 3
                            
                            SoundHandler playSound = new SoundHandler();
                            playSound.playSound("src/SoundAssets/Crepe_Sizzle.wav");
                            // Disable player movement
                            
                            PlayerMovement updateMove = new PlayerMovement();
                            updateMove.updateCanMove(false);
                            
                            visualDisplayTimerAnimation("Crepe", current_slot, clock_display2);
                            // Break the loop since we've found and processed the order
                            break;
                        }
                    }
                }
            }
        });


        Station_Bakery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                // Play click sound 
                playSound.playSound("src/SoundAssets/click.wav");
                System.out.println("Station Pressed: Bakery");
                // Check if the user is nearby the Crepe station
                if (isPlayerCloseToStation("Bakery") == true && stats.isBakeryButtonPressable() == true) {
                    System.out.println("Player is Close to Bakery Station");
                    String current_slot = null; // Initialize current_slot as null
                    
                    Stats stats = new Stats();
                    for (Map.Entry<String, String> entry : stats.getPendingOrdersEntrySet()) {
                        String slot = entry.getKey();
                        String order = entry.getValue();
                        
                        // Check if the order exists in the possible_orders array
                        if (Arrays.asList(stats.getPossibleOrders()).contains(order)) {
                            System.out.print("     | Initiating process for Station");
                            
                            stats.setBakeryButtonPressable(false);
                            // Store the current slot in the current_slot variable
                            current_slot = slot; // slot 1, 2, or 3
                            
                            SoundHandler playSound = new SoundHandler();
                            playSound.playSound("src/SoundAssets/Lid_Open.wav");
                            // Disable player movement
                            PlayerMovement updateMove = new PlayerMovement();
                            updateMove.updateCanMove(false);
                            
                            visualDisplayTimerAnimation("Bakery", current_slot, clock_display3);
                            // Break the loop since we've found and processed the order
                            break;
                        }
                    }

                }
            }
        });    
        
        
        Station_Drinks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                // Play click sound 
                playSound.playSound("src/SoundAssets/click.wav");
                System.out.println("Station Pressed: Drinks");
                // Check if the user is nearby the Crepe station
                if (isPlayerCloseToStation("Drinks") == true && stats.isDrinksButtonPressable() == true) {
                    System.out.println("Player is Close to Drinks Station");
                    String current_slot = null; // Initialize current_slot as null
                   
                    Stats stats = new Stats();
                                          
                    
                    for (Map.Entry<String, String> entry : stats.getPendingOrdersEntrySet()) {
                        String slot = entry.getKey();
                        String order = entry.getValue();

                        // Check if the order exists in the possible_orders array
                        if (Arrays.asList(stats.getPossibleOrders()).contains(order)) {
                            System.out.print("     | Initiating process for Station");
                            
                            stats.setDrinksButtonPressable(false);   
                            // Store the current slot in the current_slot variable
                            current_slot = slot; // slot 1, 2, or 3
                            playSound.playSound("src/SoundAssets/click.wav");
                            // Disable player movement
                            
                            PlayerMovement updateCanMove = new PlayerMovement();
                            updateCanMove.updateCanMove(false);
                            
                            visualDisplayTimerAnimation("Drinks", current_slot, clock_display1);
                            // Break the loop since we've found and processed the order
                            break;
                        }
                    }

                }
            }
        });    
    }
    
    
    // Define a Timer object for clock animation
    private Timer clockAnimationTimer;
    boolean debounce_completed = false; // Debounce to avoid continuous loop of timer
    private boolean orderCompleted = false;



    public void visualDisplayTimerAnimation(String station, String slot, JLabel clock_display) {
        Random random = new Random();

        // Check if the station is in progress
        boolean stationInProgress = false;
        final int[] currentDuration = { 0 }; // Declare as final and use an array
        final String[] imagePaths;
        final int[] currentImageIndex = { 0 }; // Use an array to make it effectively final
        
        Stats stats = new Stats();
        if (station.equals("Crepe") && stats.getCrepeDuration() > 0) {
            stationInProgress = true;
            currentDuration[0] = stats.getCrepeDuration(); // Assign the initial value
            imagePaths = new String[] {
                "src/Assets/Main_Game_Assets/Clock_Frames/clock_frame1.png",
                "src/Assets/Main_Game_Assets/Clock_Frames/clock_frame2.png",
                "src/Assets/Main_Game_Assets/Clock_Frames/clock_frame3.png"
            };

            // Initialize the clock animation timer
            initializeClockAnimationTimer(imagePaths, clock_display);
        } else if (station.equals("Bakery") && stats.getBakeryDuration() > 0) {
            stationInProgress = true;
            currentDuration[0] = stats.getBakeryDuration(); // Assign the initial value
            imagePaths = new String[] {
                "src/Assets/Main_Game_Assets/Clock_Frames/clock_frame1.png",
                "src/Assets/Main_Game_Assets/Clock_Frames/clock_frame2.png",
                "src/Assets/Main_Game_Assets/Clock_Frames/clock_frame3.png"
            };

            // Initialize the clock animation timer
            initializeClockAnimationTimer(imagePaths, clock_display);
        } else if (station.equals("Drinks") && stats.getDrinksDuration() > 0) {
            stationInProgress = true;
            currentDuration[0] = stats.getDrinksDuration(); // Assign the initial value
            imagePaths = new String[] {
                "src/Assets/Main_Game_Assets/Clock_Frames/clock_frame1.png",
                "src/Assets/Main_Game_Assets/Clock_Frames/clock_frame2.png",
                "src/Assets/Main_Game_Assets/Clock_Frames/clock_frame3.png"
                // Add more image paths here to match the desired duration
            };

            // Initialize the clock animation timer
            initializeClockAnimationTimer(imagePaths, clock_display);
        } else {
            return;
        }
        
        /*
        Where we left off:
        1. Clock continuously gets stuck on loop on anything after first order? (Bakery station test case)
        2. Character teleports to starting position when a station button is pressed
        3. NPC Respawning is a little buggy (The display text isnt proper and npc is not disappearing during the reload option)
        */
        if (stationInProgress) {
            System.out.println(station + " Station in Progress!");
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (currentDuration[0] > 0) {
                        currentDuration[0]--;
                    } else if (debounce_completed == false && orderCompleted == false) {
                        debounce_completed = true;

                        // Preparation Completed
                        System.out.println("\n-------------\nOrder Completed! [" + station + " Station]\n----------------\n");
                        clock_display.setVisible(false);
                        clock_display.setIcon(new ImageIcon(""));
                        
                        Stats stats = new Stats();
                        // Reset debounce buttons
                        if (station.equals("Crepe")) {
                            stats.setCrepeButtonPressable(false);
                            stats.setCrepeDuration();
                        } else if (station.equals("Bakery")) {
                            stats.setBakeryButtonPressable(false);
                            stats.setBakeryDuration();
                        } else if (station.equals("Drinks")) {
                            stats.setDrinksButtonPressable(false);
                            stats.setDrinksDuration();
                       
                        }
                        // Enable player movement
                        
                        PlayerMovement updateMove = new PlayerMovement();
                        updateMove.updateCanMove(true);
                        // Stats Increase
                        // Call stats update function
                        updateStats(slot);

                        // Set orderCompleted to true for the next order
                        orderCompleted = true;
                    }
                }
            });
            timer.start();
        }
    }

    // Function to initialize the clock animation timer
    private int clockImageIndex = 0;

    // Function to initialize the clock animation timer
    private void initializeClockAnimationTimer(String[] imagePaths, JLabel clock_display) {
        int delay = 1000; // Delay in milliseconds between clock frames

        clockAnimationTimer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display the current clock frame
                clock_display.setIcon(new ImageIcon(imagePaths[clockImageIndex]));

                // Increment the image index and loop back to the first image if needed
                clockImageIndex = (clockImageIndex + 1) % imagePaths.length;
            }
        });

        // Start the clock animation timer
        clockAnimationTimer.start();
    }

    public boolean isPlayerCloseToStation(String station) {    
        //game tested distance
        int threshold_bakery = 311;
        int threshold_crepes = 385;
        int threshold_drinks = 382;
 
        if (station.equals("Crepe")) {
            // Get current position of the player character
            int playerX = Main_StarterCharacter.getX();
            int playerY = Main_StarterCharacter.getY();

           
            int stationX = Station_Crepes.getX();
            int stationY = Station_Crepes.getY();

            int distance = (int) Math.sqrt(Math.pow(playerX - stationX, 2) + Math.pow(playerY - stationY, 2));

            System.out.println("Distance: "+distance);
            return distance < threshold_crepes && distance > 365;
        } else if (station.equals("Drinks")) {
                 
            int playerX = Main_StarterCharacter.getX();
            int playerY = Main_StarterCharacter.getY();

           
            int stationX = Station_Drinks.getX();
            int stationY = Station_Drinks.getY();

            int distance = (int) Math.sqrt(Math.pow(playerX - stationX, 2) + Math.pow(playerY - stationY, 2));

            System.out.println("Distance: "+distance);
            return distance < threshold_drinks && distance > 365;
        } else if (station.equals("Bakery")) {
            int playerX = Main_StarterCharacter.getX();
            int playerY = Main_StarterCharacter.getY();

           
            int stationX = Station_Bakery.getX();
            int stationY = Station_Bakery.getY();

            int distance = (int) Math.sqrt(Math.pow(playerX - stationX, 2) + Math.pow(playerY - stationY, 2));

            System.out.println("Distance: "+distance);
            return distance < threshold_bakery && distance > 300;
        }

      
        return false;
    }
    
    public void updateNotePad() {
        // Update the order list using HTML for line breaks
        StringBuilder finalList = new StringBuilder("<html>");

        int index = 1;
        
        Stats stats = new Stats();
        for (String order : stats.getPendingOrdersValues()) {
            finalList.append(index++).append(". ").append(order).append("<br>");
        }

        finalList.append("</html>");

        Order_List.setText(finalList.toString());
    }
    

   
    public boolean completeOrder(String slot) {
        if (stats.containsKeyInOrderStartTime(slot)) {
            long startTime = stats.getValueFromOrderStartTime(slot);
            long endTime = System.currentTimeMillis();
            long timeTaken = (endTime - startTime) / 1000; // Convert to seconds

            if (timeTaken <= stats.getPrepTime()) {
                //succesful order
                return true;
            } else {
                // The player took longer than 20 seconds
                return false;
            }
          
        }
        
        return false;
    }
    
    public void updateStats(String slot) {
        Random random = new Random();
        Stats stats = new Stats();
        // Determine if it's a missed customer and check if order is still an existing order
        if (completeOrder(slot) == true && stats.hasPendingOrder(slot)) {
            // Successful order completion
            if (slot.equals("Slot1")) {
                Order_Display_1.setText("Thanks!");
            } else if (slot.equals("Slot2")) {
                Order_Display_2.setText("Thanks!");
            } else if (slot.equals("Slot3")) {
                Order_Display_3.setText("Thanks!");
            }

            // Increase day earnings randomly between 4-9$
            double earningsToday = 4 + random.nextDouble() * 5;
            stats.updateDayEarnings(earningsToday);
    
            String formattedString = String.format("%.2f$", stats.getDayEarnings());
            Sales_Display.setText(formattedString);

            
            System.out.println("Earnings today: " + earningsToday);

            // Increase total earnings by a random selected amount
            double earningsIncrease = 1 + random.nextDouble() * 10;
            stats.updateTotalEarnings(earningsIncrease);
            System.out.println("Earnings increase: " + earningsIncrease);
            
            // 50% chance customer tips
            if (random.nextBoolean()) {
                // If tips, add on a random tip amount 4-10$
                double tipAmount = 4 + random.nextDouble() * 7;
                stats.updateTips(tipAmount);
                System.out.println("Tip amount: " + tipAmount);
            }

            // Calculate new Truck rating
            
            stats.updateRating(stats.calculateRating());
            System.out.println("New Truck rating: " + stats.getRating());
            Stats_Display.setText("Rating: " + stats.getRating());
            
            //remove the order completed from Pending_order hashmap
            stats.removeOrderFromPendingOrders(slot);
            stats.updateCompletedOrders();
            //notepad update
            updateNotePad();
            
            NPC_Spawning npc = new NPC_Spawning();
            //respawn function for specific slots
            if(slot.equals("Slot1")) {    
                npc.NPC_Respawn(1);
            } else if(slot.equals("Slot2")) {
                npc.NPC_Respawn(2);
            } else if(slot.equals("Slot3")) {
                npc.NPC_Respawn(3);
            }

 
        } else if (completeOrder(slot) == false && stats.hasPendingOrder(slot)) { // Order Missed!
            // Remove the start time entry from the HashMap
            stats.removeEntryFromOrderStartTime(slot);

            if (slot.equals("Slot1")) {
                Order_Display_1.setText("Too Slow!");
            } else if (slot.equals("Slot2")) {
                Order_Display_2.setText("Too Slow!");
            } else if (slot.equals("Slot3")) {
                Order_Display_3.setText("Too Slow!");
            }

            // Take a life away if not already at minimum health
            if (stats.getCurrentHealth() > 0) {
                stats.updateCurrentHealth();
            } else {
                // All mistakes used, reset the game
                Reset reset = new Reset();
                reset.Reset_Game();
            }
        } else {
            //if this runs, its a part of the loop
            return;
        }
        
        //Update Stats Label
        String formattedString = String.format("%.2f$", stats.getTotalEarnings());
        Stats_Display.setText("Total Earnings: $"+formattedString+"  Days Survived["+(int)stats.getDaysSurvived()+"]");
        debounce_completed = false;
        
    }


}
