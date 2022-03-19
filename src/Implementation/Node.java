package Implementation;

public class Node<T extends Comparable>{
    private T value;
    private boolean color; /// True means "Red" and False means "Black"
    private Node<T> parent;
    private Node<T> rightChild;
    private Node<T> leftChild;

    public Node(T value) {
        this.value = value;
        this.color = true;
        this.rightChild = null;
        this.leftChild = null;
        this.parent = null; /// it will be fixed using some methods
    }

    public boolean isRed() {return color;}
    public void setColor(boolean color) {this.color = color;}

    public void setValue(T value) {this.value = value;}
    public T getValue() {return value;}

    public boolean hasParent(){return this.parent != null;}
    public Node<T> getParent() {return parent;}
    public void setParent(Node<T> parent) {this.parent = parent;}

    public boolean hasRightChild(){return this.rightChild != null;}
    public Node<T> getRightChild() {return rightChild;}
    public void setRightChild(Node<T> rightChild) {this.rightChild = rightChild;}

    public boolean hasLeftChild(){return this.leftChild != null;}
    public Node<T> getLeftChild() {return leftChild;}
    public void setLeftChild(Node<T> leftChild) {this.leftChild = leftChild;}

    public boolean isRight(){return this == this.parent.getRightChild();}
    public boolean isLeft(){return this == this.parent.getLeftChild();}

    public void swapColor(Node<T> anotherNode){
        boolean myPrevColor = this.isRed();
        this.setColor(anotherNode.isRed());
        anotherNode.setColor(myPrevColor);
    }

    public Node<T> rotateLeft(){ //////its right child gets in its place
        Node<T> myParent = this.getParent();
        Node<T> rightChild = this.getRightChild();
        boolean right = false;
        if(myParent != null) right = this.isRight();
        this.setRightChild(rightChild.getLeftChild());
        if(this.hasRightChild()) this.getRightChild().setParent(this);
        rightChild.setLeftChild(this);
        this.setParent(rightChild);
        rightChild.setParent(myParent);
        if(myParent != null){
            if(right) myParent.setRightChild(rightChild);
            else myParent.setLeftChild(rightChild);
        }
        return rightChild;
    }

    public Node<T> rotateRight(){ //////its left child gets in its place
        Node<T> myParent = this.getParent();
        Node<T> leftChild = this.getLeftChild();
        boolean left = false;
        if(myParent != null) left = this.isLeft();
        this.setLeftChild(leftChild.getRightChild());
        if(this.hasLeftChild()) this.getLeftChild().setParent(this);
        leftChild.setRightChild(this);
        this.setParent(leftChild);
        leftChild.setParent(myParent);
        if(myParent != null){
            if(left) myParent.setLeftChild(leftChild);
            else myParent.setRightChild(leftChild);
        }
        return leftChild;
    }

    public Node<T> rebalance(boolean parentIsLeft, boolean nodeIsLeft) {
        // node
        if(parentIsLeft && nodeIsLeft) {
            this.setColor(true);
            this.getLeftChild().setColor(false);
            return this.rotateRight();
        } else if (!parentIsLeft && !nodeIsLeft) {
            this.setColor(true);
            this.getRightChild().setColor(false);
            return this.rotateLeft();
        } else if (parentIsLeft) {
            this.getLeftChild().rotateLeft();
            return this.rotateRight();
        } else {
            this.getRightChild().rotateRight();
            return this.rotateLeft();
        }
    }

    public Node<T> rebalanceDeletion(Node<T> deleted){ // this caller node is u : replacer
        if(this.getParent() == null || (!this.isRed() ^ !deleted.isRed())) {
            this.setColor(false);
            return this;
        }
        else if(!this.isRed() && !deleted.isRed()) {
            Node<T> sibling;
            if (this.isLeft()) sibling = this.getParent().getRightChild();
            else sibling = this.getParent().getLeftChild();

            if (sibling == null || (sibling != null && !sibling.isRed() &&
                    ((sibling.hasLeftChild() && !sibling.getLeftChild().isRed()) || !sibling.hasLeftChild()) &&
                    ((sibling.hasRightChild() && !sibling.getRightChild().isRed()) || !sibling.hasRightChild()))) {
                this.getParent().setColor(false);
                this.setParent(this.getParent().rebalanceDeletion(deleted));
                return this.getParent();
            }

            else if (sibling != null && sibling.isRed()) {
                sibling.swapColor(sibling.getParent());
                if (sibling.isLeft()) {
                    sibling = sibling.getParent().rotateRight();
                    sibling.getRightChild().setColor(false);
                } else {
                    sibling = sibling.getParent().rotateLeft();
                    sibling.getLeftChild().setColor(false);
                }
                return sibling;
            }
//            else if(sibling != null && !sibling.isRed())
            else {
                if (sibling.parent != null && sibling.isLeft() && sibling.hasRightChild() && sibling.getRightChild().isRed()
                   && ((sibling.hasLeftChild() && !sibling.getLeftChild().isRed()) || !sibling.hasLeftChild())) {
                    sibling.swapColor(sibling.getRightChild());
                    sibling = sibling.rotateLeft();
                }
                if (sibling.parent != null &&sibling.isLeft() && sibling.hasLeftChild() && sibling.getLeftChild().isRed()) {
                    sibling.swapColor(sibling.getParent());
                    sibling = sibling.getParent().rotateRight();
                    sibling.getLeftChild().setColor(false);
                }
                if (sibling.parent != null && sibling.isRight() && sibling.hasLeftChild() && sibling.getLeftChild().isRed()
                        && ((sibling.hasRightChild() && !sibling.getRightChild().isRed()) || !sibling.hasRightChild())) {
                    sibling.swapColor(sibling.getLeftChild());
                    sibling = sibling.rotateRight();
                }
                if (sibling.parent != null && sibling.isRight() && sibling.hasRightChild() && sibling.getRightChild().isRed()) {
                    sibling.swapColor(sibling.getParent());
                    sibling = sibling.getParent().rotateLeft();
                    sibling.getRightChild().setColor(false);
                }
                return sibling;
            }
        }
        return this;
    }

}
