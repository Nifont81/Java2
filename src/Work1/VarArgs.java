package Work1;

public class VarArgs {
    public static void main(String[] args) {
        varPrint(777,"a","b","c");
    }

    public static void varPrint(int x, String... arr){
        System.out.println("x="+x);
        for (String str: arr) {
            System.out.println(str);
        }

    }
}
