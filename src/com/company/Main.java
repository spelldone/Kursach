package com.company;


public class Main {

    private static final double k = 0.13;
    private static final double c = 2.00;
    private static final double lx = 8;
    private static final double ly = 6;
    private static final double T = 120;
    private static final double alfa = 0.002;
    private static final int I = 50;
    private static final int I2 = 10;
    private static final int K = 1000;
    private static final double hx = lx / I;
    private static final double hy = ly / I2;
    private static final double ht = T / K;
    static double[][] Vmatrix = new double[K + 1][I + 1];
    static double[][] Wmatrix = new double[K + 1][I2 + 1];
    static double[][][] Umatrix = new double[K + 1][I + 1][I2 + 1];

    private static double xi(double x) {
        return Math.sin(Math.PI * x / lx) * 5;

    }


    private static double eta(double y) {
        return 1 - Math.cos(2 * Math.PI * y / ly);
    }


    //Расчет значений узла для W
    private static double W(int i, int j) {

        if (j == 0) {
            return Wmatrix[i][1];

        }
        if (j == I2) {
            return Wmatrix[i][I2 - 1];
        }

        return k * ht * (Wmatrix[i - 1][j + 1] - 2 * Wmatrix[i - 1][j] + Wmatrix[i - 1][j - 1]) / (c * hy * hy) + Wmatrix[i - 1][j];


    }

    //Расчет значений узла для V
    private static double V(int i, int j) {

        if (j == 0) {
            double fict = Vmatrix[i - 1][1] - 2 * hx * alfa * Vmatrix[i - 1][0] / k;
            return k * ht * (Vmatrix[i - 1][1] - 2 * Vmatrix[i - 1][0] + fict) / (c * hx * hx) + Vmatrix[i - 1][0];
        }
        if (j == I) {
            double fict = Vmatrix[i - 1][I - 1] - 2 * hx * alfa * Vmatrix[i - 1][I] / k;
            return k * ht * (fict - 2 * Vmatrix[i - 1][I] + Vmatrix[i - 1][I - 1]) / (c * hx * hx) + Vmatrix[i - 1][I];
        }

        return k * ht * (Vmatrix[i - 1][j + 1] - 2 * Vmatrix[i - 1][j] + Vmatrix[i - 1][j - 1]) / (c * hx * hx) + Vmatrix[i - 1][j];
    }


    public static void main(String[] args) {

//        //Расчет значений V(Новичков)
//        for (int i = 0; i < I + 1; i++) { // заполняем изначальное распределение температуры
//            Vmatrix[0][i] = xi(i * hx);
//        }
//
//
//        for (int i = 1; i < K + 1; i++) { // идем по времени
//            for (int j = 0; j < I + 1; j++) { // идем по стержню
//                Vmatrix[i][j] = V(i, j);
//            }
//
//        }


//        for (int i = 0; i < I + 1; i++) {
//            String str = String.format("%.7f", Vmatrix[K][i]);
//            System.out.println(str);
//            if (i < I) {
//                for (int j = 1; j < 10; j++) {
//                    String str2 = String.format("%.7f",Vmatrix[K][i]+j*(Vmatrix[K][i+1] - Vmatrix[K][i]) / 10);
//                    System.out.println(str2);
//                }
//            }
//        }

//        for(int i=0;i<I+1;i++){
//            String str=String.format("%.7f",i*hx);
//            System.out.println(str);
//        }
//
//        for (int i = 0; i < I + 1; i++) {
//            String str=String.format("%.7f",Vmatrix[K / 10][i]);
//            System.out.println(str);
//        }


        // Расчет значений W(Деев)
        for (int i = 0; i < I2 + 1; i++) { // заполняем изначальное распределение температуры
            Wmatrix[0][i] = eta(i * hy);
        }

        for (int i = 1; i < K + 1; i++) { // идем по времени
            for (int j = 1; j < I2 + 1; j++) { // идем по стержню
                Wmatrix[i][j] = W(i, j);
            }
            Wmatrix[i][0] = W(i, 0);
        }

        for (int i = 0; i < I2 + 1; i++) {
            String str = String.format("%.7f", Wmatrix[K / 4][i]);
            System.out.println(str);
            if (i < I2) {
                for (int j = 1; j < 10; j++) {
                    String str2 = String.format("%.7f", Wmatrix[K / 4][i] + j * (Wmatrix[K / 4][i + 1] - Wmatrix[K / 4][i]) / 10);
                    System.out.println(str2);
                }
//                String str2 = String.format("%.7f",(Wmatrix[K/4][i] + Wmatrix[K/4][i+1]) / 2);
//                System.out.println(str2);
            }
        }
//        for(int i=0;i<K+1;i++){
//            String str=String.format("%.7f",i*ht);
//            System.out.println(str);
//        }

//// Расчет значений температуры пластины
//        for(int i=0;i<K+1;i++){
//            for(int j=0;j<I+1;j++){
//                for (int o=0;o<I2+1;o++)
//                    Umatrix[i][j][o]=Wmatrix[i][o]*Vmatrix[i][j];
//            }
//        }
//
//    }
    }


}

