package com.efake.service;

import com.efake.dao.UsuarioFacade;
import com.efake.dto.UsuarioDTO;
import com.efake.entity.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
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
    UsuarioFacade userFacade;
    
    //Tools
    private List<UsuarioDTO> convertToDTO(List<Usuario> userList){
        List<UsuarioDTO> listaDTO = null;
        if (userList != null) {
            listaDTO = new ArrayList<>();
            for (Usuario user: userList) {
                listaDTO.add(user.getDTO());
            }
        }
        return listaDTO;
    }
    
    //Services
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
    
    public int countByEsAdmin(int esAdmin){
        return userFacade.findByEsAdminCount(esAdmin);
    }
    
    public void delete(UsuarioDTO userDTO){
        Usuario user = userFacade.find(userDTO.getId());
        
        userFacade.remove(user);
    }
    //Finds
    public UsuarioDTO findById(int id){
        Usuario user = userFacade.find(id);
        
        return user.getDTO();
    }
    
    public List<UsuarioDTO> findByEsAdminAndRange(int esAdmin, int pageNumber, int pageSize){
        List<Usuario> userList = userFacade.findByEsAdminAndRange(esAdmin, pageNumber, pageSize);
        List<UsuarioDTO> dtoList = convertToDTO(userList);
        
        return dtoList;
    }
    
    
    
}
