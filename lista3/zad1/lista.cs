using System;

class ListNode<T> {
    public T val;
    public ListNode<T> next;
    public ListNode<T> prev;

    public ListNode(T _val) {
        val = _val;
    }
};

public class Lista<T> {
    ListNode<T> front;
    ListNode<T> back;

    public void push_front(T elem) {
        ListNode<T> tmp = new ListNode<T>(elem);

        if(front == null) back = tmp;
        else {front.prev = tmp; tmp.next = front;}
        front = tmp;
    }
    public void push_back(T elem) {
        ListNode<T> tmp = new ListNode<T>(elem);

        if(back == null) front = tmp;
        else {back.next = tmp; tmp.prev = back;}
        back = tmp;
    }
    public T pop_front() {
        if(is_empty()) throw new Exception("pop_front: empty list"); //return default(T);

        T r = front.val;
        if(front == back) front = back = null;
        else {front = front.next; front.prev = null;}
        return r;
    }
    public T pop_back() {
        if(is_empty()) throw new Exception("pop_back: empty list"); //return default(T);

        T r = back.val;
        if(back == front) back = front = null;
        else {back = back.prev; back.next = null;}
        return r;
    }
    public bool is_empty() {
        return front == null;
    }
};