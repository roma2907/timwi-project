package projet.service;

import projet.service.model.Wire;

import java.io.IOException;
import java.util.Collection;

public interface IWireService {

    /**
     * Lit le fichier en paramètre pour charger tous les wire.
     * Puis évaluation de chacun des wire un à un.
     * On commence par déterminer les wires de départ, les wires avec une valeur
     * puis on regarde les wire qui dépendaient des wire déjà résolu, et on calcule la valeur du wire
     * puis on passe aux wires qui dépendaient ...
     * @param wireName null si on veut avoir toute les valeurs des wires, sinon le nom de la wire
     * @return la liste des wires avec les valeurs résoluent ou le nom de la wire que l'on veut résoudre
     * @throws IOException
     */
    Collection<Wire> evaluateWires(String wireName) throws IOException;
}
