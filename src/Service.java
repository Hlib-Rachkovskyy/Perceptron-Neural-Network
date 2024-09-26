import java.util.Arrays;

public interface Service {

    static Data createData(String text){
        double []tmp = new double[26];
        int total = 0;
        double valueOfAllText = 0;
        Arrays.fill(tmp,0.0);
        for (String s : text.split("")) {
            if (!s.isEmpty()) {
                valueOfAllText++;
                char character = s.toLowerCase().charAt(0);
                if (96 < character && character < 123) {
                    int value = character - 97;
                    tmp[value]++;
                    total++;
                }
            }
        }

        for (int i = 0; i < tmp.length; i++){
            tmp[i] /= total;
        }

        return new Data(tmp,valueOfAllText);
    }
    static int createData(String text, int i){
        double []tmp = new double[26];
        int total = 0;
        Arrays.fill(tmp,0.0);
        for (String s : text.split("")) {
            if (!s.isEmpty()) {
                char character = s.toLowerCase().charAt(0);
                if (96 < character && character < 123) {
                    int value = character - 97;
                    tmp[value]++;
                    total++;
                }
            }
        }

        return total;
    }
}
