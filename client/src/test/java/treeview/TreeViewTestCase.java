package treeview;

/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import model.node.NodeImpl;
import model.node.NodeRenderer;

/**
 *
 * @author sdaskalov
 */
public class TreeViewTestCase extends JFrame {
    
    JFrame frame;
    
    public TreeViewTestCase() {
        TreeModel model = new DefaultTreeModel(new NodeImpl());
        JTree tree = new JTree(model);
        tree.setCellRenderer(new NodeRenderer());
        frame = new JFrame("ButtonDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(tree);
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TreeViewTestCase();
            }
        });
    }
}
