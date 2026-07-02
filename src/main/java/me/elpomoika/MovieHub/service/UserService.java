package me.elpomoika.MovieHub.service;

import me.elpomoika.MovieHub.dto.authentication.UserDto;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
public interface UserService  {
    UserDto getUserByEmail(String email) throws ChangeSetPersister.NotFoundException;
    String addUser(UserDto user);
}
