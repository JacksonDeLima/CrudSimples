package br.com.dio.dao;

import br.com.dio.model.UserModel;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private long nexid = 1L;

    private List<UserModel> models = new ArrayList<>();

    public UserModel save (final UserModel model) {
        model.setId(nexid++);
        models.add(model);
        return model;
    }

    public UserModel update(final UserModel model) {

    }

    public UserModel findById(final long id) {
        models.stream()
                .filter(u -> u.getId()== id)
                .findFirst().orElseThrow();
    }
}
