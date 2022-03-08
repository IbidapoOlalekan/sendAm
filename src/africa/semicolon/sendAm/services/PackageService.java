package africa.semicolon.sendAm.services;

import africa.semicolon.sendAm.data.repositories.PackageRepository;
import africa.semicolon.sendAm.dtos.requests.AddPackageRequest;
import africa.semicolon.sendAm.dtos.responses.AddPackageResponse;

public interface PackageService {
    AddPackageResponse add(AddPackageRequest addForm);

    PackageRepository getRepository();
}
