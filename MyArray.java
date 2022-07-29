//Q1create an array of size 5 of int and iterate it. ***Find the greatest value in that array
//Q3


import java.util.Arrays;

public class MyArray {
    void myArray() {
        int large = 0;
        int MYArray[] = {2, 15, 1, 100, 70};
    //Printing array using for each loop
        for (int k : MYArray) {
            System.out.println(k);

            for (int j = 0; j < MYArray.length; j++) {
                if (k < MYArray[j]) {
                    large = MYArray[j];
                }
            }
        }
        System.out.println("Largest value in array is "+ large);

        Arrays.sort(MYArray);
        System.out.println("Third largest is   " + MYArray[2]);

    }




    public static void main(String[] args) {

        MainApplication mainApplication = new MainApplication();
        mainApplication.myArray();
    }
}
