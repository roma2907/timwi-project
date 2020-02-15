package projet.service;

public interface IReadingDigitService {

    /**
     * Cette méthode va à partir d'un input généré une nouvelle suite de chiffre en utilisant la suite de Conway.
     * @param input : suite de chiffre en entrée
     * @param times : nombre de fois qu'est appliqué la suite
     * @return la nouvelle séquence de chiffre
     */
    String lookAndSay(String input, int times);
}
