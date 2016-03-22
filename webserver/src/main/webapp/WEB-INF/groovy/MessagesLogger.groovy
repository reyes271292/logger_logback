import javax.servlet.http.HttpServletResponse

import org.slf4j.Logger
import org.slf4j.LoggerFactory

Logger log = LoggerFactory.getLogger(getClass())

log.debug "*"*40
log.debug request.properties.toString()
log.error "Error"
log.warn "Warning"
log.info "Info"
log.trace "Trace"

def method = request.method

try{

  def contentType = headers.find{ k,v -> k.toLowerCase() == 'content-type' }?.value

  if(method.toLowerCase()=="post"){
    json([method:"POST"])
  }
  if(method.toLowerCase()=="get"){
    json([method:"GET"])
  }

}
catch(RuntimeException e){
  response.contentType='application/json'
  response.setStatus(HttpServletResponse.SC_BAD_REQUEST,e.message)
}
catch(Exception e){
  response.contentType='application/json'
  response.setStatus(HttpServletResponse.SC_NOT_FOUND,e.message)
}
