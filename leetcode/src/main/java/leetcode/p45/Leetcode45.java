package leetcode.p45;
class Solution {
    public int jump(int[] nums) {
        int step_nums = 0;
        if(nums.length == 1){
            return 0;
        }

        for(int i=0;i<nums.length;){
            step_nums++;
            int step = nums[i];
            if(i+step>=nums.length-1){
                break;
            }else{
                int next_i = i;
                int max_step = -1;
                for(int j = 1;j<=step;j++){
                    if(nums[i+j]+j>=max_step){
                        max_step = nums[i+j]+j;
                        next_i = i+j;

                    }
                }

                i = next_i;
                //System.out.println(String.format("Nexti%d",i));
            }
        }

        return step_nums;
    }
}
public class Leetcode45 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] a =  {2,3,1,1,4};
        int s = solution.jump(a);
        System.out.println(s);
    }
}
