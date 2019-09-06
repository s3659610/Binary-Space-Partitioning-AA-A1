

import java.io.PrintWriter;

/**
 * Linked Tree Representation implementation for the {@link BSPTree} interface.
 * <p>
 * Your task is to complete the implementation of this class.
 * You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016. 
 * @author Yongli Ren, 2019.
 */
public class LinkedRepresentation<T> implements BSPTree<T> {

    Node root;
    String treeString ="";

    /**
     * Constructs empty tree.
     */
    public LinkedRepresentation() {
        root = new Node(null);
    } // end of LinkedRepresentation()

    @Override
    public void setRootNode(T nodeLabel) {
        root.setData(nodeLabel.toString());
    } // end of setRootNode()

    @Override
    public void splitNode(T srcLabel, T leftChild, T rightChild) {
        Node leftChildNode = new Node(leftChild.toString());
        Node rightChildNode = new Node(rightChild.toString());
        Node parent = root;
        try {
            parent= returnNode(root, srcLabel);

            // set parent
            leftChildNode.setParent(parent);
            rightChildNode.setParent(parent);

            // set children
            parent.setChildren(leftChildNode,rightChildNode);
        }catch (Exception e){
            e.printStackTrace();
        }



    } // end of splitNode

    /*
        Recursively search for a node and return said node.
        Used in functions:
            - SplitNode
            - Find Parent
            - Find Children
     */
    public Node returnNode(Node node, T nodeLabel){
        if (node.getData().equals(nodeLabel)) {
            return node;
        }
        Node[] children = node.getChildren();
        Node tempNode = null;
        // Note: This would only work on a balanced tree
        if (children[0] != null && children[1] != null)
        for (int i = 0; tempNode == null && i < children.length; i++) {
            tempNode = returnNode(children[i], nodeLabel);
            if(tempNode!=null)
                return tempNode;
        }
        return tempNode;


    }



    @Override
    public boolean findNode(T nodeLabel) { return findNodeRecursion(root, nodeLabel); } // end of findNode
    /*
        Recursively Search the tree given the root node
     */
    public boolean findNodeRecursion(Node node, T nodeLabel){
        if (node.getData().equals(nodeLabel))
            return true;
        try {
                for (Node n : node.getChildren()) {
                if (findNodeRecursion(n, nodeLabel))
                    return true;
            }
        } catch (Exception e){
            // has no children
        }

        return false;
    }

    @Override
    public String findParent(T nodeLabel) {
        String parentString = "";

        try{
            String parent = returnNode(root, nodeLabel).getParent().getData();
            parentString = String.format("%s %s",nodeLabel, parent );


        } catch (Exception e){
            // parent null, could mean two things the find parent parmater is equal to the root and/or root not found
            // in either case print nodeLabel
            parentString +=nodeLabel;
        }

        return parentString;
    } // end of findParent

    @Override
    public String findChildren(T nodeLabel) {
        String childrenString = "";
        childrenString += nodeLabel;
        try{
            Node[] children = returnNode(root, nodeLabel).getChildren();

            for(Node c: children){
                if(c.getData() != "")
                    childrenString += " " + c.getData();
            }


        } catch (Exception e){

        }

        return childrenString;

    } // end of findParent

    /*
        Works as the recursive method for printers
     */
    public void printTree(Node node, String printOrder){
        if (node == null)
            return;

        if(printOrder.equals("pre")){
            treeString += node.getData() + " ";
            printTree(node.getLeftChild(), printOrder);

            printTree(node.getRightChild(), printOrder);
        } else if(printOrder.equals("post")){

            printTree(node.getLeftChild(), printOrder);

            printTree(node.getRightChild(), printOrder);
            treeString += node.getData() + " ";

        } else if(printOrder.equals("order")){

            printTree(node.getLeftChild(), printOrder);
            treeString += node.getData() + " ";
            printTree(node.getRightChild(), printOrder);
        }




    }


    @Override
    public void printInPreorder(PrintWriter writer) {
        printTree(root, "pre");
        writer.println(treeString);
        treeString = "";

    } // end of printInPreorder

    @Override
    public void printInInorder(PrintWriter writer) {
        printTree(root, "order");
        writer.println(treeString);
        treeString = "";
    } // end of printInInorder


    @Override
    public void printInPostorder(PrintWriter writer) {
        printTree(root, "post");
        writer.println(treeString);
        treeString = "";
    } // end of printInPostorder

} // end of class LinkedRepresentation
