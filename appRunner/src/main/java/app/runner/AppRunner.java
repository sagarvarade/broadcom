package app.runner;

import app.runner.MakeRunnable.MakeDockerFiles;
import app.runner.MakeRunnable.MakeRunnable;

import java.util.Scanner;

public class AppRunner {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("1. Make runnable folder grab jars ");
            System.out.println("2. Build docker images ");
            System.out.println("Enter your input :");
            int input = sc.nextInt();
            switch (input) {
                case 1:
                    MakeRunnable.MakeRunnableFolder();
                case 2:
                    MakeDockerFiles.MakeDockerFiles();
            }

        }

        System.out.println("Done");
    }
}