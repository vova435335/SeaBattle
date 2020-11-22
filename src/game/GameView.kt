package game

import field.FieldState
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.layout.AnchorPane
import players.Player
import game.screens.AlienScreen
import game.screens.OwnScreen
import util.FieldUtil

class GameView : AnchorPane() {

    private lateinit var canvas: AnchorPane
    private val nextMove = Button("Пропустить ход")

    private val presenter = GamePresenter(this)

    private lateinit var player1: Player
    private lateinit var player2: Player

    var moveState = MoveState.STARTING

    fun render(canvas: AnchorPane) {
        this.canvas = canvas

        player1 =
            Player(OwnScreen(FieldState.CONSTRUCTOR, FieldUtil.START_OWN_SCREEN_X, FieldUtil.START_OWN_SCREEN_Y), true)
        player2 =
            Player(AlienScreen(FieldState.WAITING, FieldUtil.START_ALIEN_SCREEN_X, FieldUtil.START_ALIEN_SCREEN_Y), false)

        drawButton()

        canvas.children.add(player1.screen.fieldView.canvas)
        canvas.children.add(player2.screen.fieldView.canvas)
        canvas.children.add(nextMove)

        onClickButton()
    }


    private fun drawButton() {
        nextMove.prefWidth = 180.0
        nextMove.prefHeight = 40.0
        nextMove.layoutX = 770.0
        nextMove.layoutY = 600.0
    }

    private fun onClickButton() {
        nextMove.onAction = EventHandler {
            when(moveState) {
                MoveState.STARTING -> {
                    presenter.starting(player1, player2)
                }
                MoveState.EXECUTION -> {
                    presenter.execution(player1, player2)
                }
                else -> {

                }
            }
        }
    }
}