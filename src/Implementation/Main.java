package Implementation;

public class Main {

    public static void main(String[] args) {
	// write your code here
        TreeRB tree = new TreeRB<Integer>();
        tree.insert(3);
        tree.insert(21);
        tree.insert(32);

        tree.insert(15);
        tree.insert(2);
        tree.insert(7);
        tree.insert(4);
        Node node = tree.search(32);
        System.out.println(node.getValue());
        System.out.println(tree.contain(24));
     //   tree.preOrderTraverse(tree.root);


    }
}
