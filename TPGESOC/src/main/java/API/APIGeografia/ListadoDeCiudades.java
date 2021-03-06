package API.APIGeografia;

import java.util.List;
import java.util.Optional;

public class ListadoDeCiudades {
    public String id;
    public String name;
    public Parametro parametros;
    public List<Ciudad> cities;

    public Optional<Ciudad> ciudadPorId(String id){
        return this.cities.stream()
                .filter(unaCiudad -> unaCiudad.id.equals(id))
                .findFirst();
    }

    private class Parametro {
        public List<String> campos;
    }
}
