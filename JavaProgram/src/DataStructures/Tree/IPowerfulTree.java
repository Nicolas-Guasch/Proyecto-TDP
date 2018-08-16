package DataStructures.Tree;

import java.util.*;

public interface IPowerfulTree<T> extends ITree<T> {

	public INode<T> getNode(T element);

	public Iterable<T> inorder();

	public Iterable<T> postorder();

	public Iterable<T> preorder();

}
