import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // --- 1. MODUL NIM ---
        prosesNim(input);

        // --- 2. MODUL JAM SAYA ---
        // Contoh pemanggilan modul Jam Saya
        // prosesJamSaya(input);

        // --- 3. MODUL PALING TER ---
        // Contoh pemanggilan modul Paling Ter
        // prosesPalingTer(input);

        input.close();
    }

    // ==========================================
    // 1. MODUL NIM
    // ==========================================
    public static void prosesNim(Scanner input) {
        if (!input.hasNextLine()) return;
        String nim = input.nextLine().trim();

        if (nim.length() != 8) {
            System.out.println("NIM harus 8 karakter");
            return;
        }

        String prefixProdi = nim.substring(0, 3);
        String namaProdi = ambilNamaProgramStudi(prefixProdi);

        if (namaProdi.equals("Kode tidak tersedia")) {
            System.out.println("Kode tidak tersedia");
        } else {
            tampilkanInformasiNim(nim, namaProdi);
        }
    }

    public static void tampilkanInformasiNim(String nim, String namaProdi) {
        int angkatan = Integer.parseInt("20" + nim.substring(3, 5));
        int urutan = Integer.parseInt(nim.substring(5));

        System.out.printf("Informasi NIM %s: \n", nim);
        System.out.printf(">> Program Studi: %s\n", namaProdi);
        System.out.printf(">> Angkatan: %d\n", angkatan);
        System.out.printf(">> Urutan: %d\n", urutan);
    }

    public static String ambilNamaProgramStudi(String prefix) {
        return switch (prefix) {
            case "11S" -> "Sarjana Informatika";
            case "12S" -> "Sarjana Sistem Informasi";
            case "13S" -> "Sarjana Teknik Elektro";
            case "21S" -> "Sarjana Manajemen Rekayasa";
            case "22S" -> "Sarjana Teknik Metalurgi";
            case "31S" -> "Sarjana Teknik Bioproses";
            case "32S" -> "Sarjana Bioteknologi";
            case "114" -> "Diploma 4 Teknologi Rekayasa Perangkat Lunak";
            case "113" -> "Diploma 3 Teknologi Informasi";
            case "133" -> "Diploma 3 Teknologi Komputer";
            default -> "Kode tidak tersedia";
        };
    }

    // ==========================================
    // 2. MODUL JAM SAYA (Perhitungan Langsung tanpa Simulasi Menit)
    // ==========================================
    public static void prosesJamSaya(int h1, int m1, int h2, int m2) {
        // Kalkulasi total menit langsung secara matematis
        int totalMenit1 = (h1 * 60) + m1;
        int totalMenit2 = (h2 * 60) + m2;

        int selisih = totalMenit2 - totalMenit1;
        if (selisih < 0) {
            selisih += 24 * 60; // Penanganan melintasi tengah malam
        }

        int durasiJam = selisih / 60;
        int durasiMenit = selisih % 60;

        System.out.printf("Durasi: %d jam %d menit\n", durasiJam, durasiMenit);
    }

    // ==========================================
    // 3. MODUL PALING TER (Menggunakan HashMap & ArrayList)
    // ==========================================
    public static void prosesPalingTer(Scanner input) {
        if (!input.hasNextLine()) return;
        
        String teks = input.nextLine();
        
        // Penanganan input kosong (sesuai spesifikasi: tidak menghasilkan output)
        if (teks == null || teks.trim().isEmpty()) {
            return;
        }

        // Menghitung frekuensi menggunakan HashMap
        Map<Character, Integer> frekuensiMap = new HashMap<>();
        for (char c : teks.toCharArray()) {
            frekuensiMap.put(c, frekuensiMap.getOrDefault(c, 0) + 1);
        }

        // Mencari frekuensi tertinggi
        int maxFrekuensi = Collections.max(frekuensiMap.values());

        // Mengumpulkan semua karakter dengan frekuensi tertinggi
        List<Character> hasilPalingTer = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : frekuensiMap.entrySet()) {
            if (entry.getValue() == maxFrekuensi) {
                hasilPalingTer.add(entry.getKey());
            }
        }

        // Tampilkan hasil
        System.out.println("Karakter Paling Sering: " + hasilPalingTer);
        System.out.println("Frekuensi: " + maxFrekuensi);
    }
}