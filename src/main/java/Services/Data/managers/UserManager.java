package Services.Data.managers;

import Domain.Usuario;
import Semantics.NotNullInteger;

import java.util.HashMap;

public class UserManager {
    final HashMap<NotNullInteger, Usuario> usuarios = new HashMap<>();

    public Usuario getUserByID(NotNullInteger id) {
        Usuario usuario = usuarios.get(id);
        if (usuarios==null) {
            createUsuario(id);
            return usuarios.get(id);
        }else return usuario;
    }
    private void createUsuario(NotNullInteger id) {
        Usuario usuario = new Usuario(id);
        usuarios.put(id, usuario);
    }

}
