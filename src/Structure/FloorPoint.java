package Structure;

import javax.swing.*;
import java.awt.*;

public class FloorPoint extends JLabel {
    public FloorPoint() {
        this.setPreferredSize(new Dimension(60, 60));
        this.setBackground(Color.darkGray);
        this.setLayout(new FlowLayout());
        JPanel point = new JPanel();

        point.setBackground(new Color(0, 0, 100));
        point.setSize(new Dimension(20, 20));
        this.add(point);
        setOpaque(true);
    }
}
