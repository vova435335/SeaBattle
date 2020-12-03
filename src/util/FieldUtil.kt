package util

import Position

object FieldUtil {

    const val ITEM_INLINE_COUNT = 10
    const val LINE_COUNT = 10
    const val ITEM_SIZE = 40

    const val START_SHIPS_COUNT = 20

    const val START_OWN_SCREEN_X = 50
    const val START_OWN_SCREEN_Y = 50
    const val START_ALIEN_SCREEN_X = 550
    const val START_ALIEN_SCREEN_Y = 50

    const val LEFT_BORDER = 0
    const val RIGHT_BORDER = 11
    const val TOP_BORDER = 0
    const val BOTTOM_BORDER = 11

    fun positionToCoordinationMiddle(positionValue: Int) =
        (positionValue - 1) * ITEM_SIZE + ITEM_SIZE / 2

    fun positionToCoordination(positionValue: Int) =
        (positionValue - 1) * ITEM_SIZE

    fun coordinationToPosition(xPosition: Int, yPosition: Int) =
        Position(
            xPosition / ITEM_SIZE + 1,
            yPosition / ITEM_SIZE + 1
        )
}