import java.util.Scanner;

public class Clicker
{
    public static long points = 0;
    public static long pointsPerClick = 1;
    public static long totalClicks = 0;
    public static int autoClickers = 0;
    private boolean autoClickerRunning = false;

    public static void click()
    {
        points += pointsPerClick;
        totalClicks += pointsPerClick;
        System.out.println("Klik!");
    }

    public static void openShop(Scanner input)
    {
        System.out.println("--- SHOP ---");
        System.out.println("1. Opgrader klik (+1 point pr. klik) - koster 10 point");
        System.out.println("2. Køb en auto-clicker (1 klik hvert femte sekund)");
        System.out.println("2. Tilbage til spillet");
        System.out.println("Vælg: ");

        String choice = input.nextLine();

        switch (choice)
        {
            case "1":
                if (points >= 10)
                {
                    points -= 10;
                    pointsPerClick++;
                    System.out.println("Du har nu opgraderet! Du får nu " + pointsPerClick + " point pr. klik!");
                }
                else
                {
                    System.out.println("Du har ikke nok point.");
                }
                break;
            case "2":
                if (points >= 50)
                {
                    points -= 50;
                    autoClickers++;
                    System.out.println("Du har købt en autoclicker! Du har nu " + autoClickers + " auto-clickers.");
                }
                else
                {
                    System.out.println("Du har ikke nok point.");
                }
                break;
            default:
                System.out.println("Tilbage til spillet.");
        }
    }

    public void startAutoClicker()
    {
        if (autoClickerRunning) return;

        autoClickerRunning = true;
        Thread t = new Thread()
        {
            @Override
            public void run()
            {
                while (true)
                {
                    try
                    {
                        Thread.sleep(5000);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    points += autoClickers;
                    System.out.println("Dine auto-clickers har givet dig et point! Du har nu " + points + " points");
                }
            }
        };
        t.setDaemon(true);
        t.start();
    }

}
