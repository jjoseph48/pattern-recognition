/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pola_final;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author Gallery
 */
public class Knn {

    // variabel di bawah ini digunakan untuk menyimpan data inputan
//    private final String[] labelInput;
//    private final double[][] dataInput;

    private String[] labelTrain;
    private double[][] dataTrain;

    private String[] labelTest;
    private double[][] dataTest;
    
    private String[] labelTemp;
    private double[] hasilJarak;
    private Object[][] dataPred;
    
    private int jumData = 0, jumFitur = 0;

    public Knn(int jumTrain, int jumFitur) {
        this.jumData = jumTrain;
        this.jumFitur = jumFitur;

        dataTrain  = new double[jumTrain][jumFitur - 1];
        labelTrain = new String[jumTrain];
    }

    // inisiasikan getter dan setter
    public String[] getLabelTrain() {
        return labelTrain;
    }

    public void setLabelTrain(String[] labelTrain) {
        this.labelTrain = labelTrain;
    }

    public double[][] getDataTrain() {
        return dataTrain;
    }

    public void setDataTrain(double[][] dataTrain) {
        this.dataTrain = dataTrain;
    }

    public String[] getLabelTest() {
        return labelTest;
    }

    public void setLabelTest(String[] labelTest) {
        this.labelTest = labelTest;
    }

    public double[][] getDataTest() {
        return dataTest;
    }

    public void setDataTest(double[][] dataTest) {
        this.dataTest = dataTest;
    }

    public int getJumData() {
        return jumData;
    }

    public void setJumData(int jumData) {
        this.jumData = jumData;
    }

    public int getJumFitur() {
        return jumFitur;
    }

    public void setJumFitur(int jumFitur) {
        this.jumFitur = jumFitur;
    }

    public Object[][] getDataPred() {
        return dataPred;
    }

    public void setDataPred(Object[][] dataPred) {
        this.dataPred = dataPred;
    }

    // inputkan data train tomat
    public void inputDataTrain(String filePath) {
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String label = "";

            String line = br.readLine();
            for (int i = 0; i < this.jumData; i++) {
                if (line != null) {
                    String[] isi = line.split(",");
                    for (int j = 0; j < jumFitur; j++) {
                        if (j == 12) {
//                            int labelTemp = Integer.valueOf(isi[j]);
//                            if (labelTemp == 1) {
//                                label = "Tomat Setengah Matang";
//                            } else if (labelTemp == 2) {
//                                label = "Tomat Mentah";
//                            } else {
//                                label = "Tomat Matang";
//                            }
                            this.labelTrain[i] = isi[j];
                        } else {
                            double dataDaun = Double.valueOf(isi[j]);
                            dataTrain[i][j] = dataDaun;
                        }

                    }
                    line = br.readLine();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    // inputkan data train tomat
    public void inputDataTest(String filePath, int jumTrain) {
        
        dataTest  = new double[jumTrain][jumFitur - 1];
        labelTest = new String[jumTrain];
        
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String label = "";

            String line = br.readLine();
            for (int i = 0; i < jumTrain; i++) {
                if (line != null) {
                    String[] isi = line.split(",");
                    for (int j = 0; j < jumFitur; j++) {
                        if (j == 12) {
//                            int labelTemp = Integer.valueOf(isi[j]);
//                            if (labelTemp == 1) {
//                                label = "Tomat Setengah Matang";
//                            } else if (labelTemp == 2) {
//                                label = "Tomat Mentah";
//                            } else {
//                                label = "Tomat Matang";
//                            }
                            this.labelTest[i] = isi[j];
                        } else {
                            double dataDaun = Double.valueOf(isi[j]);
                            dataTest[i][j] = dataDaun;
                        }

                    }
                    line = br.readLine();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    

    // bagi data menjadi 3-fold
//    private double[][] ambilDataFold(int noFold) {
//        int start, index = 0;
//        int jumFold = jumData / 3;
//        double[][] fold = new double[jumFold][jumFitur-1];
//
//        if (noFold == 1) {
//            start = 0;
//        } else if (noFold == 2) {
//            start = jumFold;
//        } else {
//            start = jumFold * 2;
//        }
//        index = start;
//        for (int i = 0; i < jumFold; i++) {
//            for (int j = 0; j < jumFitur-1; j++) {
//                fold[i][j] = dataInput[index][j];
//            }
//            index++;
//        }
//
//        return fold;
//    }
//    
//    private String[] ambilLabelFold(int noFold) {
//        int start, index = 0;
//        int jumFold = jumData / 3;
//        String[] labelfold = new String[jumFold];
//
//        if (noFold == 1) {
//            start = 0;
//        } else if (noFold == 2) {
//            start = jumFold;
//        } else {
//            start = jumFold * 2;
//        }
//        index = start;
//        for (int i = 0; i < jumFold; i++) {
//            labelfold[i] = labelInput[index];
//            index++;
//        }
//
//        return labelfold;
//    }
//
//    public void olahFold(int kFold) {
//        double[][] fold1  = ambilDataFold(1);
//        String[] lblfold1 = ambilLabelFold(1);
//        
//        double[][] fold2  = ambilDataFold(2);
//        String[] lblfold2 = ambilLabelFold(2);
//        
//        double[][] fold3  = ambilDataFold(3); 
//        String[] lblfold3 = ambilLabelFold(3);
//
//        switch (kFold) {
//            case 1:
//                this.dataTrain  = mergeData(fold2, fold3);
//                this.labelTrain = mergeLabel(lblfold2, lblfold3);
//                this.dataTest   = fold1;
//                this.labelTest  = lblfold3;
//                break;
//            case 2:
//                this.dataTrain = mergeData(fold1, fold3);
//                this.labelTrain = mergeLabel(lblfold1, lblfold3);
//                this.dataTest = fold2;
//                this.labelTest  = lblfold2;
//                break;
//            default:
//                this.dataTrain = mergeData(fold1, fold2);
//                this.labelTrain = mergeLabel(lblfold1, lblfold2);
//                this.dataTest = fold3;
//                this.labelTest  = lblfold3;
//                break;
//        }
//        
//    }

    public void cariKnn(double[] fiturTest) {
        double[] fiturTrain = new double[jumFitur];
        double[] jarakTemp = new double[this.dataTrain.length];

        for (int a = 0; a < dataTrain.length; a++) {
            for (int b = 0; b < jumFitur-1; b++) {
                fiturTrain[b] = dataTrain[a][b];
            }
            double hasil = jarakEuclidean(fiturTrain, fiturTest);
            jarakTemp[a] = hasil;
        }
        this.hasilJarak = sortingJarak(jarakTemp);
        this.labelTemp = sortingLabel(this.labelTrain, jarakTemp, this.hasilJarak);
        this.dataPred = gabungData(this.hasilJarak, this.labelTemp);
        
    }

    public String prediksi(int k, Object[][] dataPred) {
        String[] labelPrediksi = new String[k];
        Object[][] dataPredOlah = dataPred.clone();

        for (int i = 0; i < k; i++) {
            Object dataK = dataPredOlah[i][1];
            labelPrediksi[i] = String.valueOf(dataK);
        }

        return cariModus(labelPrediksi);
    }
    
    private Object[][] gabungData(double[] dataJarak, String[] label) {
        Object[][] gabung = new Object[dataJarak.length][2];
        double[] hasilJarak = dataJarak.clone();
        String[] hasilLabel = label.clone();

        // memasukkan hasil hitung euclidean
        for (int i = 0; i < dataJarak.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    gabung[i][j] = hasilJarak[i];
                } else {
                    gabung[i][j] = hasilLabel[i];
                }
            }
        }
        return gabung;
    }

    private double[][] mergeData(double[][] train1, double[][] train2) {
        double[][] mergeData = new double[train1.length + train2.length][jumFitur - 1];

        int counter = 0;
        for (int i = 0; i < train1.length; i++) {
            for (int j = 0; j < jumFitur - 1; j++) {
                mergeData[counter][j] = train1[i][j];
            }
            counter++;
        }
        for (int i = 0; i < train2.length; i++) {
            for (int j = 0; j < jumFitur - 1; j++) {
                mergeData[counter][j] = train2[i][j];
            }
            counter++;
        }
        return mergeData;
    }
    
    private String[] mergeLabel(String[] train1, String[] train2) {
        String[] mergeLabel = new String[train1.length + train2.length];

        int counter = 0;
        for (int i = 0; i < train1.length; i++) {
            mergeLabel[counter] = train1[i];
            counter++;
        }
        for (int i = 0; i < train2.length; i++) {
            mergeLabel[counter] = train1[i];
            counter++;
        }
        return mergeLabel;
    }

    private double jarakEuclidean(double[] dataLatih, double[] dataUji) {

        return Math.sqrt(Math.pow(dataLatih[0] - dataUji[0], 2)
                + Math.pow(dataLatih[1] - dataUji[1], 2)
                + Math.pow(dataLatih[2] - dataUji[2], 2)
                + Math.pow(dataLatih[3] - dataUji[3], 2)
                + Math.pow(dataLatih[4] - dataUji[4], 2)
                + Math.pow(dataLatih[5] - dataUji[5], 2)
                + Math.pow(dataLatih[6] - dataUji[6], 2)
                + Math.pow(dataLatih[7] - dataUji[7], 2)
                + Math.pow(dataLatih[8] - dataUji[8], 2)
                + Math.pow(dataLatih[9] - dataUji[9], 2)
                + Math.pow(dataLatih[10] - dataUji[10], 2)
                + Math.pow(dataLatih[11] - dataUji[11], 2));
    }

    private double[] sortingJarak(double[] hasil) {
        double helper;
        double[] hasilSort = new double[hasil.length];
        hasilSort = hasil.clone();

        for (int i = 0; i < hasilSort.length - 1; i++) {
            for (int j = 0; i + j < hasilSort.length - 1; j++) {
                if (hasilSort[j] > hasilSort[j + 1]) {
                    helper = hasilSort[j];
                    hasilSort[j] = hasilSort[j + 1];
                    hasilSort[j + 1] = helper;
                }
            }
        }
        return hasilSort;
    }

    private String[] sortingLabel(String[] labelLama, double[] hasilLama,
            double[] hasilSort) {
        String[] labelBaru = new String[labelLama.length];

        for (int i = 0; i < hasilSort.length; i++) {
            for (int j = 0; j < hasilLama.length; j++) {
                if (hasilSort[i] == hasilLama[j]) {
                    labelBaru[i] = labelLama[j];
                }
            }
        }
        return labelBaru;
    }

    private String cariModus(String[] a) {
        int index = 0;
        int temp = 1;
        int jum = 1;
        for (int k = 1; k < a.length; k++) {

            if (a[k - 1].equals(a[k])) {
                jum++;
            }
            if (jum > temp) {
                temp = jum;
                index = k;
            } else {
                jum = 1;
            }
        }
        return a[index];
    }

    public double[] cariAkurasi(String[][] labelPrediksi, String[] labelUji) {
        double[] akurasi = new double[3];
        double sumBenar = 0;

        String[][] lblPred = labelPrediksi.clone();
        String[] lblUji = labelUji.clone();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < lblUji.length; j++) {
                if (lblPred[j][i].equalsIgnoreCase(lblUji[j])) {
                    sumBenar = sumBenar + 1;
                }
            }
            akurasi[i] = sumBenar / lblUji.length;
            sumBenar = 0;
        }

        return akurasi;
    }

    public void tampilDataUji() {
        int noUrut = 1;
        System.out.println("No. \t MeanR \t\t MeanG \t\t MeanB \t\t VarR  \t\t "
                + "VarG \t\t VarB \t\t SkewR \t\t SkewG \t\t SkewB \t\t KurtR "
                + "\t\t KurtG \t\t KurtB");

        for (int i = 0; i < dataTest.length; i++) {
            System.out.print(noUrut + "\t");
            for (int j = 0; j < jumFitur; j++) {
                if(j == 12)
                    System.out.print(labelTest[i] + "\t\t");
                else
                    System.out.print(dataTest[i][j] + "\t\t");
            }
            noUrut++;
            System.out.println("");
        }
    }

    public void tampilDataPred(Object[][] dataPred) {
        int noUrut = 1;
        System.out.println("No. \t Jarak Euclidean \t\t Label");

        for (int i = 0; i < dataPred.length; i++) {
            System.out.print(noUrut + "\t");
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    System.out.print(dataPred[i][j] + "\t\t");
                } else {
                    System.out.print(dataPred[i][j] + "\t\t");
                }
            }
            noUrut++;
            System.out.println("");
        }
    }
}
