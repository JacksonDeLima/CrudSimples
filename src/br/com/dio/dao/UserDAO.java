package br.com.dio.dao;

import br.com.dio.exepion.UserNotFoundExeption;
import br.com.dio.model.UserModel;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private long nextid = 1L;

    private final List<UserModel> models = new ArrayList<>();

    public UserModel save (final UserModel model) {
        model.setId(nextid++);
        models.add(model);
        return model;
    }

    public UserModel update(final UserModel model) {
        var toupdate = findById(model.getId());
        models.remove(toupdate);
        models.add(model);
        return model;

    }

    public UserModel findById(final long id) {
        var messege = String.format("Não existe o usuário com id cadastrado", id);
         return models.stream()
                .filter(u -> u.getId()== id)
                .findFirst().orElseThrow(() -> new UserNotFoundExeption(messege));
    }
}
