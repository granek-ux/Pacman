package Structure;

import javax.swing.*;
import java.awt.*;

public class FloorPacman extends JLabel {
    private static final Icon icon1 = new ImageIcon("Icons/PacMan2.png");
    private static final Icon icon2 = new ImageIcon("Icons/PacMan1.png");
    private static final Icon icon3 = new ImageIcon("Icons/PacMan3.png");
    private static int openingStatus;

    public static int getOpeningStatus() {
        return openingStatus;
    }

    public static void setOpeningStatus(int openingStatus) {
        FloorPacman.openingStatus = openingStatus;
    }

    public FloorPacman(Boolean playerShield, int ops) {
        this.setPreferredSize(new Dimension(60, 60));
        this.setIcon(switch (openingStatus) {
            case 2, 4 -> icon2;
            case 3 -> icon3;
            default -> icon1;
        });

        if (playerShield) this.setBackground(Color.BLUE);
        else this.setBackground(Color.darkGray);
        setOpaque(true);
    }
}
