package com.company;

public class Main {

    private static final double k = 0.13;
    private static final double c = 2.00;
    private static final double lx = 8;
    private static final double ly = 6;
    private static final double T = 1000;
    private static final double alfa = 0.002;
    private static final int I = 10;
    private static final int K = 10;
    private static final double hx = lx / I;
    private static final double ht = T / K;
    static double[][] Vmatrix = new double[K][I];

    private static double xi(double x) {
        // return Math.sin(Math.PI * x / lx) * 5;
        return 4;
    }


    private static double eta(double y) {
        return 1 - Math.cos(2 * Math.PI * y / ly);
    }

    private static double V(int i, int j) {
        if (i < 1 || j < 0 || i >= K || j >= I)
            return 0;
        if (j == 0) {
            double fict = Vmatrix[i-1][1] - 2 * hx * alfa * Vmatrix[i-1][0] / k;
            return k * ht * (Vmatrix[i - 1][1] - 2 * Vmatrix[i - 1][0] + fict) / (c * hx * hx) + Vmatrix[i - 1][0];
        }
        if (j == I - 1) {
            double fict = Vmatrix[i-1][I - 2] - 2 * hx * alfa * Vmatrix[i-1][I - 1] / k;
            return k * ht * (fict - 2 * Vmatrix[i - 1][I - 1] + Vmatrix[i - 1][I - 2]) / (c * hx * hx) + Vmatrix[i - 1][I - 1];
        }

        return k * ht * (Vmatrix[i - 1][j + 1] - 2 * Vmatrix[i - 1][j] + Vmatrix[i - 1][j - 1]) / (c * hx * hx) + Vmatrix[i - 1][j];
    }


    public static void main(String[] args) {
        // Матрица температуры стержня в каждый момент времени
        for (int i = 0; i < I; i++) { // заполняем изначальное распределение температуры
            Vmatrix[0][i] = xi(i * hx);
        }

        for (int i = 1; i < K; i++) { // идем по времени
            for (int j = 0; j < I ; j++) { // идем по стержню
                Vmatrix[i][j] = V(i, j);
            }

        }
//        for(int i=1;i<K;i++){ // идем по времени
//            for (int j=1;j<I+1;j++){ // идем по стержню
//                Vmatrix[i][j]=k*ht*(Vmatrix[i-1][j+1]-2*Vmatrix[i-1][j]+Vmatrix[i-1][j-1])/(c*hx*hx)+Vmatrix[i-1][j]; // рассчитываем температуру в точке
//            }
//            Vmatrix[i][0]=Vmatrix[i][2]-2*hx*alfa*Vmatrix[i][1]/k;// рассчитываем граниченые условия
//            Vmatrix[i][I+1]=Vmatrix[i][I-1]-2*hx*alfa*Vmatrix[i][I]/k;
//        }

//        for(int i=1;i<K;i++){ // идем по времени
//            for (int j=2;j<I;j++){ // идем по стержню
//                Vmatrix[i][j]=k*ht*(Vmatrix[i-1][j+1]-2*Vmatrix[i-1][j]+Vmatrix[i-1][j-1])/(c*hx*hx)+Vmatrix[i-1][j]; // рассчитываем температуру в точке
//            }
//            Vmatrix[i][0]=Vmatrix[i-1][1]-(Vmatrix[i-1][2]-Vmatrix[i-1][1]);// рассчитываем граниченые условия
//            Vmatrix[i][I+1]=Vmatrix[i-1][I]-(Vmatrix[i-1][I-1]-Vmatrix[i-1][I]);
//
//            Vmatrix[i][1]=Vmatrix[i][3]-2*hx*alfa*Vmatrix[i][2]/k;// рассчитываем граниченые условия
//            Vmatrix[i][I]=Vmatrix[i][I-2]-2*hx*alfa*Vmatrix[i][I-1]/k;


            for (int i = 0; i < K; i++) {
                for (int j = 0; j < I ; j++)
                    System.out.print(Vmatrix[i][j] + " ");
                System.out.println();
            }
        }


    }

