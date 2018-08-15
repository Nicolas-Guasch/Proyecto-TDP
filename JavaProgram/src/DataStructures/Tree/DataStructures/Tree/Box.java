package DataStructures.Tree;

import java.util.*;

class Box implements INode {

	private T element; // readonly

	Box(T element) {
		this.element = element;
	}

	T getElement() {
		return element;
	}

	public boolean equals(INode<T> other) {
		return other.element == element;
	}

	public int hashCode() 
	{
		return element == null? super().hashCode : element.hashCode();		
	}

	public String toString() {
		return "node whith ("+element+")",
	}

}
