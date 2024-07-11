package app.runner.MakeRunnable;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MakeDockerFiles {
    public static void MakeDockerFiles() {
        System.out.println("Hello World");
        File parentFolder = new File(getCurrentFolderPath());
        buildImages(parentFolder.getAbsolutePath());

    }
    private static String getCurrentFolderPath() {
        return System.getProperty("BLogic.dir");
    }


    public static void buildImages(String parentFolder) {
        File parentDir = new File(parentFolder);

        if (!parentDir.isDirectory()) {
            System.out.println("Error: Not a valid directory");
            return;
        }

        File[] subDirs = parentDir.listFiles(File::isDirectory);

        if (subDirs == null) {
            System.out.println("Error: No subdirectories found");
            return;
        }

        for (File subDir : subDirs) {
            File dockerfile = findDockerfile(subDir);
            System.out.println(dockerfile.getName());

            }
        }

    public static File findDockerfile(File directory) {
        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().startsWith("dockerfile"));

        if (files != null && files.length > 0) {
            return files[0];
        }

        return null;
    }

}
