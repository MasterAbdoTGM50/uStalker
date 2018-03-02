package namato.uva.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserSubmission {

    static Map<Long, String> LANG;
    static Map<Long, String> VERDICT;

    static {
        LANG = new HashMap<>();
        LANG.put(1L, "ANSI C");
        LANG.put(2L, "Java");
        LANG.put(3L, "C++");
        LANG.put(4L, "Pascal");
        LANG.put(5L, "C++11");
        LANG.put(6L, "Python 3");

        VERDICT = new HashMap<>();
        VERDICT.put(10L, "UserSubmissions Error");
        VERDICT.put(15L, "Can't be Judged");
        VERDICT.put(20L, "In Queue");
        VERDICT.put(30L, "Compilation Error");
        VERDICT.put(35L, "Restricted Function");
        VERDICT.put(40L, "Runtime Error");
        VERDICT.put(45L, "Output Limit");
        VERDICT.put(50L, "Time Limit");
        VERDICT.put(60L, "Memory Limit");
        VERDICT.put(70L, "Wrong Answer");
        VERDICT.put(80L, "Presentation Error");
        VERDICT.put(90L, "Accepted");
    }

    String name;
    String uname;

    Long sid;
    Long pid;
    String title;
    String verdict;
    Long runtime;
    Date date;
    String lang;
    Long rank;

    UserSubmission() { }

    UserSubmission(String uname, String name, Long[] submission) {
        this.uname = uname;
        this.name = name;

        this.sid = submission[0];
        this.pid = submission[1];
        this.verdict = VERDICT.get(submission[2]);
        this.runtime = submission[3];
        this.date = Date.from(Instant.ofEpochSecond(submission[4]));
        this.lang = LANG.get(submission[5]);
        this.rank = submission[6];
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getUname() { return uname; }

    public void setUname(String uname) { this.uname = uname; }

    public Long getSid() { return sid; }

    public void setSid(Long sid) { this.sid = sid; }

    public void setPid(Long pid) { this.pid = pid; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Long getPid() { return pid; }

    @JsonProperty("ver")
    public String getVerdict() { return verdict; }

    @JsonProperty("ver")
    public void setVerdict(Long verdict) { this.verdict = VERDICT.get(verdict); }

    @JsonProperty("run")
    public Long getRuntime() { return runtime; }

    @JsonProperty("run")
    public void setRuntime(Long runtime) { this.runtime = runtime; }

    @JsonProperty("sbt")
    public Date getDate() { return date; }

    @JsonProperty("sbt")
    public void setDate(Long date) { this.date = Date.from(Instant.ofEpochSecond(date)); }

    @JsonProperty("lan")
    public String getLang() { return lang; }

    @JsonProperty("lan")
    public void setLang(Long lang) { this.lang = LANG.get(lang); }

    public Long getRank() { return rank; }

    public void setRank(Long rank) { this.rank = rank; }
}
