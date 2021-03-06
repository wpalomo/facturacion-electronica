package documentos

import utils.expresionRegularMoneda
import utils.mensajeNulo
import utils.mensajeVacio
import utils.mensajeValores
import java.util.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
import kotlin.collections.ArrayList

open class Detalle {

    @NotEmpty(message = "codigoPrincipal $mensajeVacio")
    @Size(min = 1, max = 25, message = "codigoPrincipal $mensajeValores de 1 a 25 caracteres")
    var codigoPrincipal: String?

    fun getCodigoPrincipal(): Optional<String> {
        return Optional.of(codigoPrincipal!!)
    }

    @NotEmpty(message = "codigoAuxiliar $mensajeVacio")
    @Size(min = 1, max = 25, message = "codigoAuxiliar $mensajeValores de 1 a 25 caracteres")
    var codigoAuxiliar: String?

    fun getCodigoAuxiliar(): Optional<String> {
        return Optional.of(codigoAuxiliar!!)
    }

    @NotNull(message = "descripcion $mensajeNulo")
    @NotEmpty(message = "descripcion $mensajeVacio")
    @Size(min = 1, max = 300, message = "descripcion $mensajeValores de 1 a 300 caracteres")
    var descripcion: String

    @NotNull(message = "cantidad $mensajeNulo")
    @Pattern(
        regexp = expresionRegularMoneda,
        message = "cantidad $mensajeValores de 1 a 14 enteros y desde 1 hasta 6 decimales separados por punto"
    )
    var cantidad: String

    @NotNull(message = "precioUnitario $mensajeNulo")
    @Pattern(
        regexp = expresionRegularMoneda,
        message = "precioUnitario $mensajeValores de 1 a 14 enteros y desde 1 hasta 6 decimales separados por punto"
    )
    var precioUnitario: String

    @NotNull(message = "descuento $mensajeNulo")
    @Pattern(
        regexp = expresionRegularMoneda,
        message = "descuento $mensajeValores de 1 a 14 enteros y desde 1 hasta 6 decimales separados por punto"
    )
    var descuento: String

    @NotNull(message = "precioTotalSinImpuesto $mensajeNulo")
    @Pattern(
        regexp = expresionRegularMoneda,
        message = "precioTotalSinImpuesto $mensajeValores de 1 a 14 enteros y desde 1 hasta 6 decimales separados por punto"
    )
    var precioTotalSinImpuesto: String

    var detallesAdicionales: ArrayList<DetalleAdicional>?

    @NotNull(message = "impuestos $mensajeNulo")
    var impuestos: ArrayList<Impuesto>

    constructor(
        codigoPrincipal: String?,
        codigoAuxiliar: String?,
        descripcion: String,
        cantidad: String,
        precioUnitario: String,
        descuento: String,
        precioTotalSinImpuesto: String,
        detallesAdicionales: ArrayList<DetalleAdicional>?,
        impuestos: ArrayList<Impuesto>
    ) {
        this.codigoPrincipal =
                if (codigoPrincipal == null) null else GenerarDocumentos.removerCaracteresEspeciales(codigoPrincipal)
        this.codigoAuxiliar =
                if (codigoAuxiliar == null) null else GenerarDocumentos.removerCaracteresEspeciales(codigoAuxiliar)
        this.descripcion = GenerarDocumentos.removerCaracteresEspeciales(descripcion)
        this.cantidad = cantidad
        this.precioUnitario = precioUnitario
        this.descuento = descuento
        this.precioTotalSinImpuesto = precioTotalSinImpuesto
        this.detallesAdicionales = detallesAdicionales
        this.impuestos = impuestos
    }
}