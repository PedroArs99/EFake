package com.efake.service;

import com.efake.dao.UsuarioFacade;
import com.efake.dto.UsuarioDTO;
import com.efake.entity.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author PedroArenas
 * @authoe Laura Rosón (create, edit, findByCorreo, esMenor, calcularEdad)
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

    public void create(UsuarioDTO userDTO){
        Usuario user = new Usuario(userDTO);
        userFacade.create(user);
    }

    public void delete(UsuarioDTO userDTO){
        Usuario user = userFacade.find(userDTO.getId());

        userFacade.remove(user);
    }

    public void edit(UsuarioDTO userDTO){
        Usuario user = new Usuario(userDTO);
        
        userFacade.edit(user);
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

    public UsuarioDTO findByCorreo(String correo){
        Usuario user = userFacade.findByCorreo(correo);
        if (user == null) {
            return null;
        } else {
            return user.getDTO();
        }
    }

    public boolean esMenor(Date nacimiento) {
        int mes, dia, edad;

        java.util.Date fechaSistema = new Date();

        edad = fechaSistema.getYear() - nacimiento.getYear();

        boolean esMenor = edad < 18;

        if (edad == 18) {
            mes = fechaSistema.getMonth() - nacimiento.getMonth();
            if (mes == 0) {
                dia = fechaSistema.getDay() - nacimiento.getDay();
                if (dia >= 0) {
                    esMenor = false;
                } else {
                    esMenor = true;
                }
            } else if (mes < 0) {
                esMenor = true;
            }else{
                esMenor = false;
            }
        }

        return esMenor;
    }

    public int calcularEdad(Date nacimiento) {
        int mes, dia;

        Date fechaSistema = new Date();
        int edad = fechaSistema.getYear() - nacimiento.getYear();
        Usuario newUser = null, posibleUser;
        byte[] contrasenaCifrada;
        boolean esMenor = edad < 18;

        if (edad == 18) {
            mes = fechaSistema.getMonth() - nacimiento.getMonth();
            if (mes == 0) {
                dia = fechaSistema.getDay() - nacimiento.getDay();
                if (dia >= 0) {
                    esMenor = false;
                } else {
                    esMenor = true;
                    edad = 17;
                }
            } else if (mes < 0) {
                esMenor = true;
                edad = 17;
            }else{
                esMenor = false;
            }
        }

        return edad;
    }

    public List<UsuarioDTO> findByFilters(String email, String name, String surname, Integer age, Date lastLogin){
        List<Usuario> userList = userFacade.findByFilters(email, name, surname, age, lastLogin);

        return this.convertToDTO(userList);
    }
}
