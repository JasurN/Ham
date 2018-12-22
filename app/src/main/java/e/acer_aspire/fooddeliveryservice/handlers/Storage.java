package e.acer_aspire.fooddeliveryservice.handlers;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class Storage {
    private StorageReference storageRef;

    Storage() {
        storageRef = FirebaseStorage.getInstance().getReference();
    }

    public void uploadImage() {

    }
}
