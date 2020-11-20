package players

class PlayersManager(var player1: Player, var player2: Player) {

    fun changeOfcourse() {
        if (player1.active) {
            player1.active = false
            player2.active = true
        } else {
            player1.active = true
            player2.active = false
        }
    }
}