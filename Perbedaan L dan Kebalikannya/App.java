import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        prosesAnalisisMatriks(sc);
        sc.close();
    }

    private static void prosesAnalisisMatriks(Scanner sc) {
        if (!sc.hasNextInt()) return;

        int n = sc.nextInt();
        long[][] matrix = new long[n][n];

        // Safe reading matrix element
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (sc.hasNextLong()) {
                    matrix[i][j] = sc.nextLong();
                } else {
                    return; // Mencegah InputMismatchException / NumberFormatException
                }
            }
        }

        if (n < 3) {
            prosesMatriksKecil(matrix, n);
        } else {
            prosesMatriksStandar(matrix, n);
        }
    }

    private static void prosesMatriksKecil(long[][] matrix, int n) {
        long totalSum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                totalSum += matrix[i][j];
            }
        }
        System.out.println("Nilai L: Tidak Ada");
        System.out.println("Nilai Kebalikan L: Tidak Ada");
        System.out.println("Nilai Tengah: " + totalSum);
        System.out.println("Perbedaan: Tidak Ada");
        System.out.println("Dominan: " + totalSum);
    }

    private static void prosesMatriksStandar(long[][] matrix, int n) {
        long sumL = hitungPolaL(matrix, n);
        long sumRevL = hitungPolaKebalikanL(matrix, n);
        long sumCenter = hitungNilaiTengah(matrix, n);
        long diff = Math.abs(sumL - sumRevL);

        long maxVal = (sumL == sumRevL) ? sumCenter : Math.max(sumL, Math.max(sumRevL, sumCenter));

        System.out.println("Nilai L: " + sumL);
        System.out.println("Nilai Kebalikan L: " + sumRevL);
        System.out.println("Nilai Tengah: " + sumCenter);
        System.out.println("Perbedaan: " + diff);
        System.out.println("Dominan: " + maxVal);
    }

    private static long hitungPolaL(long[][] matrix, int n) {
        long sum = 0;
        for (int i = 0; i < n; i++) sum += matrix[i][0];
        for (int j = 1; j < n - 1; j++) sum += matrix[n - 1][j];
        return sum;
    }

    private static long hitungPolaKebalikanL(long[][] matrix, int n) {
        long sum = 0;
        for (int i = 0; i < n; i++) sum += matrix[i][n - 1];
        for (int j = 1; j < n - 1; j++) sum += matrix[0][j];
        return sum;
    }

    private static long hitungNilaiTengah(long[][] matrix, int n) {
        if (n % 2 != 0) {
            return matrix[n / 2][n / 2];
        } else {
            int mid = n / 2;
            return matrix[mid - 1][mid - 1] + matrix[mid - 1][mid]
                 + matrix[mid][mid - 1]     + matrix[mid][mid];
        }
    }
}