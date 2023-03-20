package qaa.be.test.petfinderapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import qaa.be.petfinderapi.PetFinderWrapper;
import qaa.be.petfinderapi.models.user.PetUser;
import qaa.be.util.Properties;
import qaa.be.util.TestContext;
import qaa.internship.util.PlainTextReporter;

@Slf4j
@ContextConfiguration(classes = TestContext.class)
@Listeners(PlainTextReporter.class)
public abstract class BasePetFinderApi extends AbstractTestNGSpringContextTests {

    @Autowired
    protected PetFinderWrapper restWrapper;

    @Autowired
    protected PetFinderWrapper restWrapperWithoutToken;

    @Autowired
    protected Properties properties;
    protected String userId;
    protected String token;

    @BeforeClass(alwaysRun = true)
    public void addHeader() {
        log.info("Entering: " + this.getClass().toString());
        log.info("Setting in header access token");
        loginWithRandomUser();
        restWrapper.addRequestHeader("Authorization", "Bearer " + token);
    }

    public void loginWithRandomUser() {
        PetUser createUser = PetUser.generateRandomUser();
        System.out.println(createUser.getPassword());
        restWrapper.usingUsers().createItem(createUser);
        PetUser createLogin = new PetUser();
        createLogin.setEmail(createUser.getEmail());
        createLogin.setPassword(createUser.getPassword());
        PetUser responseCreate = restWrapper.usingUsers().createLogin(createLogin);
        this.userId = responseCreate.getId();
        this.token = responseCreate.getToken();
    }
}
