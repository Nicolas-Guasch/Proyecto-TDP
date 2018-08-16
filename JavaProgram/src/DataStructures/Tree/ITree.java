package DataStructures.Tree;

/**
 *  Models a classic Tree Behaviour.
 * @param <T>
 */
public interface ITree<T>
{
    /**
     * @param elem first element to be stored in the tree.
     *             If the tree already had a root it will throw an exception at run time.
     * @return the root node.
     */
    public INode<T> setRoot(T elem);

    /**
     * Gives the root.
     * @return The root node of the tree.
     */
    public INode<T> getRoot();


    public INode<T> addChild(INode<T> node, T element);
    /**
     * Gives an iterable of the children of the node.
     * @param node This is the node to which we are going to ask you for an iterable of your children.
     * @return Iterable of your children.
     */
    public Iterable<INode<T>> getChildren(INode<T> node);
    /**
     *  Get the parent of the node
     * @param node node to which we are asking the father
     * @return Parent node.
     */
    public INode<T> getParent(INode<T> node);

    /**
     * removes the current node from the tree and all the subtree
     * @param node node to remove
     */
    public void remove(INode<T> node);

}


