package com.hexad.service;

import com.hexad.entity.Pack;
import com.hexad.repository.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author R.fatthi
 */
@Service
public class PackServiceImpl implements PackService {

    @Autowired
    private PackRepository packRepository;

    @Override
    public JpaRepository<Pack, Long> getGenericRepo() {
        return packRepository;
    }
}
