import org.kohsuke.github.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Dashboard {
    public static void main(String[] args) throws IOException {
        String my_personal_token = "sample";
        GitHub gitHub = new GitHubBuilder().withOAuthToken(my_personal_token).build();

        GHRepository ghRepository = gitHub.getRepository("highright96/live-study").getSource();
        List<GHIssue> ghIssues = ghRepository.getIssues(GHIssueState.CLOSED); //모든 이슈 리스트
        List<GHIssueComment> ghIssueComments; //각 이슈의 코멘트 리스트
        HashMap<String, Integer> users = new HashMap<>(); //유저 리스트

        for(GHIssue issue : ghIssues){
            ghIssueComments = issue.getComments();
            String name = "";
            for(GHIssueComment comment : ghIssueComments){
                name = comment.getUser().getLogin();
                if(name != null && users.containsKey(name)){
                    users.put(name, users.get(name) + 1);
                }
                else if(name != null && !users.containsKey(name)){
                    users.put(name, 1);
                }
            }
        }

        System.out.println("-------참여율-------");
        users.forEach((key,value)-> {
            float countPercent = (float)(value * 100) / 18;
            System.out.print(key);
            System.out.println(" -> " + String.format("%.2f", countPercent) + "%");
        });
    }
}
