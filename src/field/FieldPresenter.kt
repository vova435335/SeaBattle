package field

import Position
import field.field_items.*
import util.FieldUtil

class FieldPresenter(private val view: FieldView) {

    private var model = FieldModel()

    fun start() {
        view.drawField(model.field)
    }

    fun shot(position: Position) {
        val item = model.field.items[position]

        when (item) {
            is Water -> {
                model.field.items[position] = Miss()
            }
            is Ship -> {
                model.field.items[position] = WoundShip()
            }
            else -> {
                //Do nothing
            }
        }
        view.drawField(model.field)
    }

    fun constructor(position: Position) {
        val item = model.field.items[position]

        when (item) {
            is AllowedField -> {
                if (!model.ships[0].isCreated()) {
                    model.ships[0].positions.add(position)
                    model.ships[0].sort()
                    model.field.items[position] = Ship()

                    isNotCorrectPlacement(position)
                    created(model.ships[0])
                }

            }
            is Water -> {
                if (getItems() == 0) {
                    model.ships[0].positions.add(position)
                    model.field.items[position] = Ship()

                    isNotCorrectPlacement(position)
                }
            }
            is Ship -> {
                model.ships.forEach {
                    if (!it.positions.isEmpty()) {
                        if (it.positions.first() == position && it.positions.size != 0) {
                            model.field.items[position] = Water()
                            it.positions.remove(position)
                            isCorrectPlacement(position)
                        } else if (it.positions.last() == position && it.positions.size != 0) {
                            model.field.items[position] = Water()
                            it.positions.remove(position)
                            isCorrectPlacement(position)
                        }
                    }

                }
            }
            else -> {
                //Do nothing
            }
        }
        view.drawField(model.field)
    }

    fun getItems(): Int {
        var count = 0
        for (col in 1..FieldUtil.ITEM_INLINE_COUNT) {
            for (row in 1..FieldUtil.LINE_COUNT) {
                if (model.field.items[Position(col, row)] is Ship) {
                    count++
                }
            }
        }
        return count
    }

    private fun isNotCorrectPlacement(item: Position) {
        val items = model.field.items

        val colMinusOne = item.col - 1
        val rowMinusOne = item.row - 1
        val colPlusOne = item.col + 1
        val rowPlusOne = item.row + 1

        model.ships.forEach {
            when {
                it.positions.size == 1 -> {
                    if (colMinusOne != FieldUtil.LEFT_BORDER) {
                        items[Position(colMinusOne, item.row)] = AllowedField()
                    }
                    if (colPlusOne != FieldUtil.RIGHT_BORDER) {
                        items[Position(colPlusOne, item.row)] = AllowedField()
                    }
                    if (rowMinusOne != FieldUtil.TOP_BORDER) {
                        items[Position(item.col, rowMinusOne)] = AllowedField()
                    }
                    if (rowPlusOne != FieldUtil.BOTTOM_BORDER) {
                        items[Position(item.col, rowPlusOne)] = AllowedField()
                    }
                }
                it.positions.size == 2 -> {
                    if (items[Position(colMinusOne, item.row)] is Ship) {
                        items[Position(colMinusOne, rowPlusOne)] = Water()
                        items[Position(colMinusOne, rowMinusOne)] = Water()
                    }
                    if (items[Position(colPlusOne, item.row)] is Ship) {
                        items[Position(colPlusOne, rowPlusOne)] = Water()
                        items[Position(colPlusOne, rowMinusOne)] = Water()
                    }
                    if (items[Position(item.col, rowMinusOne)] is Ship) {
                        items[Position(colPlusOne, rowMinusOne)] = Water()
                        items[Position(colMinusOne, rowMinusOne)] = Water()
                    }
                    if (items[Position(item.col, rowPlusOne)] is Ship) {
                        items[Position(colPlusOne, rowPlusOne)] = Water()
                        items[Position(colMinusOne, rowPlusOne)] = Water()
                    }

                }
                colPlusOne != FieldUtil.RIGHT_BORDER && items[Position(colMinusOne, item.row)] is Ship -> {
                    items[Position(colPlusOne, item.row)] = AllowedField()
                }
                colMinusOne != FieldUtil.LEFT_BORDER && items[Position(colPlusOne, item.row)] is Ship -> {
                    items[Position(colMinusOne, item.row)] = AllowedField()
                }
                rowMinusOne != FieldUtil.TOP_BORDER && items[Position(item.col, rowPlusOne)] is Ship -> {
                    items[Position(item.col, rowMinusOne)] = AllowedField()
                }
                rowPlusOne != FieldUtil.BOTTOM_BORDER && items[Position(item.col, rowMinusOne)] is Ship -> {
                    items[Position(item.col, rowPlusOne)] = AllowedField()
                }
            }
        }
    }

    private fun isCorrectPlacement(item: Position) {
        val items = model.field.items

        val colMinusOne = item.col - 1
        val rowMinusOne = item.row - 1
        val colPlusOne = item.col + 1
        val rowPlusOne = item.row + 1

        model.ships.forEach { ship ->
            if (!ship.positions.isEmpty()) {
                items[item] = AllowedField()

//                if (
//                    (colMinusOne != FieldUtil.LEFT_BORDER && rowMinusOne != FieldUtil.TOP_BORDER) ||
//                    (ship.positions[0].col - 1 != FieldUtil.LEFT_BORDER && ship.positions[0].row - 1 != FieldUtil.TOP_BORDER)
//                ) {
//                    items[Position(colMinusOne, rowMinusOne)] = Water()
//                    items[Position(ship.positions[0].col - 1, ship.positions[0].row - 1)] = Water()
//                }
//                if (
//                    (colMinusOne != FieldUtil.LEFT_BORDER && rowPlusOne != FieldUtil.BOTTOM_BORDER) ||
//                    (ship.positions[0].col - 1 != FieldUtil.LEFT_BORDER && ship.positions[0].row + 1 != FieldUtil.BOTTOM_BORDER)
//                ) {
//                    items[Position(colMinusOne, rowPlusOne)] = Water()
//                    items[Position(ship.positions[0].col - 1, ship.positions[0].row + 1)] = Water()
//                }
//                if (
//                    (colPlusOne != FieldUtil.RIGHT_BORDER && rowMinusOne != FieldUtil.TOP_BORDER) ||
//                    (ship.positions[0].col + 1 != FieldUtil.RIGHT_BORDER && ship.positions[0].row - 1 != FieldUtil.TOP_BORDER)
//                ) {
//                    items[Position(colPlusOne, rowMinusOne)] = Water()
//                    items[Position(ship.positions[0].col + 1, ship.positions[0].row - 1)] = Water()
//                }
//                if (
//                    (colPlusOne != FieldUtil.RIGHT_BORDER && rowPlusOne != FieldUtil.BOTTOM_BORDER) ||
//                    (ship.positions[0].col + 1 != FieldUtil.RIGHT_BORDER && ship.positions[0].row + 1 != FieldUtil.BOTTOM_BORDER)
//                ) {
//                    items[Position(colPlusOne, rowPlusOne)] = Water()
//                    items[Position(ship.positions[0].col + 1, ship.positions[0].row + 1)] = Water()
//                }

                ship.positions.forEach { position ->
                    if (colMinusOne != FieldUtil.LEFT_BORDER && items[Position(colMinusOne, position.row)] !is Ship) {
                        items[Position(colMinusOne, position.row)] = Water()
                    }
                    if (colPlusOne != FieldUtil.RIGHT_BORDER && items[Position(colPlusOne, position.row)] !is Ship) {
                        items[Position(colPlusOne, position.row)] = Water()
                    }
                    if (rowMinusOne != FieldUtil.TOP_BORDER && items[Position(position.col, rowMinusOne)] !is Ship) {
                        items[Position(position.col, rowMinusOne)] = Water()
                    }
                    if (rowPlusOne != FieldUtil.BOTTOM_BORDER && items[Position(position.col, rowPlusOne)] !is Ship) {
                        items[Position(position.col, rowPlusOne)] = Water()
                    }
                }
            }

            if (colMinusOne != 0 && items[Position(colMinusOne, item.row)] !is Ship) {
                items[Position(colMinusOne, item.row)] = Water()
            }
            if (colPlusOne != 11 && items[Position(colPlusOne, item.row)] !is Ship) {
                items[Position(colPlusOne, item.row)] = Water()
            }
            if (rowMinusOne != 0 && items[Position(item.col, rowMinusOne)] !is Ship) {
                items[Position(item.col, rowMinusOne)] = Water()
            }
            if (rowPlusOne != 11 && items[Position(item.col, rowPlusOne)] !is Ship) {
                items[Position(item.col, rowPlusOne)] = Water()
            }
            if (ship.positions.size == 1) {
                if (ship.positions[0].col - 1 != FieldUtil.LEFT_BORDER) {
                    items[Position(ship.positions[0].col - 1, ship.positions[0].row)] = AllowedField()
                }
                if (ship.positions[0].col + 1 != FieldUtil.RIGHT_BORDER) {
                    items[Position(ship.positions[0].col + 1, ship.positions[0].row)] = AllowedField()
                }
                if (ship.positions[0].row - 1 != FieldUtil.TOP_BORDER) {
                    items[Position(ship.positions[0].col, ship.positions[0].row - 1)] = AllowedField()
                }
                if (ship.positions[0].row + 1 != FieldUtil.BOTTOM_BORDER) {
                    items[Position(ship.positions[0].col, ship.positions[0].row + 1)] = AllowedField()
                }
            }
        }
    }

    private fun created(ship: Ship) {
        val items = model.field.items

        ship.positions.forEach {
            val colMinusOne = it.col - 1
            val rowMinusOne = it.row - 1
            val colPlusOne = it.col + 1
            val rowPlusOne = it.row + 1

            when {
                ship.isCreated() -> {
                    if (colMinusOne != FieldUtil.LEFT_BORDER && items[Position(colMinusOne, it.row)] !is Ship) {
                        items[Position(colMinusOne, it.row)] = KilledShip()
                    }
                    if (colPlusOne != FieldUtil.RIGHT_BORDER && items[Position(colPlusOne, it.row)] !is Ship) {
                        items[Position(colPlusOne, it.row)] = KilledShip()
                    }
                    if (rowMinusOne != FieldUtil.TOP_BORDER && items[Position(it.col, rowMinusOne)] !is Ship) {
                        items[Position(it.col, rowMinusOne)] = KilledShip()
                    }
                    if (rowPlusOne != FieldUtil.BOTTOM_BORDER && items[Position(it.col, rowPlusOne)] !is Ship) {
                        items[Position(it.col, rowPlusOne)] = KilledShip()
                    }

                    if (colMinusOne != FieldUtil.LEFT_BORDER && rowMinusOne != FieldUtil.TOP_BORDER) {
                        items[Position(colMinusOne, rowMinusOne)] = KilledShip()
                    }
                    if (colMinusOne != FieldUtil.LEFT_BORDER && rowPlusOne != FieldUtil.BOTTOM_BORDER) {
                        items[Position(colMinusOne, rowPlusOne)] = KilledShip()
                    }
                    if (colPlusOne != FieldUtil.RIGHT_BORDER && rowMinusOne != FieldUtil.TOP_BORDER) {
                        items[Position(colPlusOne, rowMinusOne)] = KilledShip()
                    }
                    if (colPlusOne != FieldUtil.RIGHT_BORDER && rowPlusOne != FieldUtil.BOTTOM_BORDER) {
                        items[Position(colPlusOne, rowPlusOne)] = KilledShip()
                    }
                }
            }
        }

    }
}