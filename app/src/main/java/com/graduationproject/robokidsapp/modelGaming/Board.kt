package com.graduationproject.robokidsapp.modelGaming


data class Board(private val board: MutableMap<Cell, CellState> = mutableMapOf()) {

    val topLeft: CellState
        get() = board[Cell.TOP_LEFT] ?: CellState.Blank
    val topCenter: CellState
        get() = board[Cell.TOP_CENTER] ?: CellState.Blank
    val topRight: CellState
        get() = board[Cell.TOP_RIGHT] ?: CellState.Blank
    val centerLeft: CellState
        get() = board[Cell.CENTER_LEFT] ?: CellState.Blank
    val centerCenter: CellState
        get() = board[Cell.CENTER_CENTER] ?: CellState.Blank
    val centerRight: CellState
        get() = board[Cell.CENTER_RIGHT] ?: CellState.Blank
    val bottomLeft: CellState
        get() = board[Cell.BOTTOM_LEFT] ?: CellState.Blank
    val bottomCenter: CellState
        get() = board[Cell.BOTTOM_CENTER] ?: CellState.Blank
    val bottomRight: CellState
        get() = board[Cell.BOTTOM_RIGHT] ?: CellState.Blank

    /**
     * Set the cell state
     *
     * @param cell: The Cell to assign a state to
     * @param state: The State to assign
     *
     * @return true iff the state was set successfully. If the cell has already been set,
     * false will be returned
     */
    fun setCell(cell: Cell, state: CellState): Boolean {
        if (board.containsKey(cell)) {
            return false
        }
        board[cell] = state
        return true
    }

    /**
     * Clear the board of all states
     */
    fun clearBoard() {
        board.clear()
    }

    /**
     * Find the next winning move for the current cell state
     *
     * @return The cell of the winning move for the provided state.
     * If there is no winning move on this turn, return null
     */
    fun findNextWinningMove(state: CellState): Cell? = when {
        Cell.TOP_LEFT wins state -> Cell.TOP_LEFT
        Cell.TOP_CENTER wins state -> Cell.TOP_CENTER
        Cell.TOP_RIGHT wins state -> Cell.TOP_RIGHT
        Cell.CENTER_LEFT wins state -> Cell.CENTER_LEFT
        Cell.CENTER_CENTER wins state -> Cell.CENTER_CENTER
        Cell.CENTER_RIGHT wins state -> Cell.CENTER_RIGHT
        Cell.BOTTOM_LEFT wins state -> Cell.BOTTOM_LEFT
        Cell.BOTTOM_CENTER wins state -> Cell.BOTTOM_CENTER
        Cell.BOTTOM_RIGHT wins state -> Cell.BOTTOM_RIGHT
        else -> null
    }

    private infix fun Cell.wins(state: CellState): Boolean {
        if (board.containsKey(this)) {
            return false
        }
        board[this] = state
        val hasWon = stateWon(state)
        board.remove(this)
        return hasWon
    }

    /**
     * Determine if the board has been won / current status of the board
     *
     * @return state of the board
     */
    val boardState: BoardState
        get() {
            return when {
                stateWon(CellState.Star) -> BoardState.STAR_WON
                stateWon(CellState.Circle) -> BoardState.CIRCLE_WON
                board.size < 9 -> BoardState.INCOMPLETE
                else -> BoardState.DRAW
            }
        }

    private fun stateWon(state: CellState): Boolean {
        fun testState(vararg cells: Cell) = cells.all { cell ->
            board[cell] == state
        }

        return testState(Cell.TOP_LEFT, Cell.CENTER_LEFT, Cell.BOTTOM_LEFT) ||
                testState(Cell.TOP_CENTER, Cell.CENTER_CENTER, Cell.BOTTOM_CENTER) ||
                testState(Cell.TOP_RIGHT, Cell.CENTER_RIGHT, Cell.BOTTOM_RIGHT) ||
                testState(Cell.TOP_LEFT, Cell.TOP_CENTER, Cell.TOP_RIGHT) ||
                testState(Cell.CENTER_LEFT, Cell.CENTER_CENTER, Cell.CENTER_RIGHT) ||
                testState(Cell.BOTTOM_LEFT, Cell.BOTTOM_CENTER, Cell.BOTTOM_RIGHT) ||
                testState(Cell.TOP_LEFT, Cell.CENTER_CENTER, Cell.BOTTOM_RIGHT) ||
                testState(Cell.BOTTOM_LEFT, Cell.CENTER_CENTER, Cell.TOP_RIGHT)
    }
}