package leetcode.p146;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;
import java.util.Queue;

class LRUCache {

    class DLinkedNode{
        DLinkedNode next;
        DLinkedNode pre;
        int key;
        int value;
        DLinkedNode(int k,int v){
            this.key = k;
            this.value = v;
        }
    }

    int capacity;
    DLinkedNode head;
    DLinkedNode tail;


    HashMap<Integer,DLinkedNode> map = new HashMap<>();

    LRUCache(int capacity) {
        this.capacity=capacity;
        DLinkedNode vHLinkedNode = new DLinkedNode(-1,-1);
        DLinkedNode vTLinkedNode = new DLinkedNode(-1,-1);
        head=vHLinkedNode;
        tail=vTLinkedNode;
        head.pre = tail;
        tail.next=head;
        //初始化 tail|head
    }
    int get(int key) {

        if(map.containsKey(key)){
            //tail  pre n next head

            //边界问题
            //
            map.get(key).pre.next = map.get(key).next;
            map.get(key).next.pre = map.get(key).pre;
            map.get(key).pre = head;
            head = map.get(key);
            map.get(key).next = null;

            return map.get(key).value;
        }else{
            return -1;
        }

    }
    void put(int key, int value) {
        if(map.size()==this.capacity){

            key = tail.next.key;
            tail.next.next.pre = tail;
            tail.next=tail.next.next;
            map.remove(key);

            DLinkedNode tempNode= new DLinkedNode(key,value);
            tempNode.pre=head.pre;
            tempNode.next=head;
            head.pre.next = tempNode;
            head.pre = tempNode;
            map.put(key,tempNode);
        }else if(!map.containsKey(key)){
            //tail n head
            DLinkedNode tempNode= new DLinkedNode(key,value);
            tempNode.pre=head.pre;
            tempNode.next=head;
            head.pre.next = tempNode;
            head.pre = tempNode;
            map.put(key,tempNode);
        }else if(map.containsKey(key)){

            map.get(key).pre.next = map.get(key).next;
            map.get(key).next.pre = map.get(key).pre;


            DLinkedNode tempNode= new DLinkedNode(key,value);
            tempNode.pre=head.pre;
            tempNode.next=head;
            head.pre.next = tempNode;
            head.pre = tempNode;
            map.put(key,tempNode);

        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */