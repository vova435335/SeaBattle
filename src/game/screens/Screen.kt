package game.screens

import field.FieldState
import field.FieldView
import javafx.event.EventHandler

class Screen (
    var state: FieldState,
    var xPosition: Int,
    var yPosition: Int
){

    val fieldView = FieldView(state)
    var click: Boolean = false

    init {
        render()

        fieldView.canvas.onMousePressed = EventHandler {
            if (!click && state == FieldState.CONSTRUCTOR) {
                fieldView.onClick(it.x.toInt(), it.y.toInt())
            } else if (!click && state == FieldState.BATTLE) {
                fieldView.onClick(it.x.toInt(), it.y.toInt())
                click = true
            }
        }


    }

    fun render() {
        fieldView.state = state
        fieldView.render(xPosition, yPosition)
    }
}