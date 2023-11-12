package io.frlib.algorithm.classic;


/**
 * 大数求和
 */
public class BigDataSum {

    /**
     * 大数求和
     * 12312443534556745747457645867634346456 + 243536547567657456436437657567567565675869789890890
     * @param a
     * @param b
     * @return
     */
    public String sum(String a, String b) {
        if (illegalString(a) && illegalString(b)) {
            return "";
        }
        if (illegalString(a)) return b;
        if (illegalString(b)) return a;
        char[] charA, charB, result;
        // 保证两串相同(补0)
        if (a.length() < b.length()) {
            charB = new StringBuffer(b).reverse().toString().toCharArray();
            StringBuffer newA = new StringBuffer(a).reverse();
            int index = a.length();
            while (index < b.length()) {
                newA.append('0');
                index++;
            }
            charA = newA.toString().toCharArray();
        } else {
            charA = new StringBuffer(a).reverse().toString().toCharArray();
            StringBuffer newB = new StringBuffer(b).reverse();
            int index = b.length();
            while(index < a.length()) {
                newB.append('0');
                index++;
            }
            charB = newB.toString().toCharArray();
        }
        int length = Math.max(a.length(), b.length());
        result = new char[length + 1];
        int carry = 0; // 进位标志
        int i = 0;
        // 公共部分就和
        while (i < length) {
            int currentResult = convertCharNumberToInt(charA[i]) + convertCharNumberToInt(charB[i]) + carry;
            if (currentResult >= 10) {
                carry = 1;
                currentResult = currentResult - 10;
            } else {
                carry = 0;
            }
            result[i] = convertNumberToChar(currentResult);
            i++;
        }
        // 最后一位相加可能进位
        String s = "";
        if (carry == 1) {
            result[result.length - 1] = '1';
            s = new String(result);
        } else {
            s = new String(result, 0 ,result.length - 1);
        }
        return new StringBuffer(new String(s)).reverse().toString();
    }

    private boolean illegalString(String s) {
        return s == null || s.length() == 0;
    }

    private int convertCharNumberToInt(char c) {
        int result = c - 48;
        return result;
    }

    private char convertNumberToChar(int i) {
        if (i >= 10) {
            i = i - 10;
        }
        char result = (char)(i + 48);
        return result;
    }

    public static void main(String[] args) {
        String a = "2";
        String b = "78";
        BigDataSum instance = new BigDataSum();
        System.out.println(instance.sum(a, b));
    }

}
