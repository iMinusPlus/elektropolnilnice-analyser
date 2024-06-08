package task

class Parser (private val scanner: Scanner) {
    private var token:Token = scanner.getToken()

    private fun eof(): Boolean {
        if (token.symbol == Symbol.EOF) {
            return true
        }else {
            return false
        }
    }

    fun start():Boolean{
        return region();
    }

    private fun region(): Boolean {
        if (token.symbol == Symbol.REGION){
            token = scanner.getToken()
            if (token.symbol==Symbol.VARIABLE){
                token = scanner.getToken()
                if (token.symbol == Symbol.LCURLYBRACKET){
                    token = scanner.getToken()
                    if (region_elements()){
                        if (token.symbol == Symbol.RCURLYBRACKET){
                            token=scanner.getToken()
                            return true
                        }
                    }
                }
            }
        }
        return false
    }

    private fun region_elements(): Boolean {
        return elements_inside_region() && region_element()
    }

    private fun region_element(): Boolean {
        if (region_elements())
            return true
        return true
    }

    private fun elements_inside_region(): Boolean {
        if (city()){
            return true
        }
        return false
    }

    private fun city(): Boolean {
        if (token.symbol == Symbol.CITY){
            token = scanner.getToken()
            if (token.symbol == Symbol.VARIABLE){
                token = scanner.getToken()
                if (token.symbol == Symbol.LCURLYBRACKET){
                    token = scanner.getToken()
                    if (city_elements()){
                        if (token.symbol == Symbol.RCURLYBRACKET){
                            token = scanner.getToken()
                            return true
                        }
                    }
                }
            }
        }
        return false
    }

    private fun city_elements(): Boolean {
        return (elements_inside_city() && city_element())
    }

    private fun elements_inside_city(): Boolean {
        if (charging_station()){
            return true
        }
        return false
    }

    private fun charging_station(): Boolean {
        if (token.symbol == Symbol.CHARGINGSTATION){
            token = scanner.getToken()
            if (token.symbol == Symbol.VARIABLE){
                token = scanner.getToken()
                if (token.symbol == Symbol.LCURLYBRACKET){
                    token = scanner.getToken()
                    if (charging_station_elements()){
                        if (token.symbol == Symbol.RCURLYBRACKET){
                            token = scanner.getToken()
                            return true
                        }
                    }
                }
            }
        }
        return false
    }

    private fun charging_station_elements(): Boolean {
        return elements_inside_charging_station() && charging_station_element()
    }

    private fun charging_station_element(): Boolean {
        if (charging_station_elements())
            return true
        return true
    }

    private fun elements_inside_charging_station(): Boolean {
        if (charger() || parking_spot() || station_status())
            return true
        return false // true?
    }

    private fun station_status(): Boolean {
        if (token.symbol == Symbol.VARIABLE){
            token = scanner.getToken()
            return true
        }
        return false
    }

    private fun parking_spot(): Boolean {
        if (token.symbol == Symbol.PARKINGSPOT){
            token = scanner.getToken()
            if (token.symbol == Symbol.VARIABLE){
                token = scanner.getToken()
                if (token.symbol == Symbol.LCURLYBRACKET){
                    token = scanner.getToken()
                    if (box()){
                        if (token.symbol == Symbol.RCURLYBRACKET){
                            token = scanner.getToken()
                            return true
                        }
                    }
                }
            }
        }
        return false
    }

    private fun charger(): Boolean {
        if (token.symbol == Symbol.CHARGER){
            token = scanner.getToken()
            if (token.symbol == Symbol.VARIABLE){
                token = scanner.getToken()
                if (token.symbol == Symbol.LCURLYBRACKET){
                    token = scanner.getToken()
                    if (charger_type() && charger_location()){
                        if (token.symbol == Symbol.RCURLYBRACKET){
                            token = scanner.getToken()
                            return true
                        }
                    }
                }
            }
        }
        return false
    }

    private fun charger_location(): Boolean {
        if (point())
            return true
        return false
    }

    private fun charger_type(): Boolean {
        if (token.symbol == Symbol.VARIABLE){
            token = scanner.getToken()
            return true
        }
        return false
    }

    private fun city_element(): Boolean {
        if (city_elements())
            return true
        return true
    }

    private fun box(): Boolean {
        if (token.symbol == Symbol.BOX){
            token = scanner.getToken()
            if (token.symbol == Symbol.LPAREN){
                token = scanner.getToken()
                if (point()){
                    if (token.symbol == Symbol.COMMA){
                        token = scanner.getToken()
                        if (point()){
                            if (token.symbol == Symbol.RPAREN){
                                token = scanner.getToken()
                                return true
                            }
                        }
                    }
                }
            }
        }
        return false
    }

    private fun point(): Boolean {
        if (token.symbol == Symbol.POINT){
            token = scanner.getToken()
            if (token.symbol == Symbol.LPAREN){
                token = scanner.getToken()
                if (unary()){
                    if (token.symbol == Symbol.COMMA){
                        token = scanner.getToken()
                        if (unary()){
                            if (token.symbol == Symbol.RPAREN){
                                token = scanner.getToken()
                                return true
                            }
                        }
                    }
                }
            }
        }
        return false
    }

    private fun unary(): Boolean {
        if (token.symbol == Symbol.PLUS) {
            token = scanner.getToken()
            return primary()
        }
        if (token.symbol == Symbol.MINUS) {
            token = scanner.getToken()
            return primary()
        }
        return primary()
    }

    private fun primary(): Boolean {
        if (token.symbol == Symbol.REAL) {
            token = scanner.getToken()
            return true
        }
        /*if (token.symbol == Symbol.LPAREN) { //token=COMMA
            token = scanner.getToken()
            if (unary()){
                if (token.symbol == Symbol.RPAREN) {
                    token = scanner.getToken()
                    return true
                }
            }
        }*/
        return false
    }

}