import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        prosesStatistikList(sc);
        sc.close();
    }

    private static void prosesStatistikList(Scanner sc) {
        List<Integer> list = bacaInputList(sc);

        if (list.isEmpty()) {
            return;
        }

        int min = Collections.min(list);
        int max = Collections.max(list);

        Map<Integer, Integer> freqMap = hitungFrekuensi(list);
        
        int[] terbanyakTersedikit = cariTerbanyakTersedikit(list, freqMap);
        int mostFreqNum = terbanyakTersedikit[0];
        int maxFreq = terbanyakTersedikit[1];
        int leastFreqNum = terbanyakTersedikit[2];
        int minFreq = terbanyakTersedikit[3];

        int countMax = freqMap.get(max);
        int countMin = freqMap.get(min);

        long sumTertinggi = (long) max * countMax;
        long sumTerendah = (long) min * countMin;

        tampilkanHasilStatistik(max, min, mostFreqNum, maxFreq, leastFreqNum, minFreq, countMax, countMin, sumTertinggi, sumTerendah);
    }

    private static List<Integer> bacaInputList(Scanner sc) {
        List<Integer> list = new ArrayList<>();
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
        return list;
    }

    private static Map<Integer, Integer> hitungFrekuensi(List<Integer> list) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : list) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        return freqMap;
    }

    private static int[] cariTerbanyakTersedikit(List<Integer> list, Map<Integer, Integer> freqMap) {
        int maxFreq = -1;
        int minFreq = Integer.MAX_VALUE;
        int mostFreqNum = list.get(0);
        int leastFreqNum = list.get(0);

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();

            if (count > maxFreq) {
                maxFreq = count;
                mostFreqNum = num;
            } else if (count == maxFreq) {
                mostFreqNum = Math.max(mostFreqNum, num);
            }

            if (count < minFreq) {
                minFreq = count;
                leastFreqNum = num;
            } else if (count == minFreq) {
                leastFreqNum = Math.min(leastFreqNum, num);
            }
        }

        return new int[]{mostFreqNum, maxFreq, leastFreqNum, minFreq};
    }

    private static void tampilkanHasilStatistik(int max, int min, int mostFreq, int maxFreq, int leastFreq, int minFreq, int countMax, int countMin, long sumMax, long sumMin) {
        System.out.println("Tertinggi: " + max);
        System.out.println("Terendah: " + min);
        System.out.println("Terbanyak: " + mostFreq + " (" + maxFreq + "x)");
        System.out.println("Tersedikit: " + leastFreq + " (" + minFreq + "x)");
        System.out.println("Jumlah Tertinggi: " + max + " * " + countMax + " = " + sumMax);
        System.out.println("Jumlah Terendah: " + min + " * " + countMin + " = " + sumMin);
    }
}