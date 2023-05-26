package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;


public class CollisionDetector implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        // two for loops for all entities in the world

        for (Entity entity : world.getEntities()) {

            for (Entity collisionDetection : world.getEntities()) {
                // If two entities are the same they wont destroy each other. I.e 2 asteroids wont destroy each other.
                if (entity.getID().equals(collisionDetection.getID())){
                    continue;
                }


                // CollisionDetection
                if (this.collides(entity, collisionDetection)){
                    LifePart entityLife = entity.getPart(LifePart.class);
                    LifePart collisionLife = collisionDetection.getPart(LifePart.class);

                    if (entityLife.getLife() > 0) {
                        entityLife.setLife(entityLife.getLife() - 1);
                        entityLife.setIsHit(true);
                        // if entity is out of life - remove
                        if (entityLife.getLife() <= 0) {
                            world.removeEntity(entity);
                        }
                    }
                    if (collisionLife.getLife() > 0) {
                        collisionLife.setLife(collisionLife.getLife() - 1);
                        collisionLife.setIsHit(true);
                        // if entity is out of life - remove
                        if (collisionLife.getLife() <= 0) {
                            world.removeEntity(collisionDetection);
                        }
                    }


                }

            }
        }
    }

    public Boolean collides(Entity entity, Entity entity2) {
        PositionPart entityPosition1 = entity.getPart(PositionPart.class);
        PositionPart entityPosition2 = entity2.getPart(PositionPart.class);
        float dx = entityPosition1.getX() - entityPosition2.getX();
        float dy = entityPosition1.getY() - entityPosition2.getY();
        float distance = (float)Math.sqrt(dx * dx + dy * dy);
        if (distance < entity.getRadius() + entity2.getRadius()){
            return true;
        } else{
            return false;
        }
    }

}
