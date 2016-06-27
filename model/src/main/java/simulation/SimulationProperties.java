/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package simulation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author sdaskalov
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SimulationProperties {

    private String title;
    private String description;
    private Integer simulationRuns;

    public SimulationProperties() {
    }

    public SimulationProperties(String title, String description, Integer simulationRuns) {
        this.title = title;
        this.description = description;
        this.simulationRuns = simulationRuns;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSimulationRuns() {
        return simulationRuns;
    }

    public void setSimulationRuns(Integer simulationRuns) {
        this.simulationRuns = simulationRuns;
    }

}
