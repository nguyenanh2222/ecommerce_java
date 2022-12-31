package com.example.restservice.dao;

import com.example.restservice.user.entity.UserEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USER_FOLOWING")
public class UserFolowing {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sourceId", nullable = false)
    private UserEntity source;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "targetId", nullable = false)
    private UserEntity target;

    @Size(max = 45)
    @Column(name = "createdAt", length = 45)
    private String createdAt;

    @Size(max = 100)
    @Column(name = "status", length = 100)
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getSource() {
        return source;
    }

    public void setSource(UserEntity source) {
        this.source = source;
    }

    public UserEntity getTarget() {
        return target;
    }

    public void setTarget(UserEntity target) {
        this.target = target;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}