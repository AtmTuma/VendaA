
package br.com.venda.entity;

import java.util.Date;
import java.util.Objects;



public class Venda {
    private Integer oid;
    private Date dt_inicial;
    private Date dt_final;
    private Double vl_total;
    private String observacao;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Date getDt_inicial() {
        return dt_inicial;
    }

    public void setDt_inicial(Date dt_inicial) {
        this.dt_inicial = dt_inicial;
    }

    public Date getDt_final() {
        return dt_final;
    }

    public void setDt_final(Date dt_final) {
        this.dt_final = dt_final;
    }

    public Double getVl_total() {
        return vl_total;
    }

    public void setVl_total(Double vl_total) {
        this.vl_total = vl_total;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.oid);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Venda other = (Venda) obj;
        if (!Objects.equals(this.oid, other.oid)) {
            return false;
        }
        return true;
    }
    
    
}
