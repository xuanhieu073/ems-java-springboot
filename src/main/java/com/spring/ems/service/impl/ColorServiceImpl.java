package com.spring.ems.service.impl;

import com.spring.ems.entity.Color;
import com.spring.ems.repository.ColorRepository;
import com.spring.ems.service.ColorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ColorServiceImpl implements ColorService {
    private ColorRepository colorRepository;
    @Override
    public Color createColor(Color color) {
        Color savedColor = colorRepository.save(color);
        return savedColor;
    }

    @Override
    public List<Color> getALl() {
        return colorRepository.findAll();
    }

}
