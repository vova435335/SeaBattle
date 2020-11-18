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

    private val presenter = FieldPresenter(this)
    private lateinit var canvas: AnchorPane

    private var x = 0
    private var y = 0

    fun render(canvas: AnchorPane, x: Int, y: Int) {
        this.x = x
        this.y = y
        this.canvas = canvas

        presenter.start()
        drawFieldGreed()
    }

    fun onClick(x: Int, y: Int) {
        when (state) {
            FieldState.CONSTRUCTOR -> {
                presenter.constructor(FieldUtil.coordinationToPosition(x - this.x, y - this.y))
            }
            FieldState.BATTLE -> {
                presenter.shot(FieldUtil.coordinationToPosition(x - this.x, y - this.y))
            }
            FieldState.LOCK -> {
                //Do nothing
            }
        }
    }

    fun drawField(field: Field) {
        for ((key, value) in field.items) {
            when (value) {
                is Ship -> drawShip(key)
                is WoundShip -> drawWoundShip(key)
                is Miss -> drawMiss(key)
                is Water -> drawWater(key)
            }
        }
    }

    fun getShips() = presenter.getItems()

    private fun drawMiss(position: Position) {
        val rectangle = Rectangle(
            (positionToCoordinationMiddle(position.col) - 4).toDouble() + x,
            (positionToCoordinationMiddle(position.row) - 4).toDouble() + y,
            8.0,
            8.0
        )
        canvas.children.add(rectangle)
    }

    private fun drawShip(position: Position) {
        val rectangle = Rectangle(
            (FieldUtil.positionToCoordination(position.col) + 1).toDouble() + x,
            (FieldUtil.positionToCoordination(position.row) + 1).toDouble() + y,
            (FieldUtil.ITEM_SIZE - 1).toDouble(),
            (FieldUtil.ITEM_SIZE - 1).toDouble()
        )
        rectangle.fill = Color.GREEN
        canvas.children.add(rectangle)
    }

    private fun drawWoundShip(position: Position) {
        val rectangle = Rectangle(
            (FieldUtil.positionToCoordination(position.col) + 1).toDouble() + x,
            (FieldUtil.positionToCoordination(position.row) + 1).toDouble() + y,
            (FieldUtil.ITEM_SIZE - 1).toDouble(),
            (FieldUtil.ITEM_SIZE - 1).toDouble()
        )
        rectangle.fill = Color.RED
        canvas.children.add(rectangle)
    }

    private fun drawWater(position: Position) {
        val rectangle = Rectangle(
            (FieldUtil.positionToCoordination(position.col) + 1).toDouble() + x,
            (FieldUtil.positionToCoordination(position.row) + 1).toDouble() + y,
            (FieldUtil.ITEM_SIZE - 1).toDouble(),
            (FieldUtil.ITEM_SIZE - 1).toDouble()
        )
        rectangle.fill = Color.DODGERBLUE
        canvas.children.add(rectangle)
    }

    private fun drawFieldGreed() {
        for (lineCounter in 0..FieldUtil.LINE_COUNT) {
            canvas.children.add(
                Line(
                    0.0 + x,
                    (lineCounter * FieldUtil.ITEM_SIZE).toDouble() + y,
                    (FieldUtil.ITEM_SIZE * FieldUtil.ITEM_INLINE_COUNT).toDouble() + x,
                    (lineCounter * FieldUtil.ITEM_SIZE).toDouble() + y
                )
            )
            canvas.children.add(
                Line(
                    (lineCounter * FieldUtil.ITEM_SIZE).toDouble() + x,
                    0.0 + y,
                    (lineCounter * FieldUtil.ITEM_SIZE).toDouble() + x,
                    (FieldUtil.ITEM_SIZE * FieldUtil.ITEM_INLINE_COUNT).toDouble() + y
                )
            )
        }
    }
}