package ejvr.physics.collision;

import ejvr.math.real.VectorDouble;

public record CircularColliderPair(CircularCollider one, CircularCollider two) {

    public static CircularColliderPair seperateEntityPair(CircularCollider currentEntity, CircularCollider targetEntity) {
        var distance = CircularCollider.overlapDistance(currentEntity, targetEntity);
        var overlap = (0.51 * (distance - currentEntity.radius - targetEntity.radius));

        var newCX = currentEntity.position.x();
        newCX -= overlap * (currentEntity.position.x() - targetEntity.position.x()) / distance;
        var newCY = currentEntity.position.y();
        newCY -= overlap * (currentEntity.position.y() - targetEntity.position.y()) / distance;

        var newC = currentEntity.byPosition(new VectorDouble(newCX,newCY));

        var newTX = targetEntity.position.x();
        newTX += overlap * (currentEntity.position.x() - targetEntity.position.x()) / distance;
        var newTY = targetEntity.position.y();
        newTY += overlap * (currentEntity.position.y() - targetEntity.position.y()) / distance;

        var newT = targetEntity.byPosition(new VectorDouble(newTX,newTY));

        return new CircularColliderPair(newC,newT);
    }
}
