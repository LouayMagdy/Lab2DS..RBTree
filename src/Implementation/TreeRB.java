package Implementation;

public class TreeRB<T extends Comparable> implements IRedBLackTree<T> {
    public Node<T> root;

    public TreeRB() {this.root = null;}

    @Override
    public void getRoot() {
        try{
            System.out.println("The Root is: " + this.root.getValue());
        }
        catch(Exception e){
            System.out.println("The Tree is Empty !!");
        }
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public boolean contain(T value) { //////not implemented
        return false;
    }

    @Override
    public Node<T> search(T value) { ////////not implemented
        return null;
    }

    @Override
    public void insert(T value) { ///////not implemented

    }

    @Override
    public void delete(T value) {
        if(!this.isEmpty()){
            root = deleteNode(value, root);
            while (root != null && root.hasParent()) root = root.getParent();
        }
        else System.out.println("false");
    }

    private Node<T> deleteNode(T value, Node<T> node){
        int result = node.getValue().compareTo(value);
        boolean deletedSuccessfully = false;
        Node<T> deleted = node, replacing = null, parent = node.getParent();
        if(result > 0 && node.hasLeftChild()) {return deleteNode(value, node.getLeftChild());}
        if(result < 0 && node.hasRightChild()) {return deleteNode(value, node.getRightChild());}
        else if(result == 0){
            deletedSuccessfully = true;
            if(node.hasLeftChild() && node.hasRightChild()){
                deleted = node.getRightChild();
                while(deleted.hasLeftChild()) deleted = deleted.getLeftChild();
                replacing = deleted.getRightChild();
                parent = deleted.getParent();
                node.setValue(deleted.getValue());
                if(deleted.isLeft()) parent.setLeftChild(replacing);
                else parent.setRightChild(replacing);
                if(replacing != null){replacing.setParent(parent);}
            }
            else if(node.hasLeftChild() ^ node.hasRightChild()){
                replacing = node.getLeftChild();
                if(replacing == null) replacing = node.getRightChild();
                replacing.setParent(parent);
                if(deleted.isLeft()) parent.setLeftChild(replacing);
                else parent.setRightChild(replacing);
            }
            if(replacing != null) replacing = replacing.rebalanceDeletion(deleted);
            else{
                replacing = deleted.getParent();
                boolean left = false;
                if(replacing != null){
                    left = node.isLeft();
                    if(left) replacing.setLeftChild(null);
                    else replacing.setRightChild(null);
                }
                else {
                    System.out.println(deletedSuccessfully);
                    return null;
                }
            }
        }
        System.out.println(deletedSuccessfully);
        return replacing;
    }

    public void preOrderTraverse(Node<T> temp){
        if(temp != null){
            System.out.println("value : " + temp.getValue());
            if(temp.hasParent()) System.out.println("parent : " + temp.getParent().getValue());
            if(temp.hasLeftChild()) System.out.println("left : " + temp.getLeftChild().getValue());
            if(temp.hasRightChild()) System.out.println("right : " + temp.getRightChild().getValue());
            if(temp.isRed()) System.out.println("color: " + "red");
            else System.out.println("color: " + "black");
            System.out.println();
        }
        if(temp.hasLeftChild()) preOrderTraverse(temp.getLeftChild());
        if(temp.hasRightChild()) preOrderTraverse(temp.getRightChild());
    }
}
