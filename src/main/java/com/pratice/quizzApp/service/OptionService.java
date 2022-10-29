package com.pratice.quizzApp.service;

import com.pratice.quizzApp.models.Option;
import com.pratice.quizzApp.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OptionService {

    @Autowired
    private OptionRepository optionRepository;

    public void saveOption(Option option) {
        optionRepository.save(option);
    }

    public ArrayList<Option> saveAllOptions(Set<Option> option) {
        return (ArrayList<Option>) optionRepository.saveAll(option);
    }

    public Option loadOptionByBy(Long id) throws Exception {
        return optionRepository.findById(id).orElseThrow(() -> new Exception("No such option found"));
    }

}
