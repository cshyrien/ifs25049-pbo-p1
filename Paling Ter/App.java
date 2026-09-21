import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();

        // Membaca input hingga menemukan "---" atau EOF
        while (sc.hasNext()) {
            if (sc.hasNextInt()) {
                list.add(sc.nextInt());
            } else {
                String token = sc.next();
                if (token.equals("---")) {
                    break;
                }
            }
        }

        // Jika input kosong, tidak menghasilkan output apa pun
        if (list.isEmpty()) {
            sc.close();
            return;
        }

        // Cari nilai minimal dan maksimal
        int min = Collections.min(list);
        int max = Collections.max(list);

        // Hitung frekuensi setiap angka menggunakan HashMap
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : list) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int maxFreq = -1;
        int minFreq = Integer.MAX_VALUE;
        int mostFreqNum = list.get(0);
        int leastFreqNum = list.get(0);

        // Cari angka dengan frekuensi terbanyak dan tersedikit
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();

            // Paling banyak / terbanyak
            if (count > maxFreq) {
                maxFreq = count;
                mostFreqNum = num;
            } else if (count == maxFreq) {
                mostFreqNum = Math.max(mostFreqNum, num);
            }

            // Paling sedikit / tersedikit
            if (count < minFreq) {
                minFreq = count;
                leastFreqNum = num;
            } else if (count == minFreq) {
                leastFreqNum = Math.min(leastFreqNum, num);
            }
        }

        int countMax = freqMap.get(max);
        int countMin = freqMap.get(min);

        long sumTertinggi = (long) max * countMax;
        long sumTerendah = (long) min * countMin;

        // Output Resmi sesuai format
        System.out.println("Tertinggi: " + max);
        System.out.println("Terendah: " + min);
        System.out.println("Terbanyak: " + mostFreqNum + " (" + maxFreq + "x)");
        System.out.println("Tersedikit: " + leastFreqNum + " (" + minFreq + "x)");
        System.out.println("Jumlah Tertinggi: " + max + " * " + countMax + " = " + sumTertinggi);
        System.out.println("Jumlah Terendah: " + min + " * " + countMin + " = " + sumTerendah);

        sc.close();
    }
}