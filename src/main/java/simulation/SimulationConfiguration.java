/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sdaskalov
 */
@XmlRootElement(name = "configuration")
@XmlAccessorType(XmlAccessType.FIELD)
public class SimulationConfiguration {

    private Integer simulationRuns;
    private Integer calculationPrecision;

    public SimulationConfiguration() {
    }

    public SimulationConfiguration(Integer simulationRuns) {
        this.simulationRuns = simulationRuns;
    }

    public SimulationConfiguration(Integer simulationRuns, Integer precision) {
        this.simulationRuns = simulationRuns;
        this.calculationPrecision = precision;
    }

    public Integer getSimulationRuns() {
        return simulationRuns;
    }

    public void setSimulationRuns(Integer simulationRuns) {
        this.simulationRuns = simulationRuns;
    }

    public Integer getPrecision() {
        return calculationPrecision;
    }

    public void setPrecision(Integer precision) {
        this.calculationPrecision = precision;
    }

}
