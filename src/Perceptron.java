import java.util.ArrayList;
import java.util.Arrays;

public class Perceptron {
    private double alpha = 0.2;
    ArrayList<String> dataToLearn;
    ArrayList<String> dataToPredict;

    private double[] weights = new double[26];
    private double teta = 0.0;

    public Perceptron(){
        this.dataToLearn = new ArrayList<>();
        this.dataToPredict = new ArrayList<>();
        for (int i = 0; i < 26; i++){
            this.weights[i] = (Math.random()*2)-1;
        }
    }
    public void test() {
        for (String text : dataToPredict) {
            double[] testData = Service.createData(text);
            System.out.print(predict(testData) + " ");
        }
        System.out.println();
    }
    public void PerceptronTrainingFromData(){
        for (String trainText : dataToLearn) {
            double[] dataForPerceptron = Service.createData(trainText);
            train(dataForPerceptron, 1);
        }
        System.out.println(Arrays.toString(weights));
    }


    public void train(double[] dataToTrain, int iterations){
        for (int i = 0; i < iterations; i++){
            double prediction = predict(dataToTrain);
            double error =  1 - prediction ;
            for (int j = 0; j < weights.length; j++){
                double delta = alpha * error * dataToTrain[j];
                this.weights[j] += delta;
            }
            teta += error * alpha;
        }
    }

    public double predict(double[] input){
        double ans = 0;
        for (int i = 0; i < input.length; i++){
            ans += weights[i] * input[i];
        }
        return ans >= 0 ? 1.0 : 0.0 ;
    }

    public void addLearningData(String data){
        dataToLearn.add(data);
    }

    public void addTestData(String data){
        dataToPredict.add(data);
    }
}
