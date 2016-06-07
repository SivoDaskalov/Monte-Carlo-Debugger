/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package tree.builder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import node.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sdaskalov
 */
public class JTreeBuilder {

    private static final JTreeBuilder INSTANCE = new JTreeBuilder();
    private static final Logger log = LoggerFactory.getLogger(JTreeBuilder.class);

    public static JTree buildTree(Node node) {
        DefaultMutableTreeNode root = INSTANCE.walk(node);
        return new JTree(root);
    }

    public DefaultMutableTreeNode walk(Node node) {
        DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(node);
        getChildNodes(node).forEach((k, v) -> treeNode.add(walk(v)));
        return treeNode;
    }

    private Map<String, Node> getChildNodes(Node node) {
        Map<String, Node> children = new LinkedHashMap<>();
        List<Field> fields = getAllFields(node.getClass());
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object object = field.get(node);
                if (object instanceof Iterable) {
                    markCollectionForWalking(children, field.getName(), (Iterable) object);
                } else {
                    markItemForWalking(children, field.getName(), field.get(node));
                }
                field.setAccessible(false);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                log.error("Exception while marking children of "
                        + node.getClass().getSimpleName(), ex);
            }
        }
        return children;
    }

    private static List<Field> getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<>();
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
        }
        return fields;
    }

    private void markItemForWalking(Map<String, Node> pending,
            String name, Object fieldValue) {
        if (fieldValue instanceof Node) {
            pending.put(name, (Node) fieldValue);
        }
    }

    private void markCollectionForWalking(Map<String, Node> pending,
            String name, Iterable collection) {
        int count = 0;
        for (Object item : collection) {
            if (item instanceof Node) {
                pending.put(name + "[" + count + "]", (Node) item);
                count++;
            }
        }
    }
}
