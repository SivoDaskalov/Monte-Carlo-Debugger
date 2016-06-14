/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package view;

import java.awt.Dimension;
import javax.swing.JPanel;
import model.DebugContext;
import view.styles.Styles;

/**
 *
 * @author sdaskalov
 */
public class MainMenuPanel extends JPanel {

    private static final Dimension preferredSize = new Dimension(800, 50);

    private final DebugContext context;

    public MainMenuPanel(DebugContext context) {
        this.context = context;
        this.setPreferredSize(preferredSize);
        this.setBackground(Styles.defaultPanelBackgroundColor);
    }

}
