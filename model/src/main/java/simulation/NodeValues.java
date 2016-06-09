/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sdaskalov
 */
@XmlRootElement(name = "node")
@XmlAccessorType(XmlAccessType.NONE)
public class NodeValues {

    @XmlAttribute(name = "id")
    private String nodeId;
    @XmlElement(name = "value")
    private double[] values;

    public NodeValues() {
    }

    public NodeValues(String nodeId, double[] values) {
        this.nodeId = nodeId;
        this.values = values;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;
    }

}
