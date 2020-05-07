package dominio.TiposDeEmpresas;

import dominio.GestorDePasswords;
import dominio.GestorDeUsuarios;

public class PequeniaEmpresa extends TipoDeEmpresa {

    private static PequeniaEmpresa instance = null;

    private PequeniaEmpresa() {
    }

    public static PequeniaEmpresa GetInstance() {
        if (instance == null)
            instance = new PequeniaEmpresa();
        return instance;
    }
}