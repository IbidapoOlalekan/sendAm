package africa.semicolon.sendAm.services;

import africa.semicolon.sendAm.data.repositories.PackageRepository;
import africa.semicolon.sendAm.dtos.requests.AddPackageRequest;
import africa.semicolon.sendAm.dtos.requests.UpdateTrackingInfoRequest;
import africa.semicolon.sendAm.dtos.responses.AddPackageResponse;
import africa.semicolon.sendAm.dtos.responses.TrackingPackageResponse;
import africa.semicolon.sendAm.dtos.responses.UpdateTrackingInfoResponse;

public interface ProductService {
    AddPackageResponse addPackage(AddPackageRequest myPackage);

    PackageRepository getRepository();

    TrackingPackageResponse trackPackage(int trackingNumber);

    UpdateTrackingInfoResponse updateTrackingInfo(UpdateTrackingInfoRequest updateTrackingInfoRequest);


}
