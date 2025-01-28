import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Walkers");
        frame.setSize(Constants.WIDTH, Constants.HEIGHT + 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Limb limb2 = new Limb(200, 100, 50, 0.5, null, null);
        Limb limb1 = new Limb(150, 110, 50, Math.PI/2, limb2, null);
        BaseLimb limb = new BaseLimb(100, 100, 50, Math.PI-0.5, limb1);
        Walker walker = new Walker(limb);

        frame.add(new DrawingPanel(walker));

        while (true) {
            // Ensure 60 FPS
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // try {
            //     System.in.read();
            // } catch (IOException e) {
            //     e.printStackTrace();
            // }
            walker.update();
            frame.paintAll(frame.getGraphics());
        }
    }

    static class DrawingPanel extends JPanel {
        private Walker walker;

        public DrawingPanel(Walker walker) {
            this.walker = walker;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            walker.draw(g);
            g.drawLine(0, Constants.HEIGHT, Constants.WIDTH, Constants.HEIGHT);
        }
    }
}
