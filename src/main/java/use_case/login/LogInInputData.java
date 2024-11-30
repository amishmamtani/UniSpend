package use_case.login;

public class LogInInputData {
    private final String username;
    private final String password;

    public LogInInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {return username;}
    public String getPassword() {return password;}
}
