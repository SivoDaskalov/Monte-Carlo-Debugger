/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import variable.AbstractVariable;
import variable.StochasticVariable;

/**
 *
 * @author sdaskalov
 */
@XmlSeeAlso({AbstractVariable.class})
@XmlAccessorType(XmlAccessType.NONE)
public class StochasticVariableRegistry {

    @XmlAnyElement(lax = true)
    private List<StochasticVariable> variables;

    public StochasticVariableRegistry() {
        variables = new ArrayList<>();
    }

    public void putVariable(StochasticVariable variable) {
        variables.add(variable);
    }

    public List<StochasticVariable> getVariables() {
        return variables;
    }

    public void setVariables(List<StochasticVariable> variables) {
        this.variables = variables;
    }

}
