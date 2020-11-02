package controllers;

import Negocio.Entidad.Entidad;
import Negocio.Usuario.GestorDePasswords;
import Negocio.Usuario.GestorDeUsuarios;
import Negocio.Usuario.Usuario;
import repositories.RepositorioDeEntidades;
import repositories.RepositorioDeUsuarios;
import repositories.factories.FactoryRepositorioEntidad;
import repositories.factories.FactoryRepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class LoginController {

    public ModelAndView inicio(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"GESOC_Menu.hbs");
    }

    public ModelAndView menu_login(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"GESOC_Login.hbs");
    }

    public Response login(Request request, Response response){
        try{
            RepositorioDeEntidades repositorioDeEntidades = FactoryRepositorioEntidad.get();
            RepositorioDeUsuarios repoUsuarios = FactoryRepositorioUsuarios.get();
            GestorDePasswords gestorDePasswords = GestorDePasswords.GetInstance();
            GestorDeUsuarios gestorDeUsuarios = GestorDeUsuarios.GetInstance();

            String nombreDeUsuario = request.queryParams("usuario");
            String passwordHasheada = gestorDePasswords.hashearPassword(request.queryParams("password"));

            if(repoUsuarios.existe(nombreDeUsuario, passwordHasheada)){
                gestorDeUsuarios.loguearse(nombreDeUsuario, passwordHasheada, repoUsuarios);

                Usuario usuario = repoUsuarios.buscarUsuario(nombreDeUsuario, passwordHasheada);

                request.session(true);
                request.session().attribute("id", usuario.getId());

                response.redirect("/menu_logueado");
            }
            else{
                response.redirect("/menu_login");
            }
        }
        catch (Exception e){
            //Funcionalidad disponible solo con persistencia en Base de Datos
            response.redirect("/menu_login");
        }
        finally {
            return response;
        }

    }

    public Response logout(Request request, Response response){
        request.session().invalidate();
        response.redirect("/");
        return response;
    }
}
