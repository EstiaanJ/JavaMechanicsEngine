package ejvr.physics.collision;

import ejvr.math.real.VectorDouble;
import ejvr.physics.kinematics.KinematicBody;

public class Collider extends KinematicBody {
    public final AABB aabb;
    public final int id;

    public Collider(int id, KinematicBody body, double height, double width) {
        super(body);
        this.id = id;
        this.aabb = new AABB(body.position, width, height);
    }

    public boolean quickEquals(Collider targetEntity) {
        return this.id == targetEntity.id;
    }

    public VectorDouble pos(){
        return aabb.getPos();
    }
}
