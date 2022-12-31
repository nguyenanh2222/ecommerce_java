package com.example.restservice.dao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table(name = "GROUP_FOLLOWING")
public class GroupFollowing {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sourceId", nullable = false)
    private Group source;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "targetId", nullable = false)
    private Group target;

    @Column(name = "createdAt")
    private Instant createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getSource() {
        return source;
    }

    public void setSource(Group source) {
        this.source = source;
    }

    public Group getTarget() {
        return target;
    }

    public void setTarget(Group target) {
        this.target = target;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

}