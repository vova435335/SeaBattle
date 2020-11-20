package game

import field.FieldState
import javafx.scene.control.Button
import javafx.scene.layout.AnchorPane
import players.Player
import screens.AlienScreen
import screens.OwnScreen
import util.FieldUtil

class GameView : AnchorPane() {

    private lateinit var canvas: AnchorPane
    private lateinit var player1 : Player
    private lateinit var player2 : Player
    private val nextMove = Button("Пропустить ход")

    fun render(canvas: AnchorPane) {
        this.canvas = canvas

        player1 = Player(OwnScreen(FieldState.CONSTRUCTOR, FieldUtil.START_OWN_SCREEN_X, FieldUtil.START_OWN_SCREEN_Y), true)
        player2 = Player(AlienScreen(FieldState.BATTLE, FieldUtil.START_ALIEN_SCREEN_x, FieldUtil.START_ALIEN_SCREEN_Y), false)


        drawButton()
        canvas.children.add(player1.screen.fieldView.canvas)
        canvas.children.add(player2.screen.fieldView.canvas)
        canvas.children.add(nextMove)
    }

    private fun drawButton() {
        nextMove.prefWidth = 180.0
        nextMove.prefHeight = 40.0
        nextMove.layoutX = 770.0
        nextMove.layoutY = 600.0
    }


}