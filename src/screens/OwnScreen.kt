package screens

import field.FieldState
import field.FieldView
import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane
import util.FieldUtil

class OwnScreen : AnchorPane() {

    private val ownFieldView = FieldView(FieldState.CONSTRUCTOR)

    init {
        ownFieldView.render(this, 50, 50)

        onMousePressed = EventHandler {
            ownFieldView.onClick(it.x.toInt(), it.y.toInt())
            if(ownFieldView.getShips() >= FieldUtil.START_SHIPS_COUNT) {
                ownFieldView.state = FieldState.LOCK
            }
        }
    }


}