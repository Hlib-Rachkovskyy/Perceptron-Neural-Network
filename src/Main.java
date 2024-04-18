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
        findData("Test");
        for (Perceptron perceptron : perceptronLayer.values()) {
            perceptron.makeMagic(perceptron.dataToLearn);
        }
    }

    public static void createPerceptron(String language){
        perceptronLayer.put(language, new Perceptron(language));
    }

    public static double[] findData(String dataFor){
        try {
            Files.walkFileTree(Paths.get("Data/" + dataFor), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    System.out.println("Directory: " + dir.getFileName());
                    if (!(dir.getFileName().toString().equals("Test") || dir.getFileName().toString().equals("Training"))) {
                        createPerceptron(dir.getFileName().toString());
                    }
                    System.out.println(perceptronLayer);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    //System.out.println("File: " + file.getParent().getFileName());
                    BufferedReader br = new BufferedReader(new FileReader(file.toFile()));
                    String a;
                    String s = "";
                    while(( a = br.readLine()) != null) {
                        s += a;
                    }
                    perceptronLayer.get(file.getParent().getFileName().toString()).getLearningData(s);
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


        return null;
    }
}
