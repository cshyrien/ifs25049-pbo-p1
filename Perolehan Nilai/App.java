import java.util.Locale;
import java.util.Scanner;

public class App {

    private static final String[] KODE = {"PA", "T", "K", "P", "UTS", "UAS"};
    private static final String[] NAMA = {"Partisipatif", "Tugas", "Kuis", "Proyek", "UTS", "UAS"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);

        prosesKalkulasiNilai(sc);
        sc.close();
    }

    private static void prosesKalkulasiNilai(Scanner sc) {
        int[] bobotFinal = new int[6];
        int totalBobot = 0;

        for (int i = 0; i < 6; i++) {
            if (!sc.hasNextLine()) break;
            try {
                bobotFinal[i] = Integer.parseInt(sc.nextLine().trim());
                totalBobot += bobotFinal[i];
            } catch (NumberFormatException e) {
                // Ignore bad input line
            }
        }

        if (totalBobot != 100) {
            System.out.println("Total bobot harus 100");
            return;
        }

        int[] totalBobotKomponen = new int[6];
        int[] totalPerolehan = new int[6];

        prosesInputKomponen(sc, totalBobotKomponen, totalPerolehan);
        tampilkanHasil(bobotFinal, totalBobotKomponen, totalPerolehan);
    }

    private static void prosesInputKomponen(Scanner sc, int[] totalBobotKomponen, int[] totalPerolehan) {
        while (sc.hasNextLine()) {
            String baris = sc.nextLine().trim();
            if (baris.equals("---")) break;
            if (baris.isEmpty()) continue;

            String[] potongan = baris.split("\\|", -1);
            if (potongan.length != 3) {
                System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
                continue;
            }

            String simbol = potongan[0].trim();
            int idx = cariIndeksSimbol(simbol);

            if (idx == -1) {
                System.out.println("Simbol tidak dikenal");
                continue;
            }

            try {
                int b = Integer.parseInt(potongan[1].trim());
                int p = Integer.parseInt(potongan[2].trim());

                totalBobotKomponen[idx] += b;
                totalPerolehan[idx] += p;
            } catch (NumberFormatException e) {
                System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
            }
        }
    }

    private static int cariIndeksSimbol(String simbol) {
        for (int i = 0; i < KODE.length; i++) {
            if (KODE[i].equals(simbol)) return i;
        }
        return -1;
    }

    private static void tampilkanHasil(int[] bobotFinal, int[] totalBobotKomponen, int[] totalPerolehan) {
        double nilaiAkhir = 0.0;
        System.out.println("Perolehan Nilai:");

        for (int i = 0; i < 6; i++) {
            int persen = 0;
            if (totalBobotKomponen[i] > 0) {
                persen = (totalPerolehan[i] * 100) / totalBobotKomponen[i];
            }

            if (persen > 100) persen = 100;
            else if (persen < 0) persen = 0;

            double kontribusi = (persen / 100.0) * bobotFinal[i];
            nilaiAkhir += kontribusi;

            System.out.printf(">> %s: %d/100 (%.2f/%d)%n", NAMA[i], persen, kontribusi, bobotFinal[i]);
        }

        if (nilaiAkhir > 100.0) nilaiAkhir = 100.0;
        else if (nilaiAkhir < 0.0) nilaiAkhir = 0.0;

        nilaiAkhir = Math.round(nilaiAkhir * 100.0) / 100.0;

        System.out.println();
        System.out.printf(">> Nilai Akhir: %.2f%n", nilaiAkhir);
        System.out.println(">> Grade: " + tentukanGrade(nilaiAkhir));
    }

    // PERBAIKAN: Bersihkan operator '||' yang membingungkan/redundant
    private static String tentukanGrade(double n) {
        if (n >= 79.5) return "A";
        if (n >= 72.0) return "AB";
        if (n >= 64.5) return "B";
        if (n >= 57.0) return "BC";
        if (n >= 49.5) return "C";
        if (n >= 34.0) return "D";
        return "E";
    }
}