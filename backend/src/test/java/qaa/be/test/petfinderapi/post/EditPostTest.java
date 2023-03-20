package qaa.be.test.petfinderapi.post;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import qaa.be.petfinderapi.models.post.PetPost;
import qaa.be.test.petfinderapi.BasePetFinderApi;

import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class EditPostTest extends BasePetFinderApi {

    protected PetPost newRandomPost;

    protected String postId;

    @BeforeClass
    public void createPostInDb() {
        PetPost postBody = PetPost.createRandomPostBody(userId);
        newRandomPost = restWrapper.usingPosts().createItem(postBody);
        postId = newRandomPost.getPostId();
    }

    @Test
    public void testEditPost() {
        PetPost postUpdateBody = PetPost.randomUpdatePostBody(postId);
        PetPost updatedPost = restWrapper.usingPosts().updateItem(postId, postUpdateBody);

        assertEquals(restWrapper.getStatusCode(), SC_OK);
        assertEquals(updatedPost.getPostId(), postId);
        assertEquals(updatedPost.getPetName(), postUpdateBody.getPetName());
        assertEquals(updatedPost.getCoordinateLatitude(), postUpdateBody.getCoordinateLatitude());
        assertEquals(updatedPost.getCoordinateLongitude(), postUpdateBody.getCoordinateLongitude());
        assertEquals(updatedPost.getCoordinateLongitude(), postUpdateBody.getCoordinateLongitude());
        assertEquals(updatedPost.getArea(), postUpdateBody.getArea());
        assertEquals(updatedPost.getPetTypeId(), postUpdateBody.getPetTypeId());
        assertEquals(updatedPost.getDescription(), postUpdateBody.getDescription());

    }
}
