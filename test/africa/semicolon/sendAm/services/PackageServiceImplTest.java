package africa.semicolon.sendAm.services;

import africa.semicolon.sendAm.dtos.requests.AddPackageRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackageServiceImplTest {
    private PackageService packageService;
    @BeforeEach void setup(){packageService = new PackageServiceImpl();}

    @Test void afterAdd_packageRepositoryContainsOneElement(){
        AddPackageRequest addForm = new AddPackageRequest();

        packageService.add(addForm);
    }
}