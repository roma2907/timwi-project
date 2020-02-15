package projet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import projet.model.ResultReadingDigit;
import projet.service.IReadingDigitService;

@RestController
public class ReadingApiController {

    @Autowired
    private IReadingDigitService readingDigitService;

    /**
     * Api qui calcule la suite de Conway pour un input donné.
     * Pour l'entrée : 1113122113 répété 40 fois
     * on obtient 360154 caractères
     * @param input : suite de chiffre en entrée
     * @param times : nombre de fois qu'est appliqué la suite
     * @return la nouvelle séquence de chiffre
     */
    @RequestMapping(value = "/api/lookandsay/{input}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultReadingDigit readingDigits(@PathVariable("input") String input, @RequestParam("times") int times) {
        if(!validateInput(input)){
            return null;
        }
        return new ResultReadingDigit(readingDigitService.lookAndSay(input,times));
    }

    /**
     * Validation que l'entrée est composée que par des chiffres
     * @param input
     * @return true si l'input est composée que par des chiffres sinon false
     */
    private boolean validateInput(String input){
        for(int i=0;i<input.length();i++){
            //vérification que le caractère est bien un chiffre
            if(!Character.isDigit(input.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
