package exercise;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String path1, String path2, String path3) {
        Path file1Path = Paths.get(path1).toAbsolutePath().normalize();
        Path file2Path = Paths.get(path2).toAbsolutePath().normalize();
        Path destPath = Paths.get(path3).toAbsolutePath().normalize();

        CompletableFuture<String> readFile1 = CompletableFuture.supplyAsync(() -> {
            try {
                return Files.readString(file1Path);
            } catch (IOException e) {
                throw new RuntimeException(e.getClass().getName());
            }
        });

        CompletableFuture<String> readFile2 = CompletableFuture.supplyAsync(() -> {
            try {
                return Files.readString(file2Path);
            } catch (IOException e) {
                throw new RuntimeException(e.getClass().getName());
            }
        });

        return readFile1.thenCombine(readFile2, (reading1, reading2) -> {
            try {
                Files.writeString(destPath, reading1, StandardOpenOption.CREATE);
                Files.writeString(destPath, reading2, StandardOpenOption.APPEND);
                return reading1 + reading2;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return null;
        });
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        unionFiles("src/main/resources/file1.txt", "src/main/resources/file2.txt", "src/main/resources/dest.txt");
        // END
    }
}

