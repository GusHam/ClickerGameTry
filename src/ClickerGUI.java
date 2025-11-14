import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickerGUI
{
    private Clicker clicker;
    private JLabel pointsLabel;
    private int upgradeCost = 25;

    public ClickerGUI(Clicker clicker)
    {
        this.clicker = clicker;

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
        pointsLabel = new JLabel("Points: " + Clicker.points);
        pointsLabel.setFont(new Font("Arial", Font.BOLD, 24));
        pointsLabel.setBounds(25, 30, 300, 30);
        frame.add(pointsLabel);

        //Point pr. klik
        JLabel pointClick = new JLabel("Points pr. click: " + Clicker.pointsPerClick);
        pointClick.setFont(new Font("Arial", Font.BOLD, 15));
        pointClick.setBounds(25, 70, 300, 30);
        frame.add(pointClick);

        //Klik knap
        JButton clickButton = new JButton("Click!");
        clickButton.setFont(new Font("Arial", Font.BOLD, 20));
        clickButton.setBounds(350, 25, 100, 50);
        frame.add(clickButton);

        //Håndter klik
        clickButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Clicker.points += Clicker.pointsPerClick;
                pointsLabel.setText("Points: " + Clicker.points);
            }
        });

        //Knap til at lukke shop
        JButton closeShop = new JButton("Close");
        closeShop.setFont(new Font("Arial", Font.BOLD, 10));
        closeShop.setBounds(675,85,75,40);
        frame.add(closeShop);
        closeShop.setVisible(false);


        //Ikke nok point felt
        JLabel noPoints = new JLabel("Not enough points!");
        noPoints.setBounds(100, 105, 200, 50);
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

        //Upgrade price
        JLabel upgradePrice = new JLabel("Price: " + upgradeCost + "points");
        upgradePrice.setBounds(100, 105, 200, 20);
        upgradePrice.setFont(new Font("Arial", Font.PLAIN, 14));
        shopPanel.add(upgradePrice);

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
                if (Clicker.points >= upgradeCost)
                {
                    Clicker.points -= upgradeCost;
                    Clicker.pointsPerClick++;
                    pointClick.setText("Points pr. click: " + Clicker.pointsPerClick);
                    pointsLabel.setText("Points: " + Clicker.points);
                    upgradeCost *= 1.4;
                    upgradePrice.setText("Price: " + upgradeCost + "points");
                    noPoints.setVisible(false);
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

        frame.setVisible(true);
    }
}
