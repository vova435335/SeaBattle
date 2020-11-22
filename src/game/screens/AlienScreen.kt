package game.screens

import field.FieldState
import field.FieldView
import javafx.event.EventHandler

class AlienScreen(
    override var state: FieldState,
    override var xPosition: Int,
    override var yPosition: Int
) : Screen {

    override val fieldView = FieldView(state)
    override var click: Boolean = false

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

    override fun render() {
        fieldView.state = state
        fieldView.render(xPosition, yPosition)
    }
}