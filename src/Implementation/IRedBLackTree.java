package Implementation;

public interface IRedBLackTree<T extends Comparable>{
    void getRoot();
    boolean isEmpty();
    void clear();
    boolean contain(T value);
    Node<T> search(T value);
    void insert(T value);
    void delete(T value);
}
