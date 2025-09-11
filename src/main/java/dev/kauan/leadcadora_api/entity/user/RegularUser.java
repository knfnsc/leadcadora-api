package dev.kauan.leadcadora_api.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("REGULAR")
public class RegularUser extends User {

    @Column(name = "favorite_id")
    private Long favoriteId;

    protected RegularUser() {
        super();
    }

    public RegularUser(String name, String password, String token) {
        super(name, password, false, token);
    }

    public RegularUser(String name, String password, String token, Long favoriteId) {
        super(name, password, false, token);
        this.favoriteId = favoriteId;
    }

    public Long getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Long favoriteId) {
        this.favoriteId = favoriteId;
    }
}
