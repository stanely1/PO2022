using System;

class DictionaryNode<K,V> {
    public K key;
    public V val;
    public DictionaryNode<K,V> next;

    public DictionaryNode(K _key, V _val) {
        key = _key;
        val = _val;
    }
};

public class MyDictionary<K,V> {
    DictionaryNode<K,V> body;

    public void add(K key, V val) {
        DictionaryNode<K,V> tmp = body;
        for(; tmp != null; tmp = tmp.next)
            if(tmp.key.Equals(key)) {
                tmp.val = val;
                return;
            }
        tmp = new DictionaryNode<K,V>(key,val);
        tmp.next = body;
        body = tmp;
    }
    public void delete(K key) {
        if(body == null) return;

        if(body.key.Equals(key)) body = body.next;
        else 
            for(DictionaryNode<K,V> tmp = body; tmp.next != null; tmp = tmp.next)
                if(tmp.next.key.Equals(key)) {
                    tmp.next = tmp.next.next;
                    break;
                }
    }
    public V find(K key) {
        for(DictionaryNode<K,V> tmp = body; tmp != null; tmp = tmp.next)
            if(tmp.key.Equals(key)) return tmp.val;
        throw new Exception("find: key not found");//return default(V);
    }
};