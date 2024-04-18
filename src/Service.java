import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface Service {

    static double[] createData(String text){
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
        for (int i = 0; i < tmp.length; i++){
            tmp[i] /= total;
        }

        return tmp;
    }
}
