package ru.rd.mantis.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@XStreamAlias("user")
@Entity
@Table(name="mantis_user_table")
public class UserData {
    @XStreamOmitField
    @Id
    @Column(name="id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column (name="username")
    private String username;

    @Expose
    @Column (name="email")
    private String email;

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

}
