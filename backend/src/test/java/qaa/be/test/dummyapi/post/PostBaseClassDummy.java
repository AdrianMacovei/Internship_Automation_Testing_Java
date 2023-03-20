package qaa.be.test.dummyapi.post;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import qaa.be.dummyapi.models.post.PostsCollection;
import qaa.be.dummyapi.models.user.UserModel;
import qaa.be.test.dummyapi.BaseDummyApi;

@Slf4j
public abstract class PostBaseClassDummy extends BaseDummyApi {

    protected UserModel user;

    @BeforeClass(alwaysRun = true)
    protected void createUser() {
        restWrapper.addRequestHeader("app-id", "640a0b861f8d5a27ab95c093");
        log.info("New user is created.");
        user = restWrapper.usingUsers().createItem(UserModel.generateRandomUser());
    }

    @AfterSuite(alwaysRun = true)
    protected void deletePostsInCurrentEnvironment() {
        log.info("Created posts are deleted.");
        PostsCollection postsCollection = restWrapper.usingPosts().usingParams(CREATED_ITEMS_PARAMS).getItems();
        postsCollection.getData().stream().forEach(x -> restWrapper.usingPosts().deleteItem(x.getId()));
    }

    @AfterSuite(alwaysRun = true)
    protected void deleteUser() {
        log.info("Newly created user is deleted.");
        restWrapper.usingUsers().deleteItem(user.getId());
    }
}
