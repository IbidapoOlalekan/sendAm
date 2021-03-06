package africa.semicolon.sendAm.services;

import africa.semicolon.sendAm.data.models.Package;
import africa.semicolon.sendAm.data.models.PackageDescription;
import africa.semicolon.sendAm.data.models.Status;
import africa.semicolon.sendAm.data.repositories.PackageRepository;
import africa.semicolon.sendAm.data.repositories.PackageRepositoryImpl;
import africa.semicolon.sendAm.dtos.requests.AddPackageRequest;
import africa.semicolon.sendAm.dtos.requests.UpdateTrackingInfoRequest;
import africa.semicolon.sendAm.dtos.responses.AddPackageResponse;
import africa.semicolon.sendAm.dtos.responses.TrackingInfo;
import africa.semicolon.sendAm.dtos.responses.TrackingPackageResponse;
import africa.semicolon.sendAm.dtos.responses.UpdateTrackingInfoResponse;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProductServiceImpl implements ProductService{
    private PackageRepository packageRepository = new PackageRepositoryImpl();
    private int availableId = 0;

    @Override
    public AddPackageResponse addPackage(AddPackageRequest myPackage) {
        Package packageToBeAdded = new Package();
        PackageDescription description = new PackageDescription();
        description.setName(myPackage.getName());
        description.setWeightInGrammes(myPackage.getWeightInGrammes());

        packageToBeAdded.setDescription(description);

        Status status = new Status();
        status.setStatus("Packed");
        packageToBeAdded.getStatusList().add(status);
        packageToBeAdded.setId(generateId());

        Package savedPackage = packageRepository.save(packageToBeAdded);

        AddPackageResponse packageResponse = new AddPackageResponse();
        packageResponse.setId(savedPackage.getId());
        packageResponse.setName(savedPackage.getDescription().getName());
        packageResponse.setWeightInGrammes(savedPackage.getDescription().getWeightInGrammes());
//        packageResponse.setStatus(savedPackage.getDescription());
        return packageResponse;
    }

    private int generateId() {
        availableId = availableId + 1;
        return availableId;
    }


    @Override
    public PackageRepository getRepository() {
        return packageRepository;
    }



    @Override
    public UpdateTrackingInfoResponse updateTrackingInfo(UpdateTrackingInfoRequest trackingRequest) {
        //get package from repo
        Package foundPackage = packageRepository.findById(trackingRequest.getTrackingNumber());


//        create new status
        Status status = new Status();
        status.setStatus(trackingRequest.getTrackingInfo());
//        add new status
        foundPackage.getStatusList().add(status);
//        save package
        packageRepository.save(foundPackage);
        return null;
    }

    @Override
    public TrackingPackageResponse trackPackage(int trackingNumber) {
// find package
        Package savedPackage = packageRepository.findById(trackingNumber);
//        get list of status
        List<Status> statusList = savedPackage.getStatusList();
//        create response
        TrackingPackageResponse response = new TrackingPackageResponse();
//        add list of status
        for (Status status : statusList) {
            TrackingInfo info = new TrackingInfo();
            info.setInformation(status.getStatus());
            info.getDateTime(DateTimeFormatter.ofPattern("E yyyy-MM-dd a").format(status.getDateTime()));
            response.getTrackingInfo().add(info);
        }
//  return response
        return response;
    }
}
