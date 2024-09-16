package app.runner.MakeRunnable;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;

public class MakeRunnable {

    public static final String DEPLOY_DIRECTORY = "deployDirectory";

    public static void MakeRunnableFolder() {
        Code_One();
    }

    private static void Code_One() {
        String parentFolder = new File(getCurrentFolderPath()).getParent();
        Map<String, String> jarsWithPath = getListOfJarsWithDirectory(parentFolder);
        boolean copyFilesToFolder = copyJarsTodDeployDirectoryFolder(jarsWithPath, parentFolder);
        if (copyFilesToFolder)
            createBatchFileForRunningThisApps(jarsWithPath, parentFolder);
    }

    private static void createBatchFileForRunningThisApps(Map<String, String> jarsWithPath, String parentFolder) {
        StringBuilder fileString = new StringBuilder();
        fileString.append("@echo off\n");

        SortedMap<String, String> sortedMap = new TreeMap<String,String>();

        for (Entry<String, String> entry : jarsWithPath.entrySet()) {
            String x=entry.getValue().split("target")[1].replace("\\", "").split("-")[1].split("\\.")[0];
            sortedMap.put(x, entry.getValue());
        }
        for (Entry<String, String> entry : sortedMap.entrySet()) {
            String jarName = entry.getValue().split("target")[1].replace("\\", "");
            fileString
                    .append("start /b \"" + entry.getKey() + "\" java -jar " + DEPLOY_DIRECTORY + "/" + jarName + "\n");
            fileString.append("timeout 5 > NUL \n");
        }
        fileString.append("pause\n");
        File flDeployBatParent = new File(parentFolder + "/" + "runApps.bat");
        if (flDeployBatParent.exists()) {
            flDeployBatParent.delete();
            try {
                FileUtils.write(flDeployBatParent, fileString, "UTF8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileUtils.write(flDeployBatParent, fileString, "UTF8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Map<String, String> getListOfJarsWithDirectory(String parentFolder) {
        Map<String, String> jarsMap = new HashMap<String, String>();
        try {
            boolean recursive = true;
            Collection<File> filesList = FileUtils.listFiles(new File(parentFolder), null, recursive);
            Iterator<File> itr = filesList.iterator();
            while (itr.hasNext()) {
                File fl = itr.next();
                String fileName = fl.getName();
                String filePath = fl.getCanonicalPath();
                if (fileName.endsWith(".jar") && filePath.indexOf("target") > 0 && fileName.indexOf("AppRunner")<0 )
                {
                    jarsMap.put(fileName, filePath);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jarsMap;
    }

    private static String getCurrentFolderPath() {
        return System.getProperty("broadcast.dir");
    }

    private static boolean copyJarsTodDeployDirectoryFolder(Map<String, String> jarsWithPath, String parentFolder) {
        File folderPath=new File(parentFolder + "/" + DEPLOY_DIRECTORY);
        if(folderPath.exists())
        {
            folderPath.delete();
        }
        if(folderPath.mkdir())
        {
            for (Entry<String, String> entry : jarsWithPath.entrySet()) {
                String file = entry.getValue();
                try {
                    FileUtils.copyFileToDirectory(new File(file), new File(parentFolder + "/" + DEPLOY_DIRECTORY));
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }
}