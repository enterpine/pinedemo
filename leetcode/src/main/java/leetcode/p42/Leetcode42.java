package leetcode.p42;
class Solution {
    public int minarea(int[] height,int l_i,int i){

//        int block = 0;
//        for(int j = i-1;j>=l_i;j--){
//            if(height[j]>height[j+1])
//        }
        int[] sub_height = new int[i-l_i+1];
        for(int j = 0 ;j<i-l_i+1;j++){
            sub_height[j] = height[i-j];
        }
        return trap(sub_height);
    }
    public int trap(int[] height) {
        int result = 0;

        if(height.length<=2){
            return 0;
        }

        int l_i = -1;
        int l_height = 0;

        int temp_trap_status = 0;//0 正在找左边界

        int max = 0;
        int min = 0;

        int block = 0;
        for(int i = 0;i<height.length;i++){

            if(temp_trap_status == 0 ) {
                //正在找容器左边界
                if (l_i == -1 && height[i] > 0) {
                    //第一次找到容器左边界
                    l_i = i;
                    l_height = height[i];
                }
                else if (i > 0 && height[i] >= height[i - 1]) {
                    //紧挨着的右侧是高于上一个，更新左边
                    l_i = i;
                    l_height = height[i];
                }
                else if(i > 0 && height[i] < height[i - 1]){
                    //如果这一个比上一个矮，确认好左边界
                    temp_trap_status = 1;
                    max = l_height-height[i];
                    min = 0;
                }

            }else{
                if(height[i]<l_height){
                    max = max+l_height-height[i];
                    min = minarea(height,l_i,i);
                    System.out.println(String.format("max = %d",max));
                }else if(height[i]>= l_height){

                    temp_trap_status = 0;//重新寻找边界
                    result=result+max;
                    l_i = i;
                    l_height = height[i];
                }
            }

        }
        result = result + min;

        return result ;
    }
}
public class Leetcode42 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int [] height = {4,2,0,3,2,5};
        int s = solution.trap(height);
        System.out.println(s);
    }
}


