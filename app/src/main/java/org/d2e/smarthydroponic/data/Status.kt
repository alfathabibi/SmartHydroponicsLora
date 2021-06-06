package org.d2e.smarthydroponic.data

data class Status(
    var cooler: Int = 0,
    var heater: Int = 0,
    var mixPump: Int = 0,
    var nutridown: Int = 0,
    var nutriup: Int = 0,
    var phValue: Double = 0.0,
    var phdown: Int = 0,
    var phup: Int = 0,
    var tdsValue: Int = 0,
    var tempratureValue: Double = 0.0
)