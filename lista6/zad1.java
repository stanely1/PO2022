import java.io.*;

class ListNode<T> implements Serializable {
    public T val;
    public ListNode<T> prev;
    public ListNode<T> next;

    public ListNode(T _val) {
        val = _val;
    }
}

class List<T> implements Serializable {
    private static final long serialVersionUID = 1234L;
    
    private ListNode<T> front;
    private ListNode<T> back;

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
    public T pop_front() throws Exception {
        if(is_empty()) throw new Exception("pop_front: empty list");

        T r = front.val;
        if(front == back) front = back = null;
        else {front = front.next; front.prev = null;}
        return r;
    }
    public T pop_back() throws Exception {
        if(is_empty()) throw new Exception("pop_back: empty list"); 

        T r = back.val;
        if(back == front) back = front = null;
        else {back = back.prev; back.next = null;}
        return r;
    }
    public boolean is_empty() {
        return front == null;
    }
}

public class zad1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, Exception {
        List<String> l = new List<String>();
        l.push_back("Stefan"); l.push_front("Marian"); l.push_back("Jacek");

        FileOutputStream fos = new FileOutputStream("lista");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(l);
        oos.flush();
        oos.close();

        FileInputStream fis = new FileInputStream("lista");
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        List<?> l2 = (List<?>) ois.readObject();
        ois.close();

        while(!l.is_empty()) System.out.println(l.pop_front());
        while(!l2.is_empty()) System.out.println(l2.pop_front());
    }
}