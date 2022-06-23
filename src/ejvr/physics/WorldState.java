package ejvr.physics;

import ejvr.math.real.VectorDouble;
import ejvr.physics.collision.CircularCollider;
import ejvr.physics.collision.CircularColliderPair;
import ejvr.physics.kinematics.KinematicBody;
import processing.core.PApplet;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class WorldState {
    public static final int CLAMP_X_MAX = 800;
    public static final int CLAMP_Y_MAX = 800;
    public static final int CLAMP_X_MIN = 0;
    public static final int CLAMP_Y_MIN = 0;

    private final CircularCollider[] colliderList;

    public WorldState(CircularCollider[] colliderList) {
        ArrayList<CircularCollider> list = new ArrayList<>();
        for(CircularCollider collider : colliderList) {
            list.add(new CircularCollider(collider.id, collider.radius, new KinematicBody(collider.position, collider.velocity, collider.netForce, collider.mass)));
        }
        this.colliderList = list.toArray(new CircularCollider[list.size()]);
    }

    public WorldState(WorldState worldState){
        ArrayList<CircularCollider> colliders = new ArrayList<>();
        for(CircularCollider collider : worldState.colliderList){
            colliders.add(collider);
        }
        colliderList = colliders.toArray(new CircularCollider[colliders.size()]);
    }

    private CircularCollider[] stepKinematics(double deltaTime) {
        ArrayList<CircularCollider> returnList = new ArrayList<>();
        for (CircularCollider entity : colliderList) {
            KinematicBody body = entity.stepPhysics(deltaTime);
            returnList.add(new CircularCollider(entity.id, entity.radius, body));
        }
        CircularCollider[] ret = new CircularCollider[returnList.size()];
        return returnList.toArray(ret);
    }

    public WorldState stepCollisions(double deltaTime) {
        return new WorldState(resolveCollisions());
    }

    public WorldState stepMotion(double deltaTime) {

        return new WorldState(stepKinematics(deltaTime));
    }

    public void stepFrame(PApplet graphics) {
        for (CircularCollider cc : colliderList) {
            graphics.pushMatrix();
            graphics.translate(cc.position.xFloat(), cc.position.yFloat());
            graphics.stroke(255);
            graphics.fill(0);
            graphics.circle(0, 0, (float) cc.radius * 2);

            graphics.popMatrix();
        }
    }

    public void stepFrame(PApplet graphics, Method method) {}

    private CircularCollider[] resolveCollisions() {
        CollisionIDPair[] collisions = detectIntersections(colliderList);
        CircularCollider[] returnList = seperateIntersectingEntities(collisions);//colliderList;
        int itter = 0;
        /*
        while (collisions.length > 0 && itter < 3) {
            itter++;
            collisions = detectIntersections(returnList);
            returnList = seperateIntersectingEntities(collisions);

        }*/
        return returnList;
    }

    private CollisionIDPair[] detectIntersections(CircularCollider[] entitiesToCheck) {
        ArrayList<CollisionIDPair> pairs = new ArrayList<>();
        for (CircularCollider currentEntity : entitiesToCheck) {
            for (CircularCollider targetEntity : entitiesToCheck) {
                if (!currentEntity.quickEquals(targetEntity)) {
                    if (CircularCollider.overlap(currentEntity, targetEntity)) {
                        pairs.add(new CollisionIDPair(currentEntity.id, targetEntity.id));
                    }
                }
            }
        }
        return pairs.toArray(new CollisionIDPair[pairs.size()]);
    }


    private CircularCollider[] seperateIntersectingEntities(CollisionIDPair[] idPairs) {
        HashMap<Integer, CircularCollider> seperatedEntities = new HashMap<>();
        ArrayList<CircularCollider> allEntites = new ArrayList<CircularCollider>();

        for (CollisionIDPair pair : idPairs) {
            var newPair = CircularCollider.solveCollision(CircularColliderPair.seperateEntityPair(colliderList[pair.id1()], colliderList[pair.id2()]));
            seperatedEntities.put(newPair.one().id, newPair.one());
            seperatedEntities.put(newPair.two().id, newPair.two());
        }
        for (CircularCollider circularCollider : colliderList) {
            if (seperatedEntities.containsKey(circularCollider.id)) {
                CircularCollider col = seperatedEntities.get(circularCollider.id);
                allEntites.add(col);
            } else {
                allEntites.add(circularCollider);
            }
        }
        return allEntites.toArray(new CircularCollider[allEntites.size()]);
    }


    public WorldState addCircularCollider(double size, VectorDouble position, VectorDouble velocity, VectorDouble netForce) {
        ArrayList<CircularCollider> newColliderList = new ArrayList<>();
        if (colliderList.length > 0) {
            for (CircularCollider colliderInOriginal : colliderList) {
                newColliderList.add(colliderInOriginal);
            }
        }
        KinematicBody newKinematicBody = new KinematicBody(position, velocity, netForce, size);
        CircularCollider newCollider = new CircularCollider(colliderList.length, size, newKinematicBody);
        newColliderList.add(newCollider);
        return new WorldState(newColliderList.toArray(new CircularCollider[newColliderList.size()]));
    }


    public CircularCollider[] colliderList() {
        return colliderList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (WorldState) obj;
        return Objects.equals(this.colliderList, that.colliderList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(colliderList);
    }

    @Override
    public String toString() {
        return "WorldState[" +
                "colliderList=" + colliderList + ']';
    }

    public WorldState replaceEntity(CircularCollider collider, int id) {
        ArrayList<CircularCollider> colliders = new ArrayList<>();
        for(int i = 0; i < colliderList.length; i++){
            if(i == id){
                colliders.add(collider);
            } else {
                colliders.add(colliderList[i]);
            }
        }
        return new WorldState(colliders.toArray(new CircularCollider[colliders.size()]));
    }



}
