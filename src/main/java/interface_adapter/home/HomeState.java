package interface_adapter.home;

/**
 * Represents the state of the home view, including the user's email ID.
 */
public class HomeState {
    /** The email ID of the user */
    private String emailId;

    /**
     * Retrieves the user's email ID.
     *
     * @return The email ID.
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * Sets the user's email ID.
     *
     * @param emailId The email ID to set.
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
