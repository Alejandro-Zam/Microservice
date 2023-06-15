package com.jaz.springbootmicroservice1inmueble.service;

import com.jaz.springbootmicroservice1inmueble.model.Inmueble;
import com.jaz.springbootmicroservice1inmueble.repository.InmuebleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InmuebleServiceImpl implements InmuebleService{

    @Autowired
    private InmuebleRepository inmuebleRepository;

    @Override
    public Inmueble saveInmueble(Inmueble inmueble){

        inmueble.setFecha_Creacion(LocalDateTime.now());
        return inmuebleRepository.save(inmueble);
    }

    @Override
    public void deleteInmueble(Long inmuebleID) {

        inmuebleRepository.deleteById(inmuebleID);
    }

    @Override
    public List<Inmueble> findAllInmueble() {

        return inmuebleRepository.findAll();
    }
}
