/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pola_final;

/**
 *
 * @author Gallery
 */
public class Pola_Final {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("\t\t\t IMPLEMENTASI KNN DENGAN 3-FOLD DALAM "
                + "MENGIDENTIFIKASI KEMATANGAN BUAT TOMAT \t\t\t");
        System.out.println("\n");

        // mendata path file yang akan dipakai
        String pathTrain = "D:\\TUGAS VEROL\\Materi Kuliah Smstr 5\\"
                + "Pengenalan Pola\\Proyek\\inputTomatSegm.csv";
        String pathTest = "D:\\TUGAS VEROL\\Materi Kuliah Smstr 5\\"
                + "Pengenalan Pola\\Proyek\\dataUjiTomat.csv";

        // inisiaslisasikan objek uji1 untuk melakukan perhitungan KNN
        // 18 = jumlah data citra tomat
        // 13 = jumlah fitur yang dipakai
        Knn uji1 = new Knn(15, 13);

        // panggil method insert untuk memasukkan data latih dan data uji
        uji1.inputDataTrain(pathTrain);
        uji1.inputDataTest(pathTest, 3);

        //inisialisasikan objek yang akan digunakan untuk menampilkan data
        int lenTest = uji1.getDataTest().length;
        int lenFitur = uji1.getJumFitur();

        double[][] dataTest = new double[lenTest][lenFitur];
        dataTest = uji1.getDataTest();
        double[] elmTest = new double[lenFitur];
        double[] hasilAkurasi = new double[3];

        // inisiasikan variabel dataPredTotal yang akan dipakai untuk
        // meyimpan hasil prediksi keseluruhan k
        String[][] dataPredTotal = new String[lenTest][3];
        int countLabel = 0;

        // dapatkan knn untuk tiap data uji
        for (int a = 0; a < lenTest; a++) {
            System.out.println("=========================================== "
                    + "HASIL SORTING DATA UJI KE- " + (a+1) + " "
                    + "================================================");
            System.out.println("");
            int k = 1;
            for (int b = 0; b < uji1.getJumFitur() - 1; b++) {
                elmTest[b] = dataTest[a][b];
            }
            uji1.cariKnn(elmTest);
            // cari prediksi untuk tiap data uji yang diberikan
            for (int m = 0; m < 3; m++) {
                dataPredTotal[a][countLabel] = uji1.prediksi(k, uji1.getDataPred());
                if (countLabel > 1) {
                    countLabel = 0;
                } else {
                    countLabel++;
                }
                k = k + 2;
            }
            uji1.tampilDataPred(uji1.getDataPred());
            System.out.println("");

        }
        // hitung akurasi untuk tiap k (1,3,5)
        hasilAkurasi = uji1.cariAkurasi(dataPredTotal, uji1.getLabelTest());

        System.out.println("PERHITUNGAN AKURASI");
        int noUrut = 1;
        int kTemp = 1;
        for (int x = 0; x < hasilAkurasi.length; x++) {
            System.out.print(noUrut + ". ");
            System.out.print("Akurasi K -" + kTemp + " : ");
            System.out.println(hasilAkurasi[x]);
            noUrut++;
            kTemp = kTemp + 2;
        }

        System.out.println("\n");

// Uji Validasi
//        for (int i = 1; i <= 3; i++) {
//            uji1.olahFold(i);
//            
//            System.out.println("=========================================== "
//                        + "DATA UJI TOMAT FOLD KE- " + (i) + " "
//                        + "================================================");
//                System.out.println("");
//            uji1.tampilDataUji();
//            System.out.println("\n");
//
//            // inisialisasikan objek yang akan digunakan untuk menampilkan data
//            int lenTest  = uji1.getDataTest().length;
//            int lenFitur = uji1.getJumFitur();
//
//            double[][] dataTest = new double[lenTest][lenFitur];
//            dataTest            = uji1.getDataTest();
//            double[] elmTest    = new double[lenFitur];
//            double[] hasilAkurasi = new double[3];
//
//            // inisiasikan variabel dataPredTotal yang akan dipakai untuk
//            // meyimpan hasil prediksi keseluruhan k
//            String[][] dataPredTotal = new String[lenTest][3];
//            int countLabel = 0;
//
//            // dapatkan knn untuk tiap data uji
//            for (int a = 0; a < lenTest; a++) {
//                System.out.println("=========================================== "
//                        + "HASIL SORTING KNN FOLD KE- " + (i) + " "
//                        + "================================================");
//                System.out.println("");
//                int k = 1;
//                for (int b = 0; b < uji1.getJumFitur() - 1; b++) {
//                    elmTest[b] = dataTest[a][b];
//                }
//                uji1.cariKnn(elmTest);
//                // cari prediksi untuk tiap data uji yang diberikan
//                for (int m = 0; m < 3; m++) {
//                    dataPredTotal[a][countLabel] = uji1.prediksi(k, uji1.getDataPred());
//                    if (countLabel > 1) {
//                        countLabel = 0;
//                    } else {
//                        countLabel++;
//                    }
//                    k = k + 2;
//                }
//                uji1.tampilDataPred(uji1.getDataPred());
//                System.out.println("");                
//                
//            }
//            // hitung akurasi untuk tiap k (1,3,5)
//            hasilAkurasi = uji1.cariAkurasi(dataPredTotal, uji1.getLabelTest());
//
//                System.out.println("PERHITUNGAN AKURASI");
//                int noUrut = 1;
//                int kTemp = 1;
//                for (int x = 0; x < hasilAkurasi.length; x++) {
//                    System.out.print(noUrut + ". ");
//                    System.out.print("Akurasi K -" + kTemp + " : ");
//                    System.out.println(hasilAkurasi[x]);
//                    noUrut++;
//                    kTemp = kTemp + 2;
//                }
//
//                System.out.println("\n");
//        }
    }
}
