import java.util.ArrayList;
import java.util.Arrays;

public class Perceptron {
    int dataForTainLanguages = 0;
    private double teta = 0.05;
    ArrayList<String> dataToLearn;
    ArrayList<String> dataToPredict;
    public String language;
    private double[] weights;
    public Perceptron(String language){
        this.language = language;
        this.weights = new double[26];
        this.dataToLearn = new ArrayList<>();
        this.dataToPredict = new ArrayList<>();
        for (int i = 0; i < 26; i++){
            this.weights[i] = 0;
        }
    }
    public void test() {
        int counter = 0;
        for (String text : dataToPredict) {
            Data data = Service.createData(text);
            double[] testData = data.array;
            if (predict(testData) == 1) {
                counter++;
            }
            System.out.println("Skutecznosc tekstu dla jezyka " + language + " " +  Data.math(data.skutecznosc, dataForTainLanguages) + "%");

        }
        System.out.println("Skutecznosc wszystkich testow " + (int) ((double )counter/dataToPredict.size() * 100));
        System.out.println(counter + " of " + dataToPredict.size() + " tests passed " + language);
    }
    public void PerceptronTrainingFromData(){
        int counter;
        do {
            for (String trainText : dataToLearn) {
                double[] dataForPerceptron = Service.createData(trainText).array;
                dataForTainLanguages += Service.createData(trainText, dataForTainLanguages);
                train(dataForPerceptron);
            }

            counter = 0;
            for (String text : dataToLearn) {
                double[] testData = Service.createData(text).array;
                if (predict(testData) == 1) {
                    counter++;
                }
            }
        } while (counter != dataToLearn.size());
        System.out.println(Arrays.toString(weights));
    }


    public void train(double[] dataToTrain){

            double prediction = predict(dataToTrain);
            double error =  1 - prediction;
            for (int j = 0; j < weights.length; j++){
                double alpha = 0.1;
                double delta = alpha * error * dataToTrain[j];
                this.weights[j] += delta;
            }

    }

    public double predict(double[] input){
        double ans = 0;
        for (int i = 0; i < input.length; i++){
            ans += weights[i] * input[i];
        }
        return ans > teta ? 1.0 : 0.0;
    }

}
