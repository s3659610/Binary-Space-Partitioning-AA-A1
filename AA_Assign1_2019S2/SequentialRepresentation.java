import java.io.PrintWriter;


/**
 * Sequential Tree Representation implementation for the {@link BSPTree} interface.
 * <p>
 * Your task is to complete the implementation of this class.
 * You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 * @author Yongli Ren, 2019.
 * @author Alexander Ruberto, 2019.
 */
public class SequentialRepresentation<T> implements BSPTree<T> {

    //Primitive array to represent tree sequentially
    @SuppressWarnings("unchecked")
    private T[] tree = (T[]) new Object[2];

    /**
     * Constructs empty graph.
     */
    public SequentialRepresentation() {
        // Implement me!
        //@SuppressWarnings("unchecked")
        //tree = (T[]) new Object[1];
    } // end of SequentialRepresentation()

    @Override
    public void setRootNode(T nodeLabel) {
        // Implement me!
        
        //Check if root node has already been set
        if (tree[1] == null) {
           tree[1] = nodeLabel;
        }
        //If already set send error warning
        else {
           System.err.println("ERROR: Root node already set!");
        }
    } // end of setRootNode()

    @Override
    public void splitNode(T srcLabel, T leftChild, T rightChild) {
        // Implement me!
        //TODO: Implement dynamic array
        boolean found = false;
        //Look through tree until srcLabel is found
        for (int k = 0; k < tree.length && !found; i++) {

            if (tree[k].equals(srcLabel)) {
                found = true;

                //If the node is not already split
                if (tree[2*k] == null) {
                    //Set split values
                    tree[2*k] = leftChild;
                    tree[2*k + 1] = rightChild;
                }
                else
                    System.err.println("ERROR: Node already split!");            
            }
        }

        if (!found)
            System.err.println("ERROR: Node not found in tree!");
            
    } // end of splitNode

    @Override
    public boolean findNode(T nodeLabel) {
        // Implement me!
        boolean found = false;

        //Look through tree until srcLabel is found
        for (int i = 0; i < tree.length && !found; i++) {

            if (tree[i].equals(nodeLabel)) {
                found = true;
            }
        }

        return found;
    } // end of findNode

    @Override
    public String findParent(T nodeLabel) {
        // Implement me!
        String parentString = nodeLabel.toString() + " ";
        boolean found = false;
        T parent = null;

        //Look through tree until node is found
        for (int k = 0; k < tree.length && !found; i++) {

            if (tree[k].equals(nodeLabel)) {
                found = true;
                
                //If the child is on the right, move the index to get parent evenly
                if (!isLeftChild(k)) {
                    k -= 1;
                }

                parent = tree[k / 2];
            }
        }

        if (found) {
            parentString += parent.toString();
        }
        else {
            System.err.println("ERROR: Node not found in tree!");
        }

        return parentString;
    } // end of findParent

    @Override
    public String findChildren(T nodeLabel) {
        // Implement me!
        String childString = nodeLabel.toString() + " ";
        boolean found = false;
        T leftChild = null;
        T rightChild = null;

        //Look through tree until node is found
        for (int k = 0; k < tree.length && !found; i++) {

            if (tree[k].equals(nodeLabel)) {
                found = true;

                //There may be children if the tree extends far enough
                if (tree.length > 2*k) {
                    //If nodes exist at the children positions set the children
                    if (tree[2*k] != null) {
                        leftChild = tree[2*k];
                        rightChild = tree[2*k + 1];
                    }
                }
            }
        }
        
        if (found) {
            childString += leftChild.toString() + " " + rightChild.toString();
        }
        else {
            System.err.println("ERROR: Node not found in tree!");
        }

        return childString;
    } // end of findParent

    @Override
    public void printInPreorder(PrintWriter writer) {
        // Implement me!
        printInPreorder(writer, tree[1]);

    } // end of printInPreorder

    //Recursive preorder traversal
    private void printInPreorder(PrintWriter writer, T node) {
        if (node != null) {
            writer.print(node.toString() + " ");
            printInPreorder(writer, getLeftChild(node));
            printInPreorder(writer, getRightChild(node));
        }
    }

    @Override
    public void printInInorder(PrintWriter writer) {
        // Implement me!
        printInInorder(writer, tree[1]);
    } // end of printInInorder

    private void printInInorder(PrintWriter writer, T node) {
        if (node != null) {
            printInInorder(writer, getLeftChild(node));
            writer.print(node.toString() + " ");
            printInInorder(writer, getRightChild(node));
        }
    }

    @Override
    public void printInPostorder(PrintWriter writer) {
        // Implement me!
        printInPostorder(writer, tree[1]);
    } // end of printInPostorder

    private void printInPostorder(PrintWriter writer, T node) {
        if (node != null) {
            printInPostorder(writer, getLeftChild(node));
            printInPostorder(writer, getRightChild(node));
            writer.print(node.toString() + " ");            
        }
    }

    private T getLeftChild(T parentNode) {

        boolean found = false;
        T leftChild = null;

        for (int k = 0; k < tree.length && !found; i++) {

            if (tree[k].equals(nodeLabel)) {
                found = true;
                leftChild = tree[2*k];
            }
        }

        return leftChild;

    }

    private T getRightChild(T parentNode) {

        boolean found = false;
        T rightChild = null;

        for (int k = 0; k < tree.length && !found; i++) {

            if (tree[k].equals(nodeLabel)) {
                found = true;
                rightChild = tree[2*k + 1];
            }
        }

        return rightChild;

    }

    private boolean isLeftChild(int childIndex) {
        boolean isLeft;
        
        //If the childs index is even it is on the left otherwise on the right if odd
        if (childIndex % 2 == 0) {
            isLeft = true;
        } 
        else {
            isLeft = false;
        }

        return isLeft;
    }

} // end of class SequentialRepresentation