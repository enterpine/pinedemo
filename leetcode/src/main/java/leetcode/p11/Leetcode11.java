package leetcode.p11;

class Solution {
    public int maxArea(int[] height) {
        int len = height.length;
        if(len <= 1){
            return 0;
        }

        int cur_left = 0;
        int cur_right = len - 1;
        int max_s = 0;
        int cur_s = 0;


        while(cur_left<cur_right){

            cur_s = Math.min(height[cur_left],height[cur_right]) * (cur_right - cur_left);
            max_s = Math.max(cur_s,max_s);

            int next_left = cur_left + 1;
            int next_right = cur_right - 1;
            if(height[next_left] >=height[cur_left]){
                cur_left = next_left;
            }else{

                if(height[next_right]>=height[cur_right]){
                    cur_right = next_right;
                }
                else{
                    if(height[cur_left]>=height[cur_right]){
                        cur_right = next_right;
                    }else{
                        cur_left = next_left;
                    }

                }
            }


        }

        return max_s;

    }
}
public class Leetcode11 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //int maxArea = solution.maxArea(new int[]{1,8,6,2,5,4,8,3,7});
        //int maxArea = solution.maxArea(new int[]{});
       // int maxArea = solution.maxArea(new int[]{2,3,4,5,18,17,6});
        int maxArea = solution.maxArea(new int[]{166,63,82,92,144,186,192,68,101,179,50,170,141,31,149,131,180,94,168,142,91,137,159,184,89,135,101,21,70,191,136,37,7,18,81,103,4,26,171,106,5,173,28,99,4,129,30,185,23,198,127,67,88,39,51,129,126,104,150,196,48,38,185,7,56,19,110,12,45,33,70,50,7,98,149,163,179,131,100,3,82,180,70,170,171,73,51,97,130,153,45,130,191,31,137,199,2,199,11,47});
        System.out.println(maxArea);
    }
}
