import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextLine()) {
            System.out.println("Jam tidak valid");
            sc.close();
            return;
        }

        String line1 = sc.nextLine().trim();

        // Validasi format HH:MM
        if (!line1.matches("^\\d{1,2}:\\d{1,2}$")) {
            System.out.println("Jam tidak valid");
            sc.close();
            return;
        }

        String[] timeParts = line1.split(":");
        int startH = Integer.parseInt(timeParts[0]);
        int startM = Integer.parseInt(timeParts[1]);

        // Validasi rentang jam (0-23) dan menit (0-59)
        if (startH < 0 || startH > 23 || startM < 0 || startM > 59) {
            System.out.println("Jam tidak valid");
            sc.close();
            return;
        }

        String jamAwal = String.format("%02d:%02d", startH, startM);
        int currentMinutes = startH * 60 + startM;
        int totalGeser = 0;
        int pergantianHari = 0;

        final int MENIT_PER_HARI = 1440; // 24 * 60

        while (sc.hasNextLine()) {
            String command = sc.nextLine().trim();

            if (command.equals("---")) {
                break;
            }

            if (command.isEmpty()) {
                continue;
            }

            if (!command.matches("^[+-]\\d+$")) {
                System.out.println("Perintah tidak valid");
                continue;
            }

            int n = Integer.parseInt(command);
            totalGeser += n;

            // Kalkulasi langsung tanpa looping per menit
            if (n > 0) {
                pergantianHari += (currentMinutes + n) / MENIT_PER_HARI;
                currentMinutes = (currentMinutes + n) % MENIT_PER_HARI;
            } else if (n < 0) {
                int sisa = currentMinutes + n;
                if (sisa < 0) {
                    // Hitung berapa kali melewati batas 00:00 ke belakang
                    int hariBerkurang = (Math.abs(sisa) + MENIT_PER_HARI - 1) / MENIT_PER_HARI;
                    pergantianHari += hariBerkurang;
                    currentMinutes = (sisa % MENIT_PER_HARI + MENIT_PER_HARI) % MENIT_PER_HARI;
                } else {
                    currentMinutes = sisa;
                }
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

        sc.close();
    }
}