/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package model.node;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.swing.tree.TreeNode;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 *
 * @author sdaskalov
 */
@XmlAccessorType(XmlAccessType.NONE)
public class NodeImpl implements Node {

    @XmlElement(name = "nodeId")
    private String id;
    private String name;
    private String role;
    private String description;

    private Node parent;
    @XmlElementWrapper
    @XmlAnyElement(lax = true)
    private List<Node> children;

    public NodeImpl() {
        id = "id123";
        name = "pesho";
        role = "dude";
        description = "wow such descr";
        parent = null;
        children = new ArrayList<>();

    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return children.get(childIndex);
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public int getIndex(TreeNode node) {
        return children.indexOf(node);
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return children.isEmpty();
    }

    @Override
    public Enumeration children() {
        return new NodeEnumeration(children.iterator());
    }

    class NodeEnumeration implements Enumeration<Node> {

        private final Iterator<Node> iterator;

        public NodeEnumeration(Iterator iterator) {
            this.iterator = iterator;
        }

        @Override
        public boolean hasMoreElements() {
            return iterator.hasNext();
        }

        @Override
        public Node nextElement() {
            return iterator.next();
        }

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NodeImpl other = (NodeImpl) obj;
        return Objects.equals(this.id, other.id);
    }

}
