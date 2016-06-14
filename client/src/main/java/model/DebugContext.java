/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package model;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.tree.DefaultMutableTreeNode;
import node.Node;
import simulation.NodeValues;
import simulation.SimulationProperties;
import simulation.SimulationRequest;
import simulation.SimulationResponse;
import simulation.StochasticVariableRegistry;
import tree.DebuggedNode;
import tree.JTreeBuilder;

/**
 *
 * @author sdaskalov
 */
public class DebugContext {

    private int currentRun;
    private int runCount;

    private NodeStatistics selectedNode;
    private DefaultMutableTreeNode currentlyDebuggedNode;

    private SimulationProperties properties;
    private StochasticVariableRegistry variables;
    private DefaultMutableTreeNode root;

    private final Map<String, NodeStatistics> statistics;
    private final Map<String, DefaultMutableTreeNode> nodes;

    public DebugContext() {
        statistics = new HashMap<>();
        nodes = new HashMap<>();
        currentRun = 1;
    }

    public DebugContext(SimulationRequest request) {
        this();
        setup(request);
    }

    public DebugContext(SimulationResponse response) {
        this();
        setup(response);
    }

    public final void setup(SimulationRequest request) {
        properties = request.getProperties();
        variables = request.getVariableRegistry();
        setupTree(request.getFormula());
    }

    public final void setup(SimulationResponse response) {
        properties = response.getProperties();
        variables = response.getVariableRegistry();
        setupTree(response.getFormula());
        setupStatistics(response.getValues());
    }

    public SimulationProperties getProperties() {
        return properties;
    }

    public StochasticVariableRegistry getVariables() {
        return variables;
    }

    public DefaultMutableTreeNode getRoot() {
        return root;
    }

    public int getCurrentRun() {
        return currentRun;
    }

    public void setCurrentRun(int currentRun) {
        if (!statistics.isEmpty()
                && currentRun > 0
                && currentRun <= runCount) {
            this.currentRun = currentRun;
            resetDebugging();
        }
    }

    public int getRunCount() {
        return runCount;
    }

    public NodeStatistics getSelectedNodeStatistics() {
        return selectedNode;
    }

    public void setSelectedNode(String nodeId) {
        selectedNode = statistics.get(nodeId);
    }

    public DefaultMutableTreeNode getCurrentlyDebuggedNode() {
        return currentlyDebuggedNode;
    }

    public void setCurrentlyDebuggedNode(DefaultMutableTreeNode currentlyDebugged) {
        currentlyDebuggedNode = currentlyDebugged;
    }

    public Double getValue(String nodeId) {
        NodeStatistics nodeStats = statistics.get(nodeId);
        return nodeStats == null ? null : nodeStats.getValue(currentRun - 1);
    }

    public void resetDebugging() {
        currentlyDebuggedNode = root;
        resetDebugging(root);
    }

    private void resetDebugging(DefaultMutableTreeNode current) {
        DebuggedNode node = (DebuggedNode) current.getUserObject();
        node.setValueVisible(false);
        Enumeration children = current.children();
        while (children.hasMoreElements()) {
            resetDebugging((DefaultMutableTreeNode) children.nextElement());
        }
    }

    private void setupStatistics(List<NodeValues> nodeValuesList) {
        statistics.clear();
        if (nodeValuesList == null) {
            return;
        }
        for (NodeValues nodeValues : nodeValuesList) {
            statistics.put(nodeValues.getNodeId(),
                    new NodeStatistics(
                            nodeValues.getNodeId(),
                            nodeValues.getValues()));
        }
        currentRun = 1;
        runCount = nodeValuesList.size();
    }

    private void setupTree(Node formula) {
        root = (DefaultMutableTreeNode) JTreeBuilder.buildTreeModel(formula).getRoot();
        currentlyDebuggedNode = root;
        mapNodes(root);
    }

    private void mapNodes(DefaultMutableTreeNode current) {
        Node node = ((DebuggedNode) current.getUserObject()).getNode();
        nodes.put(node.getId(), current);
        Enumeration children = current.children();
        while (children.hasMoreElements()) {
            mapNodes((DefaultMutableTreeNode) children.nextElement());
        }
    }
}
