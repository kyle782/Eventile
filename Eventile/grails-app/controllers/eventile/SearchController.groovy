package eventile

import com.evdb.javaapi.APIConfiguration
import com.evdb.javaapi.EVDBAPIException
import com.evdb.javaapi.EVDBRuntimeException
import com.evdb.javaapi.data.SearchResult
import com.evdb.javaapi.data.request.EventSearchRequest
import com.evdb.javaapi.operations.EventOperations

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

        if (search == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        APIConfiguration.setApiKey("3GZZwncKn3BHQ4rw")
        APIConfiguration.setEvdbUser("camboppolo2@gmail.com")
        APIConfiguration.setEvdbPassword("testing123")

        EventOperations eo = new EventOperations()
        EventSearchRequest esr = new EventSearchRequest()

        esr.setKeywords(search.query)

        SearchResult sr = null

        try {
            sr = eo.search(esr)
        } catch (EVDBRuntimeException var){
            System.out.println("Opps got runtime an error...RUNTIME")
        } catch (EVDBAPIException var){
            System.out.println("Opps got runtime an error...API")
        }

        search.setSr(sr)
        System.out.println("it is " + sr.toString())

        search.save (flush:true)
        redirect(action: "show", id: search.getId())

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
