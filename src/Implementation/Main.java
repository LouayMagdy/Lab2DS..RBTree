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
        tree.delete(15);
        tree.delete(2);
        tree.delete(32);
        tree.delete(4);

        tree.preOrderTraverse(tree.root);
//        Node node = tree.search(32);
//        System.out.println(node.getValue());
//        System.out.println(tree.contain(24));


    }
}


//                 21b
//        3 r                  32b
//    2 b      7b
//         4 r     15 r

