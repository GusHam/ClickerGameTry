import javax.swing.*;
import java.awt.*;

public class FloatingText extends JComponent
{
    private final String text;
    private float alpha = 1f;
    private int y;
    private int x;
    private Timer timer;

    public FloatingText(String text, int startX, int startY)
    {
        this.text = text;
        this.x = 0;
        this.y = 30;

        setOpaque(false);
        int w = 120;
        int h = 80;

        setBounds(startX - w/2, startY - h/2, w, h);

        timer = new Timer(30, e ->
        {
            y -= 3;
            alpha -= 0.03f;
            repaint();

            if (alpha <= 0f)
            {
                ((Timer)e.getSource()).stop();
                Container p = getParent();
                if (p != null)
                {
                    p.remove(this);
                    SwingUtilities.invokeLater(() -> { p.revalidate(); p.repaint(); });
                }
            }
        });
    }

    public void start()
    {
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 16f));
        g2.setColor(Color.BLACK);
        FontMetrics fm = g2.getFontMetrics();
        int tx = (getWidth() - fm.stringWidth(text)) / 2;
        int ty = getHeight() / 2;
        g2.drawString(text, tx, ty);
        g2.dispose();
    }
}