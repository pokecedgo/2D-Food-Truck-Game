/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managetruckboba;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author pokec
 */
public class Stats {
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
    private int completed_orders;
    private double tips; //resets each new day
    

    private HashMap<String, String> pending_orders = new HashMap<>();

    private HashMap<String, Long> orderStartTime = new HashMap<>(); // Store start times for orders
    
    //Station Stats [Upgradable]
    private int DEFAULT_DURATION = 8; //game start/reset
    private int CREPE_DURATION= DEFAULT_DURATION; //Seconds
    private int BAKERY_DURATION = DEFAULT_DURATION; //Seconds
    private int DRINKS_DURATION = DEFAULT_DURATION; //Seconds
    private int PREP_TIME = 20; //20 seconds to prepare order
   
    
    //handler for order buttons
    private boolean button1_pressable = true;
    private boolean button2_pressable = true;
    private boolean button3_pressable = true;
    
    private boolean crepe_button_pressable = true;
    private boolean drinks_button_pressable = true;
    private boolean bakery_button_pressable = true;
    
    private boolean hasStartedPlaying = false; 
   
    private String[] possible_orders = 
        {"Milk Tea", "Taro", "Matcha", "Lychee", "Jasmine", "Smoothie", "Milkshake",
            "Crepe", 
            "Mamon", "Croissant", "Muffin", "Macaron"};
    
    
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

    public void setCrepeButtonPressable(boolean crepeButtonPressable) {
        this.crepe_button_pressable = crepeButtonPressable;
    }

    // Getter and Setter for drinks_button_pressable
    public boolean isDrinksButtonPressable() {
        return drinks_button_pressable;
    }

    public void setDrinksButtonPressable(boolean drinksButtonPressable) {
        this.drinks_button_pressable = drinksButtonPressable;
    }

    // Getter and Setter for bakery_button_pressable
    public boolean isBakeryButtonPressable() {
        return bakery_button_pressable;
    }

    public void setBakeryButtonPressable(boolean bakeryButtonPressable) {
        this.bakery_button_pressable = bakeryButtonPressable;
    }
    
    
    // Other Getters
    public void updateRespawnBoolean(Boolean data) {
        System.out.println("hasStartedPlaying has been set to: "+data);
        hasStartedPlaying = data;
          System.out.println("hasStartedPlaying has been set to: "+hasStartedPlaying);
    }
    
    public boolean getIsPlaying() {
        System.out.println("Returning: "+hasStartedPlaying);
        return hasStartedPlaying;
    }
    
     public void getPendingOrders(String slot, String order) {
        pending_orders.put(slot, order);
    }
     
    
    // Getter method for accessing the orderStartTime HashMap
    public boolean hasPendingOrder(String slot) {
        return pending_orders.containsKey(slot);
    }
      
    public Collection<String> getPendingOrdersValues() {
        return pending_orders.values();
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
    }
    
    public void updateCurrentHealth() {
        current_health--;
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

    public int getMissedCustomers() {
        return missed_customers;
    }

    public int getMaxMissedCustomers() {
        return max_missed_customers;
    }

    public int getCompletedOrders() {
        return completed_orders;
    }

    public double getTips() {
        return tips;
    }

    public void updateTips(double earning) {
        tips += earning;
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
    
    public void setCrepeDuration() {
        CREPE_DURATION = DEFAULT_DURATION;
    }

    // Setter method for updating the BAKERY_DURATION
    public void setBakeryDuration() {
        BAKERY_DURATION = DEFAULT_DURATION;
    }

    // Setter method for updating the DRINKS_DURATION
    public void setDrinksDuration() {
        DRINKS_DURATION = DEFAULT_DURATION;
    }
    
    public int calculateRating() {
        //rating based on missed_customers, total_earnings, and days_survived
        // You can use a formula like this:
        Stats stats = new Stats();
        
        double ratingPercentage = (1 - (double) stats.getMissedCustomers() / stats.getMaxMissedCustomers() * 0.4 +
                                 (stats.getTotalEarnings() / 100) * 0.3 +
                                 (1 - (double) stats.getDaysSurvived()) / 30) * 0.3;
        int calculatedRating = (int) Math.ceil(ratingPercentage * 5);

        //  valid range (1-5)
        return Math.max(1, Math.min(5, calculatedRating));
    }
}
