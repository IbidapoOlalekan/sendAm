package africa.semicolon.sendAm.data.repositories;

import africa.semicolon.sendAm.data.models.Package;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PackageRepositoryImplTest {
    private PackageRepository packageRepository;

    @BeforeEach
    void setup() {
        packageRepository = new PackageRepositoryImpl();
    }

    @Test
    void repositorySaveTest() {
        Package aPackage = new Package();
        Package savedPackage = packageRepository.save(aPackage);
        assertEquals(1, savedPackage.getId());
        assertEquals(1, packageRepository.count());
    }

    @Test
    void repositoryFindByIdTest() {
        Package firstPackage = new Package();
        Package secondPackage = new Package();
        Package thirdPackage = new Package();

        packageRepository.save(firstPackage);
        packageRepository.save(secondPackage);
        packageRepository.save(thirdPackage);

        Package foundPackage = packageRepository.findById(2);

        assertEquals(secondPackage, foundPackage);
        assertEquals(2, foundPackage.getId());
    }

    @Test
    void deleteByIdTest() {
        saveThreePackages();
        packageRepository.delete(2);
        assertEquals(2, packageRepository.count());
    }

    private void saveThreePackages() {
        Package firstPackage = new Package();
        Package secondPackage = new Package();
        Package thirdPackage = new Package();
        packageRepository.save(firstPackage);
        packageRepository.save(secondPackage);
        packageRepository.save(thirdPackage);
    }

    @Test
    void findIdWorks_afterADeleteTest() {
        saveThreePackages();
        packageRepository.delete(2);

        Package foundPackage = packageRepository.findById(2);
        assertNull(foundPackage);
    }

    @Test
    void saveAfterADelete_givesCorrectPackageIdTest(){
        saveThreePackages();
        packageRepository.delete(1);
        Package savedPackage = packageRepository.save(new Package());
        assertEquals(4, savedPackage.getId());
    }

    @Test
    void deleteByPackageTest() {
        Package firstPackage = new Package();
        Package secondPackage = new Package();
        Package thirdPackage = new Package();
        packageRepository.save(firstPackage);
        packageRepository.save(secondPackage);
        packageRepository.save(thirdPackage);

        packageRepository.delete(secondPackage);
        assertEquals(2, packageRepository.count());
        assertNull(packageRepository.findById(2));
    }

    @Test
    void findAllTest(){
        saveThreePackages();

        List<Package> all = packageRepository.findAll();
        assertEquals(3,all.size());
    }

}