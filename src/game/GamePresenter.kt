package game

import field.FieldState
import players.Player
import util.FieldUtil

class GamePresenter(private val view: GameView) {

    fun starting(player1: Player, player2: Player) {
        if (player1.moved && player1.screen.fieldView.getShips() == 3) {
            player1.screen.state = FieldState.WAITING
            player2.screen.state = FieldState.CONSTRUCTOR

            nextMove(player1, player2)
        } else if (player2.moved && player2.screen.fieldView.getShips() == 3) {
            player1.screen.state = FieldState.LOCK
            player2.screen.state = FieldState.BATTLE

            nextMove(player1, player2)

            view.moveState = MoveState.EXECUTION
        }
    }

    fun execution(player1: Player, player2: Player) {
        if (player1.screen.click || player2.screen.click) {
            if (player1.moved && !player1.screen.click) {
                player1.screen.state = FieldState.BATTLE
                player2.screen.state = FieldState.LOCK

                nextMove(player1, player2)
                player2.screen.click = false
            } else if (player2.moved && !player2.screen.click) {
                player1.screen.state = FieldState.LOCK
                player2.screen.state = FieldState.BATTLE

                nextMove(player1, player2)
                player1.screen.click = false
            }

            if (player1.screen.fieldView.getShips() == 0 || player2.screen.fieldView.getShips() == 0) {
                player1.screen.state = FieldState.LOCK
                player2.screen.state = FieldState.LOCK
                player1.screen.render()
                player2.screen.render()

                view.moveState = MoveState.END_GAME
            }
        }
    }

    private fun nextMove(player1: Player, player2: Player) {
        if (player1.moved) {
            player1.screen.xPosition = FieldUtil.START_ALIEN_SCREEN_X
            player1.screen.yPosition = FieldUtil.START_ALIEN_SCREEN_Y
            player2.screen.xPosition = FieldUtil.START_OWN_SCREEN_X
            player2.screen.yPosition = FieldUtil.START_OWN_SCREEN_Y

            player1.moved = false
            player2.moved = true
        } else {
            player1.screen.xPosition = FieldUtil.START_OWN_SCREEN_X
            player1.screen.yPosition = FieldUtil.START_OWN_SCREEN_Y
            player2.screen.xPosition = FieldUtil.START_ALIEN_SCREEN_X
            player2.screen.yPosition = FieldUtil.START_ALIEN_SCREEN_Y

            player1.moved = true
            player2.moved = false
        }

        player1.screen.render()
        player2.screen.render()
    }


}