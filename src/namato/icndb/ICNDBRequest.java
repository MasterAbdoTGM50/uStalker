package namato.icndb;

import namato.icndb.pojo.*;

import org.springframework.web.client.RestTemplate;

public class ICNDBRequest {

    private RestTemplate rest;

    private static ICNDBRequest instance = null;

    private ICNDBRequest() { rest = new RestTemplate(); }

    public JokeCategories getCategories() {

        return rest.getForObject("http://api.icndb.com/categories", JokeCategories.class);

    }

    public JokeCount getJokeCount() {

        return rest.getForObject("http://api.icndb.com/jokes/count", JokeCount.class);

    }

    public Joke getRandomJoke(String firstName, String lastName, Long count) {

        return rest.getForObject("http://api.icndb.com/jokes/random/" + count + "?firstName=" + firstName + "&lastName=" + lastName, Joke.class);

    }

    public Joke getRandomJoke(String firstName, String lastName, String category, Long count) {

        return rest.getForObject("http://api.icndb.com/jokes/random/" + count + "?limitTo=[" + category + "]&" + "firstName=" + firstName + "&lastName=" + lastName, Joke.class);

    }

    public static namato.icndb.ICNDBRequest get() { return (instance == null) ? new namato.icndb.ICNDBRequest() : instance; }

}