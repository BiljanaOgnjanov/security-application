package com.example.saecurityapp.User.service;

import com.example.saecurityapp.Utils.ObjectsMapper;
import com.example.saecurityapp.exception.EmailExistException;
import com.example.saecurityapp.exception.InvalidDataFormatException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.example.saecurityapp.User.repository.*;
import com.example.saecurityapp.Role.*;
import com.example.saecurityapp.confirmationToken.*;
import com.example.saecurityapp.User.model.*;
import com.example.saecurityapp.User.dto.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final HostRepository hostRepository;
    private final GuestRepository guestRepository;
    private final RoleRepository roleRepository;
    private final ConfirmationTokenService confirmationTokenService;
    public User getUserBy(String email) throws UsernameNotFoundException {
        Host host = hostRepository.findByEmail(email).orElse(null);
        if (host != null) {
            return host;
        }

        Guest guest = guestRepository.findByEmail(email).orElse(null);
        if (guest != null) {
            return guest;
        }
        throw new UsernameNotFoundException("user not found in the database");
    }

    public void addRoleToGuest(String email, String roleName) {
        User user = guestRepository.findByEmail(email).get();
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    public void addRoleToHost(String email, String roleName) {
        User user = hostRepository.findByEmail(email).get();
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    public void registerUser(RegisterBodyDTO registerBodyDTO) throws EmailExistException, InvalidDataFormatException {
        if (isAnyFieldEmpty(registerBodyDTO))
            throw new InvalidDataFormatException();
        if(registerBodyDTO.getRole().equals("Guest")) {
            if(guestRepository.findByEmail(registerBodyDTO.getEmail()).isPresent())
                throw new EmailExistException();
            if(hostRepository.findByEmail(registerBodyDTO.getEmail()).isPresent())
                throw new EmailExistException();
            Role role = roleRepository.findByName("ROLE_GUEST");
            if (role == null) {
                role = new Role(0l, "ROLE_GUEST");
                roleRepository.save(role);
            }
            Guest guest = ObjectsMapper.convertRegisterDTOToGuest(registerBodyDTO);
            guest.setPassword(passwordEncoder.encode(guest.getPassword()));
            guestRepository.save(guest);
            addRoleToGuest(guest.getEmail(), role.getName());
            String token = UUID.randomUUID().toString();
            ConfirmationToken confirmationToken = new ConfirmationToken(
                    token,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(30),
                    guest
            );
            confirmationTokenService.saveConfirmationToken(confirmationToken);
        } else {
            if(hostRepository.findByEmail(registerBodyDTO.getEmail()).isPresent())
                throw new EmailExistException();
            if(guestRepository.findByEmail(registerBodyDTO.getEmail()).isPresent())
                throw new EmailExistException();
            Role role = roleRepository.findByName("ROLE_HOST");
            if (role == null) {
                role = new Role(0l, "ROLE_HOST");
                roleRepository.save(role);
            }
            Host host = ObjectsMapper.convertRegisterDTOToHost(registerBodyDTO);
            host.setPassword(passwordEncoder.encode(host.getPassword()));
            hostRepository.save(host);
            addRoleToHost(host.getEmail(), role.getName());
            String token = UUID.randomUUID().toString();
            ConfirmationToken confirmationToken = new ConfirmationToken(
                    token,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(30),
                    host
            );
            confirmationTokenService.saveConfirmationToken(confirmationToken);
        }
    }

    private boolean isAnyFieldEmpty(RegisterBodyDTO user) {
        return !StringUtils.hasLength(user.getFirstname()) ||
                !StringUtils.hasLength(user.getLastname()) ||
                !StringUtils.hasLength(user.getEmail()) ||
                !StringUtils.hasLength(user.getPassword()) ||
                !StringUtils.hasLength(user.getAddress()) ||
                user.getBirthdate() == null ||
                !StringUtils.hasLength(user.getPhone()) ||
                user.getSex() == null ||
                user.getRole() == null;
    }
}