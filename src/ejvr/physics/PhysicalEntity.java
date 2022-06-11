package ejvr.physics;

import ejvr.physics.collision.Collider;
import ejvr.physics.kinematics.KinematicBody;

public class PhysicalEntity {
    public final KinematicBody body;
    public final Collider collider;

    public PhysicalEntity(KinematicBody kinematicEntity, Collider collider) {
        this.body = kinematicEntity;
        this.collider = collider;
    }
}
