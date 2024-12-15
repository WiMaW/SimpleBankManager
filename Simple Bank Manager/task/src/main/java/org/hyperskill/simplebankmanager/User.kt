package org.hyperskill.simplebankmanager

import java.io.Serializable

data class User (
    val userName: String = "Laura",
    val password: String = "1234"
): Serializable