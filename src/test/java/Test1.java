import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

public class Test1 {

    @Test
    public void test1() {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        // 打乱arr
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            int index = r.nextInt(arr.length);
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
        System.out.println(Arrays.toString(arr));
        // 创建一个二维数组
        int[][] data = new int[4][4];
        // 给二维数组添加数据
        // 1.遍历一维数组,把每一个元素添加到二维数组
        for (int i = 0; i < arr.length; i++) {
            // 0/4=0 ... 3/4=0  ... 14/4=3 15/4=3
            // 0-3    0%4=0 1%4=1 3%4=3      14%4=2 15%4=3
            data[i / 4][i % 4] = arr[i];
        }
        //for (int i = 0; i < 4; i++) {
        //    for (int j = 0; j < 4; j++) {
        //        System.out.print(data[i][j] +" ");
        //    }
        //}
    }
    @Test
    public void test2() {
        Random r = new Random();

        for (int j = 0; j < 100; j++) {
            int randomNum = (r.nextInt(13) + 1);
            System.out.print(randomNum + " ");
        }
    }
}
