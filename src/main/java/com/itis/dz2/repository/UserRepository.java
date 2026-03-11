package com.itis.dz2.repository;

import com.itis.dz2.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(String name) {
        String sql = "INSERT INTO users (name) VALUES (:name)";
        jdbcTemplate.update(sql, Map.of("name", name));
    }

    public UserEntity getOne(String name) {
        String sql = "SELECT * FROM users WHERE name = :name";

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    Map.of("name", name),
                    (rs, rowNum) ->
                            new UserEntity(
                                    rs.getLong("id"),
                                    rs.getString("name")
                            )
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void modify(Long id, String newName) {
        String sql =
                "UPDATE users SET name = :name WHERE id = :id";

        jdbcTemplate.update(
                sql,
                Map.of(
                        "name", newName,
                        "id", id
                )
        );
    }

    public void remove(Long id) {
        String sql =
                "DELETE FROM users WHERE id = :id";

        jdbcTemplate.update(
                sql,
                Map.of("id", id)
        );
    }
}