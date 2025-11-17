import javax.swing.*;


public class Main
{

    public static void main(String[] args)
    {
        ClickerGUI game = new ClickerGUI();
        SwingUtilities.invokeLater(() -> new ClickerGUI());

        System.out.println("");
        System.out.println("Program launched in a seperate window");
    }
}