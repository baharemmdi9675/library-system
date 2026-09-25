package entity;

public class Member {
    private Integer id;
    private String username;
    private String email;

    public Member(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public Member(String username, String email) {

        this.username = username;
        this.email = email;
    }

    public Member(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + "email " + email + '\'' +
                '}';
    }
}
