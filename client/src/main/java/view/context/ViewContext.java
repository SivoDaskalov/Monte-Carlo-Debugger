/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package view.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import simulation.NodeValues;
import simulation.NodeValuesWrapper;

/**
 *
 * @author sdaskalov
 */
public class ViewContext {

    private int currentRun;
    private final Map<String, double[]> values;
    private final Map<String, Boolean> debugged;

    public ViewContext() {
        values = new HashMap<>();
        debugged = new HashMap<>();
        currentRun = 1;
    }

    public ViewContext(NodeValuesWrapper nodeValuesWrapper) {
        values = new HashMap<>();
        debugged = new HashMap<>();
        configure(nodeValuesWrapper);
    }

    public final void configure(NodeValuesWrapper nodeValuesWrapper) {
        values.clear();
        debugged.clear();
        List<NodeValues> nodeValuesList = nodeValuesWrapper.getValues();
        if (nodeValuesList == null) {
            return;
        }
        for (NodeValues nodeValues : nodeValuesList) {
            values.put(nodeValues.getNodeId(), nodeValues.getValues());
            debugged.put(nodeValues.getNodeId(), Boolean.TRUE);
        }
        currentRun = 1;
    }

    public int getCurrentRun() {
        return currentRun;
    }

    public void setCurrentRun(int currentRun) {
        if (!values.isEmpty()
                && currentRun > 0
                && currentRun <= values.values().iterator().next().length) {
            this.currentRun = currentRun;
            resetDebugging();
        }
    }

    public void debug(String nodeId) {
        if (debugged.get(nodeId) != null) {
            debugged.put(nodeId, Boolean.TRUE);
        };
    }

    public Double getValue(String nodeId) {
        Boolean isDebugged = debugged.get(nodeId);
        if (isDebugged != null && isDebugged == true) {
            return values.get(nodeId)[currentRun - 1];
        }
        return null;
    }

    private void resetDebugging() {
        for (String entry : debugged.keySet()) {
            debugged.put(entry, Boolean.TRUE);
        }
    }

}
