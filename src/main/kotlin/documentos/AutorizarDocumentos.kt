package documentos

import ec.gob.sri.comprobantes.exception.RespuestaAutorizacionException
import ec.gob.sri.comprobantes.util.AutorizacionComprobantesWs
import ec.gob.sri.comprobantes.ws.aut.RespuestaComprobante
import ec.gob.sri.comprobantes.ws.RespuestaSolicitud
import ec.gob.sri.comprobantes.ws.RecepcionComprobantesOfflineService
import java.net.URL
import java.util.logging.Level
import java.util.logging.Logger
import javax.xml.namespace.QName
import javax.xml.ws.WebServiceException

class AutorizarDocumentos {
    companion object {

        var host: String = "https://celcer.sri.gob.ec"
        var segmentoURLComprobantesElectronicosRecepcion: String =
            "/comprobantes-electronicos-ws/RecepcionComprobantesOffline"
        var queryParamsComprobantesElectronicosRecepcion: String = "?wsdl"

        var segmentoURLComprobantesElectronicosAutorizacion: String =
            "/comprobantes-electronicos-ws/AutorizacionComprobantesOffline"
        var queryParamsComprobantesElectronicosAutorizacion: String = "?wsdl"
        var namespaceURIRecepcion: String = "http://ec.gob.sri.ws.recepcion"
        var localPartRecepcion: String = "RecepcionComprobantesOfflineService"

        fun validar(datos: ByteArray): RespuestaSolicitud? {
            try {
                //produccion
                //cel.sri.gob.ec
                val url =
                    URL("$host$segmentoURLComprobantesElectronicosRecepcion$queryParamsComprobantesElectronicosRecepcion")

                println("Validar Comprobante")
                println("$host$segmentoURLComprobantesElectronicosRecepcion$queryParamsComprobantesElectronicosRecepcion")


                val qname = QName(namespaceURIRecepcion, localPartRecepcion)
                val service = RecepcionComprobantesOfflineService(url, qname)
                val portRec = service.recepcionComprobantesOfflinePort
                return portRec.validarComprobante(datos)

            } catch (ex: Exception) {
                println("Error validando comprobante")
                val response = RespuestaSolicitud()
                println(ex.message)
                println(ex.toString())
                response.estado = ex.message
                return response
            }

        }

        @Throws(RespuestaAutorizacionException::class)
        fun autorizarComprobante(claveDeAcceso: String): RespuestaComprobante? {
            //produccion
            //cel.sri.gob.ec
            println("Autorizar comprobante")
            println("Clave de acceso $claveDeAcceso")
            println("$host$segmentoURLComprobantesElectronicosAutorizacion$queryParamsComprobantesElectronicosAutorizacion")
            try {
                return AutorizacionComprobantesWs("$host$segmentoURLComprobantesElectronicosAutorizacion$queryParamsComprobantesElectronicosAutorizacion")
                    .llamadaWSAutorizacionInd(
                        claveDeAcceso
                    )
            } catch (ex: WebServiceException) {
                Logger.getLogger(AutorizacionComprobantesWs::class.java.name).log(Level.SEVERE, null as String?, ex)
                println("ERROR EN WEB SERVICE")
            }
            return null

        }

    }


}