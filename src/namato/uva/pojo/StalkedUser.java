package namato.uva.pojo;

public class StalkedUser {

    private Long id;
    private String uname;
    private String name;

    public StalkedUser() { }

    public StalkedUser(Long id, String uname, String name) {

        this.id = id;
        this.uname = uname;
        this.name = name;

    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUname() { return uname; }

    public void setUname(String uname) { this.uname = uname; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

}
