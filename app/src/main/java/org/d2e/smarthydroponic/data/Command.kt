package org.d2e.smarthydroponic.data

data class Command (
    var flowPump: Int = 0,
    var maxTemp: Float = 0F,
    var minTemp: Float = 0F,
    var nutriSet: Int = 0,
    var phSet: Float = 0F
)