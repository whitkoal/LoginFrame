package cn.bruce.pojo;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class User implements Serializable {

    @Id
    private String _id;
    private String name;
    private String password;
    private String store;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", store='" + store + '\'' +
                '}';
    }
}
