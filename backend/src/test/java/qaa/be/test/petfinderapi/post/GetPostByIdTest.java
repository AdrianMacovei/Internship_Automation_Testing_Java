package qaa.be.test.petfinderapi.post;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import qaa.be.petfinderapi.models.post.PetPost;
import qaa.be.test.petfinderapi.BasePetFinderApi;

import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class GetPostByIdTest extends BasePetFinderApi {

    protected PetPost newRandomPost;

    protected String postId;

    @BeforeClass
    public void createPostInDb() {
        PetPost postBody = PetPost.createRandomPostBody(userId);
        newRandomPost = restWrapper.usingPosts().createItem(postBody);
        postId = newRandomPost.getPostId();
    }

    @Test
    public void testGetPostById() {
        PetPost post = restWrapper.usingPosts().getItem(postId);

        assertEquals(restWrapper.getStatusCode(), SC_OK);
        assertEquals(newRandomPost.getPostId(), post.getPostId());
    }
}
