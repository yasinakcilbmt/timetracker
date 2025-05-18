package com.jvt.timetracker.service;

import com.jvt.timetracker.model.User;
import com.jvt.timetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {
    private final UserRepository userRepository;

    /**
     * Yeni bir kullanıcı oluşturur ve veritabanına kaydeder.
     * @param user Oluşturulacak kullanıcı nesnesi
     * @return Kaydedilen kullanıcı
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Belirtilen ID'ye sahip kullanıcıyı getirir.
     * @param id Kullanıcı ID'si
     * @return Kullanıcı nesnesi
     * @throws RuntimeException Eğer kullanıcı bulunamazsa
     */
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * Tüm kullanıcıları listeler.
     * @return Kullanıcı listesi
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Var olan bir kullanıcıyı günceller.
     * @param id Güncellenecek kullanıcının ID'si
     * @param userDetails Yeni kullanıcı bilgileri
     * @return Güncellenmiş kullanıcı
     */
    public User updateUser(Long id, User userDetails) {
        User user = getUser(id);
        user.setName(userDetails.getName());
        return userRepository.save(user);
    }

    /**
     * Belirtilen ID'ye sahip kullanıcıyı siler.
     * @param id Silinecek kullanıcının ID'si
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
} 