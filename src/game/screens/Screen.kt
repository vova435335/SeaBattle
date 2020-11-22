package game.screens

import field.FieldState
import field.FieldView

interface Screen {

    val fieldView: FieldView
    var state: FieldState
    var xPosition: Int
    var yPosition: Int
    var click: Boolean

    fun render()
}