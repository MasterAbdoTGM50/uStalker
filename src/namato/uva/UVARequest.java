package namato.uva;

import namato.uva.pojo.*;

import org.springframework.web.client.RestTemplate;

import java.util.Date;

public class UVARequest {

    private RestTemplate rest;

    private static UVARequest instance = null;
    private UVARequest() { rest = new RestTemplate(); }

    public long getUserID(String username) {

        return rest.getForObject("https://uhunt.onlinejudge.org/api/uname2uid/" + username, long.class);

    }

    public UserRanking getUserRanking(long id) {

        return rest.getForObject("https://uhunt.onlinejudge.org/api/ranklist/" + id +"/0/0", UserRanking[].class)[0];

    }

    public UserSubmissions getUserSubmissions(long id) {

        return rest.getForObject("https://uhunt.onlinejudge.org/api/subs-user/" + id, UserSubmissions.class);

    }

    public UserSubmissions getLatestUserSubmissions(long id, long count) {

        return rest.getForObject("https://uhunt.onlinejudge.org/api/subs-user-last/" + id + "/" + count, UserSubmissions.class);

    }


    public Problem getProblemByNum(Long number) {

        return rest.getForObject("https://uhunt.onlinejudge.org/api/p/num/" + number, Problem.class);

    }

    public Problem getProblemByID(Long pid) {

        return rest.getForObject("https://uhunt.onlinejudge.org/api/p/id/" + pid, Problem.class);

    }

    public UserSubmission[] getProblemSubmissions(long pid, Date start, Date end) {

        return rest.getForObject("https://uhunt.onlinejudge.org/api/p/subs/" + pid + "/" + start.getTime() / 1000 + "/" + end.getTime() / 1000, UserSubmission[].class);

    }

    public static UVARequest get() { return (instance == null) ? new UVARequest() : instance; }

}
