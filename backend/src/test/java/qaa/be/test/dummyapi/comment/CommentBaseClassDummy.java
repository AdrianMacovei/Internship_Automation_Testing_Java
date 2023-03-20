package qaa.be.test.dummyapi.comment;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import qaa.be.dummyapi.models.comment.CommentsCollection;
import qaa.be.dummyapi.models.post.PostGET;
import qaa.be.dummyapi.models.post.PostPOST;
import qaa.be.dummyapi.models.user.UserModel;
import qaa.be.test.dummyapi.BaseDummyApi;

@Slf4j
public class CommentBaseClassDummy extends BaseDummyApi {

    protected UserModel user;
    protected PostGET post;

    @BeforeClass(alwaysRun = true)
    protected void createPost() {
        restWrapper.addRequestHeader("app-id", "640a13031f8d5a92c295c0a3");
        log.info("New user is created.");
        user = restWrapper.usingUsers().createItem(UserModel.generateRandomUser());

        log.info("New post is created.");
        PostPOST createPost = PostPOST.generateRandomPost();
        createPost.setOwnerId(user.getId());
        post = restWrapper.usingPosts().createItem(createPost);
    }

    @AfterSuite(alwaysRun = true)
    protected void deleteCommentsInCurrentEnvironment() {
        log.info("Created comments are deleted.");
        CommentsCollection commentsCollection = restWrapper.usingComments().usingParams(CREATED_ITEMS_PARAMS).getItems();
        commentsCollection.getData().stream().forEach(x -> restWrapper.usingComments().deleteItem(x.getId()));
    }

    @AfterSuite(alwaysRun = true)
    protected void deletePost() {

        log.info("Newly created post is deleted.");
        restWrapper.usingPosts().deleteItem(post.getId());

        log.info("Newly created user is deleted.");
        restWrapper.usingUsers().deleteItem(user.getId());
    }
}
