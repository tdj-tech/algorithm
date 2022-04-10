package com.tuodfh.algorithm.bitmap;

/**
 * @author tdj
 * 2022/4/8 0008
 * 位图实现加、减、乘和除算法
 */
public class AddSubMulAndDivImp {

    public static void main(String[] args) {
        System.out.println(add(78, -8));
        System.out.println(sub(5, 8));
        System.out.println(mul(-3, 6));
        System.out.println(div(18, -4));
    }

    /**
     * 加法
     * a + b = a^b + (a & b) << 1 即无符号位相加之和 + 进位
     * 所以 a + b = a' + b' ,如此继续循环直到进位为0，此时的值就是无符号位相加之和
     * @param a b
     * @return sum
     */
    public static int add(int a, int b) {
        int ans = a;
        while (b != 0) {
            ans = a ^ b;
            b = (a & b) << 1;
            a = ans;
        }
        return ans;
    }

    /**
     * 减法
     * a - b = a + (-b)，而符号等于取反+1
     * @param a b
     * @return sub
     */
    public static int sub(int a, int b) {
        return add(a, (add(~b, 1)));
    }

    /**
     * 乘法
     * a(0100) * b(0110) = a*2 + a*4 =  a<<1 + a<<2
     * @param a b
     * @return mul
     */
    public static int mul(int a, int b) {
        int ans = 0;
        int posA = isPositive(a) ? a : opposite(a);
        int posB = isPositive(b) ? b : opposite(b);
        while (posB != 0) {
            if ((posB & 1) == 1) {
                ans = add(ans, posA);
            }
            posB >>= 1;
            posA <<= 1;
        }
        return isPositive(a) == isPositive(b) ? ans : opposite(ans);
    }

    /**
     * 除法
     * a(1110)/b(0100) = c(0011) 等价于 a = b*c 等价于 a = b*c' + b*c" 等价于 a = b*0010 + b*0001
     * 故需要找出c'和c"的1所在位置，即可拼凑出c
     * 通过a向右移，移了i位时，a>=b，即c的i位肯定有1，此时令a=a-b<<i,再继续右移，直到移完
     * a右移与b左移是一样的效果，但是有个问题，就是符号位，b左移的时候有可能移到到符号位上了，
     * 这会造成原本正数变成了负数，而a右移则没有这个问题
     * 为什么要移到30位？因为31位是符号位，而正数永远是0
     * 未完待续...........还有边界条件未判断!!!
     * @param  a , 被除数 b
     * @return div 值
     */
    public static int divite(int a, int b) {
        int ans = 0;
        int posA = isPositive(a) ? a : opposite(a);
        int posB = isPositive(b) ? b : opposite(b);
        for (int i = 30; i >= 0; i = sub(i, 1)) {
            if ((posA >> i) >= posB) {
                posA = sub(posA, posB << i);
                ans |= (1 << i);
            }
        }
        return isPositive(a) ^ isPositive(b) ? opposite(ans) : ans;
    }

    /**
     * 除法
     * 有些边界条件 1：a和b都是最小值，返回1
     *             2：a是最小值，b不是，因为a是最小值，它的绝对值数超过了int的最大值，故需采取其他处理
     *             先令a + 1，然后除b得到c，再d=a-(b*c)，最终结果=c+ d/b
     *             3：a不是最小值，b是，返回0
     *             4：a和b都不是最小值，返回div(a, b)
     * @param
     * @return
     */
    public static int div(int a, int b) {
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        } else if (b == Integer.MIN_VALUE) {
            return 0;
        } else if (a == Integer.MIN_VALUE) {
            if (b == opposite(1)) {
                return Integer.MAX_VALUE;
            }
            int c = divite(add(a, 1), b);
            int d = sub(a, mul(b, c));
            return add(c, divite(d, b));
        } else {
            return divite(a, b);
        }
    }

    public static boolean isPositive(int a) {
        return a >= 0;
    }

    public static int opposite(int a) {
        return add(~a, 1);
    }

}
