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
        // Implement me!

        // first check if srcLabel exists if it doesn't than i can't split somehting thats not in the tree
        // once the node is found
            // consturct nodes with data
                // if data empty create empty nodes and link children to the srcLabel(parant)
                    // check that leftChild < srcLabel && rightChild > srcLabel
                        // if the above startement is false check the inverse of that logic and if thats true switch children
                            // if data exists and is correct with no excpetions place data into new nodes


    } // end of splitNode

    @Override
    public boolean findNode(T nodeLabel) {
        String node = nodeLabel.toString();
        // first check if the node wanting to be found is the root node
        if(node.equals(root.getData())){
            return true;
        }
        // implement DFS approach to finding node


        return false;
    } // end of findNode

    @Override
    public String findParent(T nodeLabel) {

        // assuming nodeLabel = 'b'
        // traverse through linked representation intill 'b' is found
            // if nodeLabel not found return 'b '
                // otherwise if found call nodeLabel.getParent()
                    // and print 'b <parent>'
        

        return null;
    } // end of findParent

    @Override
    public String findChildren(T nodeLabel) {
        // Implement me!
        return null;
    } // end of findParent

    @Override
    public void printInPreorder(PrintWriter writer) {
        // Implement me!
    } // end of printInPreorder

    @Override
    public void printInInorder(PrintWriter writer) {
        // Implement me!
    } // end of printInInorder

    @Override
    public void printInPostorder(PrintWriter writer) {
        // Implement me!
    } // end of printInPostorder

} // end of class LinkedRepresentation
