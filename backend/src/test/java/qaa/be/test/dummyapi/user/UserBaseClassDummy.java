package qaa.be.test.dummyapi.user;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import qaa.be.dummyapi.models.user.UsersCollection;
import qaa.be.test.dummyapi.BaseDummyApi;

@Slf4j
public abstract class UserBaseClassDummy extends BaseDummyApi {

    @BeforeClass(alwaysRun = true)
    public void addHeader() {
        log.info("Entering: " + this.getClass().toString());
        log.info("Setting in header app-id!");
        restWrapper.addRequestHeader("app-id", properties.getAPP_ID());
    }
    @AfterSuite(alwaysRun = true)
    protected void tearDown() {
        log.info("*************************Tear Down Start************************");
        UsersCollection usersCollectionRsp = restWrapper.usingUsers().usingParams(CREATED_ITEMS_PARAMS).getItems();
        usersCollectionRsp.getData().forEach(user -> restWrapper.usingUsers().deleteItem(user.getId()));
        log.info("*************************Tear Down Finish************************");
    }

}
