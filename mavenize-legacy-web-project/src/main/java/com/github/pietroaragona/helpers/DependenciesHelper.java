package com.github.pietroaragona.helpers;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.model.Build;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;

/**
 * @author Pietro Aragona
 */
public class DependenciesHelper {

	public static void run(Model model, Build build, String libDirRelativePath, String libDir) {
		addDependencies(model, libDirRelativePath, libDir);
		
	}

	   private static void addDependencies(Model model, String libDirRelativePath, String libDir) {

	        List<Path> jarFiles = getJarFiles(libDir);

	        Dependency dependencyTemplate = new Dependency();
	        dependencyTemplate.setGroupId(model.getGroupId()+".legacy");
	        dependencyTemplate.setVersion("${legacy.lib.version}");
	        dependencyTemplate.setScope("${legacy.lib.scope}");

	        for (Path p : jarFiles) {
	            Dependency dependency = dependencyTemplate.clone();
	            dependency.setArtifactId(p.getFileName().toString());
	            dependency.setSystemPath(libDirRelativePath + "/" + p.getFileName().toString());
	            model.addDependency(dependency);
	        }

	    }

	    public static List<Path> getJarFiles(String libDir) {
	        List<Path> jarFiles = new ArrayList<Path>();
	        File libDirFile = new File(libDir);

	        FilenameFilter filter = new FilenameFilter() {
	            public boolean accept(File directory, String fileName) {
	                return fileName.endsWith(".jar");
	            }
	        };
	        for (File file : libDirFile.listFiles(filter)) {
	            jarFiles.add(file.toPath());
	        }

	        return jarFiles;
	    }
}
