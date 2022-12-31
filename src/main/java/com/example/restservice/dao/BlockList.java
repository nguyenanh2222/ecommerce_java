package com.example.restservice.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
@Table(name = "BLOCK_LIST")
public class BlockList {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 26)
    @NotNull
    @Column(name = "users_id", nullable = false, length = 26)
    private String usersId;

    @NotNull
    @Column(name = "participants_id", nullable = false)
    private Integer participantsId;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsersId() {
        return usersId;
    }

    public void setUsersId(String usersId) {
        this.usersId = usersId;
    }

    public Integer getParticipantsId() {
        return participantsId;
    }

    public void setParticipantsId(Integer participantsId) {
        this.participantsId = participantsId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

}