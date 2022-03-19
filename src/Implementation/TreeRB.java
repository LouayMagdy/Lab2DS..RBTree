package Implementation;

public class TreeRB<T extends Comparable> implements IRedBLackTree<T> {
    public Node<T> root;
    boolean balance;
    boolean nodeIsLeft;
    boolean parentIsLeft;

    public TreeRB() {this.root = null; balance = true; nodeIsLeft = true; parentIsLeft = true;}

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
        Node<T> node = this.search(value, this.root);
        return node != null;
    }

    private Node<T> search(T value, Node<T> node) {
        if (node == null) {
            return null;
        }
        if(node.getValue() == value) return node;
        int result = node.getValue().compareTo(value);
        if(result > 0) {
            return search(value, node.getLeftChild());
        }else if(result < 0) {
            return search(value, node.getRightChild());
        }
        return null;
    }

    @Override
    public Node<T> search(T value) { ////////not implemented
        return this.search(value, this.root);
    }



    @Override
    public void insert(T value) { ///////not implemented
        this.root = this.insert(value, this.root);
        this.root.setColor(false);
    }

    private Node<T> getUncle(Node<T> node) {
        Node<T> parent = node.getParent();
        if(parent.getLeftChild() == node) {
            return parent.getRightChild();
        } else {
            return parent.getLeftChild();
        }
    }



    private Node<T> insert(T value, Node<T> node) {
        if (node == null) {
            return new Node<T>(value);
        }

        int result = node.getValue().compareTo(value);
        if(result > 0) {
            Node<T> left = insert(value, node.getLeftChild());
            left.setParent(node);
            node.setLeftChild(left);
            nodeIsLeft = true;
        }else if(result < 0) {
            Node<T> right = insert(value, node.getRightChild());
            right.setParent(node);
            node.setRightChild(right);
            nodeIsLeft = false;
        }
        if (!this.balance) {
            this.balance = true;
            return node.rebalance(parentIsLeft, nodeIsLeft);
        }
        if(node.isRed() && node != this.root) {
            if((nodeIsLeft && !node.getLeftChild().isRed()) || (!nodeIsLeft && !node.getRightChild().isRed()))
                return node;
            Node<T> uncle = getUncle(node);
            // "node" is the parent
            if(uncle != null && uncle.isRed()) {
                uncle.setColor(false);
                node.setColor(false);
                node.getParent().setColor(true);
            } else {
                this.balance = false;
                Node<T> parent = node.getParent();
                parentIsLeft = parent.getLeftChild() == node;
            }
        }
        return node;
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
        Node<T> deleted = node, replacing = null, parent = node.getParent(), temp = null;
        boolean left = false;
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
                if(deleted.isLeft()) {
                    left = true;
                    parent.setLeftChild(replacing);
                }
                else parent.setRightChild(replacing);
                if(replacing != null){replacing.setParent(parent);}

            }
            else if(node.hasLeftChild() ^ node.hasRightChild()){
                replacing = node.getLeftChild();
                if(replacing == null) replacing = node.getRightChild();
                replacing.setParent(parent);
                if(deleted.isLeft()) {
                    parent.setLeftChild(replacing);
                    left  = true;
                }
                else parent.setRightChild(replacing);
            }

            if(!(node.hasRightChild() || node.hasLeftChild()) || replacing == null){
                temp = new Node(0);
                temp.setColor(false);
                replacing = temp;
                replacing.setParent(parent);
//                boolean left = false;
                if(!(node.hasRightChild() || node.hasLeftChild())) {
                    if(parent != null) left = deleted.isLeft();
                }
                if(left) parent.setLeftChild(replacing);
                else parent.setRightChild(replacing);
            }
//            if (replacing != null)
            replacing = replacing.rebalanceDeletion(deleted);
            if(temp != null){
                if(parent == null) return null;
                left = temp.isLeft();
                if(left) parent.setLeftChild(null);
                else parent.setRightChild(null);
                replacing = parent;
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
