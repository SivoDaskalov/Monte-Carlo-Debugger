/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package runselection.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import model.DebugContext;
import view.Styles;

/**
 *
 * @author sdaskalov
 */
public class RunSelectionView extends JPanel {

    private static final Dimension preferredSize = new Dimension(150, 600);
    private final DebugContext context;
    private final JLabel title;
    private final JList<Integer> runList;

    public RunSelectionView(DebugContext context) {
        this.context = context;
        this.setLayout(new BorderLayout());
        this.setBackground(Styles.defaultPanelBackgroundColor);
        this.setPreferredSize(preferredSize);
        this.setBorder(Styles.padding);

        title = new JLabel("Select run");
        title.setFont(Styles.titleFont);
        this.add(title, BorderLayout.CENTER);

        runList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(runList);
        scrollPane.setPreferredSize(new Dimension(100, 600));
        updateRunList();
        this.add(scrollPane, BorderLayout.SOUTH);
    }

    public final void updateRunList() {
        Integer[] runs = new Integer[context.getRunCount()];
        if (runs.length == 0) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
        for (int i = 0; i < runs.length; i++) {
            runs[i] = i + 1;
        }
        runList.setListData(runs);
        runList.setSelectedIndex(0);
    }

    public void setListSelectionListener(ListSelectionListener listener) {
        runList.addListSelectionListener(listener);
    }

}
