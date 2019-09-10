import java.io.PrintWriter;
import java.util.Arrays;


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
        for (int k = 0; k < tree.length && !found; k++) {

            if (tree[k] != null) {
                if (tree[k].equals(srcLabel)) {
                    found = true;

                    if (tree.length < 2*k+1) {
                        increaseArrayLength(2*k+1);

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

            if (tree[i] != null) {
                if (tree[i].equals(nodeLabel)) {
                    found = true;
                }
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
        for (int k = 0; k < tree.length && !found; k++) {

            if (tree[k] != null) {
                if (tree[k].equals(nodeLabel)) {
                    found = true;
                    
                    //If the child is on the right, move the index to get parent evenly
                    if (!isLeftChild(k)) {
                        k -= 1;
                    }

                    if (k/2 > 0) {
                        parent = tree[k / 2];
                    }
                }
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
        for (int k = 0; k < tree.length && !found; k++) {

            if (tree[k] != null) {
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
        printInPreorder(writer, 1);
        writer.println();

    } // end of printInPreorder

    /**
     * Recursive function for preorder traversal
     * 
     * @param writer : for output of values
     * @param index : index of node in tree array
     */
    private void printInPreorder(PrintWriter writer, int index) {
        if (index < tree.length) {
            if (tree[index] != null) {
                writer.printf("%s ", tree[index].toString());
                printInPreorder(writer, 2*index);
                printInPreorder(writer, 2*index+1);
            }
        }
    }

    @Override
    public void printInInorder(PrintWriter writer) {
        // Implement me!
        printInInorder(writer, 1);
        writer.println();
    } // end of printInInorder

    /**
     * Recursive function for inorder traversal
     * 
     * @param writer : for output of values
     * @param index : index of node in tree array
     */
    private void printInInorder(PrintWriter writer, int index) {
        if (index < tree.length) {
            if (tree[index] != null) {
                printInInorder(writer, 2*index);
                writer.printf("%s ", tree[index].toString());
                printInInorder(writer, 2*index+1);
            }
        }
    }

    @Override
    public void printInPostorder(PrintWriter writer) {
        // Implement me!
        printInPostorder(writer, 1);
        writer.println();
    } // end of printInPostorder

    /**
     * Recursive function for postorder traversal
     * 
     * @param writer : for output of values
     * @param index : index of node in tree array
     */
    private void printInPostorder(PrintWriter writer, int index) {
        if (index < tree.length) {
            if (tree[index] != null) {
                printInPostorder(writer, 2*index);
                printInPostorder(writer, 2*index+1);
                writer.printf("%s ", tree[index].toString());            
            }
        }
    }

    private void increaseArrayLength(int newSize) {
        tree =  Arrays.copyOf(tree, newSize + 1);
    }

    /*private T getLeftChild(T parentNode) {

        boolean found = false;
        T leftChild = null;

        for (int k = 0; k < tree.length && !found; i++) {

            if (tree[k].equals(nodeLabel)) {
                found = true;
                leftChild = tree[2*k];
            }
        }

        return leftChild;

    }*/

    /*private T getRightChild(T parentNode) {

        boolean found = false;
        T rightChild = null;

        for (int k = 0; k < tree.length && !found; i++) {

            if (tree[k].equals(nodeLabel)) {
                found = true;
                rightChild = tree[2*k + 1];
            }
        }

        return rightChild;

    }*/

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