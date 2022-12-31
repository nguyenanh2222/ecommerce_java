package com.example.restservice.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
@Table(name = "ATTACHEMENT")
public class Attachement {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "messagesUserId")
    private Long messagesUserId;

    @Size(max = 100)
    @Column(name = "thumbUrl", length = 100)
    private String thumbUrl;

    @Size(max = 100)
    @Column(name = "fileUrl", length = 100)
    private String fileUrl;

    @Column(name = "createdAt")
    private Instant createdAt;

    @Column(name = "updatedAt")
    private Instant updatedAt;

    @Column(name = "messengesGroupId")
    private Integer messengesGroupId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getMessagesUserId() {
        return messagesUserId;
    }

    public void setMessagesUserId(Long messagesUserId) {
        this.messagesUserId = messagesUserId;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getMessengesGroupId() {
        return messengesGroupId;
    }

    public void setMessengesGroupId(Integer messengesGroupId) {
        this.messengesGroupId = messengesGroupId;
    }

}