package Structure;

import javax.swing.*;
import java.awt.*;

public class Wall extends JLabel {
    public Wall() {
        this.setPreferredSize(new Dimension(60, 60));
        this.setBackground(new Color(5, 5, 5));
        setOpaque(true);
    }
}
