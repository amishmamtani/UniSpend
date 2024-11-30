package use_case.login;

public class LogInOutputData {
    private final String username;
    private final boolean useCaseFailed;

    public LogInOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {return username;}
}
