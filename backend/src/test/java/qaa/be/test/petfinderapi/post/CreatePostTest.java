package qaa.be.test.petfinderapi.post;

import org.testng.annotations.Test;
import qaa.be.petfinderapi.models.post.PetPost;
import qaa.be.test.petfinderapi.BasePetFinderApi;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class CreatePostTest extends BasePetFinderApi {
    @Test
    public void testCreatePost() {
        PetPost postBody = PetPost.createRandomPostBody(userId);
        PetPost newPost = restWrapper.usingPosts().createItem(postBody);

        assertEquals(restWrapper.getStatusCode(), SC_OK);
        assertEquals(newPost.getPetName(), postBody.getPetName());
        assertEquals(newPost.getPetColor(), postBody.getPetColor());
        assertEquals(newPost.getDescription(), postBody.getDescription());
        assertEquals(newPost.getCoordinateLongitude(), postBody.getCoordinateLongitude());
        assertEquals(newPost.getCoordinateLatitude(), postBody.getCoordinateLatitude());
    }
}
