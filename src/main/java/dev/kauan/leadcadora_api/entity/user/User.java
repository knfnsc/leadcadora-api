package dev.kauan.leadcadora_api.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@EntityListeners(AuditingEntityListener.class)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    @Column(updatable = false)
    private Boolean isAdmin;

    @CreatedDate
    @NotNull
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @NotNull
    @Column(unique = true, updatable = false)
    private String token;

    protected User() {
    }

    protected User(String name, String password, Boolean isAdmin, String token) {
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getToken() {
        return token;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PublicUserDto toPublicDto() {
        return new PublicUserDto(id, name, isAdmin, createdAt);
    }
}
