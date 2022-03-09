package africa.semicolon.sendAm.controllers;

import africa.semicolon.sendAm.dtos.requests.AddPackageRequest;
import africa.semicolon.sendAm.dtos.requests.UpdateTrackingInfoRequest;
import africa.semicolon.sendAm.dtos.responses.AddPackageResponse;
import africa.semicolon.sendAm.dtos.responses.TrackingPackageResponse;
import africa.semicolon.sendAm.dtos.responses.UpdateTrackingInfoResponse;
import africa.semicolon.sendAm.services.ProductService;
import africa.semicolon.sendAm.services.ProductServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/package")
public class PackageControllers {
    private ProductService services = new ProductServiceImpl();

    @PostMapping("/addPackage")
    public AddPackageResponse addNewPackage(@RequestBody AddPackageRequest myPackage) {
        return services.addPackage(myPackage);
    }

    @GetMapping("/{trackingNumber}")
    public TrackingPackageResponse trackPackage(@PathVariable int trackingNumber) {
        return services.trackPackage(trackingNumber);
    }
}