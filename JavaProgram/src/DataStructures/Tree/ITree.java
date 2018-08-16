package DataStructures.Tree;

import java.util.*;

public interface ITree <T>{

	public INode<T> addChild(INode<T> node, T element);

	public Iterable<INode<T>> getChildren(INode<T> node);

	public INode<T> getParent(INode<T> node);

	public INode<T> getRoot();

	public void remove(INode<T> node);

	public INode<T> setRoot(T elem);

}
