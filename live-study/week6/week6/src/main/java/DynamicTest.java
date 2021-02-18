import java.util.Arrays;
import java.util.List;

interface Post {
    void postOn(SNS sns);
}

class Text implements Post{
    @Override
    public void postOn(SNS sns) {
        sns.post(this);
    }
}

class Picture implements Post{
    @Override
    public void postOn(SNS sns) {
        sns.post(this);
    }
}

interface SNS {
    //void post(Post post);
    void post(Text text);
    void post(Picture picture);
}

class FaceBook implements SNS {

    @Override
    public void post(Text text) {
        System.out.println(text.getClass().getSimpleName() + " ->" + this.getClass().getSimpleName());
    }

    @Override
    public void post(Picture picture) {
        System.out.println(picture.getClass().getSimpleName() + " ->" + this.getClass().getSimpleName());
    }
}

class Twitter implements SNS {

    @Override
    public void post(Text text) {
        System.out.println(text.getClass().getSimpleName() + " ->" + this.getClass().getSimpleName());
    }

    @Override
    public void post(Picture picture) {
        System.out.println(picture.getClass().getSimpleName() + " ->" + this.getClass().getSimpleName());
    }
}

public class DynamicTest {
    public static void main(String[] args) {
        List<Post> posts = Arrays.asList(new Text(), new Picture());
        List<SNS> sns = Arrays.asList(new FaceBook(), new Twitter());

        posts.forEach(p-> {
            sns.forEach(p::postOn);
        });
    }
}