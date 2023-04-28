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
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<String> readFile2 = CompletableFuture.supplyAsync(() -> {
            try {
                return Files.readString(file2Path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return readFile1.thenCombine(readFile2, (reading1, reading2) -> {
            try {
                String result = reading1 + reading2;
                Files.writeString(destPath, result, StandardOpenOption.CREATE);
                return "Done!";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return null;
        });
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> result = unionFiles(
            "src/main/resources/file1.txt",
            "src/main/resources/file2.txt",
            "src/main/resources/dest.txt"
        );
        result.get();
        // END
    }
}

