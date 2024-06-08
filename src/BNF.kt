package task

object BNF : DFA {
    override val states = (1 .. 66).toSet()
    override val alphabet = 0 .. 255
    override val startState = 1
    override val finalStates = setOf(2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 25, 29, 44, 46, 58, 62, 65, 66)

    private val numberOfStates = states.max() + 1 // plus the ERROR_STATE
    private val numberOfCodes = alphabet.max() + 1 // plus the EOF
    private val transitions = Array(numberOfStates) {IntArray(numberOfCodes)}
    private val values = Array(numberOfStates) {Symbol.SKIP}

    private fun setTransition(from: Int, chr: Char, to: Int) {
        transitions[from][chr.code + 1] = to // + 1 because EOF is -1 and the array starts at 0
    }

    private fun setTransition(from: Int, code: Int, to: Int) {
        transitions[from][code + 1] = to
    }

    private fun setSymbol(state: Int, symbol: Symbol) {
        values[state] = symbol
    }

    override fun next(state: Int, code: Int): Int {
        assert(states.contains(state))
        assert(alphabet.contains(code))
        return transitions[state][code + 1]
    }

    override fun symbol(state: Int): Symbol {
        assert(states.contains(state))
        return values[state]
    }
    init {
        initSymbols()
        initRegion()
        initCity()
        initChargingStation()
        initCharger()
        initParkingSpot()
        initPoint()
        initBox()

        setSymbol(2, Symbol.REAL)
        setSymbol(4, Symbol.REAL)
        setSymbol(6, Symbol.VARIABLE)
        setSymbol(5, Symbol.VARIABLE)
        setSymbol(7, Symbol.PLUS)
        setSymbol(8, Symbol.MINUS)
        setSymbol(9, Symbol.TIMES)
        setSymbol(10, Symbol.DIVIDES)
        setSymbol(11, Symbol.INTEGER_DIVIDES)
        setSymbol(12, Symbol.POW)
        setSymbol(13, Symbol.LPAREN)
        setSymbol(14, Symbol.RPAREN)
        setSymbol(15, Symbol.LCURLYBRACKET)
        setSymbol(16, Symbol.RCURLYBRACKET)
        setSymbol(17, Symbol.QUOTATIONMARK)
        setSymbol(18, Symbol.SKIP)
        setSymbol(19, Symbol.EOF)

        setSymbol(25, Symbol.REGION)
        setSymbol(29, Symbol.CITY)
        setSymbol(44, Symbol.CHARGINGSTATION)
        setSymbol(46, Symbol.CHARGER)
        setSymbol(58, Symbol.PARKINGSPOT)

        setSymbol(62, Symbol.POINT)
        setSymbol(65, Symbol.BOX)
    }

    private fun initRegion() {
        setTransition(1, 'r', 20)
        setTransition(20, 'e', 21)
        setTransition(21, 'g', 22)
        setTransition(22, 'i', 23)
        setTransition(23, 'o', 24)
        setTransition(24, 'n', 25)
    }
    private fun initCity() {
        setTransition(1, 'c', 26)
        setTransition(26, 'i', 27)
        setTransition(27, 't', 28)
        setTransition(28, 'y', 29)
    }
    private fun initChargingStation() {
        setTransition(26, 'h', 30)
        setTransition(30, 'a', 31)
        setTransition(31, 'r', 32)
        setTransition(32, 'g', 33)
        setTransition(33, 'i', 34)
        setTransition(34, 'n', 35)
        setTransition(35, 'g', 36)
        setTransition(36, '_', 37)
        setTransition(37, 's', 38)
        setTransition(38, 't', 39)
        setTransition(39, 'a', 40)
        setTransition(40, 't', 41)
        setTransition(41, 'i', 42)
        setTransition(42, 'o', 43)
        setTransition(43, 'n', 44)
    }
    private fun initCharger() {
        setTransition(1, 'c', 26)
        setTransition(26, 'h', 30)
        setTransition(30, 'a', 31)
        setTransition(31, 'r', 32)
        setTransition(32, 'g', 33)
        setTransition(33, 'e', 45)
        setTransition(45, 'r', 46)
    }
    private fun initParkingSpot() {
        setTransition(1, 'p', 47)
        setTransition(47, 'a', 48)
        setTransition(48, 'r', 49)
        setTransition(49, 'k', 50)
        setTransition(50, 'i', 51)
        setTransition(51, 'n', 52)
        setTransition(52, 'g', 53)
        setTransition(53, '_', 54)
        setTransition(54, 's', 55)
        setTransition(55, 'p', 56)
        setTransition(56, 'o', 57)
        setTransition(57, 't', 58)
    }

    private fun initPoint() {
        setTransition(47, 'o', 59)
        setTransition(59, 'i', 60)
        setTransition(60, 'n', 61)
        setTransition(61, 't', 62)
    }

    private fun initBox() {
        setTransition(1, 'b', 63)
        setTransition(63, 'o', 64)
        setTransition(64, 'x', 65)
    }

    private fun initSymbols() {
        // Stevila
        for(i in '0' .. '9') {
            setTransition(1, i, 2)
            setTransition(2, i, 2)
            setTransition(3,i,4)
            setTransition(4,i,4)
            setTransition(5,i,6)
            setTransition(6,i,6)
        }
        // Decimalna vejica
        setTransition(2, '.', 3)
        // Abeceda
        for (i in 'a' .. 'z'){
            setTransition(1,i,5)
            setTransition(5,i,5)
        }
        for (i in 'A' .. 'Z'){
            setTransition(1,i,5)
            setTransition(5,i,5)
        }
        // Ostali znaki - Operatorji
        setTransition(1, '+', 7)
        setTransition(1, '-', 8)
        setTransition(1, '*', 9)
        setTransition(1, '/', 10)
        setTransition(10, '/', 11)
        setTransition(1, '^', 12)
        setTransition(1, '(', 13)
        setTransition(1, ')', 14)
        setTransition(1, '{', 15)
        setTransition(1, '}', 16)
        setTransition(1, '"', 17)

        setTransition(1, ' ', 18)
        setTransition(1, '\t', 18)
        setTransition(1, '\n', 18)
        setTransition(1, '\r', 18)
        setTransition(18, ' ', 18)
        setTransition(18, '\t', 18)
        setTransition(18, '\n', 18)
        setTransition(18, '\r', 18)
        setTransition(1, EOF, 19)
    }
}