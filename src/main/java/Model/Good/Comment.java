package Model.Good;

import Model.Account.Buyer;

public class Comment {
    private String content;
    private  String title;

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
    public Comment(String title,String content){
        this.title=title;
        this.content=content;

    }
    public Comment(Buyer buyer,String content){
        this.buyer=buyer;
        this.content=content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public String getTitle() {
        return title;
    }

    public CommentState getCommentState() {
        return commentState;
    }

    public boolean isHasBought() {
        return hasBought;
    }
}
