package com.efake.service;

import com.efake.dao.UsuarioFacade;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author PedroArenas
 */
@Stateless
public class UsuarioService {

    @EJB
    UsuarioFacade usuarioFacade;

    public byte[] hashPassword(String password) {
        byte[] hash = null;

        try {
            //Create Hash algorithm instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            //Set text that's gonna be hashed in UTF-8 encoding
            md.update(password.getBytes("UTF-8"));
            //Apply hash function
            hash = md.digest();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return hash;
        }
    }

}
