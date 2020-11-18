package screens

import field.FieldState
import field.FieldView
import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane

class AlienScreen : AnchorPane() {

    private val alienFieldView = FieldView(FieldState.LOCK)

    init {
        alienFieldView.render(this, 550, 50)

        onMousePressed = EventHandler {
            alienFieldView.onClick(it.x.toInt(), it.y.toInt())
        }
    }
}