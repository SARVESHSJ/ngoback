package projectngo.ngopro.Entity;

public class NeighborPosts {

    private Long prevPostId;
    private Long nextPostId;

    public Long getPrevPostId() {
        return prevPostId;
    }

    public void setPrevPostId(Long prevPostId) {
        this.prevPostId = prevPostId;
    }

    public Long getNextPostId() {
        return nextPostId;
    }

    public void setNextPostId(Long nextPostId) {
        this.nextPostId = nextPostId;
    }

    public NeighborPosts(Long prevPostId, Long nextPostId) {
        this.prevPostId = prevPostId;
        this.nextPostId = nextPostId;
    }
}
