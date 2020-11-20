package screens

import field.FieldState
import field.FieldView
import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane
import util.FieldUtil

class OwnScreen(
    var state: FieldState,
    var xPosition: Int,
    var yPosition: Int
) : Screen {

    override val fieldView = FieldView(state)

    init {
        fieldView.render(xPosition, yPosition)

        fieldView.canvas.onMousePressed = EventHandler {
            println(it.x)
            fieldView.onClick(it.x.toInt(), it.y.toInt())
            if (fieldView.getShips() >= FieldUtil.START_SHIPS_COUNT) {
                fieldView.state = FieldState.LOCK
            }
        }
    }


}