package Model.Good;

import Model.Account.Buyer;

public class Comment {
    private Buyer buyer;
    private Good good;
    private CommentState commentState;
    private boolean hasBought;

    public Comment( Good good, boolean hasBought , Buyer buyer ) {
        this.buyer = buyer;
        this.good = good;
        this.hasBought = hasBought;
        commentState = CommentState.TO_BE_APPROVED;
    }

    public void setCommentState(CommentState commentState) {
        this.commentState = commentState;
    }

    public void setHasBought(boolean hasBought) {
        this.hasBought = hasBought;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public Good getGood() {
        return good;
    }

    public CommentState getCommentState() {
        return commentState;
    }

    public boolean isHasBought() {
        return hasBought;
    }
}
