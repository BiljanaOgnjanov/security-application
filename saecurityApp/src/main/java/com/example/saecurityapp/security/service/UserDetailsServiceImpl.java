package com.example.saecurityapp.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.saecurityapp.User.repository.*;
import com.example.saecurityapp.User.model.*;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final GuestRepository guestRepository;
    private final HostRepository hostRepository;

    public UserDetailsServiceImpl(GuestRepository guestRepository, HostRepository hostRepository) {
        this.guestRepository = guestRepository;
        this.hostRepository = hostRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Guest guest = guestRepository.findFirstByEmail(username);
        if (guest != null) {
            return new org.springframework.security.core.userdetails.User(guest.getEmail(), guest.getPassword(), new ArrayList<>());
        }
        Host host = hostRepository.findFirstByEmail(username);
        if (host != null) {
            return new org.springframework.security.core.userdetails.User(host.getEmail(), host.getPassword(), new ArrayList<>());
        }
        throw new UsernameNotFoundException("User not found", null);
    }
}