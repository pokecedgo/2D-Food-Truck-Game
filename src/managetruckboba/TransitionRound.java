
package managetruckboba;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import managetruckboba.Stats;

public class TransitionRound extends Stats {
    // Display Total Earnings/ Handle Buttons
    
    //Upgrade Costs
    private static double Crepe_Upgrade = 25; // default 25
    private static double Drink_Upgrade = 25; // default $25
    private static double Bakery_Upgrade = 25; // default $25

    private static JButton Crepe_Upgrade_Button;
    private static JButton Drinks_Upgrade_Button;
    private static JButton Bakery_Upgrade_Button;
    private static JButton Continue_Button;
    private static JLabel Lives_Display;

    private static JLabel Spending_Display;
    
    public void shareData(JLabel lives, JButton crepe, JButton drinks, JButton bakery, JButton con) {
        this.Crepe_Upgrade_Button = crepe;
        this.Drinks_Upgrade_Button = drinks;
        this.Bakery_Upgrade_Button = bakery;
        this.Continue_Button = con;
        this.Lives_Display = lives;
        updateDisplay();
        initButtonListeners();
    }

    public void ResetToDefault() {
        //reset all upgrade costs
        Crepe_Upgrade = 25; // default 25
        Drink_Upgrade = 25; // default $25
        Bakery_Upgrade = 25; // default $25
    }
    private void initButtonListeners() {
        Crepe_Upgrade_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             //check if player has enough money and check if upgrade time is greater than 1 seconds 
             if(getTotalEarnings() > Crepe_Upgrade && getCrepeDuration() > 1) {
                 SoundHandler sound = new SoundHandler();
                 sound.playSound("src/SoundAssets/CashSound.wav");
                 
                 //Crepe Upgrade cost + $15
                 Crepe_Upgrade += 15;
                 //update duration by 1 second
                 setCrepeDuration(getCrepeDuration()-1);
                
                 
                 //update button display
                 updateDisplay();
             }
            }
        });

        Drinks_Upgrade_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              if(getTotalEarnings() > Drink_Upgrade && getDrinksDuration() > 1) {
                 SoundHandler sound = new SoundHandler();
                 sound.playSound("src/SoundAssets/CashSound.wav");
                 
                 //Drink Upgrade cost + $15
                 Drink_Upgrade += 15;
                 //update duration by 1 second
                 setDrinksDuration(getDrinksDuration()-1);
                 
                 //update button display
                 updateDisplay();
              }  
            }
        });

        Bakery_Upgrade_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(getTotalEarnings() > Bakery_Upgrade && getBakeryDuration() > 1) {
                 SoundHandler sound = new SoundHandler();
                 sound.playSound("src/SoundAssets/CashSound.wav");
                 
                 //Crepe Upgrade cost + $15
                 Bakery_Upgrade += 15;
                 //update duration by 1 second
                 setBakeryDuration(getBakeryDuration()-1);
                 
                 //update button display
                 updateDisplay();
             } 
            }
        });
        
        Continue_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundHandler sound = new SoundHandler();     
                sound.playSound("src/SoundAssets/round_winning.wav");
                
                sound.playSound("src/SoundAssets/background_music.wav"); 
                Reset con = new Reset();
                con.ContinueButtonPressed();
            }
        });
        
    }

    private void updateDisplay() {
   
        Crepe_Upgrade_Button.setText("Crepe___$"+Crepe_Upgrade);
        Bakery_Upgrade_Button.setText("Bakery___$"+Bakery_Upgrade);
        Drinks_Upgrade_Button.setText("Drinks___$"+Drink_Upgrade);
        Spending_Display.setText("$" + getTotalEarnings());
        
        if(getCurrentHealth() > 0) { 
         Lives_Display.setText(getCurrentHealth()+"/"+getMaxHealth());
        } else {
         Lives_Display.setText("Last Chance!");
        }
    }

    public void transitionFunction() {
        
        initButtonListeners();
        updateDisplay();

    }


}
