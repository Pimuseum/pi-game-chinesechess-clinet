package com.pimuseum.game.chinesechess.core.chessman

import com.pimuseum.game.chinesechess.core.ChessType
import com.pimuseum.game.chinesechess.core.Chessman
import com.pimuseum.game.chinesechess.core.Position
import com.pimuseum.game.chinesechess.core.tools.ChessmanTools

/**
 * Desc : ShiChessman(士，仕)
 * Author : Jiervs
 * Date : 2019/3/20
 */
class ShiChessman(chessType: ChessType, position: Position) : Chessman(chessType, position) {

    override fun chessmanRule(nextPosition: Position): Boolean {

        //士按斜线走九宫格，每次移动列横各移动为1
        if (!(Math.abs(nextPosition.column -position.column) == 1 && Math.abs(nextPosition.row -position.row) == 1)) return false

        return if (chessType == ChessType.Red) {//红棋子

            (nextPosition.column == 4 && nextPosition.row == 1) ||
            (nextPosition.column == 4 && nextPosition.row == 3) ||
            (nextPosition.column == 5 && nextPosition.row == 2) ||
            (nextPosition.column == 6 && nextPosition.row == 1) ||
            (nextPosition.column == 6 && nextPosition.row == 3)

        } else {//黑棋子

            (nextPosition.column == 4 && nextPosition.row == 10) ||
            (nextPosition.column == 4 && nextPosition.row == 8)  ||
            (nextPosition.column == 5 && nextPosition.row == 9) ||
            (nextPosition.column == 6 && nextPosition.row == 10) ||
            (nextPosition.column == 6 && nextPosition.row == 8)
        }
    }

    override fun chessboardRule(chessmanList: ArrayList<Chessman>, nextPosition: Position): Boolean {

        ChessmanTools.isExistChessmanByPosition(chessmanList,nextPosition)?.let { chessman->
            if (chessman.chessType == this@ShiChessman.chessType) return false//同色棋子不能被吃
        }

        return true
    }

    override fun chessmanName(): String {

        return when(chessType) {
            ChessType.Red -> "士"
            ChessType.Black -> "仕"
        }
    }
}