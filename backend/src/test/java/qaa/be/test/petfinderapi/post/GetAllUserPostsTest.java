package qaa.be.test.petfinderapi.post;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qaa.be.petfinderapi.models.post.PetPost;
import qaa.be.test.petfinderapi.BasePetFinderApi;

import java.util.Random;

import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

@Slf4j
public class GetAllUserPostsTest extends BasePetFinderApi {
    int numbersOfPosts;

    Random random = new Random();

    @BeforeMethod(alwaysRun = true)
    public void createPostsOnCurrentUser() {
        numbersOfPosts = random.nextInt(4);
        for(int i = 1; i<=numbersOfPosts; i++) {
            PetPost postBody = PetPost.createRandomPostBody(userId);
            restWrapper.usingPosts().createItem(postBody);
        }
    }

    @Test
    public void checkAllUserPosts() {
        PetPost[] postList = restWrapper.usingPosts().getItems(userId);

        assertEquals(restWrapper.getStatusCode(), SC_OK);
        assertEquals(postList.length, numbersOfPosts);
    }
}
