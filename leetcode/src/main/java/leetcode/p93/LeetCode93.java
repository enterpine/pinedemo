package leetcode.p93;

import scala.Int;

import java.util.ArrayList;
import java.util.List;

public class LeetCode93 {


    public static void func(String curS,String remainString,int nums,ArrayList<String> result){
        //System.out.println(curS);
        if(remainString.equals("")){
            return;
        }

        if(nums == 4 && Long.parseLong(remainString)<=255L){
            if(!(remainString.charAt(0)=='0' && remainString.length()>1)){
                result.add(curS+remainString);
                return;
            }else{
                return;
            }

        }else if(nums ==4 && Long.parseLong(remainString)<=255L){
            return ;
        }


        int len = remainString.length();
        String cs = "";
        for(int i = 0 ; i<len ;i++){

            cs = cs + remainString.charAt(i);

            if(cs.charAt(0)=='0' && cs.length()>1){
                return;
            }
            else {
                if (Long.parseLong(cs) <= 255L) {
                    func(curS + cs + '.', remainString.substring(i + 1), nums + 1, result);
                }
            }



        }
    }
    //2.5.5.2
    public static  List<String> restoreIpAddresses(String s) {
        ArrayList<String> l = new ArrayList<>();

        if(s.length()>12){
            return l;
        }
        func("",s,1,l);

        return l;
    }

    public static void main(String[] args) {
//        输入：s = "25525511135"
//        输出：["255.255.11.135","255.255.111.35"]
//        System.out.println("1234".substring(4));
        String s = "9999999999999999999";
        for(String a:restoreIpAddresses(s)){
            System.out.println(a);
        }
    }
}
