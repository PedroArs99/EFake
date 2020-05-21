/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.session;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author PedroArenas
 */
@Named(value = "transport")
@SessionScoped
public class Transport implements Serializable {

    
    public Transport() {
    }
    
}
