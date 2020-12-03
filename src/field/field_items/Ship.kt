package field.field_items

import Position

class Ship : FieldItem() {

    var size = 0
    var positions = mutableListOf<Position>()

    fun sort() {
        if (positions.isNotEmpty()) {
            for (i in 1 until positions.size) {
                when {
                    positions.first().col == positions.last().col -> {
                        if (positions[i].row < positions[i - 1].row) {
                            swap(positions, i, i - 1)
                        }
                    }
                    positions.first().row == positions.last().row -> {
                        if (positions[i].col < positions[i - 1].col) {
                            swap(positions, i, i - 1)
                        }
                    }
                }
            }
        }
    }

    private fun swap(positions: MutableList<Position>, ind1: Int, ind2: Int) {
        val tmp = positions[ind1]
        positions[ind1] = positions[ind2]
        positions[ind2] = tmp
    }

    fun isCreated() = positions.size == this.size
}