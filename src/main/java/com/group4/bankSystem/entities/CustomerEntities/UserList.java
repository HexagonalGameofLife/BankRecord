package com.group4.bankSystem.entities.CustomerEntities;

import jakarta.persistence.*;


@Entity
@Table(name= "UserList")
public class UserList {
    
    @EmbeddedId
    private UserListId id;

    @Column(name= "is_Primary_User", nullable = false)
    private boolean isPrimaryUser;

    public UserListId getId() {
        return id;
    }
    public boolean isPrimaryUser() {
        return isPrimaryUser;
    }
    public void setId(UserListId id) {
        this.id = id;
    }
    public void setPrimaryUser(boolean isPrimaryUser) {
        this.isPrimaryUser = isPrimaryUser;
    }

}
