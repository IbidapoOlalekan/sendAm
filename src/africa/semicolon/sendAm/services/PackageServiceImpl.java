package africa.semicolon.sendAm.services;

import africa.semicolon.sendAm.data.models.Package;
import africa.semicolon.sendAm.data.models.PackageDescription;
import africa.semicolon.sendAm.data.models.Status;
import africa.semicolon.sendAm.data.models.User;
import africa.semicolon.sendAm.data.repositories.PackageRepository;
import africa.semicolon.sendAm.data.repositories.PackageRepositoryImpl;
import africa.semicolon.sendAm.dtos.requests.AddPackageRequest;
import africa.semicolon.sendAm.dtos.responses.AddPackageResponse;

import java.time.LocalDateTime;
import java.util.Locale;

public class PackageServiceImpl implements PackageService{
    private PackageRepository packageRepository = new PackageRepositoryImpl();
    private int id = 0;
    @Override
    public AddPackageResponse add(AddPackageRequest addForm) {
        Package order = new Package();
        PackageDescription order1 = new PackageDescription();
        Status packageStatus = new Status();
        User user= new User();
        order1.setName(addForm.getProductName());
        order1.setWeightInGrammes(addForm.getQuantity());
        order.setDescription(order1);
        packageStatus.setStatus("Pending");
        LocalDateTime dateTime = packageStatus.getDateTime();

        user.setEmail(addForm.getEmailAddress().toLowerCase());
        order.setOwner(user);
        order.setId(generateId());

        return getRegisterPackageResponse(order);
    }

    private AddPackageResponse getRegisterPackageResponse(Package order) {
        Package savedPackage = packageRepository.save(order);
        AddPackageResponse packageOrder = new AddPackageResponse();
        packageOrder.setEmailAddress(savedPackage.getOwner().getEmail());
        packageOrder.setId(savedPackage.getId());
        packageOrder.setDescription(savedPackage.getDescription());

        return packageOrder;

    }

    private int generateId() {
        id = id + 1;
        return id;
    }

    @Override
    public PackageRepository getRepository() {
        return packageRepository;
    }
}
