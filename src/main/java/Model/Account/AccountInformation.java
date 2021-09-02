package Model.Account;

public class AccountInformation {
    private String username;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String passWord;

    public void emailIsValid( String email ){
        // TODO IMPLEMENT change type to boolean...
    }

    public void phoneNumberIsValid( String phoneNumber ){
        // TODO IMPLEMENT change type to boolean...
    }

    public AccountInformation(String username, String name, String lastName, String email, String phoneNumber, String passWord) {
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void printAccountInformation() {
        System.out.println(this.name);
        System.out.println(this.lastName);
        System.out.println(this.email);
        System.out.println(this.phoneNumber);
        System.out.println(this.username);
    }
}
