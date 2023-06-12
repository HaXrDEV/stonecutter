/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package io.shcm.shsupercm.fabric.stonecutter;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.initialization.Settings;
import org.jetbrains.annotations.NotNull;

public class StonecutterPluginSplitter implements Plugin<Object> {
    @Override
    public void apply(@NotNull Object dest) {
        if (dest instanceof Settings settings) {
            settings.getExtensions().create("stonecutter", StonecutterSettingsGradle.class, settings);
        } else if (dest instanceof Project project)
            if (project.getBuildFile().getName().equals("stonecutter.gradle")) {
                new StonecutterControllerGradle(project);
            } else {
                new StonecutterBuildGradle(project);
            }
    }
}