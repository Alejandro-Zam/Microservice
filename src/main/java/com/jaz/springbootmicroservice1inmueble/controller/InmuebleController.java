package com.jaz.springbootmicroservice1inmueble.controller;

import com.jaz.springbootmicroservice1inmueble.model.Inmueble;
import com.jaz.springbootmicroservice1inmueble.repository.service.InmuebleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/inmueble")
public class InmuebleController {

    /**
     * El @Autowired Instancia de manera automatica el objeto
     */
    @Autowired
    private InmuebleService inmuebleService;

    /**
     * Cuando esta con la anotación  @PostMapping no es necesario mandar una URL con el objeto
     * @param inmueble
     * @return
     */
    @PostMapping
    public ResponseEntity<?> saveInmueble(@RequestBody Inmueble inmueble){

        return new ResponseEntity<>(
                inmuebleService.saveInmueble(inmueble),
                HttpStatus.CREATED
        );
    }

    /**
        La forma en la que se va a mandar la peticion para eliminar el objeto es la siguiente:
        http/localhost:3333/api/inmueble/inmuebleId
        En donde el inmuebleId es el id del inmueble que se desea eliminar
     */
    @DeleteMapping("{inmuebleId}")
    public ResponseEntity<?> deleteInmueble(@PathVariable Long inmuebleId){

        inmuebleService.deleteInmueble(inmuebleId);

        return new ResponseEntity<>(HttpStatus.OK);

    }
    /**
     * El @GetMapping idica que va a buscar y obtener lo que esta buscando
     */
    @GetMapping
    public ResponseEntity<?> getAllInmuebles(){

        return ResponseEntity.ok(inmuebleService.findAllInmueble());
    }
}
