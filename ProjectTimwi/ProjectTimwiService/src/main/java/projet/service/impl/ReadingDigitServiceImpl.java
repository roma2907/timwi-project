package projet.service.impl;

import org.springframework.stereotype.Service;
import projet.service.IReadingDigitService;

@Service
public class ReadingDigitServiceImpl implements IReadingDigitService {

    /**
     * {@inheritDoc}
     */
    @Override
    public String lookAndSay(String pInput, int pTimes) {
        String input = pInput;

        //on parcours autant de fois la suite de conway que la valeur pTimes
        for(int i=0;i< pTimes; i++){
            input = lookAndSayOneTime(input);
        }
        return input;
    }

    /**
     * Execute 1 fois la suite de conway pour l'input
     * @param input : input en entrée
     * @return la suite de conway executé 1 fois
     */
    private String lookAndSayOneTime(String input){
        String result = "";
        int i=0;
        while(i < input.length()) {
            // valeur duquel on va compter le nombre d'occurence consécutive
            int currentVal = Character.getNumericValue(input.charAt(i));

            //nombre d'occurence de la valeur en cours
            int nbOccur = 1;

            //on compte ici le nombre de même chiffre qui se suive
            for(int j=i+1;j<input.length();j++){
                if(Character.getNumericValue(input.charAt(j)) == currentVal){

                    //si le chiffre actuel correspond à la valeur courante, on augmente de 1 le
                    // nombre d'occurence et on passe le traitement sur le chiffre actuel
                    nbOccur++;
                    i++;
                }else{
                    break;
                }
            }
            result+= String.valueOf(nbOccur) + String.valueOf(currentVal);
            i++;
        }
        return result;
    }
}
