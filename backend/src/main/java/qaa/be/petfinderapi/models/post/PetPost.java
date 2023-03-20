package qaa.be.petfinderapi.models.post;

import lombok.Getter;
import lombok.Setter;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;


@Getter
@Setter
public class PetPost {

    String postId;

    String id;
    String petName;

    String petColor;

    int petGender;

    String coordinateLatitude;

    String coordinateLongitude;

    String area;

    String description;

    String userId;

    int petTypeId;

    int postStateId;

    String missingDate;

    String createdDate;

    public PetPost() {}

    public PetPost(String petName, String petColor, int petGender, String coordinateLatitude,
                   String coordinateLongitude, String area, String description, String userId,
                   int petTypeId, int postStateId, String missingDate) {
        this.petName = petName;
        this.petColor = petColor;
        this.petGender = petGender;
        this.coordinateLatitude = coordinateLatitude;
        this.coordinateLongitude = coordinateLongitude;
        this.area = area;
        this.description = description;
        this.userId = userId;
        this.petTypeId = petTypeId;
        this.postStateId = postStateId;
        this.missingDate = missingDate;
    }

    public static PetPost createRandomPostBody(String id) {
        return new PetPost(randomAlphabetic(7),
                randomAlphabetic(3), 1, randomNumeric(2), randomNumeric(2),
                randomAlphabetic(7), randomAlphabetic(7),
                id, 1,
                1, "2023-03-07T10:41:00.874Z"
        );
    }

    public static PetPost randomUpdatePostBody(String postId) {
        PetPost post = new PetPost();
        post.setId(postId);
        post.setPetName(randomAlphabetic(10));
        post.setCoordinateLongitude(randomNumeric(2));
        post.setCoordinateLatitude(randomNumeric(2));
        post.setArea(randomAlphabetic(7));
        post.setPetTypeId(1);
        post.setDescription(randomAlphabetic(7));

        return post;
    }
}
