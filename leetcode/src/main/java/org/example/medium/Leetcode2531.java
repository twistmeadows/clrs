package org.example.medium;

/**
 * @author wangguang
 * @date 2023-04-21 8:58
 * description:
 * You are given two 0-indexed strings word1 and word2.
 * <p>
 * A move consists of choosing two indices i and j such that 0 <= i < word1.length and 0 <= j < word2.length and swapping word1[i] with word2[j].
 * <p>
 * Return true if it is possible to get the number of distinct characters in word1 and word2 to be equal with exactly one move. Return false otherwise.
 * <p>
 */
public class Leetcode2531 {
    public boolean isItPossible(String word1, String word2) {
        int[] count1 = new int[26];
        int[] count2 = new int[26];
        for (char c : word1.toCharArray()) {
            count1[c - 'a']++;
        }
        for (char c : word2.toCharArray()) {
            count2[c - 'a']++;
        }

        int bitCnt1 = 0;
        int bitCnt2 = 0;
        for (int i = 0; i < 26; i++) {
            if (count1[i] != 0) {
                bitCnt1 |= (1 << i);
            }
            if (count2[i] != 0) {
                bitCnt2 |= (1 << i);
            }
        }


        for (int i = 0; i < 26; i++) {
            if (count1[i] == 0) {
                continue;
            }
            for (int j = 0; j < 26; j++) {
                if (count2[j] == 0) {
                    continue;
                }

                //交换i,j
                int newBitCnt1 = bitCnt1;
                if (count1[i] == 1) {
                    newBitCnt1 ^= (1 << i);
                }
                newBitCnt1 |= (1 << j);

                int newBitCnt2 = bitCnt2;
                if (count2[j] == 1) {
                    newBitCnt2 ^= (1 << j);
                }
                newBitCnt2 |= (1 << i);
                if (Integer.bitCount(newBitCnt1) == Integer.bitCount(newBitCnt2)) {
                    return true;
                }
            }
        }
        return false;

    }

    public static void main(String[] args) {
        Leetcode2531 lt = new Leetcode2531();
        String word1 = "abcc";
        String word2 = "b";
        System.out.println(lt.isItPossible(word1, word2));

    }
}
