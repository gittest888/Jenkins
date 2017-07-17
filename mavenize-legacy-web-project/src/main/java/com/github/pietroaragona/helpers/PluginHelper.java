package com.github.pietroaragona.helpers;

import org.apache.maven.model.Build;
import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;
import org.codehaus.plexus.util.xml.Xpp3Dom;

/**
 * @author Pietro Aragona
 */
public class PluginHelper {

	public static void run(Model model, Build build) {
        build.addPlugin(getPluginCompiler());
        build.addPlugin(getPluginWar());
        build.addPlugin(getPluginTomcat6());
        build.addPlugin(getPluginTomcat7());
        model.setBuild(build);
	}

	   private static Plugin getPluginWar() {
	        Plugin plugin = new Plugin();
	        plugin.setArtifactId("maven-war-plugin");
	        plugin.setVersion("${maven.war.plugin.version}");

	        Xpp3Dom configuration = new Xpp3Dom("configuration");

	        Xpp3Dom warSourceDirectory = new Xpp3Dom("warSourceDirectory");
	        warSourceDirectory.setValue("${war.source.dir}");
	        configuration.addChild(warSourceDirectory);

	        plugin.setConfiguration(configuration);
	        return plugin;
	    }

	    private static Plugin getPluginCompiler() {
	        Plugin pluginCompile = new Plugin();
	        pluginCompile.setArtifactId("maven-compiler-plugin");
	        pluginCompile.setVersion("${maven.compiler.plugin.version}");

	        Xpp3Dom configuration = new Xpp3Dom("configuration");

	        Xpp3Dom source = new Xpp3Dom("source");
	        source.setValue("${java.source.version}");
	        configuration.addChild(source);

	        Xpp3Dom target = new Xpp3Dom("target");
	        target.setValue("${java.target.version}");
	        configuration.addChild(target);

            Xpp3Dom compilerArgument = new Xpp3Dom("compilerArgument");
            compilerArgument.setValue("-parameters");
            configuration.addChild(compilerArgument);

	        pluginCompile.setConfiguration(configuration);
	        return pluginCompile;
	    }

	    private static Plugin getPluginTomcat6() {
	        Plugin plugin = new Plugin();
	        plugin.setGroupId("org.apache.tomcat.maven");
	        plugin.setArtifactId("tomcat6-maven-plugin");
	        plugin.setVersion("2.2");
	        return plugin;
	    }

	    private static Plugin getPluginTomcat7() {
	        Plugin plugin = new Plugin();
	        plugin.setGroupId("org.apache.tomcat.maven");
	        plugin.setArtifactId("tomcat7-maven-plugin");
	        plugin.setVersion("2.2");
	        return plugin;
	    }
}
