package com.daniel.delivery.main.restful.foodservice;

import com.daniel.delivery.abstraction.entity.User;
import java.io.Serializable;
import java.util.Objects;

public class UserDTO implements Serializable {
    private String email;
    private String password;

    public UserDTO(){
        
    }
    
    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public UserDTO(User user){
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
    
    public User toUser(){
        return new User(this.email, this.password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.email);
        hash = 13 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserDTO other = (UserDTO) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "email=" + email + ", password=" + password + '}';
    }

    
}
