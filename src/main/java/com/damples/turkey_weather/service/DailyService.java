package com.damples.turkey_weather.service;

import com.damples.turkey_weather.model.Daily;
import com.damples.turkey_weather.model.Daily5;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyService {

    private final Daily5Service daily5Service;

    public List<Daily> getAllDailyData(String provinceName) throws IOException, InterruptedException {

        // Get Daily5 data from Daily5Service
        Daily5 data = daily5Service.daily5Data(provinceName);

        // Create Daily objects from Daily5 data
        return setDailies(data);
    }

    public List<Daily> getAllDailyData(String provinceName, String districtName) throws IOException, InterruptedException {

        // Get Daily5 data from Daily5Service
        Daily5 data = daily5Service.daily5Data(provinceName, districtName);

        // Create Daily objects from Daily5 data
        return setDailies(data);
    }

    // Get a single Daily object from the list of Daily objects
    public Daily getSingleDailyData(String provinceName, Integer i) throws IOException, InterruptedException {
        List<Daily> dailyList = getAllDailyData(provinceName);
        return setDaily(i, dailyList);
    }

    public Daily getSingleDailyData(String provinceName, String districtName, Integer i) throws IOException, InterruptedException {
        List<Daily> dailyList = getAllDailyData(provinceName, districtName);
        return setDaily(i, dailyList);
    }

    private Daily setDaily(Integer i, List<Daily> dailyList) {
        Daily daily;
        try {
            daily = dailyList.get(i - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Index out of bounds. Please enter a valid index.");
        }
        return daily;
    }

    private List<Daily> setDailies(Daily5 data) {
        Daily daily1 = new Daily();
        Daily daily2 = new Daily();
        Daily daily3 = new Daily();
        Daily daily4 = new Daily();
        Daily daily5 = new Daily();

        daily1.setMinTemperature(data.getMinTemperatureDay1());
        daily1.setMaxTemperature(data.getMaxTemperatureDay1());
        daily1.setMinHumidity(data.getMinHumidityDay1());
        daily1.setMaxHumidity(data.getMaxHumidityDay1());
        daily1.setWindSpeed(data.getWindSpeedDay1());
        daily1.setWindDirection(data.getWindDirectionDay1());
        daily1.setDate(data.getDateDay1().toLocalDate());
        daily1.setEvent(data.getEventDay1());

        daily2.setMinTemperature(data.getMinTemperatureDay2());
        daily2.setMaxTemperature(data.getMaxTemperatureDay2());
        daily2.setMinHumidity(data.getMinHumidityDay2());
        daily2.setMaxHumidity(data.getMaxHumidityDay2());
        daily2.setWindSpeed(data.getWindSpeedDay2());
        daily2.setWindDirection(data.getWindDirectionDay2());
        daily2.setDate(data.getDateDay2().toLocalDate());
        daily2.setEvent(data.getEventDay2());

        daily3.setMinTemperature(data.getMinTemperatureDay3());
        daily3.setMaxTemperature(data.getMaxTemperatureDay3());
        daily3.setMinHumidity(data.getMinHumidityDay3());
        daily3.setMaxHumidity(data.getMaxHumidityDay3());
        daily3.setWindSpeed(data.getWindSpeedDay3());
        daily3.setWindDirection(data.getWindDirectionDay3());
        daily3.setDate(data.getDateDay3().toLocalDate());
        daily3.setEvent(data.getEventDay3());

        daily4.setMinTemperature(data.getMinTemperatureDay4());
        daily4.setMaxTemperature(data.getMaxTemperatureDay4());
        daily4.setMinHumidity(data.getMinHumidityDay4());
        daily4.setMaxHumidity(data.getMaxHumidityDay4());
        daily4.setWindSpeed(data.getWindSpeedDay4());
        daily4.setWindDirection(data.getWindDirectionDay4());
        daily4.setDate(data.getDateDay4().toLocalDate());
        daily4.setEvent(data.getEventDay4());

        daily5.setMinTemperature(data.getMinTemperatureDay5());
        daily5.setMaxTemperature(data.getMaxTemperatureDay5());
        daily5.setMinHumidity(data.getMinHumidityDay5());
        daily5.setMaxHumidity(data.getMaxHumidityDay5());
        daily5.setWindSpeed(data.getWindSpeedDay5());
        daily5.setWindDirection(data.getWindDirectionDay5());
        daily5.setDate(data.getDateDay5().toLocalDate());
        daily5.setEvent(data.getEventDay5());

        List<Daily> dailyList = List.of(daily1, daily2, daily3, daily4, daily5);

        for (Daily daily : dailyList) {
            regularize(daily);
        }

        return dailyList;
    }

    private void regularize(Daily daily) {
        switch (daily.getEvent()) {
            case "A" -> daily.setEvent("Açık");
            case "AB" -> daily.setEvent("Az Bulutlu");
            case "PB" -> daily.setEvent("Parçalı Bulutlu");
            case "CB" -> daily.setEvent("Çok Bulutlu");
            case "HY" -> daily.setEvent("Hafif Yağmurlu");
            case "Y" -> daily.setEvent("Yağmurlu");
            case "KY" -> daily.setEvent("Kuvvetli Yağmurlu");
            case "KKY"-> daily.setEvent("Karla Karışık Yağmurlu");
            case "HKY"-> daily.setEvent("Hafif Kar Yağışlı");
            case "K" -> daily.setEvent("Kar Yağışlı");
            case "YKY" -> daily.setEvent("Yoğun Kar Yağışlı");
            case "HSY" -> daily.setEvent("Hafif Sağanak Yağışlı");
            case "SY" -> daily.setEvent("Sağanak Yağışlı");
            case "KSY" -> daily.setEvent("Kuvvetli Sağanak Yağışlı");
            case "MSY" -> daily.setEvent("Mevzi Sağanak Yağışlı");
            case "DY" -> daily.setEvent("Dolu");
            case "GSY" -> daily.setEvent("Gökgürültülü Sağanak Yağışlı");
            case "KGY" -> daily.setEvent("Kuvvetli Gökgürültülü Sağanak Yağışlı");
            case "SIS" -> daily.setEvent("Sisli");
            case "PUS" -> daily.setEvent("Puslu");
            case "DMN" -> daily.setEvent("Dumanlı");
            case "KF" -> daily.setEvent("Kum veya Toz Taşınımı");
            case "R" -> daily.setEvent("Rüzgarlı");
            case "GKR" -> daily.setEvent("Güneyli Kuvvetli Rüzgar");
            case "KKR" -> daily.setEvent("Kuzeyli Kuvvetli Rüzgar");
            case "SCK" -> daily.setEvent("Sıcak");
            case "SGK" -> daily.setEvent("Soğuk");
            case "HHY" -> daily.setEvent("Yağışlı");
            default -> daily.setEvent("Bilinmeyen Durum");
        }
    }
}