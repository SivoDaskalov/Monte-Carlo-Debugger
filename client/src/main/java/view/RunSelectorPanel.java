/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import model.DebugContext;
import view.styles.Styles;

/**
 *
 * @author sdaskalov
 */
public class RunSelectorPanel extends JPanel {

    private static final Dimension preferredSize = new Dimension(new Dimension(150, 600));
    private final DebugContext context;
    private final JLabel title;
    private final JList<Integer> runList;

    public RunSelectorPanel(DebugContext context) {
        this.context = context;
        this.setLayout(new BorderLayout());
        this.setBackground(Styles.selectedBackgroundColor);
        this.setPreferredSize(preferredSize);
        this.setBorder(Styles.padding);

        title = new JLabel("Select run");
        title.setFont(Styles.titleFont);
        this.add(title, BorderLayout.NORTH);

        runList = new JList<>();
        updateRunList();
        this.add(new JScrollPane(runList), BorderLayout.CENTER);
    }

    public final void updateRunList() {
        Integer[] runs = new Integer[context.getRunCount()];
        for (int i = 0; i < runs.length; i++) {
            runs[i] = i + 1;
        }
        runList.setListData(runs);
    }

    public void setListSelectionListener(ListSelectionListener listener) {
        runList.addListSelectionListener(listener);
    }

}
