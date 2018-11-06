package Entities;

import Engine.GameObject;
import EntitiesVisitor.BarricadeBothVisitor;
import EntitiesVisitor.VisitorEntity;

public class BarricadeBoth extends Entity {

	public BarricadeBoth(GameObject referenced) {
		super(referenced);
		visitor = new BarricadeBothVisitor(referenced.transform());
	}

	@Override
	public void accept(VisitorEntity visitor) {
		visitor.visit(this);
	}
}
