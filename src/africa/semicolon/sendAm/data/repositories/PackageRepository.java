package africa.semicolon.sendAm.data.repositories;

import africa.semicolon.sendAm.data.models.Package;

import java.util.List;

public interface PackageRepository {
    Package save(Package aPackage);
    Package findById(int id);
    List<Package> findAll();
    void delete(Package aPackage);
    void delete(int id);

    int count();
}
