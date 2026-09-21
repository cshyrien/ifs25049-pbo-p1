import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        prosesInformasiNim(sc);
        sc.close();
    }

    private static void prosesInformasiNim(Scanner sc) {
        if (!sc.hasNextLine()) return;

        String nim = sc.nextLine().trim();

        if (nim.length() != 8) {
            System.out.println("NIM harus 8 karakter");
            return;
        }

        String prefixProdi = nim.substring(0, 3);
        String namaProdi = ambilNamaProgramStudi(prefixProdi);

        if (namaProdi.equals("Kode tidak tersedia")) {
            System.out.println("Kode tidak tersedia");
            return;
        }

        // Safe parsing untuk mencegah NumberFormatException
        try {
            int angkatan = Integer.parseInt("20" + nim.substring(3, 5));
            int urutan = Integer.parseInt(nim.substring(5));
            tampilkanInformasiNim(nim, namaProdi, angkatan, urutan);
        } catch (NumberFormatException e) {
            System.out.println("Format NIM tidak valid");
        }
    }

    private static String ambilNamaProgramStudi(String prefix) {
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

    private static void tampilkanInformasiNim(String nim, String namaProdi, int angkatan, int urutan) {
        System.out.printf("Informasi NIM %s: %n", nim);
        System.out.printf(">> Program Studi: %s%n", namaProdi);
        System.out.printf(">> Angkatan: %d%n", angkatan);
        System.out.printf(">> Urutan: %d%n", urutan);
    }
}