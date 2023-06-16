package com.jaz.springbootmicroservice1inmueble.repository.service;


import com.jaz.springbootmicroservice1inmueble.model.Inmueble;

import java.util.List;

public interface InmuebleService {
    Inmueble saveInmueble(Inmueble inmueble);

    void deleteInmueble(Long inmuebleID);

    List<Inmueble> findAllInmueble();
}
