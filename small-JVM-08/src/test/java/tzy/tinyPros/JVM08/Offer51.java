package tzy.tinyPros.JVM08;

public class Offer51 {

    public static void main(String[] args) {
        System.out.println(new Offer51().reversePairs(new int[]{1, 2, 4, 3, 7, 6, 4, 1, 8, 3}));
    }


    public int reversePairs(int[] nums) {
        int len = nums.length; // 得到长度
        if (len < 2)
            return 0; // 小于2则没有逆序对
        return mergeSort(nums, 0, len - 1, new int[len]);
    }

    // 实现归并排序
    public int mergeSort(int[] nums, int left, int right, int[] temp) {
        // 如果此时只有一个数据，则肯定没有逆序对
        if (left == right)
            return 0;
        // 这个写法可以避免溢出
        int mid = left + (right - left) / 2;
        int leftSum = mergeSort(nums, left, mid, temp);
        int rightSum = mergeSort(nums, mid + 1, right, temp);
        // 左分支的最大值<=右分支的最小值，则必定没有逆序对
        if (nums[mid] <= nums[mid + 1]) {
            return leftSum + rightSum;
        }
        int crossSum = sortAccount(nums, left, mid, right, temp);
        return leftSum + rightSum + crossSum;
    }

    // 排序+统计逆序对
    public int sortAccount(int[] nums, int left, int mid, int right, int[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i]; // 暂存
        }
        int ls = left; // 左分支的初始所指向的下标
        int rs = mid + 1; // 右分支的起点初始所指向的下标
        int count = 0; // 记录总的逆序对
        // 这里的i对应的是排序下标，无关乎左右分支遍历处在什么位置，即当前正在排的是排第几的数
        int s = 0;
        for (int i = left; i <= right; i++) {
            // 左侧分支到头
            if (ls == mid + 1) {
                nums[i] = temp[rs];
                rs++;
            }
            // 右分支到头
            else if (rs == right + 1) {
                nums[i] = temp[ls];
                // 这种情况每添加一个左分支的数，与右分支就多s组逆序对
                count += s;
                ls++;
            }
            // 如果左侧分支当前的数不大于右侧分支
            else if (temp[ls] <= temp[rs]) {
                nums[i] = temp[ls];
                ls++; // 左分支右移
                count += s;
            }
            // 如果左侧分支的数据大于右侧分支的数据
            else {
                nums[i] = temp[rs];
                s++;
                rs++;
            }
        }
        return count;
    }
}