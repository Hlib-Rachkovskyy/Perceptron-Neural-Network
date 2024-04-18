import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Perceptron {
    ArrayList<String> dataToLearn;
    ArrayList<String> dataToPredict;

    public Perceptron(String language){
        dataToLearn = new ArrayList<>();
        dataToPredict = new ArrayList<>();
    }

    public double[] makeMagic(ArrayList<String> data){
        double[] wages = new double[26];
        Arrays.fill(wages,0.0);

        for (String text : data) {
            for (String s : text.split("")) {
                if (!s.isEmpty()) {
                    char character = s.toLowerCase().charAt(0);
                    if (96 < character && character < 123) {
                        int value = character - 97;
                        wages[value]++;
                        System.out.println((int) character + " " + s);
                    }
                }
            }
        }
        System.out.println(Arrays.toString(wages));

        return wages;
    }

    public void getLearningData(String data){
        dataToLearn.add(data);
    }

    public void getTestData(String data){
        dataToPredict.add(data);
    }
}
