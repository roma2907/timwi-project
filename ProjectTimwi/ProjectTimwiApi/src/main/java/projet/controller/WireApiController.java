package projet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import projet.model.OneWire;
import projet.model.ResultWire;
import projet.service.IWireService;
import projet.service.model.Wire;

import java.io.IOException;

@RestController
public class WireApiController {

    @Autowired
    private IWireService wireService;

    /**
     * Api qui retourne la valeur des wires ou d'un wire en particulier.
     * Pour le fichier assembly-instrcutions.txt, la valeur du wire a est de 3176.
     * @param wireName
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/api/assembly", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultWire assembly(@RequestParam(value = "wire",required = false) String wireName) throws IOException {
        ResultWire resultWire = new ResultWire();
        for(Wire wire : wireService.evaluateWires(wireName)){
            resultWire.getWires().add(new OneWire(wire));
        }
        return resultWire;
    }
}
