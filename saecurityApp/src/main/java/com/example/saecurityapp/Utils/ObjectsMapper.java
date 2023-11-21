package com.example.saecurityapp.Utils;

import com.example.saecurityapp.User.dto.RegisterBodyDTO;
import com.example.saecurityapp.User.model.Guest;
import com.example.saecurityapp.User.model.Host;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class ObjectsMapper {
    public static Guest convertRegisterDTOToGuest(RegisterBodyDTO registerBodyDTO) {
        ModelMapper modelMapper = new ModelMapper();
        PropertyMap<RegisterBodyDTO, Guest> answerMap = new PropertyMap<>() {
            protected void configure() {
                skip(destination.getId());
                map().setFirstname(source.getFirstname());
                map().setLastname(source.getLastname());
                map().setEmail(source.getEmail());
                map().setPassword(source.getPassword());
                map().setPhone(source.getPhone());
                map().setAddress(source.getAddress());
                map().setSex(source.getSex());
                map().setBirthdate(source.getBirthdate());
            }
        };

        modelMapper.addMappings(answerMap);
        return modelMapper.map(registerBodyDTO, Guest.class);
    }

    public static Host convertRegisterDTOToHost(RegisterBodyDTO registerBodyDTO) {
        ModelMapper modelMapper = new ModelMapper();
        PropertyMap<RegisterBodyDTO, Host> answerMap = new PropertyMap<>() {
            protected void configure() {
                skip(destination.getId());
                map().setFirstname(source.getFirstname());
                map().setLastname(source.getLastname());
                map().setEmail(source.getEmail());
                map().setPassword(source.getPassword());
                map().setPhone(source.getPhone());
                map().setAddress(source.getAddress());
                map().setSex(source.getSex());
                map().setBirthdate(source.getBirthdate());
            }
        };

        modelMapper.addMappings(answerMap);
        return modelMapper.map(registerBodyDTO, Host.class);
    }
}
