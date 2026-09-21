import java.util.Scanner;

public class App {

    private static final int MENIT_PER_HARI = 1440;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        prosesKalkulasiJam(sc);
        sc.close();
    }

    private static void prosesKalkulasiJam(Scanner sc) {
        if (!sc.hasNextLine()) {
            System.out.println("Jam tidak valid");
            return;
        }

        String line1 = sc.nextLine().trim();

        if (!line1.matches("^\\d{1,2}:\\d{1,2}$")) {
            System.out.println("Jam tidak valid");
            return;
        }

        try {
            String[] timeParts = line1.split(":");
            int startH = Integer.parseInt(timeParts[0]);
            int startM = Integer.parseInt(timeParts[1]);

            if (startH < 0 || startH > 23 || startM < 0 || startM > 59) {
                System.out.println("Jam tidak valid");
                return;
            }

            hitungDanTampilkanHasil(sc, startH, startM);
        } catch (NumberFormatException e) {
            System.out.println("Jam tidak valid");
        }
    }

    private static void hitungDanTampilkanHasil(Scanner sc, int startH, int startM) {
        String jamAwal = String.format("%02d:%02d", startH, startM);
        int currentMinutes = startH * 60 + startM;
        int totalGeser = 0;
        int pergantianHari = 0;

        while (sc.hasNextLine()) {
            String command = sc.nextLine().trim();

            if (command.equals("---")) break;
            if (command.isEmpty()) continue;

            if (!command.matches("^[+-]\\d+$")) {
                System.out.println("Perintah tidak valid");
                continue;
            }

            try {
                int n = Integer.parseInt(command);
                totalGeser += n;

                if (n > 0) {
                    pergantianHari += (currentMinutes + n) / MENIT_PER_HARI;
                    currentMinutes = (currentMinutes + n) % MENIT_PER_HARI;
                } else if (n < 0) {
                    int sisa = currentMinutes + n;
                    if (sisa < 0) {
                        int hariBerkurang = (Math.abs(sisa) + MENIT_PER_HARI - 1) / MENIT_PER_HARI;
                        // PERBAIKAN: Selalu tambahkan frekuensi pergantian hari (+)
                        pergantianHari += hariBerkurang;
                        currentMinutes = (sisa % MENIT_PER_HARI + MENIT_PER_HARI) % MENIT_PER_HARI;
                    } else {
                        currentMinutes = sisa;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Perintah tidak valid");
            }
        }

        int finalH = currentMinutes / 60;
        int finalM = currentMinutes % 60;
        String jamAkhir = String.format("%02d:%02d", finalH, finalM);
        String strTotalMenit = (totalGeser > 0 ? "+" : "") + totalGeser;

        System.out.println("Jam Awal: " + jamAwal);
        System.out.println("Jam Akhir: " + jamAkhir);
        System.out.println("Total Menit: " + strTotalMenit);
        System.out.println("Pergantian Hari: " + pergantianHari);
    }
}