package leetcode.p406;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

class Solution {

    public int[][] reconstructQueue(int[][] people) {
        int len  = people.length;
        if(len<=1){
            return people;
        }

        for(int i = 0;i<len;i++){
            for(int j = i+1;j<len;j++){
                if(people[j][0]>people[i][0]){
                    int[] t ;
                    t =  people[i];
                    people[i] =  people[j];
                    people[j] = t;
                }else if(people[j][0]==people[i][0] && people[j][1]<people[i][1]){
                    int[] t ;
                    t =  people[i];
                    people[i] =  people[j];
                    people[j] = t;
                }
            }
        }
//
//        for(int i =0;i<people.length;i++){
//            System.out.print(String.format("%d %d | ",people[i][0],people[i][1]));
//        }


        LinkedList<int[]> result = new LinkedList<>();
        result.add(people[0]);

        for(int i = 1 ; i<len ; i++){

//            Iterator<int[]> its = result.iterator();
//            while(its.hasNext()){
//                int[] temp = its.next();
//                System.out.print(String.format("%d %d | ",temp[0],temp[1]));
//
//            }
//            System.out.println("");


            int h = people[i][0];
            int p = people[i][1];
            int counter = 0;



            ListIterator<int[]> it = result.listIterator();

            if(p==0){
                it.add(people[i]);
            }else {
                while (it.hasNext()) {
                    int h_t = it.next()[0];
                    if (h_t >= h) {
                        counter++;
                    }
                    if (counter == p) {
                        it.add(people[i]);
                        break;
                    }
                }
            }
        }

        int[][]  a = new int[len][2];
        for(int i = 0;i<result.size();i++){
            a[i][0] = result.get(i)[0];
            a[i][1] = result.get(i)[1];
        }
        return a ;
    }
}
public class Leetcode406 {
    public static void main(String[] args) {

        int[][] a =  {{9,0},{7,0},{1,9},{3,0},{2,7},{5,3},{6,0},{3,4},{6,2},{5,2}};

        //7 0,7 1,6 1,5 0,5 2,4 4

        Solution solution = new Solution();
        int [][] b  = solution.reconstructQueue(a);

        for(int i =0;i<b.length;i++){
            System.out.print(String.format("%d %d | ",b[i][0],b[i][1]));
        }

    }
}
