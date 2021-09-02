package Model.Application;

import java.util.ArrayList;

public abstract class Application {
    private static int requestNumber = 0;
    private int requestId;
    private ArrayList<String> requestDescription;
    private ApplicationState applicationState;
    private ApplicationType applicationType;

    public void setApplicationType(ApplicationType applicationType) {
        this.applicationType = applicationType;
    }

    public ApplicationType getApplicationType() {
        return applicationType;
    }

    public Application( ApplicationType applicationType) {
        this.requestId = requestNumber++;
        this.applicationState = ApplicationState.TO_BE_APPROVED;
        this.applicationType = applicationType;
    }
    public ArrayList<String> getRequestDescription(){
    	return this.requestDescription ;
    }

    public int getRequestId() {
        return requestId;
    }
    public void setApplicationState (ApplicationState applicationState)
    {
    	this.applicationState = applicationState ;
    }
    public ApplicationState getApplicationState() {
        return applicationState;
    }
}
