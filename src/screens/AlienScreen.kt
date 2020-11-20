package screens

import field.FieldState
import field.FieldView
import javafx.event.EventHandler

class AlienScreen(
    var state: FieldState,
    var xPosition: Int,
    var yPosition: Int
) : Screen {

    override val fieldView = FieldView(state)

    init {
        fieldView.render(xPosition, yPosition)

        fieldView.canvas.onMousePressed = EventHandler {
            fieldView.onClick(it.x.toInt(), it.y.toInt())
            println(it.x)
        }
    }
}