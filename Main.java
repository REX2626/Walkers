import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Walkers");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        BaseLimb limb = new BaseLimb(100, 50, 50, 0, null);
        Walker walker = new Walker(limb);

        frame.add(new DrawingPanel(walker));

        while (true) {
            // Ensure 60 FPS
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
        }
    }
}
