package br.com.dio.dao;

import br.com.dio.exception.EmptyStorageExeption;
import br.com.dio.exception.UserNotFoundExeption;
import br.com.dio.model.UserModel;

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
        var toUpdate = findById(model.getId());
        models.remove(toUpdate);
        models.add(model);
        return model;
    }

    public void delete(final long id){
        var toDelete = findById(id);
        models.remove(toDelete);
    }

    public UserModel findById(final long id) {
        verifyStorage();
        var messege = String.format("Não existe o usuário com id cadastrado", id);
         return models.stream()
                .filter(u -> u.getId()== id)
                .findFirst()
                 .orElseThrow(() -> new UserNotFoundExeption(messege));
    }

    public List<UserModel> findAll(){
        List<UserModel> result;
        try {
            verifyStorage();
            result = models;
        } catch (EmptyStorageExeption ex){
            ex.printStackTrace();
            result = new ArrayList<>();
        }
        return result;
    }

    private void verifyStorage(){
        if (models.isEmpty()) throw new EmptyStorageExeption("O armazenamento está vazio");

    }
}
