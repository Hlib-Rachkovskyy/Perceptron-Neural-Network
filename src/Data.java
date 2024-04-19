public class Data {
    public double[] array;
    public double skutecznosc;

    public Data(double[] array, double skutecznosc){
        this.array = array;
        this.skutecznosc = skutecznosc;
    }

    public static int math(double a, double b) {
        a = (a/b) * 100;
        return (int)a < 100 ? (int)a : 100;
    }
}
