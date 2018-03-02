package namato;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import namato.uva.pojo.StalkedUser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Local {

    private static final String STALKED_JSON = "stalked.json";

    private static Local instance = null;

    public static Local get() { return (instance == null) ? new Local() : instance; }

    public List<StalkedUser> loadStalked() {

        List<StalkedUser> stalked = new ArrayList<>();

        try {

            File file = new File(System.getProperty("user.home") + "\\" + STALKED_JSON);

            if(!file.exists()) {
                file.createNewFile();
                saveStalked(stalked);
            }
            else {
                ObjectMapper mapper = new ObjectMapper();
                stalked = Stream.of(mapper.readValue(new FileInputStream(file), StalkedUser[].class)).collect(Collectors.toList());

            }

        } catch (IOException e) { e.printStackTrace(); }

        return stalked;

    }

    public void saveStalked(List<StalkedUser> stalked) {

        try {

            File file = new File(System.getProperty("user.home") + "\\" + STALKED_JSON);

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            mapper.writeValue(new FileOutputStream(file), stalked.toArray());

        } catch (IOException e) { e.printStackTrace(); }

    }

}
