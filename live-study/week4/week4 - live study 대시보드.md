# 4주차 - live-study 대시보드
본 글은 [백기선님의 live-study](https://github.com/whiteship/live-study/issues)를 진행하며 작성한 자료입니다. 자료에서 사용한 **예제 코드**는 직접 만들었습니다.  
**[Github 자바 라이브러리](https://github-api.kohsuke.org/)를 참고하였습니다.**

<br/>

## 목표
* 깃헙 이슈 1번부터 18번까지 댓글을 순회하며 댓글을 남긴 사용자를 체크 할 것.
* 참여율을 계산하세요. 총 18회에 중에 몇 %를 참여했는지 소숫점 두자리가지 보여줄 것.
* Github 자바 라이브러리를 사용하면 편리합니다.
* 깃헙 API를 익명으로 호출하는데 제한이 있기 때문에 본인의 깃헙 프로젝트에 이슈를 만들고 테스트를 하시면 더 자주 테스트할 수 있습니다.

<br/>

### 코드

```java
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
```

### 결과화면

```
-------참여율-------
kimzerovirus -> 5.56%
KwangJongJeon -> 11.11%
sojintjdals -> 22.22%
jessi68 -> 11.11%
HyangKeunChoi -> 38.89%
phantom08266 -> 5.56%
twowinsh87 -> 22.22%
ohhhmycode -> 33.33%
ufonetcom -> 33.33%
sigriswil -> 16.67%
ssonsh -> 38.89%
dsunni -> 5.56%
catsbi -> 22.22%
dongsub-joung -> 11.11%
rockintuna -> 22.22%
eomgr55 -> 5.56%
Azderica -> 27.78%
jymaeng95 -> 38.89%
ggomjae -> 5.56%
fpdjsns -> 33.33%
binghe819 -> 16.67%
seovalue -> 16.67%
kys4548 -> 27.78%
Wordbe -> 5.56%
boraborason -> 33.33%
...
```

### Github 주소

<br/>

## 참고
* https://github-api.kohsuke.org