package api.test;

import api.payload.Category;
import api.payload.Pet;
import api.payload.User;
import api.payload.Tag;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BaseTest {
    protected Faker faker;
    protected Logger logger;
    protected User userPayload;
    protected Pet petPayload;

    @BeforeClass
    public void setup() {
        faker = new Faker();
        logger = Logger.getLogger(this.getClass().getName());

        // User Payload Setup
        userPayload = new User();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        // Pet Payload Setup
        petPayload = new Pet();
        Category category = new Category();
        category.setId(faker.number().randomNumber());
        category.setName(faker.animal().name());

        petPayload.setId(faker.number().randomNumber());
        petPayload.setCategory(category);
        petPayload.setName(faker.animal().name());
        petPayload.setPhotoUrls(new ArrayList<String>() {{
            add(faker.internet().url());
        }});
        List<Tag> tags = new ArrayList<>();
        Tag tag = new Tag();
        tag.setId(faker.number().randomNumber());
        tag.setName(faker.color().name());
        tags.add(tag);
        petPayload.setTags(tags);
        petPayload.setStatus("available");
    }
}
