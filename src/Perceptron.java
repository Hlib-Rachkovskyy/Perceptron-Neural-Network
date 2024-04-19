import java.util.ArrayList;
import java.util.Arrays;

public class Perceptron {
    ArrayList<String> dataToLearn;
    ArrayList<String> dataToPredict;

    private double[] weights;
    public Perceptron(){

        this.weights = new double[26];
        this.dataToLearn = new ArrayList<>();
        this.dataToPredict = new ArrayList<>();
        for (int i = 0; i < 26; i++){
            this.weights[i] = Math.random() -0.5;
        }
    }
    public void test() {
        int counter = 0;
        for (String text : dataToPredict) {
            double[] testData = Service.createData(text);
            if (predict(testData) == 1) {
                counter++;
            }
        }

        System.out.println(counter + " of " + dataToPredict.size() + " tests passed");
    }
    public void PerceptronTrainingFromData(){
        int counter;
        do {
            for (String trainText : dataToLearn) {
                double[] dataForPerceptron = Service.createData(trainText);
                train(dataForPerceptron, 1);
            }

            counter = 0;
            for (String text : dataToLearn) {
                double[] testData = Service.createData(text);
                if (predict(testData) == 1) {
                    counter++;
                }
            }
        } while (counter != dataToLearn.size());
        System.out.println(Arrays.toString(weights));
    }


    public void train(double[] dataToTrain, int iterations){
        for (int i = 0; i < iterations; i++){
            double prediction = predict(dataToTrain);
            double error =  1 - prediction;
            for (int j = 0; j < weights.length; j++){
                double alpha = 0.2;
                double delta = alpha * error * dataToTrain[j];
                this.weights[j] += delta;
            }
        }
    }

    public double predict(double[] input){
        double ans = 0;
        for (int i = 0; i < input.length; i++){
            ans += weights[i] * input[i];
        }

        return ans > 0 ? 1.0 : 0.0;
    }

}
