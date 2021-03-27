package org.d2e.smarthydroponic.data

data class Device (
    var status: Status = Status(),
    var command: Command = Command()
)