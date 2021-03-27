package org.d2e.smarthydroponic.data

data class Command (
    var flowPump: String = "",
    var maxTemp: Float = 0F,
    var minTemp: Float = 0F,
    var nutriSet: Float = 0F,
    var phSet: Float = 0F
)