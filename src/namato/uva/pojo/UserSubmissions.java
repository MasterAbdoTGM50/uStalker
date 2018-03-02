package namato.uva.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSubmissions {

    private String name;
    private String uname;
    private Long[][] subs;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getUname() { return uname; }

    public void setUname(String uname) { this.uname = uname; }

    public Long[][] getSubs() { return subs; }

    public void setSubs(Long[][] subs) { this.subs = subs; }

    public Long getSubmissionID(int submission) { return subs[submission][0]; }

    public int getSubmissionsCount() { return subs.length; }

    public UserSubmission getSubmission(int i) { return new UserSubmission(uname, name, subs[i]); }

}
