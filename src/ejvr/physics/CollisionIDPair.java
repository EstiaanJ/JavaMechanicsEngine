package ejvr.physics;

public record CollisionIDPair(int id1, int id2) implements Comparable<CollisionIDPair> {

    @Override
    public boolean equals(Object o){
        boolean isEqual = false;
        CollisionIDPair inPair = (CollisionIDPair) o;
        if(this.id1 == inPair.id1 && this.id2 == inPair.id2 || this.id1 == inPair.id2 && this.id2 == inPair.id1){
            isEqual = true;
        }
        return isEqual;
    }

    @Override
    public int compareTo(CollisionIDPair o) {
        CollisionIDPair inPair = (CollisionIDPair) o;
        if((inPair.id1 * inPair.id2) > (this.id1 * this.id2)){
            return 1;
        } else if (equals(o)) {
            return 0;
        } else {
            return -1;
        }
    }
}
