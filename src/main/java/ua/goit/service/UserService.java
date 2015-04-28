package ua.goit.service;

import ua.goit.model.User;

public interface UserService extends GenericService<User> {
    User getByLogin(String login);
}
