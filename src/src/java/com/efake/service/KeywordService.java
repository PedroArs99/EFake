/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.service;

import com.efake.dao.KeywordsFacade;
import com.efake.dto.KeywordsDTO;
import com.efake.entity.Keywords;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author JuMed
 */
@Stateless
public class KeywordService {

   @EJB
    KeywordsFacade keywordsfacade;
   
    public KeywordsDTO findOrCreate(String keyword){
        Keywords k = keywordsfacade.findOrCreate(keyword);
        
        
        return k.getDTO();
    }

    public void edit(KeywordsDTO k){
        Keywords keyword = keywordsfacade.find(k.getPalabra());

        keywordsfacade.edit(keyword);
    } 

}
