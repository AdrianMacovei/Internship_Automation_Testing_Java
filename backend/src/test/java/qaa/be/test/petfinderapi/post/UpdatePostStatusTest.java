package qaa.be.test.petfinderapi.post;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qaa.be.petfinderapi.models.post.PetPost;
import qaa.be.test.petfinderapi.BasePetFinderApi;

import java.util.HashMap;

import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class UpdatePostStatusTest extends BasePetFinderApi {

    protected PetPost newRandomPost;

    protected String postId;

    @BeforeMethod
    public void createPostInDb() {
        PetPost postBody = PetPost.createRandomPostBody(userId);
        newRandomPost = restWrapper.usingPosts().createItem(postBody);
        postId = newRandomPost.getPostId();
    }

    @Test
    public void updatePostStatusId() {
        HashMap<String, Object> updateStatusBody = new HashMap<>();
        updateStatusBody.put("userId", userId);
        updateStatusBody.put("postStateId", 2);

        PetPost post = restWrapper.usingPosts().updatePostStatus(postId, updateStatusBody);

        assertEquals(restWrapper.getStatusCode(), SC_OK);
    }
}
