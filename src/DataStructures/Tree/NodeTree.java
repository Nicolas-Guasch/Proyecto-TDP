package DataStructures.Tree;

import java.util.*;

class NodeTree <T> extends Box <T> {

	private List<INode<T>> children;
	private INode<T> parent;

	public List<INode<T>> getChildren() {
		return children;
	}

	public INode<T> getParent() {
		return parent;
	}

	public NodeTree(INode<T> parent) {
		this();
		this.parent = parent;
	}

	public NodeTree() {
		super();
		children = new LinkedList<>();
		parent = null;
	}

}
