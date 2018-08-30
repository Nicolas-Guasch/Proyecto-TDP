package DataStructures.Tree;

import java.util.*;

class Box <T> implements INode<T>
{

	private T element; // readonly

	Box(T element) {
		this.element = element;
	}

	public Box() {
		element = null;
	}

	public T getElement() {
		return element;
	}

	public boolean equals(INode<T> other) {
		return other.getElement() == element;
	}

	public int hashCode() 
	{
		return element == null? super.hashCode() : element.hashCode();
	}

	public String toString() {
		return "node whith ("+element+")";
	}

}
