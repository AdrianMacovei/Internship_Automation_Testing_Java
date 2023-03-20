package qaa.be.petfinderapi.requests;

import org.springframework.http.HttpMethod;
import qaa.be.core.RestRequest;
import qaa.be.core.exceptions.ConversionJsonToModelException;
import qaa.be.core.requests.ModelRequest;
import qaa.be.petfinderapi.PetFinderWrapper;
import qaa.be.petfinderapi.models.post.PetPost;

public class PostsRequests extends ModelRequest<PostsRequests, PetFinderWrapper>
        implements APIContractPet <PetPost> {

    private static final String POST_PATH = "/posts/v1";

    public PostsRequests(PetFinderWrapper restWrapper) {
        super(restWrapper);
    }

    @Override
    public PetPost createItem(PetPost item) throws ConversionJsonToModelException {
        RestRequest request = RestRequest.requestWithBody(HttpMethod.POST, item, POST_PATH);
        return restWrapper.processModel(PetPost.class, request);
    }

    @Override
    public PetPost createItemWithoutBody() throws ConversionJsonToModelException {
        RestRequest request = RestRequest.simpleRequest(HttpMethod.POST, POST_PATH);
        return restWrapper.processModel(PetPost.class, request);
    }

    public PetPost[] getItems(String itemId) throws ConversionJsonToModelException {
        RestRequest request = RestRequest.simpleRequest(HttpMethod.GET, POST_PATH + "/user/{parameters}",
                itemId);
        return restWrapper.sendRequest(request).as(PetPost[].class);
    }

    public PetPost getItem(String itemId) throws ConversionJsonToModelException {
        RestRequest request = RestRequest.simpleRequest(HttpMethod.GET, POST_PATH + "/{parameters}",
                itemId);
        return restWrapper.processModel(PetPost.class, request);
    }

    public PetPost updateItem(String itemId, PetPost updatedItem) throws ConversionJsonToModelException {
        RestRequest request = RestRequest.requestWithBody(HttpMethod.PUT, updatedItem, POST_PATH + "/{params}",
                itemId);
        return restWrapper.processModel(PetPost.class, request);
    }

    public PetPost updatePostStatus (String postId, Object updatePostStatus) throws ConversionJsonToModelException {
        RestRequest request = RestRequest.requestWithBody(HttpMethod.PUT, updatePostStatus, POST_PATH + "/{params}/status",
                postId);
        return restWrapper.processModel(PetPost.class, request);
    }
}
