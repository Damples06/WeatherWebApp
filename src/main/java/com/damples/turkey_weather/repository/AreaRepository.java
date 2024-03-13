package com.damples.turkey_weather.repository;

import com.damples.turkey_weather.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {
    Area findByProvinceName(String provinceName);

    Area findByProvinceNameAndDistrictName(String provinceName, String districtName);
}