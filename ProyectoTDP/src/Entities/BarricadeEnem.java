package Entities;

import Engine.GameObject;
import EntitiesVisitor.BarricadeEnemVisitor;
import EntitiesVisitor.VisitorEntity;

public class BarricadeEnem extends Entity {

	public BarricadeEnem(GameObject referenced) {
		super(referenced);
		visitor = new BarricadeEnemVisitor();
	}

	@Override
	public void accept(VisitorEntity visitor) {
		visitor.visit(this);
	}
}