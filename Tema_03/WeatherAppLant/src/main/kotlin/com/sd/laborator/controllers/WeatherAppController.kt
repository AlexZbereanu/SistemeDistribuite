package com.sd.laborator.controllers

import com.sd.laborator.interfaces.LocationSearchInterface
import com.sd.laborator.interfaces.WeatherForecastInterface
import com.sd.laborator.pojo.WeatherForecastData
import com.sd.laborator.services.TimeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class WeatherAppController {

    @Autowired
    private lateinit var weatherForecastService: WeatherForecastInterface

    @RequestMapping("/getforecast/{location}", method = [RequestMethod.GET])
    @ResponseBody
    fun getForecast(@PathVariable location: String): String {

        // pe baza  locaţiei, se interoghează al doilea serviciu care returnează datele meteo
        // încapsulate într-un obiect POJO
        val rawForecastData: WeatherForecastData = weatherForecastService.getForecastData(location)

        // fiind obiect POJO, funcţia toString() este suprascrisă pentru o afişare mai prietenoasă
        return rawForecastData.toString()
    }
}