package com.mycompany.service;


import com.mycompany.model.User;
import com.mycompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> listAll(){
        return (List<User>) repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }

    public User get(Integer id) throws UserPrincipalNotFoundException {
        Optional<User> result = repo.findById(id);
        if(result.isPresent()) {
            return result.get();
        }
        throw new UserPrincipalNotFoundException("Could not find any users with ID + id");
    }

    public void delete(Integer id) throws UserPrincipalNotFoundException {
        Long count = repo.countById(id);
        if(count==null || count ==0 ){
            throw new UserPrincipalNotFoundException("Could not find any users with ID + id");
        }
        repo.deleteById(id);
    }
}
