package leetcode.p31;
class Solution {


    public void nextPermutation(int[] nums) {
        if(nums.length<=1){

        }
        else{
            int iflag = nums.length-1;
            for(int i = nums.length-2;i>=0;i-- ){
               int minIndex = -1;
               for(int j = i+1;j<nums.length;j++){
                   if(nums[j]>nums[i]){
                       if(minIndex == -1){
                           minIndex = j;
                       }else if(nums[minIndex] > nums[j]){
                           minIndex = j;
                       }
                   }else{
                       continue;
                   }
               }
               if(minIndex!=-1){
                   int tmp = nums[minIndex];
                   nums[minIndex] = nums[i];
                   nums[i] = tmp;
                   iflag = i;
                   break;
               }
            }
            if(iflag == nums.length-1){
                for(int i = 0 ;i<nums.length;i++){
                    for(int j = i+1 ;j<nums.length;j++){
                        if(nums[j]<nums[i]){
                            int tmp = nums[i];
                            nums[i] = nums[j];
                            nums[j] = tmp ;
                        }
                    }
                }
            }else {
                for (int i = iflag + 1; i < nums.length; i++) {
                    for (int j = i + 1; j < nums.length; j++) {
                        if (nums[j] < nums[i]) {
                            int tmp = nums[i];
                            nums[i] = nums[j];
                            nums[j] = tmp;
                        }
                    }
                }
            }
        }
    }
}

public class Leetcode31 {
    public static void main(String[] args) {
        int[] a = {1};

        Solution solution = new Solution();
        solution.nextPermutation(a);

    }
}
