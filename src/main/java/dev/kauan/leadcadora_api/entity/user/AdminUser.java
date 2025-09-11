package dev.kauan.leadcadora_api.entity.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class AdminUser extends User {

    protected AdminUser() {
        super();
    }

    public AdminUser(String name, String password, String token) {
        super(name, password, true, token);
    }
}

