package field

import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import javafx.scene.shape.Line
import javafx.scene.shape.Rectangle
import Position
import field.field_items.*
import javafx.scene.shape.Circle
import util.FieldUtil
import util.FieldUtil.ITEM_SIZE
import util.FieldUtil.positionToCoordination
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
        canvas.children.clear()
        for ((key, value) in field.items) {
            when (value) {
                is Ship -> {
                    if (state == FieldState.BATTLE || state == FieldState.WAITING) {
                        drawWater(key)
                    } else {
                        drawShip(key)

                    }
                }
                is WoundShip -> {
                    drawWoundShip(key)
                }
                is KilledShip -> {
                    drawKilledShip(key)
                }
                is Miss -> {
                    drawMiss(key)
                }
                is Water -> {
                    drawWater(key)
                }
                is AllowedField -> {
                    drawAllowedField(key)
                }
            }
        }
        drawFieldGreed()
    }

    fun getShips() = presenter.getItems()

    private fun drawMiss(position: Position) {
        val oval = Circle(
            (positionToCoordinationMiddle(position.col)).toDouble(),
            (positionToCoordinationMiddle(position.row)).toDouble(),
            4.0,
            Color.DARKCYAN
        )
        canvas.children.add(oval)
    }

    private fun drawShip(position: Position) {
        drawElement(Color.GREEN, position)
    }

    private fun drawWoundShip(position: Position) {
        drawElement(Color.ORANGE, position)
    }

    private fun drawKilledShip(position: Position) {
        drawElement(Color.RED, position)
    }

    private fun drawAllowedField(position: Position) {
        drawElement(Color.DARKCYAN, position)
    }

    private fun drawWater(position: Position) {
        val rectangle = Rectangle(
            (FieldUtil.positionToCoordination(position.col) + 2).toDouble(),
            (FieldUtil.positionToCoordination(position.row) + 2).toDouble(),
            (FieldUtil.ITEM_SIZE - 2).toDouble(),
            (FieldUtil.ITEM_SIZE - 2).toDouble()
        )
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
            line.strokeWidth = 2.0
            canvas.children += line

            line = Line(
                (lineCounter * FieldUtil.ITEM_SIZE).toDouble(),
                0.0,
                (lineCounter * FieldUtil.ITEM_SIZE).toDouble(),
                (FieldUtil.ITEM_SIZE * FieldUtil.ITEM_INLINE_COUNT).toDouble()
            )
            line.stroke = Color.DARKCYAN
            line.strokeWidth = 2.0
            canvas.children += line
        }
    }

    private fun drawElement(color: Color, position: Position) {
        var line: Line
        var i = 0

        while (i < 40) {
            line = Line(
                (positionToCoordination(position.col) + 1).toDouble(),
                (positionToCoordination(position.row) + ITEM_SIZE - i + 1).toDouble(),
                (positionToCoordination(position.col) + i - 1).toDouble(),
                (positionToCoordination(position.row) + FieldUtil.ITEM_SIZE - 1).toDouble()
            )
            i += 4

            line.strokeWidth = 1.0
            line.stroke = color
            canvas.children.add(line)
        }

        i = 0
        while (i < 40) {
            line = Line(
                (positionToCoordination(position.col) + i + 1).toDouble(),
                (positionToCoordination(position.row) + 1).toDouble(),
                (positionToCoordination(position.col) + FieldUtil.ITEM_SIZE - 1).toDouble(),
                (positionToCoordination(position.row) + FieldUtil.ITEM_SIZE - i - 1).toDouble()
            )
            i += 4

            line.strokeWidth = 1.0
            line.stroke = color
            canvas.children.add(line)
        }
    }
}