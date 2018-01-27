package test.hexacta.booster.providers;

import com.hexacta.booster.project.configuration.Hibernate;
import com.hexacta.booster.project.configuration.HibernateDaos;
import com.hexacta.booster.project.configuration.JUnit3;
import com.hexacta.booster.project.configuration.JavaProjectType;
import com.hexacta.booster.project.configuration.NoBuildTool;
import com.hexacta.booster.project.configuration.NoDtoTool;
import com.hexacta.booster.project.configuration.NoFramework;
import com.hexacta.booster.project.configuration.NoVersioningSystem;
import com.hexacta.booster.project.configuration.NoViewLayerFramework;
import com.hexacta.booster.project.configuration.ProjectConfigurationTool;

/**
 * 
 */
public class ProjectConfigurationToolProvider {

    public ProjectConfigurationToolProvider() {
    }

    public ProjectConfigurationTool getProjectConfigurationTool() {
        return new ProjectConfigurationTool(new NoBuildTool(), new NoVersioningSystem(), new JavaProjectType(),
                new Hibernate(), new NoDtoTool(), new HibernateDaos(), new NoFramework(), new JUnit3(), new NoViewLayerFramework());
    }
}
