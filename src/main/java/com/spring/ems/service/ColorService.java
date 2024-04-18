package com.spring.ems.service;

import com.spring.ems.entity.Color;

import java.util.List;

public interface ColorService {
    Color createColor(Color color);
    List<Color> getALl();
}
