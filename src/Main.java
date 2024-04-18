import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<String, Perceptron> perceptronLayer;
    public static void main(String[] args) {
        perceptronLayer = new HashMap<>();
        findData();
        for (Perceptron perceptron : perceptronLayer.values()) {
            perceptron.PerceptronTrainingFromData();
            perceptron.test();
        }
    }

    public static void createPerceptron(String language){
        perceptronLayer.put(language, new Perceptron());
    }

    public static void findData(){
        try {
            Files.walkFileTree(Paths.get("Training/"), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    if (!(dir.getFileName().toString().equals("Training"))) {
                            createPerceptron(dir.getFileName().toString());
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                    BufferedReader br = new BufferedReader(new FileReader(file.toFile()));
                    String a;
                    String s = "";
                    while(( a = br.readLine()) != null) {
                        s += a;
                    }
                    if (file.getParent().getParent().getFileName().toString().equals("Training")) {
                        perceptronLayer.get(file.getParent().getFileName().toString()).addLearningData(s);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    System.err.println("Failed to visit file: " + file);
                    return FileVisitResult.CONTINUE;
                }
            });

            Files.walkFileTree(Paths.get("Test/"), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    BufferedReader br = new BufferedReader(new FileReader(file.toFile()));
                    String a;
                    String s = "";
                    while(( a = br.readLine()) != null) {
                        s += a;
                    }
                    if (file.getParent().getParent().getFileName().toString().equals("Test")) {
                        perceptronLayer.get(file.getParent().getFileName().toString()).addTestData(s);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    System.err.println("Failed to visit file: " + file);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
