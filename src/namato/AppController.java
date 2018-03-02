package namato;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import namato.icndb.ICNDBRequest;
import namato.icndb.pojo.*;

import namato.uva.UVARequest;
import namato.uva.pojo.*;

import java.net.URL;
import java.util.*;

import java.time.Instant;
import java.time.ZoneId;

public class AppController implements Initializable {
    Cache<Long, String> TITLE_CACHE;

    @FXML TextField txtSniffCount;

    @FXML TableView<UserSubmission> tblSniffed;
    @FXML TableColumn<UserSubmission, String> colSniffUname;
    @FXML TableColumn<UserSubmission, String> colSniffName;
    @FXML TableColumn<UserSubmission, Long> colSniffProblemID;
    @FXML TableColumn<UserSubmission, Long> colSniffProblemTitle;
    @FXML TableColumn<UserSubmission, Long> colSniffSubID;
    @FXML TableColumn<UserSubmission, String> colSniffLang;
    @FXML TableColumn<UserSubmission, String> colSniffVerdict;
    @FXML TableColumn<UserSubmission, Date> colSniffDate;
    @FXML TableColumn<UserSubmission, Long> colSniffRank;

    @FXML Label lblID;
    @FXML Label lblName;
    @FXML Label lblRank;
    @FXML Label lblSubmissions;
    @FXML Label lblAccepted;

    @FXML TextField txtUsername;

    @FXML TableView<UserSubmission> tblSubmissions;
    @FXML TableColumn<UserSubmission, Long> colSubID;
    @FXML TableColumn<UserSubmission, Long> colProblemID;
    @FXML TableColumn<UserSubmission, Long> colProblemTitle;
    @FXML TableColumn<UserSubmission, String> colLang;
    @FXML TableColumn<UserSubmission, String> colVerdict;
    @FXML TableColumn<UserSubmission, Date> colDate;
    @FXML TableColumn<UserSubmission, Long> colRank;

    @FXML Label lblPID;
    @FXML Label lblProblemTitle;
    @FXML Label lblProblemSubCount;

    @FXML TextField txtPID;

    @FXML DatePicker dateStartSbt;
    @FXML DatePicker dateEndSbt;

    @FXML TableView<UserSubmission> tblFished;
    @FXML TableColumn<UserSubmission, String> colFishUname;
    @FXML TableColumn<UserSubmission, String> colFishName;
    @FXML TableColumn<UserSubmission, Long> colFishSubID;
    @FXML TableColumn<UserSubmission, String> colFishLang;
    @FXML TableColumn<UserSubmission, String> colFishVerdict;
    @FXML TableColumn<UserSubmission, Date> colFishDate;
    @FXML TableColumn<UserSubmission, Long> colFishRank;

    @FXML Label lblMaxJokeCount;

    @FXML TextField txtFirstName;
    @FXML TextField txtLastName;
    @FXML TextField txtJokeCount;

    @FXML ComboBox CBoxCategory;

    @FXML TableView<JokeValue> tblJokes;
    @FXML TableColumn<JokeValue, String> colJokes;

    List<StalkedUser> stalked;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TITLE_CACHE = new Cache(1000);

        CBoxCategory.getItems().add("Random");
        CBoxCategory.getSelectionModel().selectFirst();

        stalked = Local.get().loadStalked();

        colSniffUname.setCellValueFactory(new PropertyValueFactory("uname"));
        colSniffName.setCellValueFactory(new PropertyValueFactory("name"));
        colSniffSubID.setCellValueFactory(new PropertyValueFactory("sid"));
        colSniffProblemID.setCellValueFactory(new PropertyValueFactory("pid"));
        colSniffProblemTitle.setCellValueFactory(new PropertyValueFactory("title"));
        colSniffLang.setCellValueFactory(new PropertyValueFactory("lang"));
        colSniffVerdict.setCellValueFactory(new PropertyValueFactory("verdict"));
        colSniffDate.setCellValueFactory(new PropertyValueFactory("date"));
        colSniffRank.setCellValueFactory(new PropertyValueFactory("rank"));

        colSubID.setCellValueFactory(new PropertyValueFactory("sid"));
        colProblemID.setCellValueFactory(new PropertyValueFactory("pid"));
        colProblemTitle.setCellValueFactory(new PropertyValueFactory("title"));
        colLang.setCellValueFactory(new PropertyValueFactory("lang"));
        colVerdict.setCellValueFactory(new PropertyValueFactory("verdict"));
        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        colRank.setCellValueFactory(new PropertyValueFactory("rank"));

        colFishUname.setCellValueFactory(new PropertyValueFactory("uname"));
        colFishName.setCellValueFactory(new PropertyValueFactory("name"));
        colFishSubID.setCellValueFactory(new PropertyValueFactory("sid"));
        colFishLang.setCellValueFactory(new PropertyValueFactory("lang"));
        colFishVerdict.setCellValueFactory(new PropertyValueFactory("verdict"));
        colFishDate.setCellValueFactory(new PropertyValueFactory("date"));
        colFishRank.setCellValueFactory(new PropertyValueFactory("rank"));

        colJokes.setCellValueFactory(new PropertyValueFactory("joke"));

        tblReset(tblSniffed);
        tblReset(tblSubmissions);
        tblReset(tblFished);
        tblReset(tblJokes);

    }

    private void tblReset(TableView tbl) { tbl.getItems().clear(); }

    public void btnSniffPressed() {

        tblReset(tblSniffed);


        if (stalked.size() > 0) {

            UserSubmissions us;
            for (int i = 0; i < stalked.size();  ++i) {

                us = UVARequest.get().getLatestUserSubmissions(stalked.get(i).getId(), Long.parseLong(txtSniffCount.getText()));

                for (int j = 0; j < us.getSubmissionsCount(); ++j) {

                    UserSubmission sus = us.getSubmission(j);

                    if (TITLE_CACHE.getOrDefault(sus.getPid(), "").equals("")) {

                        TITLE_CACHE.put(sus.getPid(), UVARequest.get().getProblemByID(sus.getPid()).getTitle());

                    }

                    sus.setTitle(TITLE_CACHE.get(sus.getPid()));

                    tblSniffed.getItems().add(sus);

                }

            }
        }

    }

    public void btnExposePressed() {

        tblReset(tblSubmissions);

        long id = UVARequest.get().getUserID(txtUsername.getText());

        UserRanking ur = UVARequest.get().getUserRanking(id);

        lblID.setText(String.valueOf(ur.getUserid()));
        lblName.setText(ur.getName());
        lblRank.setText(String.valueOf(ur.getRank()));
        lblSubmissions.setText(String.valueOf(ur.getNos()));
        lblAccepted.setText(String.valueOf(ur.getAc()));

        UserSubmissions us = UVARequest.get().getUserSubmissions(id);

        for (int i = 0; i < us.getSubmissionsCount(); ++i) {

            UserSubmission sus = us.getSubmission(i);

            if (TITLE_CACHE.getOrDefault(sus.getPid(), "").equals("")) {

               TITLE_CACHE.put(sus.getPid(), UVARequest.get().getProblemByID(sus.getPid()).getTitle());

            }

            sus.setTitle(TITLE_CACHE.get(sus.getPid()));

            tblSubmissions.getItems().add(sus);
        }

    }

    public void btnStalkPressed() {

        UserRanking ur = UVARequest.get().getUserRanking(UVARequest.get().getUserID(txtUsername.getText()));

        StalkedUser toAdd = new StalkedUser(ur.getUserid(), txtUsername.getText(), ur.getName());

        for (int i = 0; i < stalked.size(); ++i) {

            if (stalked.get(i).getId().equals(toAdd.getId())) { return; }

        }

        stalked.add(toAdd);
        Local.get().saveStalked(stalked);

    }

    public void btnFishPressed() {

        tblReset(tblFished);

        Problem problem = UVARequest.get().getProblemByNum(Long.parseLong(txtPID.getText()));

        lblPID.setText(String.valueOf(problem.getPid()));
        lblProblemTitle.setText(problem.getTitle());

        UserSubmission[] ps = UVARequest.get().getProblemSubmissions(problem.getPid(),
                Date.from(Instant.from(dateStartSbt.getValue().atStartOfDay(ZoneId.systemDefault()))),
                Date.from(Instant.from(dateEndSbt.getValue().atStartOfDay(ZoneId.systemDefault()))));

        lblProblemSubCount.setText(String.valueOf(ps.length));

        for (int i = 0; i < ps.length; ++i) { tblFished.getItems().add(ps[i]); }

    }


    public void btnRevisePressed() {

        JokeCategories cats = ICNDBRequest.get().getCategories();

        for (int i = 0; i < cats.getCategoriesCount(); ++i) {

            CBoxCategory.getItems().add(cats.getCategory(i).substring(0, 1).toUpperCase() + cats.getCategory(i).substring(1));

        }

    }

    public void btnTicklePressed(){

        tblReset(tblJokes);

        String cat = String.valueOf(CBoxCategory.getSelectionModel().getSelectedItem());
        Long count = Long.parseLong(txtJokeCount.getText());

        Joke joke;
        if (cat.equals("random")) {

           joke = ICNDBRequest.get().getRandomJoke(txtFirstName.getText(), txtLastName.getText(), cat.substring(0, 1).toLowerCase() + cat.substring(1), count);;

        }
        else {

            joke = ICNDBRequest.get().getRandomJoke(txtFirstName.getText(), txtLastName.getText(), count);

        }

        for (int i = 0; i < joke.getValue().length; ++i) { tblJokes.getItems().add(joke.getValue()[i]); }

    }

    public void btnDiscoverPressed() {

        JokeCount countJokes = ICNDBRequest.get().getJokeCount();

        lblMaxJokeCount.setText(String.valueOf(countJokes.getValue()));
    }
}
