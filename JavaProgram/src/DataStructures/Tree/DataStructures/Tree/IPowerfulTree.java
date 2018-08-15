package DataStructures.Tree;

import java.util.*;

public interface IPowerfulTree extends ITree {

	public INode<T> getNode(T element);

	public Iterable<T> inorder();

	public Iterable<T> postorder();

	public Iterable<T> preorder();

}
