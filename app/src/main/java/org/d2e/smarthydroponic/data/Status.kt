package org.d2e.smarthydroponic.data

data class Status (
    var cooler : Int = 0,
    var heater : Int = 0,
    var nutriDown : Int = 0,
    var nutriUp : Int = 0,
    var phValue : Int = 0,
    var phDown : Int = 0,
    var phUp : Int = 0,
    var tdsValue : Int = 0,
    var temperatureValue : Int = 0,

)