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
 * @author Juan
 */
@Stateless
public class KeywordService {

    @EJB
    KeywordsFacade keywordsfacade;

    public KeywordsDTO findOrCreate(String keyword) {
        Keywords k = keywordsfacade.findOrCreate(keyword);

        return k.getDTO();
    }

    public void edit(KeywordsDTO k) {
        Keywords keyword = new Keywords(k);

        keywordsfacade.edit(keyword);
    }

    public KeywordsDTO find(String keyword) {
        Keywords k = keywordsfacade.find(keyword);
        if (k == null) {
            return null;
        } else {
            return k.getDTO();
        }
    }

    public void create(KeywordsDTO k) {
        Keywords keyword = new Keywords(k);
        keywordsfacade.create(keyword);
    }

}
