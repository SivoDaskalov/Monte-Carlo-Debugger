/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package treeview;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import node.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simulation.response.SimulationResponse;
import view.context.ViewContext;
import view.tree.JTreeBuilder;
import view.tree.JTreeUtils;
import view.tree.renderers.NodeRendererResolver;

/**
 *
 * @author sdaskalov
 */
public class TreeViewDemo implements ActionListener {

    private static final Logger log = LoggerFactory.getLogger(TreeViewDemo.class);

    private final ViewContext viewContext;
    private final JFrame frame;
    private final JLabel runLabel;
    private final JTextField runField;
    private final JButton runButton;

    public TreeViewDemo(String url) {
        SimulationResponse response = SimulationResponseUnmarshaler.unmarshal(url);
        viewContext = makeViewContext(response);
        JPanel treePanel = makeTreePanel(response.getFormula(), viewContext);

        runLabel = new JLabel("Run 1");
        runField = new JTextField("1");
        runButton = new JButton("Set run");
        runButton.addActionListener(this);

        JPanel settings = new JPanel(new GridLayout(1, 3));
        settings.add(runLabel);
        settings.add(runField);
        settings.add(runButton);

        frame = new JFrame("TreeViewDemo");
        frame.setLayout(new BorderLayout());
        frame.add(settings, BorderLayout.NORTH);
        frame.add(treePanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    private ViewContext makeViewContext(SimulationResponse response) {
        return new ViewContext(response.getNodeValues());
    }

    private JPanel makeTreePanel(Node formula, ViewContext viewContext) {
        JPanel panel = new JPanel(new GridLayout(1, 1));
        JTree tree = JTreeBuilder.buildTree(formula);
        JTreeUtils.expandAllNodes(tree);
        tree.setCellRenderer(new NodeRendererResolver(viewContext));
        panel.add(new JScrollPane(tree));
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int run = Integer.parseInt(runField.getText());
        runLabel.setText("Run " + run);
        viewContext.setCurrentRun(run);
        frame.repaint();
    }

    public static void main(String argv[]) {
        TreeViewDemo demo = new TreeViewDemo("src/main/resources/SimulationResponse.xml");
    }

}
