package Structure;

import javax.swing.*;
import java.awt.*;

public class FloorGhost extends JLabel {
    private static final Icon icon0 = new ImageIcon("Icons/Ghost0.png");
    private static final Icon icon1 = new ImageIcon("Icons/Ghost1.png");
    private static final Icon icon2 = new ImageIcon("Icons/Ghost2.png");
    private static final Icon icon3 = new ImageIcon("Icons/Ghost3.png");

    public FloorGhost(int id) {
        this.setPreferredSize(new Dimension(60, 60));
        this.setIcon(switch (id)
        {
            case 0 -> icon0;
            case 1 -> icon1;
            case 2 -> icon2;
            default -> icon3;
        });
        this.setBackground(Color.darkGray);
        setOpaque(true);
    }
}
