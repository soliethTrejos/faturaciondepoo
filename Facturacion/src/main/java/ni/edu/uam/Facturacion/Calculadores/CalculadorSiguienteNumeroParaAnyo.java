package ni.edu.uam.Facturacion.Calculadores;

import javax.persistence.*;

import ni.edu.uam.Facturacion.modelo.Cliente;
import org.openxava.annotations.DefaultValueCalculator;
import org.openxava.annotations.PropertyValue;
import org.openxava.calculators.*;
import org.openxava.jpa.*;
import lombok.*;

public class CalculadorSiguienteNumeroParaAnyo
        implements ICalculator { // Un calculador tiene que implementar ICalculator

    @Getter @Setter
    int anyo;

    public Object calculate() throws Exception {
        Query query = XPersistence.getManager() // Una consulta JPA
                .createQuery("select max(f.numero) from Factura f where f.anyo = :anyo"); // La consulta devuelve
        // el número de factura máximo del año indicado
        query.setParameter("anyo", anyo); // Ponemos el año inyectado como parámetro de la consulta
        Integer ultimoNumero = (Integer) query.getSingleResult();
        return ultimoNumero == null ? 1 : ultimoNumero + 1; // Devuelve el último número
        // de factura del año + 1 o 1 si no hay último número
    }
    @Column(length=6)

    @DefaultValueCalculator(value=CalculadorSiguienteNumeroParaAnyo.class,
            properties=@PropertyValue(name="anyo")
    )
    int numero;

    @ManyToOne(fetch=FetchType.LAZY, optional=false) // El cliente es obligatorio
    Cliente cliente;

}