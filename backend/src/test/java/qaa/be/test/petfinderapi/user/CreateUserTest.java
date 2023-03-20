package qaa.be.test.petfinderapi.user;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import qaa.be.petfinderapi.models.user.PetUser;
import qaa.be.test.petfinderapi.BasePetFinderApi;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.testng.Assert.assertEquals;

@Slf4j
public class CreateUserTest extends BasePetFinderApi {
    @Test
    public void createUser() {
        PetUser createUser = PetUser.generateRandomUser();
        PetUser responseCreate = restWrapper.usingUsers().createItem(createUser);

        log.info("Validate status code!");
        assertEquals(restWrapper.getStatusCode(), SC_CREATED);

        log.info("Validate user is created successfully!");
        assertEquals(responseCreate.getFirstName(), createUser.getFirstName());
        assertEquals(responseCreate.getLastName(), createUser.getLastName());
        assertEquals(responseCreate.getEmail(), createUser.getEmail());
        assertEquals(responseCreate.getPhoneNumber(), createUser.getPhoneNumber());
    }

    @Test
    public void createLogin() {
        PetUser createUser = PetUser.generateRandomUser();
        restWrapper.usingUsers().createItem(createUser);

        PetUser createLogin = new PetUser();
        createLogin.setEmail(createUser.getEmail());
        createLogin.setPassword(createUser.getPassword());
        PetUser responseCreate = restWrapper.usingUsers().createLogin(createLogin);

        log.info("Validate status code!");
        assertEquals(restWrapper.getStatusCode(), SC_CREATED);

        log.info("Validate login is successful!");
        assertEquals(responseCreate.getEmail(), createLogin.getEmail());
    }


}
