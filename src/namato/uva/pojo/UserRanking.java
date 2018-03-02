package namato.uva.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRanking {

    private Long userid;
    private String name;
    private Long rank;
    private Long nos;
    private Long ac;

    public Long getUserid() { return userid; }

    public void setUserid(Long userid) { this.userid = userid; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Long getRank() { return rank; }

    public void setRank(Long rank) { this.rank = rank; }

    public Long getNos() { return nos; }

    public void setNos(Long nos) { this.nos = nos; }

    public Long getAc() { return ac; }

    public void setAc(Long ac) { this.ac = ac; }

}
