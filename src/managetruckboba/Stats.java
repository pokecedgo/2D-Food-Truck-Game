/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managetruckboba;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Timer;
/**
 *
 * @author pokec
 */
public class Stats {
        //Game Stats
    final int default_max_health = 3;
    
    final int max_health = 3;
    private static int current_health = 3; 
    private static int days_survived;
    private static double day_earnings;
    private static double total_earnings;
    private static int rating = 1; //default 1
    private static int missed_customers;
    private int max_missed_customers = 4; //default 4 
    private static int completed_orders;
    private static int tips; //resets each new day
    
    
    final int default_objective = 8;
    private static int objective = 3; //8 Customers to survive round
    private static int completed_objective = 0;
    
    //Countdown Variables

    private static double seconds_left = 10; 
    private static double round_duration = 10; //2 minutes
    private static Timer timer;
    private boolean isTimerRunning = false;
    private boolean isTimerActive = true; // variable to control the timer's state


    private static HashMap<String, String> pending_orders = new HashMap<>();

    private HashMap<String, Long> orderStartTime = new HashMap<>(); // Store start times for orders
    
    //Station Stats [Upgradable]
    final int DEFAULT_DURATION = 8; //game start/reset
    private static int CREPE_DURATION = 8; //Seconds
    private static int BAKERY_DURATION = 8; //Seconds
    private static int DRINKS_DURATION = 8; //Seconds
    private int PREP_TIME = 20; //20 seconds to prepare order or NPC goes away
   
    
    //handler for order buttons
    private static boolean button1_pressable = true;
    private static  boolean button2_pressable = true;
    private static boolean button3_pressable = true;
    
    private static boolean crepe_button_pressable = true;
    private static boolean drinks_button_pressable = true;
    private static boolean bakery_button_pressable = true;
    
    private static boolean hasStartedPlaying = false; 
    
    private static JLabel Sales_Display;
    private static JButton Tip_Jar;
    
    private String[] possible_orders = 
        {"Milk Tea", "Taro", "Matcha", "Lychee", "Jasmine", "Smoothie", "Milkshake",
            "Crepe", 
            "Mamon", "Croissant", "Muffin", "Macaron"};
    
    
    private String[] baked_goods = 
    {"Mamon", "Croissant", "Muffin", "Macaron"};
    
    private String[] drink_items = 
    {"Milk Tea", "Taro", "Matcha", "Lychee", "Jasmine", "Smoothie", "Milkshake"};
    
    private String[] crepe_items = {"Crepe"};
    
    
    
    private static JLabel Stats_Display;
    
    public void shareData(JLabel stat_d, JLabel sales, JButton tip) {
       this.Stats_Display = stat_d;
       this.Sales_Display = sales;
       this.Tip_Jar = tip;
    }
    
    public boolean compareItemExistence(String order, String data) {
        switch(data) {
            case "Bakery":
                return existsInArray(order, baked_goods);
            case "Drinks":
                return existsInArray(order, drink_items);
            case "Crepe":
                return existsInArray(order, crepe_items);
            default:
                return false;
        }
    }
    
    private boolean existsInArray(String item, String[] array) {
        for(String s : array) {
            if(s.equalsIgnoreCase(item)) {
                return true;
            }
        }
        return false;
    }
    
    //Objective Methods
    
    
    public void updateObjective() {
        int max_objective = 12;
        
        if(objective < max_objective) {
            objective += 1;
        }
    }
    
    public int returnObjective() {
        return objective;
    }
    
    public void resetObjective() {
        objective = default_objective;
        completed_objective = 0;
    }
    
    public int getCompletedObjective() {
        return completed_objective;
    }
    
    public String[] getPossibleOrders() {
        return Arrays.copyOf(possible_orders, possible_orders.length);
    }
    // Var Getters
        // Getter and Setter for button1_pressable
    public boolean isButton1Pressable() {
        return button1_pressable;
    }

    public void setButton1Pressable(boolean button1Pressable) {
        this.button1_pressable = button1Pressable;
    }

    // Getter and Setter for button2_pressable
    public boolean isButton2Pressable() {
        return button2_pressable;
    }

    public void setButton2Pressable(boolean button2Pressable) {
        this.button2_pressable = button2Pressable;
    }

    // Getter and Setter for button3_pressable
    public boolean isButton3Pressable() {
        return button3_pressable;
    }

    public void setButton3Pressable(boolean button3Pressable) {
        this.button3_pressable = button3Pressable;
    }

    // Getter and Setter for crepe_button_pressable
    public boolean isCrepeButtonPressable() {
        return crepe_button_pressable;
    }

    public void setCrepeButtonPressable(boolean data) {
        this.crepe_button_pressable = data;
        System.out.println("Updated variable: "+data);
    }

    // Getter and Setter for drinks_button_pressable
    public boolean isDrinksButtonPressable() {
        return drinks_button_pressable;
    }

    public void setDrinksButtonPressable(boolean data) {
        this.drinks_button_pressable = data;
        System.out.println("Updated variable: "+data);
    }

    // Getter and Setter for bakery_button_pressable
    public boolean isBakeryButtonPressable() {
        return bakery_button_pressable;
    }

    public void setBakeryButtonPressable(boolean data) {
        this.bakery_button_pressable = data;
        System.out.println("Updated variable: "+data);
    }
    
    
    // Other Getters
    public void updateRespawnBoolean(Boolean data) {
        hasStartedPlaying = data;
        
    }
    
    public boolean getIsPlaying() {
        return hasStartedPlaying;
    }
    
    public void addPendingOrder(String slot, String order) {
          pending_orders.put(slot, order);
          System.out.println("Added to pending orders "+pending_orders);
    }

    
    // Getter method for accessing the orderStartTime HashMap
    public boolean hasPendingOrder(String slot) {
        return pending_orders.containsKey(slot);
    }
      
    public HashMap<String, String> getPendingOrders() {
        System.out.println("Returning: "+pending_orders);
        return new HashMap<>(pending_orders);
    }

    public Set<Map.Entry<String, String>> getPendingOrdersEntrySet() {
        return pending_orders.entrySet();
    }
    
    public void removeOrderFromPendingOrders(String slot) {
        pending_orders.remove(slot);
    }
   
    public void removeEntryFromOrderStartTime(String slot) {
        orderStartTime.remove(slot);
    }
    
    public void updateCompletedOrders() {
        completed_orders++;
        completed_objective++;
    }
    
    public void updateCurrentHealth() {
        current_health--;
        System.out.println("Current Health: "+current_health);
    }
    
    public boolean containsKeyInOrderStartTime(String slot) {
        return orderStartTime.containsKey(slot);
    }
    
    public void getOrderStartTime(String slot) {
        orderStartTime.put(slot, System.currentTimeMillis());
    }
    
    public Long getValueFromOrderStartTime(String slot) {
        return orderStartTime.get(slot);
    }
    
    public int getMaxHealth() {
        return max_health;
    }

    public int getCurrentHealth() {
        return current_health;
    }

    public int getDaysSurvived() {
        return days_survived;
    }

    public double getDayEarnings() {
        return day_earnings;
    }
    
    public void updateDayEarnings(double earning) {
        day_earnings += earning;
    }

    public double getTotalEarnings() {
        return total_earnings;
    }
    
    public void updateTotalEarnings(double earning) {
        total_earnings += earning;
    }

    public int getRating() {
        return rating;
    }
    
    public void updateRating(int x) {
        rating += x;
    }
    
    public void resetRound(String data) {
        //reset tips, earnings, etc.
        tips = 0;
        missed_customers = 0;
        completed_objective = 0;
        day_earnings = 0;
        current_health--;
        
        String formattedString = String.format("%.2f$", getDayEarnings());
        Sales_Display.setText(formattedString);      
        
        Tip_Jar.setText("$"+getTips());
        
        //reset countdown timer
        seconds_left = round_duration;
        
        //clear pending orders / update notepad
        pending_orders.clear();
        
        //update objective to new req
        if(data.equals("survived")) {
            objective += 1;
            days_survived++;
        }
        Stats stats = new Stats();
        NPCButtons NPCButtons = new NPCButtons(stats);
        NPCButtons.updateNotePad();
         
        //update stats display
        NPCButtons.updateTipDisplay();
        
        //update stats display
        UIBobaTruck st_display = new UIBobaTruck();
     
        updateStatsDispay();
        

    }
    
    public void ResetGame() {
        updateRespawnBoolean(false);
        pending_orders.clear();
        objective = default_objective; //default to 8
        
        //reset tips, earnings, etc.
        tips = 0;
        days_survived = 0;
        missed_customers = 0;
        completed_objective = 0;
        day_earnings = 0;
        current_health = default_max_health;
        
        Tip_Jar.setText("");
        
        Stats stats = new Stats();
        NPCButtons NPCButtons = new NPCButtons(stats);
        NPCButtons.updateNotePad();
         
        //update stats display
        NPCButtons.updateTipDisplay();
        
        //update stats display
        UIBobaTruck st_display = new UIBobaTruck();
     
        updateStatsDispay();
        
        
        button1_pressable = true;
        button2_pressable = true;
        button3_pressable = true;

        crepe_button_pressable = true;
        drinks_button_pressable = true;
        bakery_button_pressable = true;
        
        CREPE_DURATION = DEFAULT_DURATION; //Seconds
        BAKERY_DURATION = DEFAULT_DURATION; //Seconds
       DRINKS_DURATION = DEFAULT_DURATION; //Seconds
        
    }
    
    public void updateStatsDispay() {
        String formattedString1 = String.format("%.2f", getTotalEarnings());
        Stats_Display.setText("$"+formattedString1+"   Day "+(int)getDaysSurvived() + " \t\t\t\tLives ["+getCurrentHealth()+"/"+getMaxHealth()+"]    Objective: Serve "+completed_objective+"/"+objective+" Customers!");

    }
    public int getMissedCustomers() {
        return missed_customers;
    }
    
    public void updateMissedCustomers() {
         missed_customers++;
    }

    public int getMaxMissedCustomers() {
        return max_missed_customers;
    }

    public int getCompletedOrders() {
        return completed_orders;
    }

    public int getTips() {
        return tips;
    }

    public void updateTips(double earning) {
        tips += earning;
    }
    
    public void updateTips_Int() {
        total_earnings += tips;
        day_earnings += tips;
        //reset tips
        tips = 0;
    }
    
    public int getCrepeDuration() {
        return CREPE_DURATION;
    }

    public int getBakeryDuration() {
        return BAKERY_DURATION;
    }

    public int getDrinksDuration() {
        return DRINKS_DURATION;
    }

    public int getPrepTime() {
        return PREP_TIME;
    }
    
    public void setCrepeDuration(int duration) {
        CREPE_DURATION = duration;
      //  System.out.println("Crepe Duration Resetted: "+CREPE_DURATION);
    }

    // Setter method for updating the BAKERY_DURATION
    public void setBakeryDuration(int duration) {
        BAKERY_DURATION = duration;
       // System.out.println("Bakery Duration Resetted: "+BAKERY_DURATION);
    }

    // Setter method for updating the DRINKS_DURATION
    public void setDrinksDuration(int duration) {
        DRINKS_DURATION = duration;        
        //System.out.println("Drinks Duration Resetted: "+DRINKS_DURATION);
        
    }
    
    public int calculateRating() {
        // Rating based on missed_customers, total_earnings, days_survived, and completed_orders

        Stats stats = new Stats();

        // Using 100 as a normalization factor for completed_orders
        double normalizationFactorForOrders = 100.0;

        double ratingPercentage = (1 - (double) stats.getMissedCustomers() / stats.getMaxMissedCustomers()) * 0.32 +
                                 (stats.getTotalEarnings() / 100) * 0.24 +
                                 (double) stats.getDaysSurvived() / 30 * 0.24 +
                                 (double) stats.getCompletedOrders() / normalizationFactorForOrders * 0.2;

        int calculatedRating = (int) Math.ceil(ratingPercentage * 5);

        // Ensure valid range (1-5)
        return Math.max(1, Math.min(5, calculatedRating));
    }
   
    

    //Countdown Methods
    public void pauseTimer() {
        if (timer != null && isTimerRunning) {
            timer.stop();
            isTimerRunning = false;
        }
    }

    public void resumeTimer() {
        if (timer != null && !isTimerRunning) {
            timer.start();
            isTimerRunning = true;
        }
    }

    public void stopTimer() {
        if (timer != null) {
            timer.stop();
            isTimerRunning = false;
        }
        isTimerActive = false;
    }

    public void startNewRound() {
        seconds_left = round_duration;
        isTimerActive = true;
        if (isTimerRunning) {
            timer.restart();
        } else {
            timer.start();
            isTimerRunning = true;
        }
    }
    
    public void RoundTimer(JLabel countdown_display) {
        int delay = 1000; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (seconds_left > 0 && isTimerActive) {
                    seconds_left--;
                    countdown_display.setText(formatTime(seconds_left));
                } else if (seconds_left == 0) {
                    timer.stop();
                    isTimerRunning = false;
                    System.out.println("Round Ended!");
                    
                    Reset Reset = new Reset();
                    Reset.Start_New_Round();
                }
            }
        };
        timer = new Timer(delay, taskPerformer);
        timer.start();
        isTimerRunning = true;
    }

    private String formatTime(double seconds) {
        int minutesPart = (int) seconds / 60;
        int secondsPart = (int) seconds % 60;
        return String.format("%d:%02d", minutesPart, secondsPart);
    }


}
