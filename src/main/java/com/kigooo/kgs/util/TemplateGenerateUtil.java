package com.kigooo.kgs.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TemplateGenerateUtil {

    public static void appendToFile(String filePath, String content) {
        content = "\n" + content;
        try {
            byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
            Files.write(Paths.get(filePath), content.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            try {
                Files.write(Paths.get(filePath), content.getBytes(), StandardOpenOption.CREATE);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void copyFolder(File sourceFolder, File destinationFolder) throws IOException {
        // 创建目标文件夹
        if (!destinationFolder.exists()) {
            destinationFolder.mkdirs();
        }
        // 遍历源文件夹中的文件和子文件夹
        for (File sourceFile : sourceFolder.listFiles()) {
            if (sourceFile.isDirectory()) {
                // 如果是子文件夹，递归调用复制文件夹方法
                File newDestinationFolder = new File(destinationFolder, sourceFile.getName());
                copyFolder(sourceFile, newDestinationFolder);
            } else {
                // 如果是文件，复制文件到目标文件夹
                Path sourcePath = Paths.get(sourceFile.getPath());
                Path destinationPath = Paths.get(destinationFolder.getPath(), sourceFile.getName());
                Files.copy(sourcePath, destinationPath);
            }
        }
    }

    public static void copyFolder(String sourceFolderPath ,String destinationFolderPath ){
        File sourceFolder = new File(sourceFolderPath);
        File destinationFolder = new File(destinationFolderPath);
        try {
            copyFolder(sourceFolder,destinationFolder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
