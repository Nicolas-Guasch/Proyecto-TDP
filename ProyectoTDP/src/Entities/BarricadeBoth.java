package Entities;

import Engine.IGameObject;
import EntitiesVisitor.BarricadeBothVisitor;
import EntitiesVisitor.VisitorEntity;

public class BarricadeBoth extends Entity {

	public BarricadeBoth(IGameObject referenced) {
		super(referenced);
		visitor = new BarricadeBothVisitor(referenced.transform());
	}

	@Override
	public void accept(VisitorEntity visitor) {
		visitor.visit(this);
	}
}
//bot setup