package eventile

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional


@Transactional(readOnly = true)
class SearchController {

    static allowedMethods = [save: "POST", search: "GET"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Search.list(params), model:[searchCount: Search.count()]
    }

    def show(Search search) {
        respond search
    }

    def create() {
        respond new Search(params)
    }

    @Transactional
    def save(Search search) {

        String apikey = "<YOUR KEY>";


        if (search == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (search.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond search.errors, view:'create'
            return
        }

        search.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'search.label', default: 'Search'), search.id])
                redirect view:'home/index'
            }
            '*' { respond search, [status: CREATED] }
        }
    }





    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'search.label', default: 'Search'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
