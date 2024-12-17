package Structure;

import javax.swing.*;
import java.awt.*;

public class FloorBonus extends JLabel {
    private static final Icon icon0 = new ImageIcon("Icons/Hearth.png");
    private static final Icon icon1 = new ImageIcon("Icons/2x.png");
    private static final Icon icon2 = new ImageIcon("Icons/preview.png");
    private static final Icon icon3 = new ImageIcon("Icons/slow.png");
    private static final Icon icon4 = new ImageIcon("Icons/shield.png");


    public FloorBonus(int id) {
        this.setBackground(Color.darkGray);
        this.setIcon(switch (id) {
            case 0 -> icon0;
            case 1 -> icon1;
            case 2 -> icon2;
            case 3 -> icon3;
            default -> icon4;
        });
        this.setSize(new Dimension(60, 60));
        setOpaque(true);
    }
}
