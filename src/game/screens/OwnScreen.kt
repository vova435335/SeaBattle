package game.screens

import field.FieldState
import field.FieldView
import javafx.event.EventHandler

class OwnScreen(
    override var state: FieldState,
    override var xPosition: Int,
    override var yPosition: Int
) : Screen {

    override val fieldView = FieldView(state)

    init {
        render()

        fieldView.canvas.onMousePressed = EventHandler {
            fieldView.onClick(it.x.toInt(), it.y.toInt())
        }
    }

    override fun render() {
        fieldView.state = state
        fieldView.render(xPosition, yPosition)
    }


}