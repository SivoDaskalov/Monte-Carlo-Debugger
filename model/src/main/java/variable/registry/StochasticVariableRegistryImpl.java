/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package variable.registry;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import variable.AbstractVariable;
import variable.StochasticVariable;
import variable.registry.StochasticVariableRegistry;

/**
 *
 * @author sdaskalov
 */
@XmlSeeAlso({AbstractVariable.class})
@XmlAccessorType(XmlAccessType.NONE)
public class StochasticVariableRegistryImpl implements StochasticVariableRegistry {

    @XmlAnyElement(lax = true)
    private List<StochasticVariable> variables;

    public StochasticVariableRegistryImpl() {
        variables = new ArrayList<>();
    }

    @Override
    public void putVariable(StochasticVariable variable) {
        variables.add(variable);
    }

    @Override
    public List<StochasticVariable> getVariables() {
        return variables;
    }

    @Override
    public void setVariables(List<StochasticVariable> variables) {
        this.variables = variables;
    }

}
