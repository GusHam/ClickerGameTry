import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickerGUI
{
    private JLabel pointsLabel;
    private ClickerGUI clickerGUI;
    private int upgradeCost = 25;
    public static long points = 0;
    public static long pointsPerClick = 1;
    private AutoClicker autoClicker = new AutoClicker(200, 1.4, 1);

    public ClickerGUI()
    {
        //Vinduet
        JFrame frame = new JFrame("Clicker Game");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Shop panel
        JPanel shopPanel = new JPanel(null);
        shopPanel.setBounds(200, 120, 400, 400);
        shopPanel.setVisible(false);
        frame.add(shopPanel);
        shopPanel.setBackground(Color.LIGHT_GRAY);

        //Label til point
        pointsLabel = new JLabel("Points: " + points);
        pointsLabel.setFont(new Font("Arial", Font.BOLD, 24));
        pointsLabel.setBounds(25, 30, 300, 30);
        frame.add(pointsLabel);

        //Point pr. klik
        JLabel pointClick = new JLabel("Points pr. click: " + pointsPerClick);
        pointClick.setFont(new Font("Arial", Font.BOLD, 12));
        pointClick.setBounds(25, 70, 300, 30);
        frame.add(pointClick);

        //Point pr. sekund
        JLabel pointsPrSec = new JLabel("Points pr. second: " + autoClicker.getPowerPerSecond());
        pointsPrSec.setFont(new Font("Arial", Font.BOLD, 12));
        pointsPrSec.setBounds(25, 90, 300, 30);
        frame.add(pointsPrSec);

        //Klik knap
        JButton clickButton = new JButton("Click!");
        clickButton.setFont(new Font("Arial", Font.BOLD, 20));
        clickButton.setBounds(350, 25, 100, 50);
        frame.add(clickButton);



        //Knap til at lukke shop
        JButton closeShop = new JButton("Close");
        closeShop.setFont(new Font("Arial", Font.BOLD, 10));
        closeShop.setBounds(675,85,75,40);
        frame.add(closeShop);
        closeShop.setVisible(false);


        //Ikke nok point felt
        JLabel noPoints = new JLabel("Not enough points!");
        noPoints.setBounds(100, 5, 200, 50);
        noPoints.setVisible(false);
        shopPanel.add(noPoints);

        //Shop knap
        JButton shopButton = new JButton("Shop");
        shopButton.setFont(new Font("Arial", Font.BOLD, 20));
        shopButton.setBounds(675, 25, 100, 50);
        frame.add(shopButton);

        //Opgrader knap
        JButton upgradeButton = new JButton("Upgrade clicks");
        upgradeButton.setBounds(100, 50, 200, 50);
        upgradeButton.setFont(new Font("Arial", Font.BOLD, 15));
        shopPanel.add(upgradeButton);

        //Autoclicker knap
        JButton autoClickerButton= new JButton("Auto Clicker");
        autoClickerButton.setBounds(100, 130, 200, 50);
        autoClickerButton.setFont(new Font("Arial", Font.BOLD, 15));
        shopPanel.add(autoClickerButton);

        //Autoclicker price
        JLabel autoClickerPrice = new JLabel("Price: " + autoClicker.getCost() + "points");
        autoClickerPrice.setBounds(100, 180, 200, 20);
        autoClickerPrice.setFont(new Font("Arial", Font.PLAIN, 14));
        shopPanel.add(autoClickerPrice);

        //Antal autoclicker
        JLabel autoClickerCount = new JLabel("AutoClickers: " + autoClicker.getCount());
        autoClickerCount.setBounds(100,200,200,20);
        autoClickerCount.setFont(new Font("Arial",  Font.PLAIN, 14));
        shopPanel.add(autoClickerCount);

        //Upgrade price
        JLabel upgradeClickerPrice = new JLabel("Price: " + upgradeCost + "points");
        upgradeClickerPrice.setBounds(100, 105, 200, 20);
        upgradeClickerPrice.setFont(new Font("Arial", Font.PLAIN, 14));
        shopPanel.add(upgradeClickerPrice);

        //Åben shop
        shopButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                shopPanel.setVisible(true);
                closeShop.setVisible(true);
            }
        });

        //Håndter upgrade knap
        upgradeButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (points >= upgradeCost)
                {
                    points -= upgradeCost;
                    pointsPerClick++;
                    pointClick.setText("Points pr. click: " + pointsPerClick);
                    pointsLabel.setText("Points: " + points);
                    upgradeCost *= 1.4;
                    upgradeClickerPrice.setText("Price: " + upgradeCost + "points");
                    noPoints.setVisible(false);
                    FloatingText ft = new FloatingText("+1", upgradeButton.getX() + upgradeButton.getWidth()/2, upgradeButton.getY());
                    shopPanel.add(ft);
                    shopPanel.setComponentZOrder(ft, 0);
                    ft.start();
                }
                else
                    {
                        noPoints.setVisible(true);
                    }
            }
        });

        //Close shop knap
        closeShop.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                closeShop.setVisible(false);
                shopPanel.setVisible(false);
            }
        });

        //Håndter klik
        clickButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                points += pointsPerClick;
                pointsLabel.setText("Points: " + points);
            }
        });

        //Håndter autoClicker
        autoClickerButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                double costBefore = autoClicker.getCost();
                if (points >= (long) Math.ceil(costBefore))
                {
                    boolean bought = autoClicker.buy(points);
                    if (bought)
                    {
                        points -= (long) Math.ceil(costBefore);
                        pointsLabel.setText("Points: " + points);
                        noPoints.setVisible(false);
                        autoClickerPrice.setText("Price:" + autoClicker.getCost() + "points");
                        pointsPrSec.setText("Points pr. second: " + autoClicker.getPowerPerSecond());
                        autoClickerCount.setText("AutoClickers: " +  autoClicker.getCount());
                        FloatingText ft = new FloatingText("+1", autoClickerButton.getX() + autoClickerButton.getWidth()/2, autoClickerButton.getY());
                        shopPanel.add(ft);
                        shopPanel.setComponentZOrder(ft, 0);
                        ft.start();
                    }
                }
                else
                {
                    noPoints.setVisible(true);
                }
            }
        });


        //AutoClicker timer
        Timer t = new Timer (1000, e ->
        {
            long gain = autoClicker.getPowerPerSecond();
            if (gain > 0)
            {
                points += gain;
                pointsLabel.setText("Points: " + points);
            }
            pointsPrSec.setText("Points pr. second: " + gain);
        });
        t.start();


        frame.setVisible(true);
    }
}
