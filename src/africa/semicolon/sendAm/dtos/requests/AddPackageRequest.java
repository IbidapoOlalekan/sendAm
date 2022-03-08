package africa.semicolon.sendAm.dtos.requests;

import africa.semicolon.sendAm.data.models.PackageDescription;
import africa.semicolon.sendAm.data.models.Status;
import africa.semicolon.sendAm.data.models.User;

public class AddPackageRequest {
    private String emailAddress;
    private String productName;
    private int quantity;



    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName.toLowerCase();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
