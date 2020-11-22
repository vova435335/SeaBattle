package field

import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import javafx.scene.shape.Line
import javafx.scene.shape.Rectangle
import Position
import field.field_items.Miss
import field.field_items.Ship
import field.field_items.Water
import field.field_items.WoundShip
import util.FieldUtil
import util.FieldUtil.positionToCoordinationMiddle

class FieldView(var state: FieldState) {

    val canvas = AnchorPane()
    private val presenter = FieldPresenter(this)

    fun render(x: Int, y: Int) {
        canvas.layoutX = x.toDouble()
        canvas.layoutY = y.toDouble()

        presenter.start()
    }

    fun onClick(x: Int, y: Int) {
        when (state) {
            FieldState.CONSTRUCTOR -> {
                presenter.constructor(FieldUtil.coordinationToPosition(x, y))
            }
            FieldState.BATTLE -> {
                presenter.shot(FieldUtil.coordinationToPosition(x, y))
            }
            else -> {
                //Do nothing
            }
        }
    }

    fun drawField(field: Field) {
        for ((key, value) in field.items) {
            when (value) {
                is Ship -> {
                    if (state == FieldState.BATTLE || state == FieldState.WAITING) {
                        drawWater(key)
                        drawFieldGreed()
                    } else {
                        drawShip(key)
                        drawFieldGreed()
                    }
                }
                is WoundShip -> {
                    drawWoundShip(key)
                    drawFieldGreed()
                }
                is Miss -> {
                    drawMiss(key)
                    drawFieldGreed()
                }
                is Water -> {
                    drawWater(key)
                }
            }
        }
        drawFieldGreed()
    }

    fun getShips() = presenter.getItems()

    private fun drawMiss(position: Position) {
        val rectangle = Rectangle(
            (positionToCoordinationMiddle(position.col) - 4).toDouble(),
            (positionToCoordinationMiddle(position.row) - 4).toDouble(),
            8.0,
            8.0
        )
        canvas.children.add(rectangle)
    }

    private fun drawShip(position: Position) {
        val rectangle = Rectangle(
            (FieldUtil.positionToCoordination(position.col) + 2).toDouble(),
            (FieldUtil.positionToCoordination(position.row) + 2).toDouble(),
            (FieldUtil.ITEM_SIZE - 2).toDouble(),
            (FieldUtil.ITEM_SIZE - 2).toDouble()
        )
        rectangle.fill = Color.GREEN
        canvas.children.add(rectangle)
    }

    private fun drawWoundShip(position: Position) {
        val rectangle = Rectangle(
            (FieldUtil.positionToCoordination(position.col) + 2).toDouble(),
            (FieldUtil.positionToCoordination(position.row) + 2).toDouble(),
            (FieldUtil.ITEM_SIZE - 2).toDouble(),
            (FieldUtil.ITEM_SIZE - 2).toDouble()
        )
        rectangle.fill = Color.RED
        canvas.children.add(rectangle)
    }

    private fun drawWater(position: Position) {
        val rectangle = Rectangle(
            (FieldUtil.positionToCoordination(position.col) + 2).toDouble(),
            (FieldUtil.positionToCoordination(position.row) + 2).toDouble(),
            (FieldUtil.ITEM_SIZE - 2).toDouble(),
            (FieldUtil.ITEM_SIZE - 2).toDouble()
        )
        rectangle.fill = Color.DODGERBLUE
        rectangle.fill = Color.WHITE
        canvas.children.add(rectangle)
    }

    private fun drawFieldGreed() {
        for (lineCounter in 0..FieldUtil.LINE_COUNT) {
            var line = Line(
                0.0,
                (lineCounter * FieldUtil.ITEM_SIZE).toDouble(),
                (FieldUtil.ITEM_SIZE * FieldUtil.ITEM_INLINE_COUNT).toDouble(),
                (lineCounter * FieldUtil.ITEM_SIZE).toDouble()
            )
            line.stroke = Color.DARKCYAN
            line.strokeWidth = 1.0
            canvas.children.add(line)

            line = Line(
                (lineCounter * FieldUtil.ITEM_SIZE).toDouble(),
                0.0,
                (lineCounter * FieldUtil.ITEM_SIZE).toDouble(),
                (FieldUtil.ITEM_SIZE * FieldUtil.ITEM_INLINE_COUNT).toDouble()
            )
            line.stroke = Color.DARKCYAN
            line.strokeWidth = 1.0
            canvas.children.add(line)
        }
    }
}