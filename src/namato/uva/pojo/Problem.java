package namato.uva.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Problem {

    private Long pid;
    private String title;

    Problem() { }

    Problem(Long pid, String title) {
        this.pid = pid;
        this.title = title;
    }

    public Long getPid() { return pid; }

    public void setPid(Long pid) { this.pid = pid; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

}
