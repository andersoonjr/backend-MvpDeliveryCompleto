package mvp_delivery_system.services;

import mvp_delivery_system.entites.User;
import mvp_delivery_system.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();

    }

    public User findById(Long id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.get();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id,User updatedUser) {
        User objUser = findById(id);
        objUser.setName(updatedUser.getName());
        objUser.setAddress(updatedUser.getAddress());
        objUser.setPhone(updatedUser.getPhone());
        return userRepository.save(objUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
