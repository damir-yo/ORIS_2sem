package com.itis.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.itis.database.DbConnector;
import com.itis.entity.UserEntity;
import java.sql.*;

@Repository
public class UserRepository {
    private final DbConnector connector;

    @Autowired
    public UserRepository(DbConnector connector) {
        this.connector = connector;
    }

    public void add(String name) throws SQLException {
        try (Connection con = connector.createConnection();
             PreparedStatement ps = con.prepareStatement("insert into users (name) values (?)")) {
            ps.setString(1, name);
            ps.executeUpdate();
        }
    }

    public void remove(Long id) throws SQLException {
        try (Connection con = connector.createConnection();
             PreparedStatement ps = con.prepareStatement("delete from users where id = ?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    public void modify(Long id, String newName) throws SQLException {
        try (Connection con = connector.createConnection();
             PreparedStatement ps = con.prepareStatement("update users set name = ? where id = ?")) {
            ps.setString(1, newName);
            ps.setLong(2, id);
            ps.executeUpdate();
        }
    }

    public UserEntity getOne(String name) throws SQLException {
        try (Connection con = connector.createConnection();
             PreparedStatement ps = con.prepareStatement("select * from users where name = ?")) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return new UserEntity(rs.getLong("id"), rs.getString("name"));
        }
        return null;
    }
}
