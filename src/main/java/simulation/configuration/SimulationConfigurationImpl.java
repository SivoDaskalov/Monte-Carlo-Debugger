/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.configuration;

/**
 *
 * @author sdaskalov
 */
public class SimulationConfigurationImpl implements SimulationConfiguration {

    private String title;
    private String description;
    private Integer simulationRuns;

    public SimulationConfigurationImpl() {
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Integer getSimulationRuns() {
        return simulationRuns;
    }

    public void setSimulationRuns(Integer simulationRuns) {
        this.simulationRuns = simulationRuns;
    }

}
