/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package menu.views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import view.Styles;

/**
 *
 * @author sdaskalov
 */
public class MenuView extends JPanel {

    private static final Dimension preferredSize = new Dimension(1200, 40);
    private static final Dimension preferredFilenameSize = new Dimension(770, 30);
    private static final int strut = 5;

    private final JTextField filename;
    private final JButton open;
    private final JButton simulate;
    private final JButton save;

    public MenuView() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setPreferredSize(preferredSize);
        this.setBackground(Styles.defaultPanelBackgroundColor);

        open = new JButton("Open");
        open.setFont(Styles.labelFont);
        this.add(open);
        this.add(Box.createHorizontalStrut(strut));

        simulate = new JButton("Simulate");
        simulate.setFont(Styles.labelFont);
        this.add(simulate);
        this.add(Box.createHorizontalStrut(strut));

        save = new JButton("Save");
        save.setFont(Styles.labelFont);
        this.add(save);
        this.add(Box.createHorizontalStrut(strut));

        JLabel label = new JLabel("Loaded configuration:");
        label.setFont(Styles.labelFont);
        this.add(label);
        this.add(Box.createHorizontalStrut(strut));

        filename = new JTextField("");
        filename.setEditable(false);
        filename.setFont(Styles.labelFont);
        filename.setPreferredSize(preferredFilenameSize);
        this.add(filename);
    }

    public void setFilename(String filename) {
        this.filename.setText(filename);
    }

    public void setOpenButtonListener(ActionListener listener) {
        open.addActionListener(listener);
    }

    public void setSimulateButtonListener(ActionListener listener) {
        simulate.addActionListener(listener);
    }

    public void setSaveButtonListener(ActionListener listener) {
        save.addActionListener(listener);
    }

}
