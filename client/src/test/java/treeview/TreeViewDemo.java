/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package treeview;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import node.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simulation.response.SimulationResponse;
import tree.builder.JTreeBuilder;
import view.tree.renderers.NodeRendererResolver;

/**
 *
 * @author sdaskalov
 */
public class TreeViewDemo {

    private static final Logger log = LoggerFactory.getLogger(TreeViewDemo.class);

    public static void main(String argv[]) {
        SimulationResponse response = SimulationResponseUnmarshaler.unmarshal("src/main/resources/SimulationResponse.xml");
        JFrame frame = new JFrame("TreeViewDemo");
        JPanel panel = new JPanel(new GridLayout(1, 0));
        JTree tree = JTreeBuilder.buildTree(response.getFormula());
        tree.setCellRenderer(new NodeRendererResolver());
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) e.getNewLeadSelectionPath().getLastPathComponent();
                Node node = (Node) treeNode.getUserObject();
                log.info(node.getClass().getSimpleName() + " " + node.getId());
            }
        });
        panel.add(new JScrollPane(tree));
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}
