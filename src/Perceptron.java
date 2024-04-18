import java.util.ArrayList;

public class Perceptron {
    ArrayList<String> dataToLearn;
    ArrayList<String> dataToPredict;

    public Perceptron(String language){
        dataToLearn = new ArrayList<>();
        dataToPredict = new ArrayList<>();
    }

    public double[] makeMagic(ArrayList<String> data){


        return null;
    }

    public void getLearningData(String data){
        dataToLearn.add(data);
    }

    public void getTestData(String data){
        dataToPredict.add(data);
    }
}
