package Model.Account;

public class ManagerRequest {
    private String RequestId;
    private boolean requestStatus;
    private String requestExplanation;

    public ManagerRequest(boolean requestStatus, String requestExplanation) {
        this.requestStatus = requestStatus;
        this.requestExplanation = requestExplanation;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public void setRequestStatus(boolean requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRequestId() {
        return RequestId;
    }

    public boolean isRequestStatus() {
        return requestStatus;
    }

    public String getRequestExplanation() {
        return requestExplanation;
    }
}
