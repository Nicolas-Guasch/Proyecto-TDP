package Entities;

import Engine.IGameObject;
import EntitiesVisitor.BarricadeEnemVisitor;
import EntitiesVisitor.VisitorEntity;

public class BarricadeEnem extends Entity {

	public BarricadeEnem(IGameObject referenced) {
		super(referenced);
		visitor = new BarricadeEnemVisitor();
	}

	@Override
	public void accept(VisitorEntity visitor) {
		visitor.visit(this);
	}
}