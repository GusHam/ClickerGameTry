import javax.swing.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
        Clicker game = new Clicker();
        SwingUtilities.invokeLater(() -> new ClickerGUI(game));

        System.out.println("");
        System.out.println("Program launched in a seperate window");



        /*
        Scanner input = new Scanner(System.in);
        boolean running = true;


        System.out.println("=== VELKOMMEN TIL CLICKER SPILLET ===");
        System.out.println("Tryk [Enter] for at klikke!");
        System.out.println("Skriv 'shop' for at købe opgraderinger, eller 'exit'");

        while(running)
        {
            System.out.print("> ");
            String command = input.nextLine().trim().toLowerCase();

            switch (command)
            {
                case "":
                    Clicker.click();
                    break;
                case "shop":
                    Clicker.openShop(input);
                    break;
                case "exit":
                    running = false;
                    System.out.println("Farvel! du endte med " + Clicker.points + " point");
                    System.out.println("Du har fået " + Clicker.totalClicks + " point i alt");
                    break;
                default:
                    System.out.println("Ukent kommando. Prøv igen.");
            }

            if (Clicker.autoClickers > 0)
            {
                game.startAutoClicker();
            }

            System.out.println("\rPoint: " + Clicker.points);
        }
        input.close();
    */}
}