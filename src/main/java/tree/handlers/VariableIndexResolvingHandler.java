/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package tree.handlers;

import java.util.Map;
import node.impl.AbstractNode;
import node.impl.VariableNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import variable.StochasticVariable;
import variable.registry.SampledVariableRegistry;

/**
 *
 * @author sdaskalov
 */
public class VariableIndexResolvingHandler implements NodeHandler<AbstractNode> {
    
    private static final Logger log = LoggerFactory.getLogger(VariableIndexResolvingHandler.class);
    
    Map<Integer, StochasticVariable> variableIndex;
    
    public VariableIndexResolvingHandler(SampledVariableRegistry registry) {
        variableIndex = registry.getVariableIndex();
    }
    
    @Override
    public void handle(AbstractNode node) {
        if (VariableNode.class.isAssignableFrom(node.getClass())) {
            VariableNode variableNode = (VariableNode) node;
            String variableName = variableNode.getName();
            for (Map.Entry<Integer, StochasticVariable> entry : variableIndex.entrySet()) {
                if (entry.getValue().getId().equals(variableName)) {
                    variableNode.setVariableIndex(entry.getKey());
                    return;
                }
            }
            log.error("Unable to resolve index of variable node");
        }
    }
    
}
