package africa.semicolon.sendAm.services;

import africa.semicolon.sendAm.data.models.Status;
import africa.semicolon.sendAm.dtos.requests.AddPackageRequest;
import africa.semicolon.sendAm.dtos.responses.AddPackageResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackageServiceImplTest {
    private PackageService packageService;
    @BeforeEach void setup(){packageService = new PackageServiceImpl();}

    @Test void afterAdd_packageRepositoryContainsOneElement(){
        AddPackageRequest addForm = new AddPackageRequest();
        addForm.setEmailAddress("ola@gmail.com");
        addForm.setProductName("Baileys");
        addForm.setQuantity(10);
        packageService.add(addForm);
        assertEquals(1,packageService.getRepository().count());

    }

    @Test void afterAdd_CorrectProductIsReturned() {
        AddPackageRequest addForm = new AddPackageRequest();
        addForm.setEmailAddress("ola@gmail.com");
        addForm.setProductName("Baileys");
        addForm.setQuantity(10);

        AddPackageResponse addResponse = packageService.add(addForm);
        assertEquals(1,addResponse.getId());
        assertEquals("baileys",addResponse.getDescription().getName());
        assertEquals("ola@gmail.com",addResponse.getEmailAddress());
    }
}