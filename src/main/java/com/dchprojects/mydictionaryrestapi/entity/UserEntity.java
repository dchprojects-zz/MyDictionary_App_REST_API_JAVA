package com.dchprojects.mydictionaryrestapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {

    @JsonProperty("user_id")
    @Column(name = "user_id", unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @JsonProperty("nickname")
    @Column(name = "nickname", nullable = false)
    private String nickname;

    @JsonProperty("created_at")
    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @JsonProperty("updated_at")
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

    public UserEntity() {

    }

    public UserEntity(Long userId,
                      String nickname,
                      Timestamp createdAt,
                      Timestamp updatedAt) {

        this.userId = userId;
        this.nickname = nickname;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

}
