package ni.edu.uam.Facturacion.modelo;

import javax.persistence.*;
import lombok.*;
import org.openxava.annotations.NoFrame;

@Embeddable
@Getter @Setter
public class Direccion {

    @Column(length = 30)
    String viaPublica;

    @Column(length = 5)
    int codigoPostal;

    @Column(length = 20)
    String municipio;

    @Column(length = 30)
    String provincia;


}
