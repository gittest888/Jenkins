package com.github.pietroaragona.helpers;

import java.io.IOException;
import org.apache.maven.model.Model;
import static com.github.pietroaragona.Mavenize.bufferRead;

/**
 * @author Pietro Aragona
 */
public class CoordinatesHelper {

	public static void run(Model model) throws IOException {

		model.setModelVersion("4.0.0");
		System.out.println("GroupId: ");
		model.setGroupId(bufferRead.readLine());
		System.out.println("ArtifactId: ");
		model.setArtifactId(bufferRead.readLine());
		String defaultVersion = "0.0.1-SNAPSHOT";
		System.out.println("Version: (" + defaultVersion + ")");

		String s = bufferRead.readLine();
		String version = s.trim().equals("") ? defaultVersion : s;

		model.setVersion(version.trim().equals("") ? defaultVersion : version);
		model.setPackaging("war");
	}

}
