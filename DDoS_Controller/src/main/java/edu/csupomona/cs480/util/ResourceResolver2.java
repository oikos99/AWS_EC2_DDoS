package edu.csupomona.cs480.util;

import java.io.File;

public class ResourceResolver2 {
	/** The base folder to store all the data used by this project. */
	private static final String BASE_DIR = System.getProperty("user.home") + "/ip";

	/**
	 * Get the file used to store the user object JSON
	 *
	 * @param userId
	 * @return
	 */
	public static File getIPFile() {
		File file = new File(BASE_DIR + "/" + "ip-map.json");
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		return file;
	}
}
