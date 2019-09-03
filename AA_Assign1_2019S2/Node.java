class Node{
    private String data;
    private Node parent;
    private Node left;
    private Node right;

    public Node(String element){
        data = element;
        left = null;
        right = null;
    }

    public void setRightChild(Node n)
    {
        right = n;
    }

    public void setLeftChild(Node n){
        left = n;
    }

    public void setParent(Node n) {parent = n;}

    public Node getRightChild(){
        return right;
    }

    public Node getLeftChild(){
        return left;
    }

    public Node getParent() {return parent;}

    public String getData(){
        return data;
    }

    public void setData(String x){
        data = x;
    }
}
