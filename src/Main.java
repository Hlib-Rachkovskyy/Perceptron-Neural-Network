import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<String, ArrayList<String>> perceptronLanguagesTest = new HashMap<>();
    private static Map<String, ArrayList<String>> perceptronLanguagesTrain = new HashMap<>();
    private static Map<String, Perceptron> perceptronLayer;
    public static void main(String[] args) {
        perceptronLayer = new HashMap<>();
        perceptronLanguagesTest = new HashMap<>();
        perceptronLanguagesTrain = new HashMap<>();
        findData();
        for (String language : perceptronLayer.keySet()) {
            perceptronLayer.get(language).dataToLearn = perceptronLanguagesTrain.get(language);
            perceptronLayer.get(language).dataToPredict = perceptronLanguagesTest.get(language);
            perceptronLayer.get(language).PerceptronTrainingFromData();
            perceptronLayer.get(language).test();
        }
    }

    public static void createPerceptron(String language){
        Perceptron p = new Perceptron();
        perceptronLayer.put(language, p);
        perceptronLanguagesTrain.put(language, new ArrayList<String>());
        perceptronLanguagesTest.put(language, new ArrayList<String>());
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

                        perceptronLanguagesTrain.get(file.getParent().getFileName().toString()).add(s);
                    }
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
                    while((a = br.readLine()) != null) {
                        s += a;
                    }
                    if (file.getParent().getParent().getFileName().toString().equals("Test")) {
                        perceptronLanguagesTest.get(file.getParent().getFileName().toString()).add(s);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
