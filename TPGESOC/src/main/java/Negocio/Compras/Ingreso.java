package Negocio.Compras;

import BaseDeDatos.EntidadPersistente;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ingreso")

public class Ingreso extends EntidadPersistente {

    @Column
    public String descripcion;
    @Column
    public double montoTotal;

    //@OneToMany(mappedBy = "ingresoAVincular",cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @OneToMany
    @JoinColumn(name = "ingreso_id", referencedColumnName = "id")
    private List<Egreso> egresosVinculados;

    public Ingreso(String descripcion, double montoTotal) {
        this.descripcion = descripcion;
        this.montoTotal = montoTotal;
    }

    public Ingreso() {
    }

    public List<Egreso> getEgresosVinculados() {
        return egresosVinculados;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public void setEgresosVinculados(List<Egreso> egresosVinculados) {
        this.egresosVinculados = egresosVinculados;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void vincularEgreso(Egreso unEgreso){
        System.out.println("ENTRE A VINCULAR EGRESO");
        egresosVinculados.add(unEgreso);
    }

    public double calcularMontoVinculable() {
        double monto = 0;

        for(int i=0; i<egresosVinculados.size(); i++){
            monto += egresosVinculados.get(i).getValorTotal();
        }
        return montoTotal - monto;
    }
}
